package org.firstinspires.ftc.teamcode.Commands.StateActions.LiftActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.LiftSubsystem;

/**
 * Action command to move the Lift to the OPEN position.
 */
public class LiftOpenAction extends CommandBase {

    private final LiftSubsystem liftSubsystem;
    private boolean isFinished = false;

    /**
     * Constructor for LiftOpenAction.
     * @param liftSubsystem The lift subsystem instance.
     */
    public LiftOpenAction(LiftSubsystem liftSubsystem)
    {
        addRequirements(liftSubsystem);
        this.liftSubsystem = liftSubsystem;
    }

    @Override
    public void initialize() {
        liftSubsystem.open();
    }

    @Override
    public void execute() {
        if(!(liftSubsystem.getState() == AllStates.LiftStates.OPEN)) isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
