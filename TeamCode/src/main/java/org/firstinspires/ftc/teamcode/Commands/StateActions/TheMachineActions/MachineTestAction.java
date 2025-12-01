package org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MachineTestAction extends CommandBase
{

    private final TheMachineSubsystem machineSubsystem;
    private boolean isFinished = false;

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
        if(!(machineSubsystem.getState() == AllStates.MachineStates.TEST)) isFinished = true;
    }

    @Override
    public boolean isFinished()
    {
        return isFinished;
    }
}
