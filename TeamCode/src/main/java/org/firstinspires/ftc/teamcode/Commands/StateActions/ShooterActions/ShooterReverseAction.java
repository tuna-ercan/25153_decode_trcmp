package org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class ShooterReverseAction extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;

    public ShooterReverseAction(ShooterSubsystem shooterSubsystem) {
        addRequirements(shooterSubsystem);
        this.shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void initialize() {
        shooterSubsystem.reverse();
    }

    @Override
    public void execute() {
        shooterSubsystem.reverse();
    }

    private boolean checkFinish()
    {
        return shooterSubsystem.getState() != AllStates.ShooterStates.REVERSE;
    }

    @Override
    public boolean isFinished() {
        return checkFinish();
    }
}
