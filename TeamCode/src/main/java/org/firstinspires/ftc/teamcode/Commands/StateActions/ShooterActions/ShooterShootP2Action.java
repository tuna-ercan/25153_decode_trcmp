package org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class ShooterShootP2Action extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private boolean isFinished = false;

    public ShooterShootP2Action(ShooterSubsystem shooterSubsystem) {
        addRequirements(shooterSubsystem);
        this.shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void initialize() {
        shooterSubsystem.shootP2();
    }

    @Override
    public void execute() {
        shooterSubsystem.shootP2();
        if(!(shooterSubsystem.getState() == AllStates.ShooterStates.SHOOT_P2)) isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
