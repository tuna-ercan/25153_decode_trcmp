package org.firstinspires.ftc.teamcode.Commands.StateActions.IntakeActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class IntakeOuttakeAction extends CommandBase {

    private final IntakeSubsystem intakeSubsystem;
    private boolean isFinished = false;

    public IntakeOuttakeAction(IntakeSubsystem intakeSubsystem)
    {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
        intakeSubsystem.outtake();
    }

    @Override
    public void execute() {
        if(!(intakeSubsystem.getState() == AllStates.IntakeStates.OUTTAKE)) isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
