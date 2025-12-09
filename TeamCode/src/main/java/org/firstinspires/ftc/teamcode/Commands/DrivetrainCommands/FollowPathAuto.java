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

public class FollowPathAuto extends CommandBase {
    private final PathChain path;
    private final DrivetrainSubsystem m_drive;
    public FollowPathAuto(DrivetrainSubsystem drive, PathChain path)
    {
        this.m_drive = drive;
        this.path = path;
    }

    @Override
    public void initialize()
    {
        m_drive.followPathAuto(path);
    }

    @Override
    public void execute()
    {
        super.execute();
    }

    @Override
    public boolean isFinished()
    {
        return !m_drive.isBusy();
    }

}