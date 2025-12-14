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

public class SecondAutoCommandRedReal extends SequentialCommandGroup {

    private final TheMachineSubsystem theMachineSubsystem;
    private final DrivetrainSubsystem drivetrain;

    private Command driveAndShootP4;
    private Command driveAndShootP1_2;
    private Command driveAndShootP1_3;
    private Command driveAndShootP1_4;

    public SecondAutoCommandRedReal(TheMachineSubsystem theMachineSubsystem, DrivetrainSubsystem drivetrain) {
        this.theMachineSubsystem = theMachineSubsystem;
        this.drivetrain = drivetrain;

        driveAndShootP4 = new DriveToPosePathFocus(drivetrain,RedPositions.SHOOT_P4_2).alongWith(theMachineSubsystem.prepP4Request()).withTimeout(DrivetrainConstants.AimTimeOut*2)
                .andThen(theMachineSubsystem
                    .shootFromP4Request());

        addCommands(
                new DriveToPosePathPid(drivetrain, RedPositions.SHOOT_P4_2),
                driveAndShootP4,
                theMachineSubsystem.waitForFeederToFeed(),
                new WaitCommand(250),
                new DriveToPosePathPid(drivetrain, RedPositions.START_POSE.withX(110)),
                theMachineSubsystem.idleRequest()
                );
    }

}
