package org.firstinspires.ftc.teamcode.Subsystems;


import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
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
import org.firstinspires.ftc.teamcode.Constants.ShooterConstants;
import org.firstinspires.ftc.teamcode.Container;
import org.firstinspires.ftc.teamcode.Utils.AllStates.FunnelStates;
import org.firstinspires.ftc.teamcode.Constants.FunnelConstants;

import java.util.Objects;

/**
 * Subsystem responsible for the funnel mechanism.
 */
public class FunnelSubsystem extends SubsystemBase
{
    private final Servo rightServo;
    private final Servo middleServo;
    private final Servo leftServo;
    private final Servo prePrepServo;
    private final RevColorSensorV3 sensorL;
    private final RevColorSensorV3 sensorR;
    private final RevColorSensorV3 sensorM;


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

    private int[] funnelFeedOrder;

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

        sensorL = hardwareMap.get(RevColorSensorV3.class, FunnelConstants.colorSensorL);
        sensorR = hardwareMap.get(RevColorSensorV3.class, FunnelConstants.colorSensorR);
        sensorM = hardwareMap.get(RevColorSensorV3.class, FunnelConstants.colorSensorM);

        sensorL.enableLed(true);
        sensorR.enableLed(true);
        sensorM.enableLed(true);

        rightServo.setDirection(FunnelConstants.RightDirection);
        leftServo.setDirection(FunnelConstants.LeftDirection);
        middleServo.setDirection(FunnelConstants.MiddleDirection);
        prePrepServo.setDirection(FunnelConstants.PrePrepDirection);

        funnelFeedOrder = FunnelConstants.DefaultFeedOrder;

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

    public double getDistanceL(){return sensorL.getDistance(DistanceUnit.CM);}
    public double getDistanceM(){return sensorM.getDistance(DistanceUnit.CM);}
    public double getDistanceR(){return sensorR.getDistance(DistanceUnit.CM);}

    public boolean isFull() {
        return getDistanceL() < FunnelConstants.distanceThresh && getDistanceM() < FunnelConstants.distanceThresh && getDistanceR() < FunnelConstants.distanceThresh;
    }

    public RevColorSensorV3 getSensorL() {
        return sensorL;
    }
    public RevColorSensorV3 getSensorR() {
        return sensorR;
    }
    public RevColorSensorV3 getSensorM() {
        return sensorM;
    }
    public double[] getDetectedColorL() {
        NormalizedRGBA color = sensorL.getNormalizedColors();
        double normRed, normGreen, normBlue;
        normRed = Math.floor(color.red / color.alpha*10000) / 10000.0;
        normGreen = Math.floor(color.green / color.alpha*10000) / 10000.0;
        normBlue = Math.floor(color.blue / color.alpha*10000) / 10000.0;
        return new double[] {normRed,normGreen,normBlue};
    }

    public double[] getDetectedColorM() {
        NormalizedRGBA color = sensorM.getNormalizedColors();
        double normRed, normGreen, normBlue;
        normRed = Math.floor(color.red / color.alpha*10000) / 10000.0;
        normGreen = Math.floor(color.green / color.alpha*10000) / 10000.0;
        normBlue = Math.floor(color.blue / color.alpha*10000) / 10000.0;
        return new double[] {normRed,normGreen,normBlue};
    }

    public double[] getDetectedColorR() {
        NormalizedRGBA color = sensorR.getNormalizedColors();
        double normRed, normGreen, normBlue;
        normRed = Math.floor(color.red / color.alpha*10000) / 10000.0;
        normGreen = Math.floor(color.green / color.alpha*10000) / 10000.0;
        normBlue = Math.floor(color.blue / color.alpha*10000) / 10000.0;
        return new double[] {normRed,normGreen,normBlue};
    }

    public boolean checkIsFeed()
    {
        return getState() == FunnelStates.FEED;
    }

    public Command waitForFeed() {
        return new WaitUntilCommand(this::checkIsFeed);
    }

    public double colorDistance(double r, double g, double b, double[] t) {
        return Math.sqrt(
                Math.pow(r - t[0], 2) +
                        Math.pow(g - t[1], 2) +
                        Math.pow(b - t[2], 2)
        );
    }

