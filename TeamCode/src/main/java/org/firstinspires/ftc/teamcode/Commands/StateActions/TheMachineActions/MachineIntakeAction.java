package org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MachineIntakeAction extends CommandBase
{

    private final TheMachineSubsystem theMachineSubsystem;

    public MachineIntakeAction(TheMachineSubsystem theMachineSubsystem)
    {
        this.theMachineSubsystem = theMachineSubsystem;
        addRequirements(theMachineSubsystem);
    }

    @Override
    public void initialize()
    {
        theMachineSubsystem.funnelRequest(AllStates.FunnelStates.HOME);
        theMachineSubsystem.intakeRequest(AllStates.IntakeStates.INTAKE);
        theMachineSubsystem.shooterRequest(AllStates.ShooterStates.REST);
    }

    @Override
    public void execute()
    {
        
    }

    private boolean checkFinish()
    {
        return theMachineSubsystem.getState() != AllStates.MachineStates.INTAKE;
    }

    @Override
    public boolean isFinished()
    {
        return checkFinish();
    }
}
