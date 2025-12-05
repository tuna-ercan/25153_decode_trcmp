package org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MachineParkAction extends CommandBase
{

    private final TheMachineSubsystem theMachineSubsystem;

    public MachineParkAction(TheMachineSubsystem theMachineSubsystem)
    {
        this.theMachineSubsystem = theMachineSubsystem;
        addRequirements(theMachineSubsystem);
    }

    @Override
    public void initialize()
    {
        theMachineSubsystem.funnelRequest(AllStates.FunnelStates.HOME).schedule();
        theMachineSubsystem.intakeRequest(AllStates.IntakeStates.IDLE).schedule();
        theMachineSubsystem.shooterRequest(AllStates.ShooterStates.ZERO).schedule();
    }

    @Override
    public void execute()
    {
    }

    private boolean checkFinish()
    {
        return theMachineSubsystem.getState() != AllStates.MachineStates.PARK;
    }

    @Override
    public boolean isFinished()
    {
        return checkFinish();
    }
}
