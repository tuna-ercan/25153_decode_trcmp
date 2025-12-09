package org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MachineShootFromPoseAction extends ParallelCommandGroup
{

    private final TheMachineSubsystem theMachineSubsystem;

    public MachineShootFromPoseAction(TheMachineSubsystem theMachineSubsystem)
    {
        this.theMachineSubsystem = theMachineSubsystem;
        addRequirements(theMachineSubsystem);

        addCommands(
                theMachineSubsystem.intakeRequest(AllStates.IntakeStates.IDLE),
                new SequentialCommandGroup(
                        theMachineSubsystem.shooterRequest(AllStates.ShooterStates.SHOOT_FROM_POSE),
                        theMachineSubsystem.waitForShooterToBeReady(),
                        theMachineSubsystem.funnelRequest(AllStates.FunnelStates.FEED)
                )
        );
    }

    @Override
    public void initialize()
    {
        super.initialize();
    }

    @Override
    public void execute()
    {
        super.execute();
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
