package org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.Utils.AllStates;

public class ShooterShootP5Action extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;

    public ShooterShootP5Action(ShooterSubsystem shooterSubsystem) {
        addRequirements(shooterSubsystem);
        this.shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void initialize() {
        shooterSubsystem.shootP5();
    }

    @Override
    public void execute() {
        shooterSubsystem.shootP5();
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
