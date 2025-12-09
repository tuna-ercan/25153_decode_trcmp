package org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.HeadingInterpolator;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.robotcore.external.Supplier;
import org.firstinspires.ftc.teamcode.Container;
import org.firstinspires.ftc.teamcode.Positions.BluePositions;
import org.firstinspires.ftc.teamcode.Positions.RedPositions;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;

/**
 * Command to drive the robot to the test shooting point.
 * Uses PedroPathing to generate a path on the fly.
 */
public class DriveToTestPoint extends CommandBase {
    private final Supplier<PathChain> path;
    private final Pose goalPosition;
    private final Pose focusPose;
    private final DrivetrainSubsystem m_drive;

    /**
     * Constructor for DriveToTestPoint.
     * @param drive The DrivetrainSubsystem instance.
     */
    public DriveToTestPoint(DrivetrainSubsystem drive)
    {
        this.m_drive = drive;

        goalPosition = (Container.isBlue ? BluePositions.SHOOT_TEST : RedPositions.SHOOT_TEST);

        focusPose =  (Container.isBlue ? BluePositions.SHOOT_TEST_FOCUS_POINT : RedPositions.SHOOT_TEST_FOCUS_POINT);

        path = () -> m_drive.pathBuilder() //Lazy Curve Generation
                .addPath(new Path(new BezierLine(m_drive::getPose, goalPosition)))
                .setHeadingInterpolation(HeadingInterpolator.facingPoint(focusPose))
                .build();
    }

    @Override
    public void initialize()
    {
        m_drive.followPathTest(path.get());
    }

    @Override
    public void execute()
    {
        if (!m_drive.isBusy() && !m_drive.atPose(goalPosition))
        {
            m_drive.followPathTest(path.get());
        }
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