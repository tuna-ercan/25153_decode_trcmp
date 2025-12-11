package org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;
import org.firstinspires.ftc.teamcode.Utils.AllStates;

/**
 * Request command to set the Machine subsystem to the PREP_P4 state.
 */
public class MachinePrepP5Request extends CommandBase
{

    private final TheMachineSubsystem machineSubsystem;
    private boolean isFinished;

    /**
     * Constructor for MachinePrepP4Request.
     * @param machineSubsystem The machine subsystem instance.
     */
    public MachinePrepP5Request(TheMachineSubsystem machineSubsystem)
    {
        this.machineSubsystem = machineSubsystem;
    }

    /**
     * Initializes the request by setting the state to PREP_P4.
     */
    @Override
    public void initialize()
    {
        machineSubsystem.setState(AllStates.MachineStates.PREP_P5);
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
