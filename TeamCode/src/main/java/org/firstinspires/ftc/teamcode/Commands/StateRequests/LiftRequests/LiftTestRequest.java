package org.firstinspires.ftc.teamcode.Commands.StateRequests.LiftRequests;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Subsystems.LiftSubsystem;

public class LiftTestRequest extends CommandBase
{

    private final LiftSubsystem liftSubsystem;
    private boolean isFinished;

    public LiftTestRequest(LiftSubsystem liftSubsystem)
    {
        this.liftSubsystem = liftSubsystem;
    }

    @Override
    public void initialize()
    {
        liftSubsystem.setState(AllStates.LiftStates.TEST);
        isFinished = true;
    }

    @Override
    public boolean isFinished()
    {
        return isFinished;
    }
}
