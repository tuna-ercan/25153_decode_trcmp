package org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class ShooterZeroAction extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;

    public ShooterZeroAction(ShooterSubsystem shooterSubsystem) {
        addRequirements(shooterSubsystem);
        this.shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void initialize() {
        shooterSubsystem.zero();
    }

    @Override
    public void execute() {
        shooterSubsystem.zero();
    }

    private boolean checkFinish()
    {
        return shooterSubsystem.getState() != AllStates.ShooterStates.ZERO;
    }

    @Override
    public boolean isFinished() {
        return checkFinish();
    }
}
