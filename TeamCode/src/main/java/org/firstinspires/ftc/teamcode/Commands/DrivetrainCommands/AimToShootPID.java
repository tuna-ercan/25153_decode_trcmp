package org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;

/**
 * Command to drive the robot to Shooting Position 1 (P1).
 * Uses PedroPathing to generate a path on the fly.
 */
public class AimToShootPID extends CommandBase {
    private  Pose goalPosition;
    private final DrivetrainSubsystem m_drive;

    private double atPoseCounter = 0;
    private double heading;

    /**
     * Constructor for DriveToShootP1.
     * @param drive The DrivetrainSubsystem instance.
     */
    public AimToShootPID(DrivetrainSubsystem drive, double heading)
    {
        this.m_drive = drive;
        this.heading = heading;
    }

    @Override
    public void execute()
    {
        goalPosition = m_drive.getPose().withHeading(heading);
        m_drive.driveToPosePID(goalPosition);
        if (m_drive.atPose(goalPosition)) atPoseCounter += 1;
        else atPoseCounter = 0;

    }

    public double getAtPoseCount()
    {
        return atPoseCounter;
    }

    public boolean checkPIDFinished()
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