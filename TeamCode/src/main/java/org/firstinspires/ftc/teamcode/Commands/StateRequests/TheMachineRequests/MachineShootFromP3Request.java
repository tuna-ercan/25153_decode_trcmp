package org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

/**
 * Request command to set the Machine subsystem to the SHOOT_FROM_P3 state.
 */
public class MachineShootFromP3Request extends CommandBase
{

    private final TheMachineSubsystem machineSubsystem;
    private boolean isFinished;

    /**
     * Constructor for MachineShootFromP3Request.
     * @param machineSubsystem The machine subsystem instance.
     */
    public MachineShootFromP3Request(TheMachineSubsystem machineSubsystem)
    {
        this.machineSubsystem = machineSubsystem;
    }

    /**
     * Initializes the request by setting the state to SHOOT_FROM_P3.
     */
    @Override
    public void initialize()
    {
        machineSubsystem.setState(AllStates.MachineStates.SHOOT_FROM_P3);
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
