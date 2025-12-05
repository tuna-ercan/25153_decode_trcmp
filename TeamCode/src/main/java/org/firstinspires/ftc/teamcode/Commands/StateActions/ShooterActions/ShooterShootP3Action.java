package org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class ShooterShootP3Action extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;

    public ShooterShootP3Action(ShooterSubsystem shooterSubsystem) {
        addRequirements(shooterSubsystem);
        this.shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void initialize() {
        shooterSubsystem.shootP3();
    }

    @Override
    public void execute() {
        shooterSubsystem.shootP3();
    }

    private boolean checkFinish()
    {
        return shooterSubsystem.getState() != AllStates.ShooterStates.SHOOT_P3;
    }

    @Override
    public boolean isFinished() {
        return checkFinish();
    }
}
