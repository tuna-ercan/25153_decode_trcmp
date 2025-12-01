package org.firstinspires.ftc.teamcode.Commands.StateActions.FunnelActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.FunnelSubsystem;

public class FunnelTestAction extends CommandBase
{

    private final FunnelSubsystem funnelSubsystem;
    private boolean isFinished = false;

    public FunnelTestAction(FunnelSubsystem funnelSubsystem)
    {
        this.funnelSubsystem = funnelSubsystem;
        addRequirements(funnelSubsystem);
    }

    @Override
    public void initialize()
    {
        funnelSubsystem.test();
    }

    @Override
    public void execute()
    {
        if(!(funnelSubsystem.getState() == AllStates.FunnelStates.TEST)) isFinished = true;
    }

    @Override
    public boolean isFinished()
    {
        return isFinished;
    }
}
