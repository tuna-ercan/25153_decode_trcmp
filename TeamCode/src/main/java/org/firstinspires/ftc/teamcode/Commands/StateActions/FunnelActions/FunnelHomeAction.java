package org.firstinspires.ftc.teamcode.Commands.StateActions.FunnelActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.FunnelSubsystem;

/**
 * Action command to move the funnel servos to the HOME position.
 */
public class FunnelHomeAction extends CommandBase {

    private final FunnelSubsystem funnelSubsystem;

    /**
     * Constructor for FunnelHomeAction.
     * @param funnelSubsystem The funnel subsystem instance.
     */
    public FunnelHomeAction(FunnelSubsystem funnelSubsystem)
    {
        addRequirements(funnelSubsystem);
        this.funnelSubsystem = funnelSubsystem;
    }

    @Override
    public void initialize() {
        funnelSubsystem.home();
    }

    private boolean checkFinish()
    {
        return funnelSubsystem.getState() != AllStates.FunnelStates.HOME;
    }

    @Override
    public boolean isFinished() {
        return checkFinish();
    }
}
