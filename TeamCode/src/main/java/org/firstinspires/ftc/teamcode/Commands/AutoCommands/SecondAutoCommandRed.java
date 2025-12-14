package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands.DriveToPosePath;
import org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands.DriveToPosePathFocus;
import org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands.DriveToPosePathPid;
import org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands.DriveToShootP4;
import org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants;
import org.firstinspires.ftc.teamcode.Constants.TheMachineConstants;
import org.firstinspires.ftc.teamcode.Positions.BluePositions;
import org.firstinspires.ftc.teamcode.Positions.RedPositions;
import org.firstinspires.ftc.teamcode.Positions.RedPositions;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class SecondAutoCommandRed extends SequentialCommandGroup {

    private final TheMachineSubsystem theMachineSubsystem;
    private final DrivetrainSubsystem drivetrain;

    private Command driveAndShootP4;
    private Command driveAndShootP4_2;
    private Command driveAndShootP1_2;
    private Command driveAndShootP1_3;
    private Command driveAndShootP1_4;

    public PathChain Shoot1;


    public SecondAutoCommandRed(TheMachineSubsystem theMachineSubsystem, DrivetrainSubsystem drivetrain) {
        this.theMachineSubsystem = theMachineSubsystem;
        this.drivetrain = drivetrain;

        driveAndShootP4 = new DriveToPosePathFocus(drivetrain,RedPositions.SHOOT_P4_2).alongWith(theMachineSubsystem.prepP4Request()).withTimeout(DrivetrainConstants.AimTimeOut*2)
                .andThen(theMachineSubsystem
                        .shootFromPoseRequest());;


        Shoot1 = drivetrain.getFollower()
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(84.000, 20.000),
                                new Pose(106.113, 24.151),
                                new Pose(132.000, 9.057)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(180))
                .build();


        addCommands(
                new DriveToPosePathPid(drivetrain, RedPositions.SHOOT_P4_2),
                driveAndShootP4,
                theMachineSubsystem.waitForFeederToFeed(),
                new DriveToPosePathPid(drivetrain, RedPositions.SHOOT_P4_2.withHeading(Math.PI)).withTimeout(3000),
                new WaitCommand(250),
                new DriveToPosePathPid(drivetrain, BluePositions.ZERO_POSE.withHeading(Math.PI)).withTimeout(3000),
                new DriveToPosePathPid(drivetrain, RedPositions.SHOOT_P4_2),
                driveAndShootP4,
                theMachineSubsystem.waitForFeederToFeed(),
                new DriveToPosePathPid(drivetrain, RedPositions.START_POSE.withX(110)),
                theMachineSubsystem.getShooterSubsystem().zeroRequest()
        );
    }

}
