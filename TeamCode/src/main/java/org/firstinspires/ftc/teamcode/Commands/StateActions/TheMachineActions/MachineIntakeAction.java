package org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MachineIntakeAction extends ParallelCommandGroup
{

    private final TheMachineSubsystem theMachineSubsystem;
    private boolean isFinished = false;

    public MachineIntakeAction(TheMachineSubsystem theMachineSubsystem)
    {
        super(
                theMachineSubsystem.funnelRequest(AllStates.FunnelStates.HOME),
                theMachineSubsystem.intakeRequest(AllStates.IntakeStates.INTAKE),
                theMachineSubsystem.liftRequest(AllStates.LiftStates.HOME),
                theMachineSubsystem.shooterRequest(AllStates.ShooterStates.REST)
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
        if(theMachineSubsystem.getState() != AllStates.MachineStates.INTAKE) isFinished = true;
    }

    @Override
    public boolean isFinished()
    {
        return isFinished;
    }
}
