package org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MachineShakeAction extends ParallelCommandGroup
{

    private final TheMachineSubsystem theMachineSubsystem;

    public MachineShakeAction(TheMachineSubsystem theMachineSubsystem)
    {
        this.theMachineSubsystem = theMachineSubsystem;
        addRequirements(theMachineSubsystem);

        addCommands(
                theMachineSubsystem.funnelRequest(AllStates.FunnelStates.SHAKE),
                theMachineSubsystem.intakeRequest(AllStates.IntakeStates.SHAKE),
                theMachineSubsystem.shooterRequest(AllStates.ShooterStates.SHAKE)
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
        return theMachineSubsystem.getState() != AllStates.MachineStates.SHAKE;
    }

    @Override
    public boolean isFinished()
    {
        return checkFinish();
    }
}
