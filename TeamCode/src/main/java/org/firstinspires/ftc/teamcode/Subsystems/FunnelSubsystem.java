package org.firstinspires.ftc.teamcode.Subsystems;


import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Commands.StateActions.FunnelActions.FunnelFeedAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.FunnelActions.FunnelHomeAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.FunnelActions.FunnelPrepAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.FunnelActions.FunnelShakeAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.FunnelActions.FunnelTestAction;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.FunnelRequests.FunnelFeedRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.FunnelRequests.FunnelHomeRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.FunnelRequests.FunnelPrepRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.FunnelRequests.FunnelShakeRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.FunnelRequests.FunnelTestRequest;
import org.firstinspires.ftc.teamcode.Utils.AllStates.FunnelStates;
import org.firstinspires.ftc.teamcode.Constants.FunnelConstants;

/**
 * Subsystem responsible for the funnel mechanism.
 */
public class FunnelSubsystem extends SubsystemBase
{
    private final Servo rightServo;
    private final Servo middleServo;
    private final Servo leftServo;
    private final Servo prePrepServo;


    private double goalPosLeft;
    private double goalPosMiddle;
    private double goalPosRight;
    private double goalPosPrePrep;


    private boolean isReady;

    private boolean isPatternEnabled;

    private FunnelStates currentState;
    private FunnelStates lastState;

    private final Command homeAction;
    private final Command prepAction;
    private final Command feedAction;
    private final Command shakeAction;
    private final Command testAction;

    /**
     * Creates a new FunnelSubsystem.
     * Initializes servos and commands.
     */
    public FunnelSubsystem(HardwareMap hardwareMap)
    {
        rightServo = hardwareMap.get(Servo.class, FunnelConstants.ServoR);
        leftServo = hardwareMap.get(Servo.class,FunnelConstants.ServoL);
        middleServo = hardwareMap.get(Servo.class,FunnelConstants.ServoM);
        prePrepServo = hardwareMap.get(Servo.class,FunnelConstants.ServoP);


        rightServo.setDirection(FunnelConstants.RightDirection);
        leftServo.setDirection(FunnelConstants.LeftDirection);
        middleServo.setDirection(FunnelConstants.MiddleDirection);
        prePrepServo.setDirection(FunnelConstants.PrePrepDirection);

        currentState = FunnelStates.HOME;
        lastState = FunnelStates.HOME;

        homeAction = new FunnelHomeAction(this);
        prepAction = new FunnelPrepAction(this);
        feedAction = new FunnelFeedAction(this);
        shakeAction = new FunnelShakeAction(this);
        testAction = new FunnelTestAction(this);
    }

    @Override
    public void periodic()
    {
        stateMachine();
        isReady = checkPosition();
    }

    /**
     * Executes actions based on the current state of the funnel.
     */
    public void stateMachine()
    {
        switch (currentState)
        {
            case HOME:
                if(!homeAction.isScheduled()) homeAction.schedule();
                break;
            case PREP:
                if(!prepAction.isScheduled()) prepAction.schedule();
                break;
            case FEED:
                if(!feedAction.isScheduled()) feedAction.schedule();
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
     * Requests a change in the funnel state.
     * @param requestedState The new state to request.
     */
    public void requestState(FunnelStates requestedState)
    {
        setState(requestedState);
    }

    /**
     * Sets the funnel state and updates the last state.
     * @param requestedState The new state.
     */
    public void setState(FunnelStates requestedState)
    {
        if(currentState != requestedState) lastState = currentState;
        currentState = requestedState;
    }

    /**
     * Gets the current state of the funnel.
     * @return The current FunnelStates.
     */
    public FunnelStates getState()
    {
        return currentState;
    }

    /**
     * Gets the previous state of the funnel.
     * @return The last FunnelStates.
     */
    public FunnelStates getLastState()
    {
        return lastState;
    }

    public Command homeRequest() { return new FunnelHomeRequest(this); }
    public Command prepRequest() { return new FunnelPrepRequest(this); }
    public Command feedRequest() { return new FunnelFeedRequest(this); }
    public Command shakeRequest() { return new FunnelShakeRequest(this); }
    public Command testRequest() { return new FunnelTestRequest(this); }

    public void home()
    {
        setRightServo(FunnelConstants.RightHome);
        setMiddleServo(FunnelConstants.MiddleHome);
        setLeftServo(FunnelConstants.LeftHome);
        setPrePrepServo(FunnelConstants.PrePrepHome);
    }

    public void prepTop()
    {
        setRightServo(FunnelConstants.RightPrep);
        setMiddleServo(FunnelConstants.MiddlePrep);
        setLeftServo(FunnelConstants.LeftPrep);
    }

    public void setRightServoFeed()
    {
        setRightServo(FunnelConstants.RightFeed);
    }

    public void setMiddleServoFeed()
    {
        setMiddleServo(FunnelConstants.MiddleFeed);
    }

    public void setLeftServoFeed()
    {
        setLeftServo(FunnelConstants.LeftFeed);
    }

    public void setRightServoPrep()
    {
        setRightServo(FunnelConstants.RightPrep);
    }

    public void setMiddleServoPrep()
    {
        setLeftServo(FunnelConstants.MiddlePrep);
    }

    public void setLeftServoPrep()
    {
        setLeftServo(FunnelConstants.LeftPrep);
    }

    public void setPrePrepServoPrep() {
        setPrePrepServo(FunnelConstants.PrePrepPrep);
    }

    public void shake()
    {
        setRightServo(FunnelConstants.RightHome);
        setMiddleServo(FunnelConstants.MiddleHome);
        setLeftServo(FunnelConstants.LeftHome);
        setPrePrepServo(FunnelConstants.PrePrepHome);
    }

    public void test()
    {
        setRightServo(FunnelConstants.RightTest);
        setMiddleServo(FunnelConstants.MiddleTest);
        setLeftServo(FunnelConstants.LeftTest);
        setPrePrepServo(FunnelConstants.PrePrepTest);
    }

    public void setGoalPosLeft(double pos)
    {
        goalPosLeft = pos;
    }
    public void setGoalPosMiddle(double pos)
    {
        goalPosMiddle = pos;
    }
    public void setGoalPosRight(double pos)
    {
        goalPosRight = pos;
    }
    public void setGoalPosPrePrep(double pos)
    {
        goalPosPrePrep = pos;
    }

    public boolean checkPosition()
    {
        return (Math.abs(goalPosLeft - leftServo.getPosition()) < FunnelConstants.Tol)
                && (Math.abs(goalPosMiddle - middleServo.getPosition()) < FunnelConstants.Tol)
                && (Math.abs(goalPosRight - rightServo.getPosition()) < FunnelConstants.Tol);
    }

    public boolean isReady()
    {
        return isReady;
    }

    public void setLeftServo(double pos)
    {
        leftServo.setPosition(pos);
        setGoalPosLeft(pos);
    }
    public void setMiddleServo(double pos)
    {
        middleServo.setPosition(pos);
        setGoalPosMiddle(pos);
    }
    public void setRightServo(double pos)
    {
        rightServo.setPosition(pos);
        setGoalPosRight(pos);
    }

    public void setPrePrepServo(double pos)
    {
        prePrepServo.setPosition(pos);
        setGoalPosPrePrep(pos);
    }


    public boolean getPatternEnabled()
    {
        return isPatternEnabled;
    }

    public void setPatternEnabled(boolean enabled)
    {
        isPatternEnabled = enabled;
    }
}
