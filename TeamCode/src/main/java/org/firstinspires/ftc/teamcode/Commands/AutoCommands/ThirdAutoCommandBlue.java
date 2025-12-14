package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands.DriveToPosePathFocus;
import org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands.DriveToPosePathPid;
import org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants;
import org.firstinspires.ftc.teamcode.Positions.BluePositions;
import org.firstinspires.ftc.teamcode.Positions.RedPositions;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class ThirdAutoCommandBlue extends SequentialCommandGroup {

    private final TheMachineSubsystem theMachineSubsystem;
    private final DrivetrainSubsystem drivetrain;

    private Command driveAndShootP4;
    private Command driveAndShootP1_2;
    private Command driveAndShootP1_3;
    private Command driveAndShootP1_4;

    public ThirdAutoCommandBlue(TheMachineSubsystem theMachineSubsystem, DrivetrainSubsystem drivetrain) {
        this.theMachineSubsystem = theMachineSubsystem;
        this.drivetrain = drivetrain;

        driveAndShootP4 = new DriveToPosePathFocus(drivetrain,BluePositions.SHOOT_P4_2).alongWith(theMachineSubsystem.prepP4Request()).withTimeout(DrivetrainConstants.AimTimeOut*2)
                .andThen(theMachineSubsystem
                    .shootFromP4Request());

        addCommands(
                new DriveToPosePathPid(drivetrain, BluePositions.SHOOT_P4_2),
                driveAndShootP4,
                theMachineSubsystem.waitForFeederToFeed(),
                theMachineSubsystem.intakeRequest(),
                new DriveToPosePathPid(drivetrain, BluePositions.SHOOT_P4_2.withHeading(0)).withTimeout(3000),
                new WaitCommand(250),
                new DriveToPosePathPid(drivetrain, RedPositions.ZERO_POSE.withHeading(0).withX(5)).withTimeout(3000),
                new DriveToPosePathPid(drivetrain, BluePositions.SHOOT_P4_2),
                driveAndShootP4,
                theMachineSubsystem.waitForFeederToFeed(),
                new DriveToPosePathPid(drivetrain, BluePositions.START_POSE.withX(35)),
                theMachineSubsystem.getShooterSubsystem().zeroRequest()
        );

    }

}
