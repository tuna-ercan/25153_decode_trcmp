package org.firstinspires.ftc.teamcode.Commands.StateRequests.FunnelRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.FunnelSubsystem;

public class FunnelTestRequest extends CommandBase
{

    private final FunnelSubsystem funnelSubsystem;
    private boolean isFinished;

    public FunnelTestRequest(FunnelSubsystem funnelSubsystem)
    {
        this.funnelSubsystem = funnelSubsystem;
    }

    @Override
    public void initialize()
    {
        funnelSubsystem.setState(AllStates.FunnelStates.TEST);
        isFinished = true;
    }

    @Override
    public boolean isFinished()
    {
        return isFinished;
    }
}
