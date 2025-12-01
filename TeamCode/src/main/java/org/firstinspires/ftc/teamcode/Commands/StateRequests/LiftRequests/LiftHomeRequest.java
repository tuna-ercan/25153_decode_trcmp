package org.firstinspires.ftc.teamcode.Commands.StateRequests.LiftRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.LiftSubsystem;

/**
 * Request command to set the Lift subsystem to the HOME state.
 */
public class LiftHomeRequest extends CommandBase
{
    private final LiftSubsystem liftSubsystem;
    private boolean isFinished;

    /**
     * Constructor for LiftHomeRequest.
     * @param liftSubsystem The lift subsystem instance.
     */
    public LiftHomeRequest(LiftSubsystem liftSubsystem)
    {
        this.liftSubsystem = liftSubsystem;
    }

    /**
     * Initializes the request by setting the state to HOME.
     */
    @Override
    public void initialize()
    {
        liftSubsystem.setState(AllStates.LiftStates.HOME);
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
