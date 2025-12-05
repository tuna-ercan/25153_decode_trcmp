package org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MachineTestAction extends CommandBase
{

    private final TheMachineSubsystem machineSubsystem;

    public MachineTestAction(TheMachineSubsystem machineSubsystem)
    {
        this.machineSubsystem = machineSubsystem;
        addRequirements(machineSubsystem);
    }

    @Override
    public void initialize()
    {
        
    }

    @Override
    public void execute()
    {
        
    }

    private boolean checkFinish()
    {
        return machineSubsystem.getState() != AllStates.MachineStates.TEST;
    }

    @Override
    public boolean isFinished()
    {
        return checkFinish();
    }
}
