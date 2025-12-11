package org.firstinspires.ftc.teamcode.Commands.StateActions.FunnelActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Container;
import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Constants.FunnelConstants;
import org.firstinspires.ftc.teamcode.Subsystems.FunnelSubsystem;

/**
 * Action command to move the funnel servos to the FEED position.
 * This action implements a delayed sequence for servo movement.
 */
public class FunnelFeedAction extends CommandBase
{

    private final FunnelSubsystem funnelSubsystem;
    private long now;
    private long startTime;
    private long leftDelay;
    private long middleDelay;
    private long rightDelay;
    private long maxDelay;
    /**
     * Constructor for FunnelFeedAction.
     * @param funnelSubsystem The funnel subsystem instance.
     */
    public FunnelFeedAction(FunnelSubsystem funnelSubsystem)
    {
        addRequirements(funnelSubsystem);
        this.funnelSubsystem = funnelSubsystem;

        funnelSubsystem.setFunnelFeedOrderByColor();
        int[] feedOrder = funnelSubsystem.getFunnelFeedOrder();

        leftDelay = FunnelConstants.FeedDelay*feedOrder[0];
        middleDelay = FunnelConstants.FeedDelay*feedOrder[1];
        rightDelay = FunnelConstants.FeedDelay*feedOrder[2];
        maxDelay = FunnelConstants.FeedDelay*5;

    }

    /**
     * Initializes the action, recording the start time.
     */
    @Override
    public void initialize()
    {
        startTime = System.currentTimeMillis();
        now = startTime;

        int[] feedOrder = funnelSubsystem.getFunnelFeedOrder();
        funnelSubsystem.setFunnelFeedOrderByColor();
        leftDelay = FunnelConstants.FeedDelay*feedOrder[0];
        middleDelay = FunnelConstants.FeedDelay*feedOrder[1];
        rightDelay = FunnelConstants.FeedDelay*feedOrder[2];
        maxDelay = FunnelConstants.FeedDelay*5;
    }

    /**
     * Executes the action, moving servos based on elapsed time.
     * Checks if the subsystem state has changed to determine completion.
     */
    @Override
    public void execute()
    {
        if (now - startTime <= maxDelay)
        {
            if (now - startTime >= leftDelay) funnelSubsystem.setLeftServoFeed();
            if (now - startTime >= middleDelay) funnelSubsystem.setMiddleServoFeed();
            if (now - startTime >= rightDelay) funnelSubsystem.setRightServoFeed();
        }
        now = System.currentTimeMillis();

    }

    private boolean checkFinish()
    {
        return funnelSubsystem.getState() != AllStates.FunnelStates.FEED;
    }

    /**
     * Checks if the action is finished.
     * @return true if the subsystem state is no longer FEED, false otherwise.
     */
    @Override
    public boolean isFinished()
    {
        return checkFinish();
    }
}
