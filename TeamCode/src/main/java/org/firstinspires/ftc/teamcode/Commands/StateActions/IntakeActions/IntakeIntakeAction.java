package org.firstinspires.ftc.teamcode.Commands.StateActions.IntakeActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

/**
 * Action command to set the Intake subsystem to the INTAKE state.
 * This command runs the intake motor for intaking and persists until the state changes.
 */
public class IntakeIntakeAction extends CommandBase {

    private final IntakeSubsystem intakeSubsystem;
    private boolean isFinished = false;

    /**
     * Constructor for IntakeIntakeAction.
     * @param intakeSubsystem The intake subsystem instance.
     */
    public IntakeIntakeAction(IntakeSubsystem intakeSubsystem)
    {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    /**
     * Initializes the action by starting the intake process.
     */
    @Override
    public void initialize() {
        intakeSubsystem.intake();
    }

    @Override
    public void execute() {
        if(!(intakeSubsystem.getState() == AllStates.IntakeStates.INTAKE)) isFinished = true;
    }

    /**
     * Checks if the action is finished.
     * The action finishes when the intake state is no longer INTAKE.
     * @return true if the state has changed from INTAKE, false otherwise.
     */
    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
