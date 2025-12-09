package org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class ShooterZeroRequest extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;

    public ShooterZeroRequest(ShooterSubsystem shooterSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void initialize() {
        shooterSubsystem.setState(AllStates.ShooterStates.ZERO);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
