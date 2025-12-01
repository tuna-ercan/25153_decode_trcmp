package org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class ShooterRestRequest extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private boolean isFinished;

    public ShooterRestRequest(ShooterSubsystem shooterSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void initialize() {
        shooterSubsystem.setState(AllStates.ShooterStates.REST);
        isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
