package org.firstinspires.ftc.teamcode.Commands.StateActions.LiftActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.LiftSubsystem;

/**
 * Action command to move the Lift to the HOME position.
 */
public class LiftHomeAction extends CommandBase {

    private final LiftSubsystem liftSubsystem;
    private boolean isFinished = false;

    /**
     * Constructor for LiftHomeAction.
     * @param liftSubsystem The lift subsystem instance.
     */
    public LiftHomeAction(LiftSubsystem liftSubsystem)
    {
        addRequirements(liftSubsystem);
        this.liftSubsystem = liftSubsystem;
    }

    @Override
    public void initialize() {
        liftSubsystem.home();
    }

    @Override
    public void execute() {
        if(!(liftSubsystem.getState() == AllStates.LiftStates.HOME)) isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
