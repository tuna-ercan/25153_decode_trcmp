package org.firstinspires.ftc.teamcode.Commands.StateRequests.IntakeRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class IntakeTestRequest extends CommandBase
{

    private final IntakeSubsystem intakeSubsystem;

    public IntakeTestRequest(IntakeSubsystem intakeSubsystem)
    {
        this.intakeSubsystem = intakeSubsystem;
    }

    @Override
    public void initialize()
    {
        intakeSubsystem.setState(AllStates.IntakeStates.TEST);
    }

    @Override
    public boolean isFinished()
    {
        return true;
    }
}
