package org.firstinspires.ftc.teamcode.Commands.StateActions.IntakeActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class IntakeShakeAction extends CommandBase {

    private final IntakeSubsystem intakeSubsystem;
    private boolean isFinished = false;

    public IntakeShakeAction(IntakeSubsystem intakeSubsystem)
    {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
        intakeSubsystem.shake();
    }

    @Override
    public void execute() {
        if(!(intakeSubsystem.getState() == AllStates.IntakeStates.SHAKE)) isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
