package org.firstinspires.ftc.teamcode.Commands.StateActions.FunnelActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Constants.FunnelConstants;
import org.firstinspires.ftc.teamcode.Subsystems.FunnelSubsystem;

public class FunnelPrepAction extends CommandBase {

    private final FunnelSubsystem funnelSubsystem;
    private boolean isFinished = false;
    private long now;
    private long startTime;
    private final long leftDelay;
    private final long middleDelay;
    private final long rightDelay;
    private final long maxDelay;

    public FunnelPrepAction(FunnelSubsystem funnelSubsystem)
    {
        addRequirements(funnelSubsystem);
        this.funnelSubsystem = funnelSubsystem;

        leftDelay = FunnelConstants.PrepDelay;
        middleDelay = FunnelConstants.PrepDelay*3;
        rightDelay = FunnelConstants.PrepDelay*2;
        maxDelay = FunnelConstants.PrepDelay*5;
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

            if (now - startTime >= leftDelay) funnelSubsystem.setLeftServoPrep();
            if (now - startTime >= middleDelay) funnelSubsystem.setMiddleServoPrep();
            if (now - startTime >= rightDelay) funnelSubsystem.setRightServoPrep();
        }


        if(!(funnelSubsystem.getState() == AllStates.FunnelStates.PREP)) isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
