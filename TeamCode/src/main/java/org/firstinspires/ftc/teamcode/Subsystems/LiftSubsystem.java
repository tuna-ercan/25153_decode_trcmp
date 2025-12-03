package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Commands.StateActions.LiftActions.LiftHomeAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.LiftActions.LiftOpenAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.LiftActions.LiftShakeAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.LiftActions.LiftTestAction;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.LiftRequests.LiftHomeRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.LiftRequests.LiftOpenRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.LiftRequests.LiftShakeRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.LiftRequests.LiftTestRequest;
import org.firstinspires.ftc.teamcode.Utils.AllStates.LiftStates;
import org.firstinspires.ftc.teamcode.Constants.LiftConstants;

public class LiftSubsystem extends SubsystemBase {

    private final Servo leftLiftServo;
    private final Servo rightLiftServo;

    private double goalPosLeft = 0;
    private double goalPosRight = 0;
    private boolean isReady = false;

    private LiftStates currentState;
    private LiftStates lastState;

    private final Command requestHome;
    private final Command requestOpen;
    private final Command requestShake;
    private final Command requestTest;

    private final Command homeAction;
    private final Command openAction;
    private final Command shakeAction;
    private final Command testAction;

    public LiftSubsystem(HardwareMap hardwareMap) {
        leftLiftServo = hardwareMap.get(Servo.class, LiftConstants.LeftLiftName);
        rightLiftServo = hardwareMap.get(Servo.class, LiftConstants.RightLiftName);
        leftLiftServo.setDirection(LiftConstants.LeftLiftDirection);
        rightLiftServo.setDirection(LiftConstants.RightLiftDirection);

        currentState = LiftStates.HOME;
        lastState = LiftStates.HOME;

        requestHome = new LiftHomeRequest(this);
        requestOpen = new LiftOpenRequest(this);
        requestShake = new LiftShakeRequest(this);
        requestTest = new LiftTestRequest(this);

        homeAction = new LiftHomeAction(this);
        openAction = new LiftOpenAction(this);
        shakeAction = new LiftShakeAction(this);
        testAction = new LiftTestAction(this);
    }

    @Override
    public void periodic() {
        stateMachine();

        isReady = checkPosition();
    }

    public void stateMachine() {
        switch (currentState) {
            case HOME:
                if(!homeAction.isScheduled()) homeAction.schedule();
                break;
            case OPEN:
                if(!openAction.isScheduled()) openAction.schedule();
                break;
            case SHAKE:
                if(!shakeAction.isScheduled()) shakeAction.schedule();
                break;
            case TEST:
                if(!testAction.isScheduled()) testAction.schedule();
                break;
        }
    }

    public void setState(LiftStates requestedState) {
        if(currentState != requestedState) lastState = currentState;
        currentState = requestedState;
    }

    public LiftStates getState() {
        return currentState;
    }

    public LiftStates getLastState() {
        return lastState;
    }

    public Command homeRequest() {
        return requestHome;
    }

    public Command openRequest() {
        return requestOpen;
    }

    public Command shakeRequest() {
        return requestShake;
    }

    public Command testRequest() {
        return requestTest;
    }

    public void home() {
        setLeftServo(LiftConstants.HomePos);
        setRightServo(LiftConstants.HomePos);
    }

    public void open() {
        setLeftServo(LiftConstants.OpenPos);
        setRightServo(LiftConstants.OpenPos);
    }

    public void shake() {
        setLeftServo(LiftConstants.HomePos);
        setRightServo(LiftConstants.HomePos);
    }

    public void test() {
        setLeftServo(LiftConstants.TestPos);
        setRightServo(LiftConstants.TestPos);
    }

    public boolean checkPosition() {
        return (Math.abs(goalPosLeft - leftLiftServo.getPosition()) < LiftConstants.Tol)
                && (Math.abs(goalPosRight - rightLiftServo.getPosition()) < LiftConstants.Tol);
    }

    public boolean isReady()
    {
        return isReady;
    }

    public void setLeftServo(double pos)
    {
        leftLiftServo.setPosition(pos);
        setGoalPosLeft(pos);
    }
    public void setRightServo(double pos) {
        rightLiftServo.setPosition(pos);
        setGoalPosRight(pos);
    }

    public void setGoalPosLeft(double pos)
    {
        goalPosLeft = pos;
    }
    public void setGoalPosRight(double pos)
    {
        goalPosRight = pos;
    }
}
