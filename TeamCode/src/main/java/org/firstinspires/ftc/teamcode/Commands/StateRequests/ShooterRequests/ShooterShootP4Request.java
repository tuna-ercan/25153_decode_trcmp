package org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class ShooterShootP4Request extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private boolean isFinished;

    public ShooterShootP4Request(ShooterSubsystem shooterSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void initialize() {
        shooterSubsystem.setState(AllStates.ShooterStates.SHOOT_P4);
        isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
