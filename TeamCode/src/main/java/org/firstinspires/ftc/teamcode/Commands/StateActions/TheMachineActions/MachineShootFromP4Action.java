package org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MachineShootFromP4Action extends ParallelCommandGroup
{

    private final TheMachineSubsystem theMachineSubsystem;
    private boolean isFinished = false;

    public MachineShootFromP4Action(TheMachineSubsystem theMachineSubsystem)
    {
        super(
                theMachineSubsystem.intakeRequest(AllStates.IntakeStates.IDLE),
                theMachineSubsystem.shooterRequest(AllStates.ShooterStates.SHOOT_P4)
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
        if(theMachineSubsystem.getState() != AllStates.MachineStates.SHOOT_FROM_P4) isFinished = true;
    }

    @Override
    public boolean isFinished()
    {
        return isFinished;
    }
}
