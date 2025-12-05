package org.firstinspires.ftc.teamcode.Commands.StateActions.FunnelActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.FunnelSubsystem;

/**
 * Action command to test the funnel mechanism.
 */
public class FunnelTestAction extends CommandBase
{

    private final FunnelSubsystem funnelSubsystem;
    private boolean isFinished = false;

    /**
     * Constructor for FunnelTestAction.
     * @param funnelSubsystem The funnel subsystem instance.
     */
    public FunnelTestAction(FunnelSubsystem funnelSubsystem)
    {
        this.funnelSubsystem = funnelSubsystem;
        addRequirements(funnelSubsystem);
    }

    @Override
    public void initialize()
    {
    }

    @Override
    public void execute()
    {
        funnelSubsystem.test();
        if(!(funnelSubsystem.getState() == AllStates.FunnelStates.TEST)) isFinished = true;
    }

    @Override
    public boolean isFinished()
    {
        return isFinished;
    }
}
