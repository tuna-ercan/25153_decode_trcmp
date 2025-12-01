package org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class ShooterShakeAction extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private boolean isFinished = false;

    public ShooterShakeAction(ShooterSubsystem shooterSubsystem) {
        addRequirements(shooterSubsystem);
        this.shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void initialize() {
        shooterSubsystem.shake();
    }

    @Override
    public void execute() {
        shooterSubsystem.shake();
        if(!(shooterSubsystem.getState() == AllStates.ShooterStates.SHAKE)) isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
