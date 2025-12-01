package org.firstinspires.ftc.teamcode.Commands.StateActions.IntakeActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class IntakeTestAction extends CommandBase
{

    private final IntakeSubsystem intakeSubsystem;
    private boolean isFinished = false;

    public IntakeTestAction(IntakeSubsystem intakeSubsystem)
    {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize()
    {
        intakeSubsystem.test();
    }

    @Override
    public void execute()
    {
        if(!(intakeSubsystem.getState() == AllStates.IntakeStates.TEST)) isFinished = true;
    }

    @Override
    public boolean isFinished()
    {
        return isFinished;
    }
}
