package org.firstinspires.ftc.teamcode.Commands.StateActions.FunnelActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Constants.FunnelConstants;
import org.firstinspires.ftc.teamcode.Subsystems.FunnelSubsystem;

/**
 * Action command to move the funnel servos to the PREP position.
 * This action implements a delayed sequence for servo movement.
 */
public class FunnelPrepAction extends CommandBase {

    private final FunnelSubsystem funnelSubsystem;
    private long now;
    private long startTime;
    private final long leftDelay;
    private final long waitForPrep;
    private final long middleDelay;
    private final long rightDelay;
    private final long maxDelay;

    /**
     * Constructor for FunnelPrepAction.
     * @param funnelSubsystem The funnel subsystem instance.
     */
    public FunnelPrepAction(FunnelSubsystem funnelSubsystem)
    {
        addRequirements(funnelSubsystem);
        this.funnelSubsystem = funnelSubsystem;

        waitForPrep = FunnelConstants.PrepWaitDelay;
        leftDelay = waitForPrep;
        middleDelay = waitForPrep +  FunnelConstants.PrepDelay*2;
        rightDelay = waitForPrep + FunnelConstants.PrepDelay;
        maxDelay = waitForPrep + FunnelConstants.PrepDelay*5;
    }

    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
        now = startTime;

    }

    @Override
    public void execute() {
        if (now - startTime <= maxDelay) {
            now = System.currentTimeMillis();

            if (now - startTime <= waitForPrep) funnelSubsystem.setPrePrepServoPrep();
            if (now - startTime >= leftDelay) funnelSubsystem.setLeftServoPrep();
            if (now - startTime >= middleDelay) funnelSubsystem.setMiddleServoPrep();
            if (now - startTime >= rightDelay) funnelSubsystem.setRightServoPrep();
        }
    }

    private boolean checkFinish()
    {
        return funnelSubsystem.getState() != AllStates.FunnelStates.PREP;
    }

    @Override
    public boolean isFinished() {
        return checkFinish();
    }
}
