package org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;
import org.firstinspires.ftc.teamcode.Utils.AllStates;

public class MachineShootFromP5Action extends ParallelCommandGroup
{

    private final TheMachineSubsystem theMachineSubsystem;

    public MachineShootFromP5Action(TheMachineSubsystem theMachineSubsystem)
    {
        this.theMachineSubsystem = theMachineSubsystem;
        addRequirements(theMachineSubsystem);

        addCommands(
                theMachineSubsystem.intakeRequest(AllStates.IntakeStates.IDLE),
                new SequentialCommandGroup(
                        theMachineSubsystem.shooterRequest(AllStates.ShooterStates.SHOOT_P5),
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
        return theMachineSubsystem.getState() != AllStates.MachineStates.SHOOT_FROM_P4;
    }

    @Override
    public boolean isFinished()
    {
        return checkFinish();
    }
}
