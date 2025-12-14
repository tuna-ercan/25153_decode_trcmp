package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands.DriveToPosePID;
import org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands.DriveToShootP5;
import org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands.FollowPathAuto;
import org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants;
import org.firstinspires.ftc.teamcode.Constants.TheMachineConstants;
import org.firstinspires.ftc.teamcode.Positions.BluePositions;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MainAutoCommandBlue extends SequentialCommandGroup {

    private final TheMachineSubsystem theMachineSubsystem;
    private final DrivetrainSubsystem drivetrain;

    private Command driveAndShootP1_1;
    private Command driveAndShootP1_2;
    private Command driveAndShootP1_3;
    private Command driveAndShootP1_4;

    public MainAutoCommandBlue(TheMachineSubsystem theMachineSubsystem, DrivetrainSubsystem drivetrain) {
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
        driveAndShootP1_4 = new DriveToShootP5(drivetrain).alongWith(theMachineSubsystem.prepP5Request())
                        //.andThen(new DriveToPosePID(drivetrain, BluePositions.SHOOT_P5))
                .withTimeout(DrivetrainConstants.AimTimeOut*2)
                .andThen(theMachineSubsystem.shootFromPoseRequest());

        addCommands(
                driveAndShootP1_1,
                theMachineSubsystem.waitForFeederToFeed(),
                theMachineSubsystem.intakeRequest(),
                new FollowPathAuto(drivetrain,paths.Intake1),
                //new FollowPathAuto(drivetrain,paths.Lever),
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
                new WaitCommand(200)
                //,driveAndShootP1_4
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
                                    new Pose(61.335, 11.318),
                                    new Pose(62.943, 58.415),
                                    new Pose(54.800, 82.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(130))
                    .setTimeoutConstraint(TheMachineConstants.shootTimeoutConstraint)
                    .build();

            Intake1 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(54.800, 82.000),
                                    new Pose(57.358, 117.434),
                                    new Pose(22.642, 86.038)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(130), Math.toRadians(40))
                    .build();

            Lever = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(22.642, 86.038),
                                    new Pose(42.566, 83.472),
                                    new Pose(15.396, 79.094)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(40), Math.toRadians(90))
                    .setTimeoutConstraint(0)
                    .setTValueConstraint(0.8)
                    .build();

            Shoot2 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(15.396, 79.094),
                                    new Pose(47.094, 73.811),
                                    new Pose(54.800, 82.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(130))
                    .setTimeoutConstraint(TheMachineConstants.shootTimeoutConstraint)
                    .build();

            Intake2 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(54.800, 82.000),
                                    new Pose(49.509, 91.019),
                                    new Pose(20.226, 56.453)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(130), Math.toRadians(30))
                    .build();

            Shoot3 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(20.226, 56.453),
                                    new Pose(49.358, 65.057),
                                    new Pose(54.800, 82.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(30), Math.toRadians(130))
                    .setTimeoutConstraint(TheMachineConstants.shootTimeoutConstraint)
                    .build();

            Intake3 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(54.800, 82.000),
                                    new Pose(57.509, 54.189),
                                    new Pose(18.113, 34.113)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(130), Math.toRadians(30))
                    .build();

            Shoot4 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(18.113, 34.113),
                                    new Pose(32.302, 11.925),
                                    new Pose(54.340, 16.151)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(30), Math.toRadians(110.5))
                    .setTimeoutConstraint(TheMachineConstants.shootTimeoutConstraint)
                    .build();
        }
    }
}
