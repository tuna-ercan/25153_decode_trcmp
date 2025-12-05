package org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MachineShakeAction extends CommandBase
{

    private final TheMachineSubsystem theMachineSubsystem;

    public MachineShakeAction(TheMachineSubsystem theMachineSubsystem)
    {
        this.theMachineSubsystem = theMachineSubsystem;
        addRequirements(theMachineSubsystem);
    }

    @Override
    public void initialize()
    {
        theMachineSubsystem.funnelRequest(AllStates.FunnelStates.SHAKE).schedule();
        theMachineSubsystem.intakeRequest(AllStates.IntakeStates.SHAKE).schedule();
        theMachineSubsystem.shooterRequest(AllStates.ShooterStates.SHAKE).schedule();
    }

    @Override
    public void execute()
    {
    }

    private boolean checkFinish()
    {
        return theMachineSubsystem.getState() != AllStates.MachineStates.SHAKE;
    }

    @Override
    public boolean isFinished()
    {
        return checkFinish();
    }
}
