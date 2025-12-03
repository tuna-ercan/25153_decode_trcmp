package org.firstinspires.ftc.teamcode.OpModes;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;
import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Utils.Mouth;

@TeleOp(name = "TEST_SUBSYSTEM")
public class TestOpModeForSubsystem extends CommandOpMode {
    private TelemetryManager.TelemetryWrapper panelsTelemetry;
    private JoinedTelemetry joinedTelemetry;
    private CommandScheduler scheduler;

    private IntakeSubsystem m_intake;
    private GamepadEx gamepadEx1;
    private GamepadEx gamepadEx2;

    public Command initOpCommand;

    public Command periodicOpCommand;

    @Override
    public void initialize()
    {
        m_intake = new IntakeSubsystem(hardwareMap);

        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);
        panelsTelemetry = PanelsTelemetry.INSTANCE.getFtcTelemetry();

        joinedTelemetry = new JoinedTelemetry(panelsTelemetry, telemetry);

        scheduler = CommandScheduler.getInstance();

        configureBindingsGamepad1();
        configureBindingsGamepad2();

        initOpCommand = new InstantCommand(() -> {
        });

        periodicOpCommand = new RunCommand(() -> {
            joinedTelemetry.addData("CommandScheduler", scheduler.toString());
            joinedTelemetry.addData("Intake-State", m_intake.getState());
            joinedTelemetry.update();
        });

        schedule(periodicOpCommand);
    }

    public void configureBindingsGamepad1() {
        gamepadEx1.getGamepadButton(GamepadKeys.Button.LEFT_STICK_BUTTON)
                .whenPressed(m_intake.intakeRequest());
        gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_STICK_BUTTON)
                .whenPressed(m_intake.outtakeRequest());
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(m_intake.idleRequest());
    }
    public void configureBindingsGamepad2() {

    }

}

