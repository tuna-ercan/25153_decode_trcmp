package org.firstinspires.ftc.teamcode.Commands.StateRequests.FunnelRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.FunnelSubsystem;

/**
 * Request command to set the Funnel subsystem to the PREP_COLOR state.
 */
public class FunnelPrepColorRequest extends CommandBase
{
    private final FunnelSubsystem funnelSubsystem;
    private boolean isFinished;

    /**
     * Constructor for FunnelPrepColorRequest.
     * @param funnelSubsystem The funnel subsystem instance.
     */
    public FunnelPrepColorRequest(FunnelSubsystem funnelSubsystem)
    {
        this.funnelSubsystem = funnelSubsystem;
    }

    /**
     * Initializes the request by setting the state to PREP_COLOR.
     */
    @Override
    public void initialize()
    {
        funnelSubsystem.setState(AllStates.FunnelStates.PREP_COLOR);
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
