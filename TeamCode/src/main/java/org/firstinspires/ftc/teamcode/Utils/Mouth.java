package org.firstinspires.ftc.teamcode.Utils;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.FunnelSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.LiftSubsystem;
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
    private final LiftSubsystem liftSubsystem;

    private final TelemetryManager.TelemetryWrapper panelsTelemetry;
    private final JoinedTelemetry joinedTelemetry;

    private final CommandScheduler scheduler;

    public Mouth(TheMachineSubsystem machineSubsystem, DrivetrainSubsystem drivetrainSubsystem)
    {
        this.machineSubsystem = machineSubsystem;
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.intakeSubsystem = machineSubsystem.getIntakeSubsystem();
        this.funnelSubsystem = machineSubsystem.getFunnelSubsystem();
        this.shooterSubsystem = machineSubsystem.getShooterSubsystem();
        this.liftSubsystem = machineSubsystem.getLiftSubsystem();

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

        panelsTelemetry.addData("Drive-Pose-X", drivetrainSubsystem.getPose().getX());
        panelsTelemetry.addData("Drive-Pose-Y", drivetrainSubsystem.getPose().getY());
        panelsTelemetry.addData("Drive-Pose-Heading", drivetrainSubsystem.getPose().getHeading());

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

        panelsTelemetry.addData("Funnel-IsReady", funnelSubsystem.isReady());

        // Lift
        joinedTelemetry.addData("Lift-State", liftSubsystem.getState());

        panelsTelemetry.addData("Lift-IsReady", liftSubsystem.isReady());

        // Machine
        joinedTelemetry.addData("Machine-State", machineSubsystem.getState());

        panelsTelemetry.addData("Machine-IsReady", machineSubsystem.isReady());

        // Command Scheduler
        panelsTelemetry.addData("CommandScheduler", scheduler);


        joinedTelemetry.update();
    }

}
