package org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.Constants.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MachinePrepP2Action extends ParallelCommandGroup
{

    private final TheMachineSubsystem theMachineSubsystem;
    private boolean isFinished = false;

    public MachinePrepP2Action(TheMachineSubsystem theMachineSubsystem)
    {
        super(
                theMachineSubsystem.funnelRequest(AllStates.FunnelStates.PREP),
                theMachineSubsystem.intakeRequest(AllStates.IntakeStates.IDLE),
                theMachineSubsystem.liftRequest(AllStates.LiftStates.HOME),
                theMachineSubsystem.shooterRequest(AllStates.ShooterStates.SHOOT_P2)
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
        if(theMachineSubsystem.getState() != AllStates.MachineStates.PREP_P2) isFinished = true;
    }

    @Override
    public boolean isFinished()
    {
        return isFinished;
    }
}
