package org.firstinspires.ftc.teamcode.Commands.StateRequests.IntakeRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

/**
 * Request command to set the Intake subsystem to the SHAKE state.
 */
public class IntakeShakeRequest extends CommandBase
{

    private final IntakeSubsystem intakeSubsystem;

    /**
     * Constructor for IntakeShakeRequest.
     * @param intakeSubsystem The intake subsystem instance.
     */
    public IntakeShakeRequest(IntakeSubsystem intakeSubsystem)
    {
        this.intakeSubsystem = intakeSubsystem;
    }

    /**
     * Initializes the request by setting the state to SHAKE.
     */
    @Override
    public void initialize()
    {
        intakeSubsystem.setState(AllStates.IntakeStates.SHAKE);
    }

    /**
     * Checks if the request is finished.
     * @return true always.
     */
    @Override
    public boolean isFinished()
    {
        return true;
    }
}
