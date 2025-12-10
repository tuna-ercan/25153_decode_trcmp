package org.firstinspires.ftc.teamcode.Commands.StateActions.IntakeActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

/**
 * Action command to set the Intake subsystem to the IDLE state.
 * This command stops the intake motor and runs until the state changes.
 */
public class IntakeIdleAction extends CommandBase
{

    private final IntakeSubsystem intakeSubsystem;

    /**
     * Constructor for IntakeIdleAction.
     * @param intakeSubsystem The intake subsystem instance.
     */
    public IntakeIdleAction(IntakeSubsystem intakeSubsystem)
    {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    /**
     * Initializes the action by stopping the intake.
     */
    @Override
    public void initialize()
    {
        intakeSubsystem.stop();
    }

    /**
     * Checks if the subsystem state matches the expected state.
     * If not, marks the action as finished.
     */
    @Override
    public void execute()
    {
        intakeSubsystem.stop();
    }

    private boolean checkFinish()
    {
        return intakeSubsystem.getState() != AllStates.IntakeStates.IDLE;
    }

    /**
     * Checks if the action is finished.
     * The action finishes when the intake state is no longer IDLE.
     * @return true if the state has changed from IDLE, false otherwise.
     */
    @Override
    public boolean isFinished()
    {
        return checkFinish();
    }
}
