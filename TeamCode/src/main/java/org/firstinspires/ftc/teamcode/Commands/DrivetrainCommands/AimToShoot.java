package org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.BezierPoint;
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
 * Command to rotate the robot to aim at the shooting target.
 * Uses a zero-length path with heading interpolation.
 */
public class AimToShoot extends CommandBase {
    private final Supplier<PathChain> path;
    private final Pose focusPose;
    private final DrivetrainSubsystem m_drive;

    /**
     * Constructor for AimToShoot.
     * @param drive The DrivetrainSubsystem instance.
     */
    public AimToShoot(DrivetrainSubsystem drive)
    {
        this.m_drive = drive;

        focusPose =  (Container.isBlue ? BluePositions.SHOOT_FOCUS_POINT : RedPositions.SHOOT_FOCUS_POINT);

        path = () -> m_drive.pathBuilder() //Lazy Curve Generation
                .addPath(new Path(new BezierPoint(m_drive::getPose)))
                .setHeadingInterpolation(HeadingInterpolator.facingPoint(focusPose))
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
        if (!m_drive.isBusy() && !m_drive.headingReached())
        {
            m_drive.followPathTeleop(path.get());
        }
    }

    @Override
    public boolean isFinished()
    {
        return (!m_drive.isBusy() && m_drive.headingReached());
    }

    @Override
    public void end(boolean interrupted)
    {
        m_drive.startTeleopDrive();
    }
}