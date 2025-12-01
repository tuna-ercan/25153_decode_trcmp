package org.firstinspires.ftc.teamcode.Commands.StateRequests.FunnelRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.FunnelSubsystem;

public class FunnelFeedColorRequest extends CommandBase {
    private final FunnelSubsystem funnelSubsystem;
    private boolean isFinished;

    public FunnelFeedColorRequest(FunnelSubsystem funnelSubsystem) {
        this.funnelSubsystem = funnelSubsystem;
    }

    @Override
    public void initialize() {
        funnelSubsystem.setState(AllStates.FunnelStates.FEED_COLOR);
        isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
