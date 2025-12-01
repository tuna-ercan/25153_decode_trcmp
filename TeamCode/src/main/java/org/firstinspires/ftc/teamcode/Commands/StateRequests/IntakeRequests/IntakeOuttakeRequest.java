package org.firstinspires.ftc.teamcode.Commands.StateRequests.IntakeRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

/**
 * Request command to set the Intake subsystem to the OUTTAKE state.
 */
public class IntakeOuttakeRequest extends CommandBase
{

    private final IntakeSubsystem intakeSubsystem;
    private boolean isFinished;

    /**
     * Constructor for IntakeOuttakeRequest.
     * @param intakeSubsystem The intake subsystem instance.
     */
    public IntakeOuttakeRequest(IntakeSubsystem intakeSubsystem)
    {
        this.intakeSubsystem = intakeSubsystem;
    }

    /**
     * Initializes the request by setting the state to OUTTAKE.
     */
    @Override
    public void initialize()
    {
        intakeSubsystem.setState(AllStates.IntakeStates.OUTTAKE);
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
