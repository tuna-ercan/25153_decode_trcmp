package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Constants.TheMachineConstants;
import org.firstinspires.ftc.teamcode.Container;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

public class UpdatePoseCommand extends CommandBase {
    DrivetrainSubsystem drivetrainSubsystem;
    public UpdatePoseCommand(DrivetrainSubsystem drivetrainSubsystem){
        this.drivetrainSubsystem = drivetrainSubsystem;
    }

    @Override
    public void execute() {
        if (Container.isTeleop){
        Container.autoEndPose = drivetrainSubsystem.getPose();
        }
    }
}
