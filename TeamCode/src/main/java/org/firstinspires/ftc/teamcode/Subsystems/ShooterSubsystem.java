package org.firstinspires.ftc.teamcode.Subsystems;


import androidx.annotation.NonNull;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.util.MathUtils;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions.ShooterRestAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions.ShooterReverseAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions.ShooterShakeAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions.ShooterShootFromPoseAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions.ShooterShootP1Action;
import org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions.ShooterShootP2Action;
import org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions.ShooterShootP3Action;
import org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions.ShooterShootP4Action;
import org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions.ShooterShootP5Action;
import org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions.ShooterTestAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions.ShooterZeroAction;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests.ShooterRestRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests.ShooterReverseRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests.ShooterShakeRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests.ShooterShootFromPoseRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests.ShooterShootP1Request;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests.ShooterShootP2Request;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests.ShooterShootP3Request;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests.ShooterShootP4Request;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests.ShooterShootP5Request;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests.ShooterTestRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests.ShooterZeroRequest;
import org.firstinspires.ftc.teamcode.Utils.AllStates.ShooterStates;
import org.firstinspires.ftc.teamcode.Constants.ShooterConstants;
import org.firstinspires.ftc.teamcode.Container;
import org.firstinspires.ftc.teamcode.Positions.BluePositions;
import org.firstinspires.ftc.teamcode.Positions.RedPositions;
import org.firstinspires.ftc.teamcode.Utils.ShooterPIDController;

/**
 * Subsystem responsible for the shooter mechanism.
 * Controls flywheel motors and hood servo to shoot rings/game elements.
 *
 *
 * P1: closest
 * P2: up against wall position
 * P3: near the line corner
 * P4: furthest
 *
 */
public class ShooterSubsystem extends SubsystemBase {

    private final DcMotorEx leftMotor;
    private final DcMotorEx rightMotor;
    private final DcMotorEx middleMotor;
    private final Servo hoodServo;

    private final ShooterPIDController leftPID;
    private final ShooterPIDController middlePID;
    private final ShooterPIDController rightPID;

    private final double restRpm;
    private final double p1Rpm;
    private final double p2Rpm;
    private final double p3Rpm;
    private final double p4Rpm;

    private final double p1Hood;
    private final double p2Hood;
    private final double p3Hood;
    private final double p4Hood;

    private final Pose p1Pose;
    private final Pose p2Pose;
    private final Pose p3Pose;
    private final Pose p4Pose;

    private final Pose focusPoint;

    private double goalRPM;
    private double goalHood;
    private double prevLOutput;
    private double prevMOutput;
    private double prevROutput;
    private boolean isReady;

    private ShooterStates currentState;
    private ShooterStates lastState;

    private final Command zeroAction;
    private final Command restAction;
    private final Command shootP1Action;
    private final Command shootP2Action;
    private final Command shootP3Action;
    private final Command shootP4Action;
    private final Command shootP5Action;
    private final Command shootFromPoseAction;
    private final Command reverseAction;
    private final Command shakeAction;
    private final Command testAction;

