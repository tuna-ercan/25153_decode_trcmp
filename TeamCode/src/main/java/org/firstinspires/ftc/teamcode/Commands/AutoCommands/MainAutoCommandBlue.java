package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import static org.firstinspires.ftc.teamcode.Utils.AllStates.MachineStates.PREP_P1;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands.FollowPathAuto;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class MainAutoCommandBlue extends SequentialCommandGroup {

    private final TheMachineSubsystem theMachineSubsystem;
    private final DrivetrainSubsystem drivetrain;

    public MainAutoCommandBlue(TheMachineSubsystem theMachineSubsystem, DrivetrainSubsystem drivetrain) {
        this.theMachineSubsystem = theMachineSubsystem;
        this.drivetrain = drivetrain;

        Paths paths = new Paths(drivetrain.getFollower());

        addCommands(
                theMachineSubsystem.prepP1Request(),
                new FollowPathAuto(drivetrain, paths.firstPathShootP1)
        );
    }

    public static class Paths {

        public PathChain firstPathShootP1;
        public PathChain secondPathIntake10;

        public Paths(Follower follower) {
            firstPathShootP1 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(56.000, 8.000), new Pose(56.000, 36.000))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(180))
                    .build();

            secondPathIntake10 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(56.000, 36.000), new Pose(54.949, 74.828))
                    )
                    .setTangentHeadingInterpolation()
                    .build();
        }
    }
}
