package org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.HeadingInterpolator;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.robotcore.external.Supplier;
import org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants;
import org.firstinspires.ftc.teamcode.Constants.TheMachineConstants;
import org.firstinspires.ftc.teamcode.Container;
import org.firstinspires.ftc.teamcode.Positions.BluePositions;
import org.firstinspires.ftc.teamcode.Positions.RedPositions;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;

/**
 * Command to drive the robot to Shooting Position 4 (P4).
 * Uses PedroPathing to generate a path on the fly.
 */
public class DriveToShootP4 extends SequentialCommandGroup {
    private final Pose goalPosition;
    private final DrivetrainSubsystem m_drive;

    /**
     * Constructor for DriveToShootP4.
     * @param drive The DrivetrainSubsystem instance.
     */
    public DriveToShootP4(DrivetrainSubsystem drive)
    {
        this.m_drive = drive;

        goalPosition = (Container.isBlue ? BluePositions.SHOOT_P4 : RedPositions.SHOOT_P4);

        addCommands(
                new DriveToPosePathPid(m_drive, goalPosition)
        );

    }


    @Override
    public void end(boolean interrupted)
    {
        m_drive.startTeleopDrive();
    }
}