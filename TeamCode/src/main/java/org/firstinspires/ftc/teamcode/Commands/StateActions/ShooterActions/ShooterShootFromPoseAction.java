package org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class ShooterShootFromPoseAction extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;

    public ShooterShootFromPoseAction(ShooterSubsystem shooterSubsystem) {
        addRequirements(shooterSubsystem);
        this.shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void initialize() {
        shooterSubsystem.shootFromPose();
    }

    @Override
    public void execute() {
        shooterSubsystem.shootFromPose();
    }

    private boolean checkFinish()
    {
        return shooterSubsystem.getState() != AllStates.ShooterStates.SHOOT_FROM_POSE;
    }

    @Override
    public boolean isFinished() {
        return checkFinish();
    }
}
