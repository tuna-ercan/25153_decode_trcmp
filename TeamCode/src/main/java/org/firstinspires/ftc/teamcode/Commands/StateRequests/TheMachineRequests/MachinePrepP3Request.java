package org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

/**
 * Request command to set the Machine subsystem to the PREP_P3 state.
 */
public class MachinePrepP3Request extends CommandBase
{

    private final TheMachineSubsystem machineSubsystem;
    private boolean isFinished;

    /**
     * Constructor for MachinePrepP3Request.
     * @param machineSubsystem The machine subsystem instance.
     */
    public MachinePrepP3Request(TheMachineSubsystem machineSubsystem)
    {
        this.machineSubsystem = machineSubsystem;
    }

    /**
     * Initializes the request by setting the state to PREP_P3.
     */
    @Override
    public void initialize()
    {
        machineSubsystem.setState(AllStates.MachineStates.PREP_P3);
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
