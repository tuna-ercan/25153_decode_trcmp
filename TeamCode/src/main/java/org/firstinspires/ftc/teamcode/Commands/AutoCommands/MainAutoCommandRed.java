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



        driveAndShootP1_1 = new DriveToShootP5(drivetrain).alongWith(theMachineSubsystem.prepP5Request())
                .andThen(theMachineSubsystem
                    .shootFromP5Request());
        driveAndShootP1_2 = new DriveToShootP5(drivetrain).alongWith(theMachineSubsystem.prepP5Request())
                .andThen(theMachineSubsystem
                        .shootFromP5Request());
        driveAndShootP1_3 = new DriveToShootP5(drivetrain).alongWith(theMachineSubsystem.prepP5Request())
                .andThen(theMachineSubsystem
                        .shootFromP5Request());
        driveAndShootP1_4 = new DriveToShootP5(drivetrain).alongWith(theMachineSubsystem.prepP5Request())
                .andThen(theMachineSubsystem.shootFromP5Request());

        addCommands(
                driveAndShootP1_1,
                theMachineSubsystem.waitForFeederToFeed(),
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
            Intake1 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(89.2, 82.000),
                                    new Pose(86.642, 117.434),
                                    new Pose(121.358, 86.038)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(40), Math.toRadians(310)) //310 = -50 degreed
                    .build();

            Lever = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(121.358, 86.038),
                                    new Pose(101.434, 83.472),
                                    new Pose(128.604, 79.094)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(310), Math.toRadians(0))
                    .setTimeoutConstraint(0)
                    .setTValueConstraint(0.8)
                    .build();

            Intake2 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(89.2, 82.000),
                                    new Pose(94.491, 91.019),
                                    new Pose(123.774, 56.453)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(40), Math.toRadians(300)) //300 = -60
                    .build();

            Intake3 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(89.2, 82.000),
                                    new Pose(86.491, 54.189),
                                    new Pose(125.887, 34.113)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(40), Math.toRadians(300)) //300 = -60
                    .build();

        }
    }
}
