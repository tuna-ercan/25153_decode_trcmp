package org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MachineTestAction extends ParallelCommandGroup
{

    private final TheMachineSubsystem machineSubsystem;

    public MachineTestAction(TheMachineSubsystem machineSubsystem)
    {
        this.machineSubsystem = machineSubsystem;
        addRequirements(machineSubsystem);

        addCommands(
                machineSubsystem.intakeRequest(AllStates.IntakeStates.IDLE),
                machineSubsystem.funnelRequest(AllStates.FunnelStates.PREP),
                new SequentialCommandGroup(
                        machineSubsystem.shooterRequest(AllStates.ShooterStates.TEST),
                        machineSubsystem.waitForShooterToBeReady(),
                        machineSubsystem.funnelRequest(AllStates.FunnelStates.FEED)
                ));
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
        return machineSubsystem.getState() != AllStates.MachineStates.TEST;
    }

    @Override
    public boolean isFinished()
    {
        return checkFinish();
    }
}
