package org.firstinspires.ftc.teamcode.Commands.StateActions.LiftActions;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.LiftSubsystem;

public class LiftShakeAction extends CommandBase {

    private final LiftSubsystem liftSubsystem;
    private boolean isFinished = false;

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
