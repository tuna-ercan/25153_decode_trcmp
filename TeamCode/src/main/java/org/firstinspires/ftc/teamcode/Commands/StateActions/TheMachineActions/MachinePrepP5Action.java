package org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;
import org.firstinspires.ftc.teamcode.Utils.AllStates;

public class MachinePrepP5Action extends ParallelCommandGroup
{

    private final TheMachineSubsystem theMachineSubsystem;

    public MachinePrepP5Action(TheMachineSubsystem theMachineSubsystem)
    {
        this.theMachineSubsystem = theMachineSubsystem;
        addRequirements(theMachineSubsystem);

        addCommands(
                theMachineSubsystem.funnelRequest(AllStates.FunnelStates.PREP),
                theMachineSubsystem.intakeRequest(AllStates.IntakeStates.IDLE),
                theMachineSubsystem.shooterRequest(AllStates.ShooterStates.SHOOT_P5)
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
        return theMachineSubsystem.getState() != AllStates.MachineStates.PREP_P5;
    }

    @Override
    public boolean isFinished()
    {
        return checkFinish();
    }
}