    /**
     * Creates a new ShooterSubsystem.
     * Initializes motors, servos, PIDs, and calculates preset values.
     */
    public ShooterSubsystem(HardwareMap hardwareMap) {
        leftMotor = hardwareMap.get(DcMotorEx.class, ShooterConstants.LeftMotorName);
        rightMotor = hardwareMap.get(DcMotorEx.class, ShooterConstants.RightMotorName);
        middleMotor = hardwareMap.get(DcMotorEx.class, ShooterConstants.MiddleMotorName);
        hoodServo = hardwareMap.get(Servo.class, ShooterConstants.HoodServoName);

        leftMotor.setDirection(ShooterConstants.LeftDirection);
        rightMotor.setDirection(ShooterConstants.RightDirection);
        middleMotor.setDirection(ShooterConstants.MiddleDirection);
        hoodServo.setDirection(ShooterConstants.HoodServoDirection);

        middleMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        middleMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        leftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        rightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        leftPID = new ShooterPIDController(ShooterConstants.LeftPIDCoefficients);
        middlePID = new ShooterPIDController(ShooterConstants.MiddlePIDCoefficients);
        rightPID = new ShooterPIDController(ShooterConstants.RightPIDCoefficients);

        focusPoint = (Container.isBlue ? BluePositions.SHOOT_FOCUS_POINT : RedPositions.SHOOT_FOCUS_POINT);

        p1Pose = (Container.isBlue ? BluePositions.SHOOT_P1 : RedPositions.SHOOT_P1);
        p2Pose = (Container.isBlue ? BluePositions.SHOOT_P2 : RedPositions.SHOOT_P2);
        p3Pose = (Container.isBlue ? BluePositions.SHOOT_P3 : RedPositions.SHOOT_P3);
        p4Pose = (Container.isBlue ? BluePositions.SHOOT_P4 : RedPositions.SHOOT_P4);

        p1Rpm = calculateRpmFromPose(p1Pose);
        p2Rpm = calculateRpmFromPose(p2Pose);
        p3Rpm = calculateRpmFromPose(p3Pose);
        p4Rpm = calculateRpmFromPose(p4Pose);

        restRpm = ShooterConstants.RestRPM;

        p1Hood = calculateHoodFromPose(p1Pose);
        p2Hood = calculateHoodFromPose(p2Pose);
        p3Hood = calculateHoodFromPose(p3Pose);
        p4Hood = calculateHoodFromPose(p4Pose);

        prevROutput = 0;
        prevMOutput = 0;
        prevLOutput = 0;

        currentState = ShooterStates.ZERO;
        lastState = ShooterStates.ZERO;

        zeroAction = new ShooterZeroAction(this);
        restAction = new ShooterRestAction(this);
        shootP1Action = new ShooterShootP1Action(this);
        shootP2Action = new ShooterShootP2Action(this);
        shootP3Action = new ShooterShootP3Action(this);
        shootP4Action = new ShooterShootP4Action(this);
        shootP5Action = new ShooterShootP5Action(this);
        shootFromPoseAction = new ShooterShootFromPoseAction(this);
        reverseAction = new ShooterReverseAction(this);
        shakeAction = new ShooterShakeAction(this);
        testAction = new ShooterTestAction(this);
    }

    @Override
    public void periodic() {
        stateMachine();


        //leftPID.checkAndUpdateCoefficients(ShooterConstants.LeftPIDCoefficients);
        //middlePID.checkAndUpdateCoefficients(ShooterConstants.MiddlePIDCoefficients);
        //rightPID.checkAndUpdateCoefficients(ShooterConstants.RightPIDCoefficients);
    }

    /**
     * Executes actions based on the current state of the shooter.
     */
    public void stateMachine() {
        switch (currentState) {
            case ZERO:
                if(!zeroAction.isScheduled()) zeroAction.schedule();
                break;
            case REST:
                if(!restAction.isScheduled()) restAction.schedule();
                break;
            case SHOOT_P1:
                if(!shootP1Action.isScheduled()) shootP1Action.schedule();
                break;
            case SHOOT_P2:
                if(!shootP2Action.isScheduled()) shootP2Action.schedule();
                break;
            case SHOOT_P3:
                if(!shootP3Action.isScheduled()) shootP3Action.schedule();
                break;
            case SHOOT_P4:
                if(!shootP4Action.isScheduled()) shootP4Action.schedule();
                break;
            case SHOOT_P5:
                if(!shootP5Action.isScheduled()) shootP5Action.schedule();
                break;
            case SHOOT_FROM_POSE:
                if(!shootFromPoseAction.isScheduled()) shootFromPoseAction.schedule();
                break;
            case REVERSE:
                if(!reverseAction.isScheduled()) reverseAction.schedule();
                break;
            case SHAKE:
                if(!shakeAction.isScheduled()) shakeAction.schedule();
                break;
            case TEST:
                if(!testAction.isScheduled()) testAction.schedule();
                break;
        }
    }

    /**
     * Requests a change in the shooter state.
     * @param requestedState The new state to request.
     */
    public void requestState(ShooterStates requestedState) {
        setState(requestedState);
    }

    /**
     * Sets the shooter state and updates the last state.
     * @param requestedState The new state.
     */
    public void setState(ShooterStates requestedState) {
        if(currentState != requestedState) lastState = currentState;
        currentState = requestedState;

    }

