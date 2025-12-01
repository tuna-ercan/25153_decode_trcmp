package org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

/**
 * Request command to set the Machine subsystem to the REST state.
 */
public class MachineRestRequest extends CommandBase
{

    private final TheMachineSubsystem machineSubsystem;
    private boolean isFinished;

    /**
     * Constructor for MachineRestRequest.
     * @param machineSubsystem The machine subsystem instance.
     */
    public MachineRestRequest(TheMachineSubsystem machineSubsystem)
    {
        this.machineSubsystem = machineSubsystem;
    }

    /**
     * Initializes the request by setting the state to REST.
     */
    @Override
    public void initialize()
    {
        machineSubsystem.setState(AllStates.MachineStates.REST);
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
