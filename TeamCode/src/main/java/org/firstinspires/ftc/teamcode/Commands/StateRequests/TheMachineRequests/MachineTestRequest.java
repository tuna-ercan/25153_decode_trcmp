package org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MachineTestRequest extends CommandBase
{

    private final TheMachineSubsystem machineSubsystem;
    private boolean isFinished;

    public MachineTestRequest(TheMachineSubsystem machineSubsystem)
    {
        this.machineSubsystem = machineSubsystem;
    }

    @Override
    public void initialize()
    {
        machineSubsystem.setState(AllStates.MachineStates.TEST);
        isFinished = true;
    }

    @Override
    public boolean isFinished()
    {
        return isFinished;
    }
}
