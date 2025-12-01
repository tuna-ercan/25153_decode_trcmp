package org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.Constants.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MachineShootFromP3Action extends ParallelCommandGroup
{

    private final TheMachineSubsystem theMachineSubsystem;
    private boolean isFinished = false;

    public MachineShootFromP3Action(TheMachineSubsystem theMachineSubsystem)
    {
        super(
                theMachineSubsystem.intakeRequest(AllStates.IntakeStates.IDLE),
                theMachineSubsystem.liftRequest(AllStates.LiftStates.HOME),
                theMachineSubsystem.shooterRequest(AllStates.ShooterStates.SHOOT_P3)
                        .andThen(theMachineSubsystem.waitForShooterToBeReady())
                        .andThen(theMachineSubsystem.funnelRequest(AllStates.FunnelStates.FEED))
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
        if(theMachineSubsystem.getState() != AllStates.MachineStates.SHOOT_FROM_P3) isFinished = true;
    }

    @Override
    public boolean isFinished()
    {
        return isFinished;
    }
}
