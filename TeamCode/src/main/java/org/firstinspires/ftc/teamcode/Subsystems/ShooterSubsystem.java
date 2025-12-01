package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.DcMotorEx;
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

public class ShooterSubsystem extends SubsystemBase {

    private final DcMotorEx leftMotor;
    private final DcMotorEx rightMotor;
    private final DcMotorEx middleMotor;
    private final Servo hoodServo1;

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

    public ShooterSubsystem() {
        leftMotor = hardwareMap.get(DcMotorEx.class, ShooterConstants.LeftMotorName);
        rightMotor = hardwareMap.get(DcMotorEx.class, ShooterConstants.RightMotorName);
        middleMotor = hardwareMap.get(DcMotorEx.class, ShooterConstants.MiddleMotorName);
        hoodServo1 = hardwareMap.get(Servo.class, ShooterConstants.HoodServo1Name);

        leftMotor.setDirection(ShooterConstants.LeftDirection);
        rightMotor.setDirection(ShooterConstants.RightDirection);
        middleMotor.setDirection(ShooterConstants.MiddleDirection);

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

        focusPoint = (Container.isBlue ? BluePositions.SHOOT_FOCUS_POINT : RedPositions.SHOOT_FOCUS_POINT);

        p1Pose = (Container.isBlue ? BluePositions.SHOOT_P1 : RedPositions.SHOOT_P1);
        p2Pose = (Container.isBlue ? BluePositions.SHOOT_P2 : RedPositions.SHOOT_P2);
        p3Pose = (Container.isBlue ? BluePositions.SHOOT_P3 : RedPositions.SHOOT_P3);
        p4Pose = (Container.isBlue ? BluePositions.SHOOT_P4 : RedPositions.SHOOT_P4);

        p1Rpm = calculateRpmFromPose(p1Pose);
        p2Rpm = calculateRpmFromPose(p2Pose);
        p3Rpm = calculateRpmFromPose(p3Pose);
        p4Rpm = calculateRpmFromPose(p4Pose);

        p1Hood = calculateHoodFromPose(p1Pose);
        p2Hood = calculateHoodFromPose(p2Pose);
        p3Hood = calculateHoodFromPose(p3Pose);
        p4Hood = calculateHoodFromPose(p4Pose);
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

    public void zero() {
        setPower(0);
        // hoodServo1.setPosition(ShooterConstants.zeroPos); // Implement if you have zero pos
    }

    public void rest() {
        setPower(0);
        hoodServo1.setPosition(ShooterConstants.RestHoodPos); 
    }

    public void shootP1() {
        // Implement P1 logic
    }

    public void shootP2() {
        // Implement P2 logic
    }

    public void shootP3() {
        // Implement P3 logic
    }

    public void shootP4() {
        // Implement P4 logic
    }

    public void shootFromPose() {
        // Implement Pose logic
    }

    public void reverse() {
        setPower(-0.3);
    }

    public void shake() {
        setPower(0);
    }

    public void setCurrentState(ShooterStates currentState) {
        this.currentState = currentState;
    }

        public double getRightRPM(){
        return (rightMotor.getVelocity() * 60 / 28);
    }

    private void setPower(double power) {
        leftMotor.setPower(power);
        rightMotor.setPower(power);
        middleMotor.setPower(power);
    }

    public double getMiddleRPM(){
        return (middleMotor.getVelocity() * 60 / 28);
    }
    public double getLeftRPM(){
        return (leftMotor.getVelocity() * 60 / 28);
    }

    public boolean checkRPM(double RPM)
    {
        double mRpm = RPM*ShooterConstants.MultiplierMiddle;
        double lRpm = RPM*ShooterConstants.MultiplierLeft;
        double rRpm = RPM*ShooterConstants.MultiplierRight;


        return (
                (Math.abs(rRpm-getRightRPM()) < ShooterConstants.RpmTol)
                        && (Math.abs(mRpm-getMiddleRPM()) < ShooterConstants.RpmTol)
                        && (Math.abs(lRpm-getLeftRPM()) < ShooterConstants.RpmTol));
    }

    private double calculateRpmFromPose(Pose p)
    {
        double robotY = p.getY();
        double robotX = p.getX();

        double distance = Math.sqrt(Math.pow(focusPoint.getX()-robotX,2) + Math.pow(focusPoint.getY()-robotY,2))*2.54;

        return 2750 + (distance-164)/57.5 * 250; // 2600'ü değiştir, oto şut konumuna göre
    }

    private double calculateHoodFromPose(Pose p)
    {
        double robotY = p.getY();
        double robotX = p.getX();

        double distance = Math.sqrt(Math.pow(focusPoint.getX()-robotX,2) + Math.pow(focusPoint.getY()-robotY,2))*2.54;

        return  0.0027 - (0.0025*(int)((distance-164)/20)); // 2600'ü değiştir, oto şut konumuna göre
    }

    private double calculateRpmFromCurrentPose()
    {
        return calculateRpmFromPose(Container.robotPose);
    }

    private double calculateHoodFromCurrentPose()
    {
        return calculateHoodFromPose(Container.robotPose);
    }

    public Command waitForRPM(double RPM)
    {
        return new WaitUntilCommand(() -> {return checkRPM(RPM);});
    }


}
