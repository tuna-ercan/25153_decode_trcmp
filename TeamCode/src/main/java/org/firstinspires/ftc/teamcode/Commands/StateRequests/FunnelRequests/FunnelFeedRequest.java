package org.firstinspires.ftc.teamcode.Commands.StateRequests.FunnelRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.FunnelSubsystem;

public class FunnelFeedRequest extends CommandBase {
    private final FunnelSubsystem funnelSubsystem;
    private boolean isFinished;

    public FunnelFeedRequest(FunnelSubsystem funnelSubsystem) {
        this.funnelSubsystem = funnelSubsystem;
    }

    @Override
    public void initialize() {
        funnelSubsystem.setState(AllStates.FunnelStates.FEED);
        isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
