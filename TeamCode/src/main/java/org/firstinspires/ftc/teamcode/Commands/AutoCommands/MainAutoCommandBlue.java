package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands.FollowPathAuto;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MainAutoCommandBlue extends SequentialCommandGroup {

    private final TheMachineSubsystem theMachineSubsystem;
    private final DrivetrainSubsystem drivetrain;

    private Command driveAndShootP1_1;
    private Command driveAndShootP1_2;
    private Command driveAndShootP1_3;

    public MainAutoCommandBlue(TheMachineSubsystem theMachineSubsystem, DrivetrainSubsystem drivetrain) {
        this.theMachineSubsystem = theMachineSubsystem;
        this.drivetrain = drivetrain;

        Paths paths = new Paths(drivetrain.getFollower());

        driveAndShootP1_1 = new FollowPathAuto(drivetrain,paths.Shoot1).alongWith(theMachineSubsystem.prepP1Request()).withTimeout(1700)
                .andThen(theMachineSubsystem
                        .shootFromP1Request());

        driveAndShootP1_2 = new FollowPathAuto(drivetrain,paths.Shoot2).alongWith(theMachineSubsystem.prepP1Request()).withTimeout(1700)
                .andThen(theMachineSubsystem
                        .shootFromP1Request());
        driveAndShootP1_3 = new FollowPathAuto(drivetrain,paths.Shoot3).alongWith(theMachineSubsystem.prepP1Request()).withTimeout(1700)
                .andThen(theMachineSubsystem
                        .shootFromP1Request());
        addCommands(
                driveAndShootP1_1,
                new WaitCommand(2500),
                theMachineSubsystem.intakeRequest(),
                new FollowPathAuto(drivetrain,paths.Intake1),
                new WaitCommand(350).alongWith(theMachineSubsystem.idleRequest()),
                new FollowPathAuto(drivetrain,paths.Lever),
                new WaitCommand(500),
                driveAndShootP1_2,
                new WaitCommand(2500),
                theMachineSubsystem.intakeRequest(),
                new FollowPathAuto(drivetrain,paths.Intake2),
                new WaitCommand(350).alongWith(theMachineSubsystem.idleRequest()),
                driveAndShootP1_3
                );
    }

    public static class Paths {

        public PathChain Shoot1;
        public PathChain Shoot2;
        public PathChain Shoot3;

        public PathChain Intake1;
        public PathChain Intake2;
        public PathChain Lever;

        public Paths(Follower follower) {
            Shoot1 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(61.335, 11.318),
                                    new Pose(62.943, 58.415),
                                    new Pose(47.700, 95.300)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(134))
                    .build();

            Intake1 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(47.700, 95.300),
                                    new Pose(57.811, 108.226),
                                    new Pose(22.491, 85.736)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(134), Math.toRadians(30))
                    .build();

            Lever = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(22.491, 85.736),
                                    new Pose(42.566, 83.472),
                                    new Pose(15.396, 79.094)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(30), Math.toRadians(90))
                    .build();

            Shoot2 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(15.396, 79.094),
                                    new Pose(47.094, 73.811),
                                    new Pose(47.700, 95.300)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(134))
                    .build();

            Intake2 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(47.700, 95.300),
                                    new Pose(48.755, 75.623),
                                    new Pose(19.472, 59.170)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(134), Math.toRadians(30))
                    .build();

            Shoot3 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(19.472, 59.170),
                                    new Pose(52.981, 63.396),
                                    new Pose(47.700, 95.300)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(30), Math.toRadians(134))
                    .build();
        }
    }
}
