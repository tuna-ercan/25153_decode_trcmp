package org.firstinspires.ftc.teamcode.Commands.StateRequests.IntakeRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

/**
 * Request command to set the Intake subsystem to the IDLE state.
 */
public class IntakeIdleRequest extends CommandBase
{

    private final IntakeSubsystem intakeSubsystem;
    private boolean isFinished;

    /**
     * Constructor for IntakeIdleRequest.
     * @param intakeSubsystem The intake subsystem instance.
     */
    public IntakeIdleRequest(IntakeSubsystem intakeSubsystem)
    {
        this.intakeSubsystem = intakeSubsystem;
    }

    /**
     * Initializes the request by setting the state to IDLE.
     */
    @Override
    public void initialize()
    {
        intakeSubsystem.setState(AllStates.IntakeStates.IDLE);
        isFinished = true;
    }

    /**
     * Checks if the request is finished.
     * @return true always, as this is an instant request.
     */
    @Override
    public boolean isFinished()
    {
        return isFinished;
    }
}
