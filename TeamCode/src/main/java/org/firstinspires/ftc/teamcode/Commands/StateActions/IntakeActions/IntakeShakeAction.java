package org.firstinspires.ftc.teamcode.Commands.StateActions.IntakeActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

/**
 * Action command to shake the intake mechanism.
 */
public class IntakeShakeAction extends CommandBase {

    private final IntakeSubsystem intakeSubsystem;
    private boolean isFinished = false;

    /**
     * Constructor for IntakeShakeAction.
     * @param intakeSubsystem The intake subsystem instance.
     */
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
