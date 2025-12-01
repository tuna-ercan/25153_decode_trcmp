package org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

/**
 * Action command to set the Shooter subsystem to the REST state.
 * Maintains the rest state logic during execution.
 */
public class ShooterRestAction extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private boolean isFinished = false;

    /**
     * Constructor for ShooterRestAction.
     * @param shooterSubsystem The shooter subsystem instance.
     */
    public ShooterRestAction(ShooterSubsystem shooterSubsystem) {
        addRequirements(shooterSubsystem);
        this.shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void initialize() {
        shooterSubsystem.rest();
    }

    @Override
    public void execute() {
        shooterSubsystem.rest();
        if(!(shooterSubsystem.getState() == AllStates.ShooterStates.REST)) isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
