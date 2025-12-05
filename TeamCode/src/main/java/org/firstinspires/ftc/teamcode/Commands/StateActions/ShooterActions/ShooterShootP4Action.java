package org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class ShooterShootP4Action extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;

    public ShooterShootP4Action(ShooterSubsystem shooterSubsystem) {
        addRequirements(shooterSubsystem);
        this.shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void initialize() {
        shooterSubsystem.shootP4();
    }

    @Override
    public void execute() {
        shooterSubsystem.shootP4();
    }

    private boolean checkFinish()
    {
        return shooterSubsystem.getState() != AllStates.ShooterStates.SHOOT_P4;
    }

    @Override
    public boolean isFinished() {
        return checkFinish();
    }
}
