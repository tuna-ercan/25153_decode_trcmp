package org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.HeadingInterpolator;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.robotcore.external.Supplier;
import org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants;
import org.firstinspires.ftc.teamcode.Constants.TheMachineConstants;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;

/**
 * Command to drive the robot to Shooting Position 1 (P1).
 * Uses PedroPathing to generate a path on the fly.
 */
public class DriveToPosePath extends CommandBase {
    private final Supplier<PathChain> path;
    private final Pose goalPosition;
    private final DrivetrainSubsystem m_drive;

    /**
     * Constructor for DriveToShootP1.
     * @param drive The DrivetrainSubsystem instance.
     */
    public DriveToPosePath(DrivetrainSubsystem drive, Pose goalPosition, double TValue)
    {
        this.m_drive = drive;

        this.goalPosition = goalPosition;

        path = () -> m_drive.pathBuilder() //Lazy Curve Generation
                .addPath(new Path(new BezierLine(m_drive::getPose, goalPosition)))
                 .setHeadingInterpolation(HeadingInterpolator.linearFromPoint(m_drive.getFollower()::getHeading, goalPosition.getHeading(), DrivetrainConstants.autoDriveInterpolator))
                .setBrakingStrength(DrivetrainConstants.driveBrakingStrength)
                .setTimeoutConstraint(TheMachineConstants.shootTimeoutConstraint)
                .setTValueConstraint(TValue)
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
        if (!m_drive.isBusy() && !m_drive.atPoseCoarse(goalPosition))
        {
            m_drive.followPathTeleop(path.get());
        }
    }

    @Override
    public boolean isFinished()
    {
        return (m_drive.atPoseCoarse(goalPosition));
    }

    @Override
    public void end(boolean interrupted)
    {
        m_drive.startTeleopDrive();
    }
}