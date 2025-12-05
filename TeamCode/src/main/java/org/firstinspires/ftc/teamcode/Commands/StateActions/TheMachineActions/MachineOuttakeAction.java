package org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MachineOuttakeAction extends CommandBase
{

    private final TheMachineSubsystem theMachineSubsystem;

    public MachineOuttakeAction(TheMachineSubsystem theMachineSubsystem)
    {
        this.theMachineSubsystem = theMachineSubsystem;
        addRequirements(theMachineSubsystem);
    }

    @Override
    public void initialize()
    {
        theMachineSubsystem.funnelRequest(AllStates.FunnelStates.HOME).schedule();
        theMachineSubsystem.intakeRequest(AllStates.IntakeStates.OUTTAKE).schedule();
        theMachineSubsystem.shooterRequest(AllStates.ShooterStates.REST).schedule();
    }

    @Override
    public void execute()
    {
        
    }

    private boolean checkFinish()
    {
        return theMachineSubsystem.getState() != AllStates.MachineStates.OUTTAKE;
    }

    @Override
    public boolean isFinished()
    {
        return checkFinish();
    }
}
