package org.firstinspires.ftc.teamcode.Commands.StateActions.FunnelActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.FunnelSubsystem;

/**
 * Action command to move the funnel servos to the HOME position.
 */
public class FunnelHomeAction extends CommandBase {

    private final FunnelSubsystem funnelSubsystem;
    private boolean isFinished = false;

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

    @Override
    public void execute() {
        if(!(funnelSubsystem.getState() == AllStates.FunnelStates.HOME)) isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
