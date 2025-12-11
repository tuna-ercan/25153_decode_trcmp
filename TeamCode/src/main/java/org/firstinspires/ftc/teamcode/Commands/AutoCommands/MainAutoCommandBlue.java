package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
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

        driveAndShootP1 = new FollowPathAuto(drivetrain,paths.Shoot1).alongWith(theMachineSubsystem.prepP1Request()).withTimeout(1700)
                .andThen(theMachineSubsystem
                        .shootFromP1Request());
        addCommands(
                driveAndShootP1,
                new WaitCommand(2500),
                theMachineSubsystem.intakeRequest(),
                new FollowPathAuto(drivetrain,paths.Intake1),
                new WaitCommand(750),
                //new FollowPathAuto(drivetrain,paths.secondPathIntake11),
                //new WaitCommand(500),
                theMachineSubsystem.restRequest()
                //new WaitCommand(250),
                //driveAndShootP1
        );
    }

    public static class Paths {

        public PathChain Shoot1;
        public PathChain Intake1;

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
                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(132))
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
                    .setLinearHeadingInterpolation(Math.toRadians(132), Math.toRadians(30))
                    .build();
        }
    }
}
