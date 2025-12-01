package org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.Constants.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

/**
 * Action command to set the Machine subsystem to the IDLE state.
 * This command stops the machine and runs until the state changes.
 */
public class MachineIdleAction extends ParallelCommandGroup
{

    private final TheMachineSubsystem theMachineSubsystem;
    private boolean isFinished = false;

    /**
     * Constructor for MachineIdleAction.
     * @param theMachineSubsystem The machine subsystem instance.
     */
    public MachineIdleAction(TheMachineSubsystem theMachineSubsystem)
    {
        super(
                theMachineSubsystem.funnelRequest(AllStates.FunnelStates.HOME),
                theMachineSubsystem.intakeRequest(AllStates.IntakeStates.IDLE),
                theMachineSubsystem.liftRequest(AllStates.LiftStates.HOME),
                theMachineSubsystem.shooterRequest(AllStates.ShooterStates.ZERO)
        );

        this.theMachineSubsystem = theMachineSubsystem;
        addRequirements(theMachineSubsystem);
    }

    /**
     * Initializes the action by stopping the machine.
     */
    @Override
    public void initialize()
    {
    }

    /**
     * Checks if the subsystem state matches the expected state.
     * If not, marks the action as finished.
     */
    @Override
    public void execute()
    {
        if(!(theMachineSubsystem.getState() == AllStates.MachineStates.IDLE)) isFinished = true;
    }

    /**
     * Checks if the action is finished.
     * The action finishes when the machine state is no longer IDLE.
     * @return true if the state has changed from IDLE, false otherwise.
     */
    @Override
    public boolean isFinished()
    {
        return isFinished;
    }
}
