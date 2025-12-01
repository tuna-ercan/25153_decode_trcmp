package org.firstinspires.ftc.teamcode.Commands.StateRequests.LiftRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.LiftSubsystem;

/**
 * Request command to set the Lift subsystem to the OPEN state.
 */
public class LiftOpenRequest extends CommandBase
{
    private final LiftSubsystem liftSubsystem;
    private boolean isFinished;

    /**
     * Constructor for LiftOpenRequest.
     * @param liftSubsystem The lift subsystem instance.
     */
    public LiftOpenRequest(LiftSubsystem liftSubsystem)
    {
        this.liftSubsystem = liftSubsystem;
    }

    /**
     * Initializes the request by setting the state to OPEN.
     */
    @Override
    public void initialize()
    {
        liftSubsystem.setState(AllStates.LiftStates.OPEN);
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
