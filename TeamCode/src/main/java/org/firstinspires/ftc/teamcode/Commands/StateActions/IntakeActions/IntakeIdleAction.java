package org.firstinspires.ftc.teamcode.Commands.StateActions.IntakeActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants.IntakeConstants;
import org.firstinspires.ftc.teamcode.Container;
import org.firstinspires.ftc.teamcode.Subsystems.FunnelSubsystem;
import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

/**
 * Action command to set the Intake subsystem to the IDLE state.
 * This command stops the intake motor and runs until the state changes.
 */
public class IntakeIdleAction extends CommandBase
{

    private final IntakeSubsystem intakeSubsystem;

    private long now;
    private long startTime;
    private final long intakeDelay;

    /**
     * Constructor for IntakeIdleAction.
     * @param intakeSubsystem The intake subsystem instance.
     */
    public IntakeIdleAction(IntakeSubsystem intakeSubsystem)
    {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);

        intakeDelay = IntakeConstants.IntakeIdleDelay;
    }

    /**
     * Initializes the action by stopping the intake.
     */
    @Override
    public void initialize()
    {
        startTime = System.currentTimeMillis();
        now = startTime;
    }

    /**
     * Checks if the subsystem state matches the expected state.
     * If not, marks the action as finished.
     */
    @Override
    public void execute()
    {
        if(!Container.isTeleop && intakeSubsystem.getLastState() == AllStates.IntakeStates.INTAKE)
        {
            if (now - startTime <= intakeDelay)
            {
                intakeSubsystem.intake();
            }
            else if (now - startTime <= 1.25 * intakeDelay)
            {
                intakeSubsystem.outtake();
            }
            else intakeSubsystem.idle();
            now = System.currentTimeMillis();
        }
        else intakeSubsystem.idle();
    }

    private boolean checkFinish()
    {
        return intakeSubsystem.getState() != AllStates.IntakeStates.IDLE;
    }

    /**
     * Checks if the action is finished.
     * The action finishes when the intake state is no longer IDLE.
     * @return true if the state has changed from IDLE, false otherwise.
     */
    @Override
    public boolean isFinished()
    {
        return checkFinish();
    }
}
