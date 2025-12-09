package org.firstinspires.ftc.teamcode.Utils;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Positions.BluePositions;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.FunnelSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.TelemetryManager;


public class Mouth {

    private final TheMachineSubsystem machineSubsystem;
    private final DrivetrainSubsystem drivetrainSubsystem;
    private final IntakeSubsystem intakeSubsystem;
    private final FunnelSubsystem funnelSubsystem;
    private final ShooterSubsystem shooterSubsystem;

    private final TelemetryManager.TelemetryWrapper panelsTelemetry;
    private final JoinedTelemetry joinedTelemetry;

    private final CommandScheduler scheduler;

    public Mouth(TheMachineSubsystem machineSubsystem, DrivetrainSubsystem drivetrainSubsystem, Telemetry telemetry)
    {
        this.machineSubsystem = machineSubsystem;
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.intakeSubsystem = machineSubsystem.getIntakeSubsystem();
        this.funnelSubsystem = machineSubsystem.getFunnelSubsystem();
        this.shooterSubsystem = machineSubsystem.getShooterSubsystem();

        PanelsFieldDrawing.init();

        panelsTelemetry = PanelsTelemetry.INSTANCE.getFtcTelemetry();
        joinedTelemetry = new JoinedTelemetry(panelsTelemetry, telemetry);

        scheduler = CommandScheduler.getInstance();
    }

    public void speak()
    {
        // DriveTrain
        PanelsFieldDrawing.drawRobot(drivetrainSubsystem.getPose());
        PanelsFieldDrawing.sendPacket();
        joinedTelemetry.addData("Drive-State", drivetrainSubsystem.getState());
        joinedTelemetry.addData("Drive-IsBusy", drivetrainSubsystem.isBusy());

        panelsTelemetry.addData("Drive-Pose-X", drivetrainSubsystem.getPose().getX());
        panelsTelemetry.addData("Drive-Pose-Y", drivetrainSubsystem.getPose().getY());
        panelsTelemetry.addData("Drive-Pose-Heading", drivetrainSubsystem.getPose().getHeading());
        panelsTelemetry.addData("Drive-Error-Heading", drivetrainSubsystem.getHeadingError());
        panelsTelemetry.addData("Drive-Error-Drive", drivetrainSubsystem.getDriveError());
        panelsTelemetry.addData("Drive-Error-Translational", drivetrainSubsystem.getTranslationalError());
        panelsTelemetry.addData("Drive-IsBusy", drivetrainSubsystem.isBusy());
        panelsTelemetry.addData("Drive-AtPose-P1", drivetrainSubsystem.atPose(BluePositions.SHOOT_P1));
        panelsTelemetry.addData("Drive-AtPose-P2", drivetrainSubsystem.atPose(BluePositions.SHOOT_P2));
        panelsTelemetry.addData("Drive-AtPose-P3", drivetrainSubsystem.atPose(BluePositions.SHOOT_P3));
        panelsTelemetry.addData("Drive-AtPose-P4", drivetrainSubsystem.atPose(BluePositions.SHOOT_P4));


        // Intake
        joinedTelemetry.addData("Intake-State", intakeSubsystem.getState());

        // Shooter
        joinedTelemetry.addData("Shooter-State", shooterSubsystem.getState());

        panelsTelemetry.addData("Shooter-RPM-Goal", shooterSubsystem.getGoalRPM());
        panelsTelemetry.addData("Shooter-RPM-Left", shooterSubsystem.getLeftRPM());
        panelsTelemetry.addData("Shooter-RPM-Middle", shooterSubsystem.getMiddleRPM());
        panelsTelemetry.addData("Shooter-RPM-Right", shooterSubsystem.getRightRPM());
        panelsTelemetry.addData("Shooter-HOOD-Goal", shooterSubsystem.getGoalHood());
        panelsTelemetry.addData("Shooter-HOOD-Current", shooterSubsystem.getHoodPosition());
        panelsTelemetry.addData("Shooter-IsReady", shooterSubsystem.isReady());

        // Funnel
        joinedTelemetry.addData("Funnel-State", funnelSubsystem.getState());
        joinedTelemetry.addData("SensorL", funnelSubsystem.getSensorL().getNormalizedColors().toColor());
        joinedTelemetry.addData("SensorM", funnelSubsystem.getSensorM().getNormalizedColors().toColor());
        joinedTelemetry.addData("SensorR", funnelSubsystem.getSensorR().getNormalizedColors().toColor());

        panelsTelemetry.addData("Funnel-IsReady", funnelSubsystem.isReady());

        // Machine
        joinedTelemetry.addData("Machine-State", machineSubsystem.getState());

        panelsTelemetry.addData("Machine-IsReady", machineSubsystem.isReady());

        // Command Scheduler
        panelsTelemetry.addData("CommandScheduler", scheduler);


        joinedTelemetry.update();
    }

}
