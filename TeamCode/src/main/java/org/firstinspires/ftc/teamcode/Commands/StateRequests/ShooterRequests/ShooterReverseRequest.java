package org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class ShooterReverseRequest extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;

    public ShooterReverseRequest(ShooterSubsystem shooterSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void initialize() {
        shooterSubsystem.setState(AllStates.ShooterStates.REVERSE);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
