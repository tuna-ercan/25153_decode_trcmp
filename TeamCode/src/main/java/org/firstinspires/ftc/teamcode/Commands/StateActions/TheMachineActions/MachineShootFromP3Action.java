package org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MachineShootFromP3Action extends ParallelCommandGroup
{

    private final TheMachineSubsystem theMachineSubsystem;

    public MachineShootFromP3Action(TheMachineSubsystem theMachineSubsystem)
    {
        this.theMachineSubsystem = theMachineSubsystem;
        addRequirements(theMachineSubsystem);

        addCommands(
                theMachineSubsystem.intakeRequest(AllStates.IntakeStates.IDLE),
                new SequentialCommandGroup(
                        theMachineSubsystem.shooterRequest(AllStates.ShooterStates.SHOOT_P3),
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
        return theMachineSubsystem.getState() != AllStates.MachineStates.SHOOT_FROM_P3;
    }

    @Override
    public boolean isFinished()
    {
        return checkFinish();
    }
}
