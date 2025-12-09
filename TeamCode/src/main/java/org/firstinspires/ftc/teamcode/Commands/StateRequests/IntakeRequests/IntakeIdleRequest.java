package org.firstinspires.ftc.teamcode.Commands.StateRequests.IntakeRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

/**
 * Request command to set the Intake subsystem to the IDLE state.
 */
public class IntakeIdleRequest extends CommandBase
{

    private final IntakeSubsystem intakeSubsystem;

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
    }

    /**
     * Checks if the request is finished.
     * @return true always, as this is an instant request.
     */
    @Override
    public boolean isFinished()
    {
        return true;
    }
}
