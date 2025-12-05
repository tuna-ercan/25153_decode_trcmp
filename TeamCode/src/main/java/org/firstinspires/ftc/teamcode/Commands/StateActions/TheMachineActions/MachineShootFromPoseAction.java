package org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MachineShootFromPoseAction extends CommandBase
{

    private final TheMachineSubsystem theMachineSubsystem;

    public MachineShootFromPoseAction(TheMachineSubsystem theMachineSubsystem)
    {
        this.theMachineSubsystem = theMachineSubsystem;
        addRequirements(theMachineSubsystem);
    }

    @Override
    public void initialize()
    {
        theMachineSubsystem.intakeRequest(AllStates.IntakeStates.IDLE).schedule();
        theMachineSubsystem.shooterRequest(AllStates.ShooterStates.SHOOT_FROM_POSE)
                .andThen(theMachineSubsystem.waitForShooterToBeReady())
                .andThen(theMachineSubsystem.funnelRequest(AllStates.FunnelStates.FEED))
                .schedule();
    }

    @Override
    public void execute()
    {
    }

    private boolean checkFinish()
    {
        return theMachineSubsystem.getState() != AllStates.MachineStates.SHOOT_FROM_POSE;
    }

    @Override
    public boolean isFinished()
    {
        return checkFinish();
    }
}
