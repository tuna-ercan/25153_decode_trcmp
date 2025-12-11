package org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class ShooterShootP4Request extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;

    public ShooterShootP4Request(ShooterSubsystem shooterSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void initialize() {
        shooterSubsystem.setState(AllStates.ShooterStates.SHOOT_P4);
    }
    @Override
    public void execute() {
        shooterSubsystem.setState(AllStates.ShooterStates.SHOOT_P4);
    }
    @Override
    public boolean isFinished() {
        return true;
    }
}
