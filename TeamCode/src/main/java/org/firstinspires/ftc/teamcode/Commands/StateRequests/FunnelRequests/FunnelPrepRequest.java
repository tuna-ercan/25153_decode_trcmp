package org.firstinspires.ftc.teamcode.Commands.StateRequests.FunnelRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.FunnelSubsystem;

/**
 * Request command to set the Funnel subsystem to the PREP state.
 */
public class FunnelPrepRequest extends CommandBase
{
    private final FunnelSubsystem funnelSubsystem;
    private boolean isFinished;

    /**
     * Constructor for FunnelPrepRequest.
     * @param funnelSubsystem The funnel subsystem instance.
     */
    public FunnelPrepRequest(FunnelSubsystem funnelSubsystem)
    {
        this.funnelSubsystem = funnelSubsystem;
    }

    /**
     * Initializes the request by setting the state to PREP.
     */
    @Override
    public void initialize()
    {
        funnelSubsystem.setState(AllStates.FunnelStates.PREP);
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
