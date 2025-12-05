package org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MachineShootFromP3Action extends CommandBase
{

    private final TheMachineSubsystem theMachineSubsystem;

    public MachineShootFromP3Action(TheMachineSubsystem theMachineSubsystem)
    {
        this.theMachineSubsystem = theMachineSubsystem;
        addRequirements(theMachineSubsystem);
    }

    @Override
    public void initialize()
    {
        theMachineSubsystem.intakeRequest(AllStates.IntakeStates.IDLE).schedule();
        theMachineSubsystem.shooterRequest(AllStates.ShooterStates.SHOOT_P3)
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
        return theMachineSubsystem.getState() != AllStates.MachineStates.SHOOT_FROM_P3;
    }

    @Override
    public boolean isFinished()
    {
        return checkFinish();
    }
}
