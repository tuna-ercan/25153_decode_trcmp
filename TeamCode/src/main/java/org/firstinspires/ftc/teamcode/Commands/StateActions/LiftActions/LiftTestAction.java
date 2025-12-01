package org.firstinspires.ftc.teamcode.Commands.StateActions.LiftActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.LiftSubsystem;

/**
 * Action command to run the Lift subsystem in TEST mode.
 */
public class LiftTestAction extends CommandBase
{

    private final LiftSubsystem liftSubsystem;
    private boolean isFinished = false;

    /**
     * Constructor for LiftTestAction.
     * @param liftSubsystem The lift subsystem instance.
     */
    public LiftTestAction(LiftSubsystem liftSubsystem)
    {
        this.liftSubsystem = liftSubsystem;
        addRequirements(liftSubsystem);
    }

    @Override
    public void initialize()
    {
        liftSubsystem.test();
    }

    @Override
    public void execute()
    {
        if(!(liftSubsystem.getState() == AllStates.LiftStates.TEST)) isFinished = true;
    }

    @Override
    public boolean isFinished()
    {
        return isFinished;
    }
}