    /**
     * Gets the current state of the shooter.
     * @return The current ShooterStates.
     */
    public ShooterStates getState() {
        return currentState;
    }

    public Command zeroRequest() { return new ShooterZeroRequest(this); }
    public Command restRequest() { return new ShooterRestRequest(this); }
    public Command shootP1Request() { return new ShooterShootP1Request(this); }
    public Command shootP2Request() { return new ShooterShootP2Request(this); }
    public Command shootP3Request() { return new ShooterShootP3Request(this); }
    public Command shootP4Request() { return new ShooterShootP4Request(this); }
    public Command shootP5Request() { return new ShooterShootP5Request(this); }
    public Command shootFromPoseRequest() { return new ShooterShootFromPoseRequest(this); }
    public Command reverseRequest() { return new ShooterReverseRequest(this); }
    public Command shakeRequest() { return new ShooterShakeRequest(this); }
    public Command testRequest() { return new ShooterTestRequest(this); }

    public void setCurrentState(ShooterStates currentState) {
        this.currentState = currentState;
    }

    private void setGoalRPM(double rpmGoal)
    {
        goalRPM = rpmGoal;
    }

    public double getGoalRPM() {
        return goalRPM;
    }

    private void setGoalHood(double hoodGoal)
    {
        goalHood = hoodGoal;
    }

    public double getGoalHood() {
        return goalHood;
    }


    private void setMotorPowers(double leftPower, double middlePower, double rightPower)
    {
        leftMotor.setPower(leftPower);
        middleMotor.setPower(middlePower);
        rightMotor.setPower(rightPower);

        prevLOutput = leftPower;
        prevMOutput = middlePower;
        prevROutput = rightPower;
    }

    private void setAllMotorPowers(double power)
    {
        setMotorPowers(power, power, power);
    }

    private void setHoodPosition(double position)
    {
        setGoalHood(position);
        hoodServo.setPosition(ShooterConstants.HoodStartTol -
                Math.floor(position*ShooterConstants.HoodGearRatio/ShooterConstants.ServoAngleCapacity * 10000) / 10000.0);
    }

    public double getRightRPM()
    {
        return (rightMotor.getVelocity() * 60 / 28);
    }

    public double getMiddleRPM()
    {
        return (middleMotor.getVelocity() * 60 / 28 );
    }
    public double getLeftRPM()
    {
        return (leftMotor.getVelocity() * 60 / 28);
    }

    public double getHoodPosition()
    {
        return hoodServo.getPosition();
    }

    /**
     * Controls the motor RPM using PID controllers.
     * @param rpm The target RPM.
     */
    private void controlMotorRPM(double rpm)
    {
        setGoalRPM(rpm);

        double leftRPM = getLeftRPM();
        double middleRPM = getMiddleRPM();
        double rightRPM = getRightRPM();

        double leftGoal = goalRPM*ShooterConstants.MultiplierLeft;
        double middleGoal = goalRPM*ShooterConstants.MultiplierMiddle;
        double rightGoal = goalRPM*ShooterConstants.MultiplierRight;

        double pidLOutput = leftPID.calculate(leftRPM, leftGoal);
        double lOutput = MathUtils.clamp(prevLOutput + pidLOutput, -0.6, 1);

        double pidMOutput = middlePID.calculate(middleRPM, middleGoal);
        double mOutput = MathUtils.clamp(prevMOutput + pidMOutput, -0.6, 1);

        double pidROutput = rightPID.calculate(rightRPM, rightGoal);
        double rOutput = MathUtils.clamp(prevROutput + pidROutput, -0.6, 1);

        setMotorPowers(lOutput, mOutput,  rOutput);
    }

    /**
     * Checks if the current RPM is within tolerance of the goal.
     * @param RPM The target RPM.
     * @return True if within tolerance.
     */
    public boolean checkRPM(double RPM)
    {
        double lRpm = RPM*ShooterConstants.MultiplierLeft;
        double mRpm = RPM*ShooterConstants.MultiplierMiddle;
        double rRpm = RPM*ShooterConstants.MultiplierRight;


        return (
                (Math.abs(rRpm-getRightRPM()) < ShooterConstants.RpmTol)
                        && (Math.abs(mRpm-getMiddleRPM()) < ShooterConstants.RpmTol)
                        && (Math.abs(lRpm-getLeftRPM()) < ShooterConstants.RpmTol));
    }

