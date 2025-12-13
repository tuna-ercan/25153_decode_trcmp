package org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants;
import org.firstinspires.ftc.teamcode.Constants.ShooterConstants;
import org.firstinspires.ftc.teamcode.Container;
import org.firstinspires.ftc.teamcode.Positions.BluePositions;
import org.firstinspires.ftc.teamcode.Positions.RedPositions;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;

/**
 * Command to drive the robot to Shooting Position 1 (P1).
 * Uses PedroPathing to generate a path on the fly.
 */
public class AimToFocus extends CommandBase {
    private final DrivetrainSubsystem m_drive;
    private Pose focus;
    private double atPoseCounter = 0;

    /**
     * Constructor for DriveToShootP1.
     * @param drive The DrivetrainSubsystem instance.
     */
    public AimToFocus(DrivetrainSubsystem drive)
    {
        this.m_drive = drive;
        this.focus = (Container.isBlue ? BluePositions.FOCUS_POINT : RedPositions.FOCUS_POINT);
    }

    @Override
    public void execute()
    {
        double heading = calculateHeading();
        if (Container.isBlue) this.focus = focus.withY(focus.getY()- ShooterConstants.getBlueFocusPointOffset(heading));
        else  this.focus = focus.withY(focus.getY()- ShooterConstants.getRedFocusPointOffset(heading));
        m_drive.turnToPID(heading);

        if (m_drive.atHeading(heading)) atPoseCounter += 1;
        else atPoseCounter = 0;
    }

    private double calculateHeading()
    {
        Pose robotPose = m_drive.getPose();
        double x = focus.getX() - robotPose.getX();
        double y = focus.getY() - robotPose.getY();

        return Math.atan2(y, x);
    }

    private double getAtPoseCount()
    {
        return atPoseCounter;
    }

    private boolean checkPIDFinished()
    {
        return  (getAtPoseCount() >= DrivetrainConstants.atPoseCounterLimit);
        //        return m_drive.atPose(goalPosition);
    }

    @Override
    public boolean isFinished()
    {
        return checkPIDFinished();
    }

    @Override
    public void end(boolean interrupted)
    {
        m_drive.startTeleopDrive();
    }
}