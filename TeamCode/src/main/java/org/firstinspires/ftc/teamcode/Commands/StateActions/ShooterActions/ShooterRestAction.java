package org.firstinspires.ftc.teamcode.Commands.StateActions.ShooterActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants.ShooterConstants;
import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

/**
 * Action command to set the Shooter subsystem to the REST state.
 * Maintains the rest state logic during execution.
 */
public class ShooterRestAction extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;

    private long now;
    private long startTime;
    private final long shooterRestDelay;

    /**
     * Constructor for ShooterRestAction.
     * @param shooterSubsystem The shooter subsystem instance.
     */
    public ShooterRestAction(ShooterSubsystem shooterSubsystem) {
        addRequirements(shooterSubsystem);
        this.shooterSubsystem = shooterSubsystem;
        this.shooterRestDelay = ShooterConstants.ShooterRestDelay;
    }

    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
        now = startTime;
        //shooterSubsystem.rest();
    }

    @Override
    public void execute() {
        if (now - startTime <= shooterRestDelay)
        {
            shooterSubsystem.shootFromPose();
        }
        else  shooterSubsystem.rest();
        now = System.currentTimeMillis();
    }

    private boolean checkFinish()
    {
        return shooterSubsystem.getState() != AllStates.ShooterStates.REST;
    }

    @Override
    public boolean isFinished() {
        return checkFinish();
    }
}
