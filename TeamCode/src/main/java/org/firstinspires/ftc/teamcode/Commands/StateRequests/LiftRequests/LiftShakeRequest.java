package org.firstinspires.ftc.teamcode.Commands.StateRequests.LiftRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.LiftSubsystem;

/**
 * Request command to set the Lift subsystem to the SHAKE state.
 */
public class LiftShakeRequest extends CommandBase
{
    private final LiftSubsystem liftSubsystem;
    private boolean isFinished;

    /**
     * Constructor for LiftShakeRequest.
     * @param liftSubsystem The lift subsystem instance.
     */
    public LiftShakeRequest(LiftSubsystem liftSubsystem)
    {
        this.liftSubsystem = liftSubsystem;
    }

    /**
     * Initializes the request by setting the state to SHAKE.
     */
    @Override
    public void initialize()
    {
        liftSubsystem.setState(AllStates.LiftStates.SHAKE);
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
