package org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

/**
 * Request command to set the Machine subsystem to the SHAKE state.
 */
public class MachineShakeRequest extends CommandBase
{

    private final TheMachineSubsystem machineSubsystem;
    private boolean isFinished;

    /**
     * Constructor for MachineShakeRequest.
     * @param machineSubsystem The machine subsystem instance.
     */
    public MachineShakeRequest(TheMachineSubsystem machineSubsystem)
    {
        this.machineSubsystem = machineSubsystem;
    }

    /**
     * Initializes the request by setting the state to SHAKE.
     */
    @Override
    public void initialize()
    {
        machineSubsystem.setState(AllStates.MachineStates.SHAKE);
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
