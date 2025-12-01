package org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

public class ShooterTestAction extends CommandBase
{

    private final ShooterSubsystem shooterSubsystem;
    private boolean isFinished = false;

    public ShooterTestAction(ShooterSubsystem shooterSubsystem)
    {
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void initialize()
    {
        shooterSubsystem.test();
    }

    @Override
    public void execute()
    {
        if(!(shooterSubsystem.getState() == AllStates.ShooterStates.TEST)) isFinished = true;
    }

    @Override
    public boolean isFinished()
    {
        return isFinished;
    }
}
