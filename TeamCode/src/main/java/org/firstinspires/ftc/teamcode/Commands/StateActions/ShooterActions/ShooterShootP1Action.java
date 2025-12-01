package org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

/**
 * Action command to set the Shooter subsystem to shoot at Position 1 (P1).
 * Maintains the shooting logic (RPM, hood) during execution.
 */
public class ShooterShootP1Action extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private boolean isFinished = false;

    /**
     * Constructor for ShooterShootP1Action.
     * @param shooterSubsystem The shooter subsystem instance.
     */
    public ShooterShootP1Action(ShooterSubsystem shooterSubsystem) {
        addRequirements(shooterSubsystem);
        this.shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void initialize() {
        shooterSubsystem.shootP1();
    }

    @Override
    public void execute() {
        shooterSubsystem.shootP1();
        if(!(shooterSubsystem.getState() == AllStates.ShooterStates.SHOOT_P1)) isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
