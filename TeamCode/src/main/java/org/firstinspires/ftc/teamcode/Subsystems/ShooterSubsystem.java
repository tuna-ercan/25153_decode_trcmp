package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import androidx.annotation.NonNull;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.util.MathUtils;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions.ShooterRestAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions.ShooterReverseAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions.ShooterShakeAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions.ShooterShootFromPoseAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions.ShooterShootP1Action;
import org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions.ShooterShootP2Action;
import org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions.ShooterShootP3Action;
import org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions.ShooterShootP4Action;
import org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions.ShooterZeroAction;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests.ShooterRestRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests.ShooterReverseRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests.ShooterShakeRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests.ShooterShootFromPoseRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests.ShooterShootP1Request;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests.ShooterShootP2Request;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests.ShooterShootP3Request;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests.ShooterShootP4Request;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests.ShooterZeroRequest;
import org.firstinspires.ftc.teamcode.Constants.AllStates.ShooterStates;
import org.firstinspires.ftc.teamcode.Constants.ShooterConstants;
import org.firstinspires.ftc.teamcode.Container;
import org.firstinspires.ftc.teamcode.Positions.BluePositions;
import org.firstinspires.ftc.teamcode.Positions.RedPositions;
import org.firstinspires.ftc.teamcode.Utils.ShooterPIDController;

public class ShooterSubsystem extends SubsystemBase {

    private final DcMotorEx leftMotor;
    private final DcMotorEx rightMotor;
    private final DcMotorEx middleMotor;
    private final Servo hoodServo1;

    private final PIDController leftPID;
    private final PIDController middlePID;
    private final PIDController rightPID;

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

    private final Command requestZero;
    private final Command requestRest;
    private final Command requestShootP1;
    private final Command requestShootP2;
    private final Command requestShootP3;
    private final Command requestShootP4;
    private final Command requestShootFromPose;
    private final Command requestReverse;
    private final Command requestShake;

    private final Command zeroAction;
    private final Command restAction;
    private final Command shootP1Action;
    private final Command shootP2Action;
    private final Command shootP3Action;
    private final Command shootP4Action;
    private final Command shootFromPoseAction;
    private final Command reverseAction;
    private final Command shakeAction;

    private final Command waitForReady;

    public ShooterSubsystem() {
        leftMotor = hardwareMap.get(DcMotorEx.class, ShooterConstants.LeftMotorName);
        rightMotor = hardwareMap.get(DcMotorEx.class, ShooterConstants.RightMotorName);
        middleMotor = hardwareMap.get(DcMotorEx.class, ShooterConstants.MiddleMotorName);
        hoodServo1 = hardwareMap.get(Servo.class, ShooterConstants.HoodServo1Name);

        leftMotor.setDirection(ShooterConstants.LeftDirection);
        rightMotor.setDirection(ShooterConstants.RightDirection);
        middleMotor.setDirection(ShooterConstants.MiddleDirection);

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

        waitForReady = new WaitUntilCommand(this::isReady);

        currentState = ShooterStates.ZERO;
        lastState = ShooterStates.ZERO;

        requestZero = new ShooterZeroRequest(this);
        requestRest = new ShooterRestRequest(this);
        requestShootP1 = new ShooterShootP1Request(this);
        requestShootP2 = new ShooterShootP2Request(this);
        requestShootP3 = new ShooterShootP3Request(this);
        requestShootP4 = new ShooterShootP4Request(this);
        requestShootFromPose = new ShooterShootFromPoseRequest(this);
        requestReverse = new ShooterReverseRequest(this);
        requestShake = new ShooterShakeRequest(this);

        zeroAction = new ShooterZeroAction(this);
        restAction = new ShooterRestAction(this);
        shootP1Action = new ShooterShootP1Action(this);
        shootP2Action = new ShooterShootP2Action(this);
        shootP3Action = new ShooterShootP3Action(this);
        shootP4Action = new ShooterShootP4Action(this);
        shootFromPoseAction = new ShooterShootFromPoseAction(this);
        reverseAction = new ShooterReverseAction(this);
        shakeAction = new ShooterShakeAction(this);
    }

    @Override
    public void periodic() {
        stateMachine();
    }

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
            case SHOOT_FROM_POSE:
                if(!shootFromPoseAction.isScheduled()) shootFromPoseAction.schedule();
                break;
            case REVERSE:
                if(!reverseAction.isScheduled()) reverseAction.schedule();
                break;
            case SHAKE:
                if(!shakeAction.isScheduled()) shakeAction.schedule();
                break;
        }
    }

    public void setState(ShooterStates requestedState) {
        if(currentState != requestedState) lastState = currentState;
        currentState = requestedState;
    }

    public ShooterStates getState() {
        return currentState;
    }

    public Command zeroRequest() { return requestZero; }
    public Command restRequest() { return requestRest; }
    public Command shootP1Request() { return requestShootP1; }
    public Command shootP2Request() { return requestShootP2; }
    public Command shootP3Request() { return requestShootP3; }
    public Command shootP4Request() { return requestShootP4; }
    public Command shootFromPoseRequest() { return requestShootFromPose; }
    public Command reverseRequest() { return requestReverse; }
    public Command shakeRequest() { return requestShake; }

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
        rightMotor.setPower(middlePower);
        middleMotor.setPower(rightPower);

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
        hoodServo1.setPosition(position);
    }

    public double getRightRPM()
    {
        return (rightMotor.getVelocity() * 60 / 28);
    }

    public double getMiddleRPM()
    {
        return (middleMotor.getVelocity() * 60 / 28);
    }
    public double getLeftRPM()
    {
        return (leftMotor.getVelocity() * 60 / 28);
    }

    public double getHoodPosition()
    {
        return hoodServo1.getPosition();
    }

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
        setIsReady(checkRPM(goalRPM) && checkHood(goalHood));
    }

    public void zero()
    {
        setAllMotorPowers(0);
        setGoalRPM(0);

        setHoodPosition(0);

        setIsReady(true);
    }

    public void rest()
    {
        setIsReadyByChecking();
        setHoodPosition(ShooterConstants.RestHoodPos);
        controlMotorRPM(ShooterConstants.RestRPM);
    }

    public void shootP1()
    {
        setIsReadyByChecking();
        setHoodPosition(p1Hood);
        controlMotorRPM(p1Rpm);
    }

    public void shootP2()
    {
        setIsReadyByChecking();
        setHoodPosition(p2Hood);
        controlMotorRPM(p2Rpm);
    }

    public void shootP3()
    {
        setIsReadyByChecking();
        setHoodPosition(p3Hood);
        controlMotorRPM(p3Rpm);
    }

    public void shootP4()
    {
        setIsReadyByChecking();
        setHoodPosition(p4Hood);
        controlMotorRPM(p4Rpm);
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

    public Command waitForReady()
    {
        return waitForReady;
    }
}
