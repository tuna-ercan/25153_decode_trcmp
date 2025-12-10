package org.firstinspires.ftc.teamcode.Commands.StateActions.IntakeActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

/**
 * Action command to run the Intake subsystem in TEST mode.
 */
public class IntakeTestAction extends CommandBase
{

    private final IntakeSubsystem intakeSubsystem;
    private boolean isFinished = false;

    /**
     * Constructor for IntakeTestAction.
     * @param intakeSubsystem The intake subsystem instance.
     */
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
        intakeSubsystem.test();
    }

    @Override
    public boolean isFinished()
    {
        return !(intakeSubsystem.getState() == AllStates.IntakeStates.TEST);
    }
}
