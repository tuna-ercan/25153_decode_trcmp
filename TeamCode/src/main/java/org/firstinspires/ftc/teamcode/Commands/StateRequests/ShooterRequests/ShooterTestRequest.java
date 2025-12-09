package org.firstinspires.ftc.teamcode.Commands.StateRequests.ShooterRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class ShooterTestRequest extends CommandBase
{

    private final ShooterSubsystem shooterSubsystem;

    public ShooterTestRequest(ShooterSubsystem shooterSubsystem)
    {
        this.shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void initialize()
    {
        shooterSubsystem.setState(AllStates.ShooterStates.TEST);
    }

    @Override
    public boolean isFinished()
    {
        return true;
    }
}
