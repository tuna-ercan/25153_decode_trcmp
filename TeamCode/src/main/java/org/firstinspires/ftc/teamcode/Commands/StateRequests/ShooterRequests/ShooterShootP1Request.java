package org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class ShooterShootP1Request extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private boolean isFinished;

    public ShooterShootP1Request(ShooterSubsystem shooterSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void initialize() {
        shooterSubsystem.setState(AllStates.ShooterStates.SHOOT_P1);
        isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
