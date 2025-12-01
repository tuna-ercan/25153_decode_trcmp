package org.firstinspires.ftc.teamcode.Commands.StateActions.FunnelActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.FunnelSubsystem;

/**
 * Action command to shake the funnel.
 */
public class FunnelShakeAction extends CommandBase {

    private final FunnelSubsystem funnelSubsystem;
    private boolean isFinished = false;

    /**
     * Constructor for FunnelShakeAction.
     * @param funnelSubsystem The funnel subsystem instance.
     */
    public FunnelShakeAction(FunnelSubsystem funnelSubsystem)
    {
        addRequirements(funnelSubsystem);
        this.funnelSubsystem = funnelSubsystem;
    }

    @Override
    public void initialize() {
        funnelSubsystem.shake();
    }

    @Override
    public void execute() {
        if(!(funnelSubsystem.getState() == AllStates.FunnelStates.SHAKE)) isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