    public double leftGreenDistance() {
        double[] color = getDetectedColorL();
        return colorDistance(color[0], color[1], color[2], FunnelConstants.GreenBallColor);
    }

    public double rightMiddleDistance() {
        double[] color = getDetectedColorM();
        return colorDistance(color[0], color[1], color[2], FunnelConstants.GreenBallColor);
    }

    public double rightGreenDistance() {
        double[] color = getDetectedColorR();
        return colorDistance(color[0], color[1], color[2], FunnelConstants.GreenBallColor);
    }




    public double getColorRatio(double[] color)
    {
        double greenDist = colorDistance(color[0], color[1], color[2], FunnelConstants.GreenBallColor);
        double purpleDist = colorDistance(color[0], color[1], color[2], FunnelConstants.PurpleBallColor);
        return greenDist / purpleDist;
    }

    public boolean isGreenBall(double[] color)
    {
        return getColorRatio(color) <= FunnelConstants.ColorTolerance;
    }

    public double getLeftColorRatio()
    {
        return getColorRatio(getDetectedColorL());
    }

    public double getMiddleColorRatio()
    {
        return getColorRatio(getDetectedColorM());
    }

    public double getRightColorRatio()
    {
        return getColorRatio(getDetectedColorR());
    }

    public boolean isGreenBallL()
    {
        return isGreenBall(getDetectedColorL());
    }

    public boolean isGreenBallM()
    {
        return isGreenBall(getDetectedColorM());
    }

    public boolean isGreenBallR()
    {
        return isGreenBall(getDetectedColorR());
    }

    public int[] getFunnelGreenRatioOrder()
    {
        int leftOrder = 0;
        int middleOrder = 0;
        int rightOrder = 0;

        double leftRatio = getLeftColorRatio();
        double middleRatio = getMiddleColorRatio();
        double rightRatio = getRightColorRatio();

        if (leftRatio > middleRatio) {
            leftOrder++;
        } else {
            middleOrder++;
        }

        if (leftRatio > rightRatio) {
            leftOrder++;
        } else {
            rightOrder++;
        }

        if (middleRatio > rightRatio) {
            middleOrder++;
        } else {
            rightOrder++;
        }


        return new int[]{leftOrder,middleOrder, rightOrder };
    }

    public void setFunnelFeedOrder(int[] funnelFeedOrder) {

        this.funnelFeedOrder = funnelFeedOrder;
    }

    public void setFunnelFeedOrderByColor() {

        int[] newOrder = FunnelConstants.DefaultFeedOrder;

        if (Container.useColor && !(Objects.equals(Container.colorCombination, "x")) && isFull())
        {
            int[] greenOrder = getFunnelGreenRatioOrder();

            int indexOfGreen = 0;
            int indexOfSecond = 0;
            int indexOfThird = 0;

            for (int i = 0; i < greenOrder.length; i++) {
                if (greenOrder[i] == 0) {
                    indexOfGreen = i;
                }
                if (greenOrder[i] == 1) {
                    indexOfSecond = i;
                }
                if (greenOrder[i] == 2) {
                    indexOfThird = i;
                }
            }


            if (Objects.equals(Container.colorCombination, "GPP"))
            {
                newOrder[indexOfGreen] = 0;
                newOrder[indexOfSecond] = 1;
                newOrder[indexOfThird] = 2;
            }
            else if (Objects.equals(Container.colorCombination, "PGP"))
            {
                newOrder[indexOfGreen] = 1;
                newOrder[indexOfSecond] = 0;
                newOrder[indexOfThird] = 2;
            }
            else if (Objects.equals(Container.colorCombination, "PPG"))
            {
                newOrder[indexOfGreen] = 2;
                newOrder[indexOfSecond] = 1;
                newOrder[indexOfThird] = 0;
            }
            else
            {
                setFunnelFeedOrder(FunnelConstants.DefaultFeedOrder);
            }
        }

        if (!Container.isBlue) {
            newOrder = new int[]{newOrder[2], newOrder[1], newOrder[0]};
        }

        setFunnelFeedOrder(newOrder);
    }


    public int[] getFunnelFeedOrder() {
        return funnelFeedOrder;
    }
}
