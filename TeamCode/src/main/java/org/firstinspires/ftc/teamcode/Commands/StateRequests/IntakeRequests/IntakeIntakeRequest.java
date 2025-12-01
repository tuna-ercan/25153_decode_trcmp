package org.firstinspires.ftc.teamcode.Commands.StateRequests.IntakeRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

/**
 * Request command to set the Intake subsystem to the INTAKE state.
 */
public class IntakeIntakeRequest extends CommandBase
{

    private final IntakeSubsystem intakeSubsystem;
    private boolean isFinished;

    /**
     * Constructor for IntakeIntakeRequest.
     * @param intakeSubsystem The intake subsystem instance.
     */
    public IntakeIntakeRequest(IntakeSubsystem intakeSubsystem)
    {
        this.intakeSubsystem = intakeSubsystem;
    }

    /**
     * Initializes the request by setting the state to INTAKE.
     */
    @Override
    public void initialize()
    {
        intakeSubsystem.setState(AllStates.IntakeStates.INTAKE);
        isFinished = true;
    }

    /**
     * Checks if the request is finished.
     * @return true always.
     */
    @Override
    public boolean isFinished()
    {
        return isFinished;
    }
}
