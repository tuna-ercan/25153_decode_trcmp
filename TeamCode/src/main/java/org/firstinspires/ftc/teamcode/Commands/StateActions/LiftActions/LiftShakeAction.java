package org.firstinspires.ftc.teamcode.Commands.StateActions.LiftActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.LiftSubsystem;

/**
 * Action command to shake the Lift mechanism.
 */
public class LiftShakeAction extends CommandBase {

    private final LiftSubsystem liftSubsystem;
    private boolean isFinished = false;

    /**
     * Constructor for LiftShakeAction.
     * @param liftSubsystem The lift subsystem instance.
     */
    public LiftShakeAction(LiftSubsystem liftSubsystem)
    {
        addRequirements(liftSubsystem);
        this.liftSubsystem = liftSubsystem;
    }

    @Override
    public void initialize() {
        liftSubsystem.shake();
    }

    @Override
    public void execute() {
        if(!(liftSubsystem.getState() == AllStates.LiftStates.SHAKE)) isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
