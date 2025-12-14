package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands.DriveToShootP5;
import org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands.FollowPathAuto;
import org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants;
import org.firstinspires.ftc.teamcode.Constants.TheMachineConstants;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MainAutoCommandRed extends SequentialCommandGroup {

    private final TheMachineSubsystem theMachineSubsystem;
    private final DrivetrainSubsystem drivetrain;

    private Command driveAndShootP1_1;
    private Command driveAndShootP1_2;
    private Command driveAndShootP1_3;
    private Command driveAndShootP1_4;


    public MainAutoCommandRed(TheMachineSubsystem theMachineSubsystem, DrivetrainSubsystem drivetrain) {
        this.theMachineSubsystem = theMachineSubsystem;
        this.drivetrain = drivetrain;

        Paths paths = new Paths(drivetrain.getFollower());



        driveAndShootP1_1 = new DriveToShootP5(drivetrain).alongWith(theMachineSubsystem.prepP5Request()).withTimeout(DrivetrainConstants.AimTimeOut*2)
                .andThen(theMachineSubsystem
                    .shootFromPoseRequest());
        driveAndShootP1_2 = new DriveToShootP5(drivetrain).alongWith(theMachineSubsystem.prepP5Request()).withTimeout(DrivetrainConstants.AimTimeOut)
                .andThen(theMachineSubsystem
                        .shootFromPoseRequest());
        driveAndShootP1_3 = new DriveToShootP5(drivetrain).alongWith(theMachineSubsystem.prepP5Request()).withTimeout(DrivetrainConstants.AimTimeOut)
                .andThen(theMachineSubsystem
                        .shootFromPoseRequest());
        driveAndShootP1_4 = new DriveToShootP5(drivetrain).alongWith(theMachineSubsystem.prepP5Request()).withTimeout(DrivetrainConstants.AimTimeOut)
                .andThen(theMachineSubsystem.shootFromPoseRequest());

        addCommands(
                driveAndShootP1_1,
                theMachineSubsystem.waitForFeederToFeed(),
                new WaitCommand(250),
                theMachineSubsystem.intakeRequest(),
                new FollowPathAuto(drivetrain,paths.Intake1),
                new FollowPathAuto(drivetrain,paths.Lever),
                new WaitCommand(250),
                driveAndShootP1_2,
                theMachineSubsystem.waitForFeederToFeed(),
                theMachineSubsystem.intakeRequest(),
                new FollowPathAuto(drivetrain,paths.Intake2),
                new WaitCommand(200),
                driveAndShootP1_3,
                theMachineSubsystem.waitForFeederToFeed(),
                theMachineSubsystem.intakeRequest(),
                new FollowPathAuto(drivetrain,paths.Intake3),
                new WaitCommand(200),
                driveAndShootP1_4
                );
    }

    public static class Paths {

        public PathChain Shoot1;
        public PathChain Shoot2;
        public PathChain Shoot3;
        public PathChain Shoot4;

        public PathChain Intake1;
        public PathChain Intake2;
        public PathChain Intake3;
        public PathChain Lever;

        public Paths(Follower follower) {
            Shoot1 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(79.665 + DrivetrainConstants.RedXOffset, 11.319),
                                    new Pose(81.057 + DrivetrainConstants.RedXOffset, 58.415),
                                    new Pose(89.200 + DrivetrainConstants.RedXOffset, 82.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(46))
                    .build();

            Intake1 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(89.200 + DrivetrainConstants.RedXOffset, 82.000),
                                    new Pose(100.226 + DrivetrainConstants.RedXOffset, 113.962),
                                    new Pose(121.509 + DrivetrainConstants.RedXOffset, 88.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(46), Math.toRadians(130))
                    .build();

            Lever = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(121.509 + DrivetrainConstants.RedXOffset, 88.000),
                                    new Pose(101.434 + DrivetrainConstants.RedXOffset, 83.472),
                                    new Pose(128.604 + DrivetrainConstants.RedXOffset, 79.094)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(130), Math.toRadians(90))
                    .build();

            Shoot2 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(128.604 + DrivetrainConstants.RedXOffset, 79.094),
                                    new Pose(81.057 + DrivetrainConstants.RedXOffset, 73.811),
                                    new Pose(89.200 + DrivetrainConstants.RedXOffset, 82.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(44))
                    .build();

            Intake2 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(89.200 + DrivetrainConstants.RedXOffset, 82.000),
                                    new Pose(98.472 + DrivetrainConstants.RedXOffset, 100.830),
                                    new Pose(121.057 + DrivetrainConstants.RedXOffset, 61.736)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(44), Math.toRadians(120))
                    .build();

            Shoot3 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(121.057 + DrivetrainConstants.RedXOffset, 61.736),
                                    new Pose(81.057 + DrivetrainConstants.RedXOffset, 65.057),
                                    new Pose(89.200 + DrivetrainConstants.RedXOffset, 82.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(120), Math.toRadians(44))
                    .build();

            Intake3 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(89.200 + DrivetrainConstants.RedXOffset, 82.000),
                                    new Pose(114.566 + DrivetrainConstants.RedXOffset, 65.358),
                                    new Pose(119.547 + DrivetrainConstants.RedXOffset, 34.415)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(44), Math.toRadians(120))
                    .build();

            Shoot4 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(119.547 + DrivetrainConstants.RedXOffset, 34.415),
                                    new Pose(110.642 + DrivetrainConstants.RedXOffset, 17.358),
                                    new Pose(89.200 + DrivetrainConstants.RedXOffset, 16.151)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(120), Math.toRadians(69.5)).build();
        }
    }
    }


