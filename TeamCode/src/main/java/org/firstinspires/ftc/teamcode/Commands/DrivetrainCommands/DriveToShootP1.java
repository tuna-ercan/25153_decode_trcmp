package org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.HeadingInterpolator;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.robotcore.external.Supplier;
import org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants;
import org.firstinspires.ftc.teamcode.Container;
import org.firstinspires.ftc.teamcode.Positions.BluePositions;
import org.firstinspires.ftc.teamcode.Positions.RedPositions;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;

/**
 * Command to drive the robot to Shooting Position 1 (P1).
 * Uses PedroPathing to generate a path on the fly.
 */
public class DriveToShootP1 extends CommandBase {
    private final Supplier<PathChain> path;
    private final Pose goalPosition;
    private final Pose focusPose;
    private final DrivetrainSubsystem m_drive;

    /**
     * Constructor for DriveToShootP1.
     * @param drive The DrivetrainSubsystem instance.
     */
    public DriveToShootP1(DrivetrainSubsystem drive)
    {
        this.m_drive = drive;

        goalPosition = (Container.isBlue ? BluePositions.SHOOT_P1 : RedPositions.SHOOT_P1);

        focusPose =  (Container.isBlue ? BluePositions.SHOOT_FOCUS_POINT : RedPositions.SHOOT_FOCUS_POINT);

        path = () -> m_drive.pathBuilder() //Lazy Curve Generation
                .addPath(new Path(new BezierLine(m_drive::getPose, goalPosition)))
                 .setHeadingInterpolation(HeadingInterpolator.linearFromPoint(m_drive.getFollower()::getHeading, goalPosition.getHeading(), DrivetrainConstants.autoDriveInterpolator))
                .setBrakingStrength(DrivetrainConstants.driveBrakingStrength)
                .build();
    }

    @Override
    public void initialize()
    {
        m_drive.followPathTeleop(path.get());
    }

    @Override
    public void execute()
    {
    }

    @Override
    public boolean isFinished()
    {
        return (m_drive.atPose(goalPosition) && m_drive.headingReached());
    }

    @Override
    public void end(boolean interrupted)
    {
        m_drive.startTeleopDrive();
    }
}