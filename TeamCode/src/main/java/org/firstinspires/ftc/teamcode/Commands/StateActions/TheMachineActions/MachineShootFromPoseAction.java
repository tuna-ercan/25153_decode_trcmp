package org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MachineShootFromPoseAction extends ParallelCommandGroup
{

    private final TheMachineSubsystem theMachineSubsystem;
    private boolean isFinished = false;

    public MachineShootFromPoseAction(TheMachineSubsystem theMachineSubsystem)
    {
        super(
                theMachineSubsystem.funnelRequest(AllStates.FunnelStates.FEED),
                theMachineSubsystem.intakeRequest(AllStates.IntakeStates.IDLE),
                theMachineSubsystem.liftRequest(AllStates.LiftStates.HOME),
                theMachineSubsystem.shooterRequest(AllStates.ShooterStates.SHOOT_FROM_POSE)
        );

        this.theMachineSubsystem = theMachineSubsystem;
        addRequirements(theMachineSubsystem);
    }

    @Override
    public void initialize()
    {
        isFinished = false;
    }

    @Override
    public void execute()
    {
        if(theMachineSubsystem.getState() != AllStates.MachineStates.SHOOT_FROM_POSE) isFinished = true;
    }

    @Override
    public boolean isFinished()
    {
        return isFinished;
    }
}
