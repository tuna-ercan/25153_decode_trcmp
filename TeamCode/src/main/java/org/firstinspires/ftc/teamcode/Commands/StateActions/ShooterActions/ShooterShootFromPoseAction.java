package org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class ShooterShootFromPoseAction extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private boolean isFinished = false;

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
        if(!(shooterSubsystem.getState() == AllStates.ShooterStates.SHOOT_FROM_POSE)) isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
