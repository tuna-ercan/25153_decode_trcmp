package org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class ShooterReverseAction extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private boolean isFinished = false;

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
        if(!(shooterSubsystem.getState() == AllStates.ShooterStates.REVERSE)) isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
