package org.firstinspires.ftc.teamcode.Commands.StateActions.IntakeActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

/**
 * Action command to set the Intake subsystem to the OUTTAKE state.
 * This command runs the intake motor in reverse and persists until the state changes.
 */
public class IntakeOuttakeAction extends CommandBase {

    private final IntakeSubsystem intakeSubsystem;
    private boolean isFinished = false;

    /**
     * Constructor for IntakeOuttakeAction.
     * @param intakeSubsystem The intake subsystem instance.
     */
    public IntakeOuttakeAction(IntakeSubsystem intakeSubsystem)
    {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
        intakeSubsystem.outtake();
    }

    @Override
    public void execute() {
        if(!(intakeSubsystem.getState() == AllStates.IntakeStates.OUTTAKE)) isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
