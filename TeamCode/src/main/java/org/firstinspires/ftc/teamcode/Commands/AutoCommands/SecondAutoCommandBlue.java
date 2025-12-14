package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands.DriveToPosePID;
import org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands.DriveToPosePathFocus;
import org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands.DriveToPosePathPid;
import org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands.DriveToShootP4;
import org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands.DriveToShootP5;
import org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands.FollowPathAuto;
import org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants;
import org.firstinspires.ftc.teamcode.Constants.TheMachineConstants;
import org.firstinspires.ftc.teamcode.Positions.BluePositions;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class SecondAutoCommandBlue extends SequentialCommandGroup {

    private final TheMachineSubsystem theMachineSubsystem;
    private final DrivetrainSubsystem drivetrain;

    private Command driveAndShootP4;
    private Command driveAndShootP1_2;
    private Command driveAndShootP1_3;
    private Command driveAndShootP1_4;

    public SecondAutoCommandBlue(TheMachineSubsystem theMachineSubsystem, DrivetrainSubsystem drivetrain) {
        this.theMachineSubsystem = theMachineSubsystem;
        this.drivetrain = drivetrain;

        driveAndShootP4 = new DriveToPosePathFocus(drivetrain,BluePositions.SHOOT_P4_2).alongWith(theMachineSubsystem.prepP4Request()).withTimeout(DrivetrainConstants.AimTimeOut*2)
                .andThen(theMachineSubsystem
                    .shootFromPoseRequest());;

        addCommands(
                new DriveToPosePathPid(drivetrain, BluePositions.SHOOT_P4_2),
                driveAndShootP4,
                theMachineSubsystem.waitForFeederToFeed(),
                new WaitCommand(250),
                new DriveToPosePathPid(drivetrain, BluePositions.START_POSE.withX(32)),
                theMachineSubsystem.idleRequest()
                );
    }

}
