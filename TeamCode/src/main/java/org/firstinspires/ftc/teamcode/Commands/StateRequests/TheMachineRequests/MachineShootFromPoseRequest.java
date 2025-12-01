package org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

/**
 * Request command to set the Machine subsystem to the SHOOT_FROM_POSE state.
 */
public class MachineShootFromPoseRequest extends CommandBase
{

    private final TheMachineSubsystem machineSubsystem;
    private boolean isFinished;

    /**
     * Constructor for MachineShootFromPoseRequest.
     * @param machineSubsystem The machine subsystem instance.
     */
    public MachineShootFromPoseRequest(TheMachineSubsystem machineSubsystem)
    {
        this.machineSubsystem = machineSubsystem;
    }

    /**
     * Initializes the request by setting the state to SHOOT_FROM_POSE.
     */
    @Override
    public void initialize()
    {
        machineSubsystem.setState(AllStates.MachineStates.SHOOT_FROM_POSE);
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
