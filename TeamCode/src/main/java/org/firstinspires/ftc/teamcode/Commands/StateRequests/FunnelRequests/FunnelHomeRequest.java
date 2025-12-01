package org.firstinspires.ftc.teamcode.Commands.StateRequests.FunnelRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.FunnelSubsystem;

/**
 * Request command to set the Funnel subsystem to the HOME state.
 */
public class FunnelHomeRequest extends CommandBase
{
    private final FunnelSubsystem funnelSubsystem;
    private boolean isFinished;

    /**
     * Constructor for FunnelHomeRequest.
     * @param funnelSubsystem The funnel subsystem instance.
     */
    public FunnelHomeRequest(FunnelSubsystem funnelSubsystem)
    {
        this.funnelSubsystem = funnelSubsystem;
    }

    /**
     * Initializes the request by setting the state to HOME.
     */
    @Override
    public void initialize()
    {
        funnelSubsystem.setState(AllStates.FunnelStates.HOME);
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