    /**
     * Checks if the hood is within tolerance of the goal position.
     * @param hood The target hood position.
     * @return True if within tolerance.
     */
    public boolean checkHood(double hood)
    {

        return (Math.abs(hood - getHoodPosition()) < ShooterConstants.HoodTol);
    }

    private double calculateRpmFromPose(@NonNull Pose p)
    {
        double robotY = p.getY();
        double robotX = p.getX();

        double distance = Math.sqrt(Math.pow(focusPoint.getX()-robotX,2) + Math.pow(focusPoint.getY()-robotY,2))*2.54;

        return ShooterConstants.CalculateRpmFromDistance(distance);
    }

    private double calculateHoodFromPose(@NonNull Pose p)
    {
        double robotY = p.getY();
        double robotX = p.getX();

        double distance = Math.sqrt(Math.pow(focusPoint.getX()-robotX,2) + Math.pow(focusPoint.getY()-robotY,2))*2.54;

        return  ShooterConstants.CalculateHoodFromDistance(distance);
    }

    private double calculateRpmFromCurrentPose()
    {
        return calculateRpmFromPose(Container.robotPose);
    }

    private double calculateHoodFromCurrentPose()
    {
        return calculateHoodFromPose(Container.robotPose);
    }

    public boolean isReady()
    {
        return isReady;
    }

    private void setIsReady(boolean ready)
    {
        isReady = ready;
    }

    private void setIsReadyByChecking()
    {
        setIsReady(checkRPM(goalRPM));
    }

    public void zero()
    {
        setAllMotorPowers(0);
        setGoalRPM(0);

        //setHoodPosition(ShooterConstants.TestHoodPos);
        setHoodPosition(0);

        setIsReady(true);
    }

    public void rest()
    {
        setIsReadyByChecking();
        setHoodPosition(ShooterConstants.RestHoodPos);

        if(!Container.isTeleop) controlMotorRPM(ShooterConstants.RestRPMAuto);
        else controlMotorRPM(ShooterConstants.RestRPM);

    }

    public void shootP1()
    {
        setIsReadyByChecking();
        setHoodPosition(ShooterConstants.P1HoodPos);
        controlMotorRPM(ShooterConstants.P1Rpm);
    }

    public void shootP2()
    {
        setIsReadyByChecking();
        setHoodPosition(ShooterConstants.P2HoodPos);
        controlMotorRPM(ShooterConstants.P2Rpm);
    }

    public void shootP3()
    {
        setIsReadyByChecking();
        setHoodPosition(ShooterConstants.P3HoodPos);
        controlMotorRPM(ShooterConstants.P3Rpm);
    }

    public void shootP4()
    {
        setIsReadyByChecking();
        setHoodPosition(ShooterConstants.P4HoodPos);
        controlMotorRPM(ShooterConstants.P4Rpm);
    }

    public void shootP5()
    {
        setIsReadyByChecking();
        setHoodPosition(ShooterConstants.P5HoodPos);
        controlMotorRPM(ShooterConstants.P5Rpm);
    }

    public void shootFromPose()
    {
        setIsReadyByChecking();
        setHoodPosition(calculateHoodFromCurrentPose());
        controlMotorRPM(calculateRpmFromCurrentPose());
    }

    public void reverse()
    {
        setAllMotorPowers(-0.3);
        setGoalRPM(-1);

        setHoodPosition(0.01);

        setIsReady(true);
    }

    public void shake()
    {
        setAllMotorPowers(0);
        setGoalRPM(0);

        setHoodPosition(0);

        setIsReady(true);
    }

    public void test()
    {
        setIsReadyByChecking();
        setHoodPosition(ShooterConstants.TestHoodPos);
        controlMotorRPM(ShooterConstants.TestRpm);
    }

    public void zeroHood()
    {
        setHoodPosition(0);
    }

    public Command waitForReady()
    {
        return new WaitUntilCommand(this::isReady);
    }
}
