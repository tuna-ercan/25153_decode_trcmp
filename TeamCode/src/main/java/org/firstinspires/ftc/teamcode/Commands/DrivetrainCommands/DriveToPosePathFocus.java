package org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;

/**
 * Command to drive the robot to Shooting Position 4 (P4).
 * Uses PedroPathing to generate a path on the fly.
 */
public class DriveToPosePathFocus extends SequentialCommandGroup {
    private final Pose goalPosition;
    private final DrivetrainSubsystem m_drive;

    /**
     * Constructor for DriveToShootP4.
     * @param drive The DrivetrainSubsystem instance.
     */
    public DriveToPosePathFocus(DrivetrainSubsystem drive, Pose goalPosition)
    {
        this.m_drive = drive;

        this.goalPosition = goalPosition;

        addCommands(
                new DriveToPoseAuto(m_drive, goalPosition),
                new AimToFocus(drive)
        );

    }


    @Override
    public void end(boolean interrupted)
    {
        m_drive.startTeleopDrive();
    }
}