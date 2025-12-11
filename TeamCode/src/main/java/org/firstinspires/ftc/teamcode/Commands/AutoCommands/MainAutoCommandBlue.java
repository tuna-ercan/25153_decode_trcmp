package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import static org.firstinspires.ftc.teamcode.Utils.AllStates.MachineStates.PREP_P1;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands.FollowPathAuto;
import org.firstinspires.ftc.teamcode.Positions.BluePositions;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MainAutoCommandBlue extends SequentialCommandGroup {

    private final TheMachineSubsystem theMachineSubsystem;
    private final DrivetrainSubsystem drivetrain;

    private Command driveAndShootP1;

    public MainAutoCommandBlue(TheMachineSubsystem theMachineSubsystem, DrivetrainSubsystem drivetrain) {
        this.theMachineSubsystem = theMachineSubsystem;
        this.drivetrain = drivetrain;

        Paths paths = new Paths(drivetrain.getFollower());

        driveAndShootP1 = new FollowPathAuto(drivetrain,paths.firstShootP1).alongWith(theMachineSubsystem.prepP1Request()).withTimeout(1700)
                .andThen(theMachineSubsystem
                        .shootFromP1Request());
        addCommands(
                driveAndShootP1,
                theMachineSubsystem.intakeRequest(),
                new FollowPathAuto(drivetrain,paths.secondPathIntake10),
                new FollowPathAuto(drivetrain,paths.secondPathIntake11)
        );
    }

    public static class Paths {

        public PathChain firstShootP1;
        public PathChain secondPathIntake10;
        public PathChain secondPathIntake11;

        public Paths(Follower follower) {
            firstShootP1 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    BluePositions.START_POSE,
                                    new Pose(70.236, 65.195),
                                    new Pose(47.700, 95.300)
                            )
                    )
                    .setLinearHeadingInterpolation(
                            Math.toRadians(90),
                            Math.toRadians(127.353251)
                    )
                    .build();

            secondPathIntake10 = follower
                    .pathBuilder()
                    .addPath(
                                new BezierLine(new Pose(56.000, 36.000), new Pose(54.949, 74.828))
                    )
                    .setTangentHeadingInterpolation()
                    .build();
            secondPathIntake10 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierCurve(
                                    new Pose(47.700, 95.300),
                                    new Pose(43.183, 87.543),
                                    new Pose(35.118, 87.039)
                            )
                    )
                    .setLinearHeadingInterpolation(
                            Math.toRadians(127.353251),
                            Math.toRadians(30)
                    )
                    .build();

            secondPathIntake11 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(35.118, 87.039), new Pose(15.291, 81.998))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(30), Math.toRadians(30))
                    .build();
        }
    }
}
