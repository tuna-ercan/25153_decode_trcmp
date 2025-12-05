package org.firstinspires.ftc.teamcode.Commands.StateActions.FunnelActions;

import com.arcrobotics.ftclib.command.CommandBase;

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
    private boolean isFinished = false;
    private long now;
    private long startTime;
    private final long leftDelay;
    private final long middleDelay;
    private final long rightDelay;
    private final long maxDelay;
    private boolean isTimeVariablesSet = false;

    /**
     * Constructor for FunnelFeedAction.
     * @param funnelSubsystem The funnel subsystem instance.
     */
    public FunnelFeedAction(FunnelSubsystem funnelSubsystem)
    {
        addRequirements(funnelSubsystem);
        this.funnelSubsystem = funnelSubsystem;

        leftDelay = FunnelConstants.FeedDelay;
        middleDelay = FunnelConstants.FeedDelay*3;
        rightDelay = FunnelConstants.FeedDelay*2;
        maxDelay = FunnelConstants.FeedDelay*5;
    }

    /**
     * Initializes the action, recording the start time.
     */
    @Override
    public void initialize()
    {

    }

    /**
     * Executes the action, moving servos based on elapsed time.
     * Checks if the subsystem state has changed to determine completion.
     */
    @Override
    public void execute()
    {
        if (!isTimeVariablesSet)
        {
            startTime = System.currentTimeMillis();
            now = startTime;
            isTimeVariablesSet = true;
        }

        if (now - startTime <= maxDelay)
        {
            now = System.currentTimeMillis();

            if (now - startTime >= leftDelay) funnelSubsystem.setLeftServoFeed();
            if (now - startTime >= middleDelay) funnelSubsystem.setMiddleServoFeed();
            if (now - startTime >= rightDelay) funnelSubsystem.setRightServoFeed();
        }

        if(!(funnelSubsystem.getState() == AllStates.FunnelStates.FEED)) isFinished = true;
    }

    /**
     * Checks if the action is finished.
     * @return true if the subsystem state is no longer FEED, false otherwise.
     */
    @Override
    public boolean isFinished()
    {
        return isFinished;
    }

    @Override
    public void end(boolean interrupted) {
        isTimeVariablesSet = false;
    }
}
