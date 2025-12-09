package org.firstinspires.ftc.teamcode.OpModes;

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

import org.firstinspires.ftc.teamcode.Container;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

@TeleOp(name = "HOOD_ZERO")
public class zeroHood extends CommandOpMode {
    private TelemetryManager.TelemetryWrapper panelsTelemetry;
    private JoinedTelemetry joinedTelemetry;
    private CommandScheduler scheduler;

    private ShooterSubsystem m_shoot;
    private GamepadEx gamepadEx1;
    private GamepadEx gamepadEx2;

    public Command initOpCommand;

    public Command periodicOpCommand;

    @Override
    public void initialize()
    {
        m_shoot = new ShooterSubsystem(hardwareMap);

        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);
        panelsTelemetry = PanelsTelemetry.INSTANCE.getFtcTelemetry();

        joinedTelemetry = new JoinedTelemetry(panelsTelemetry, telemetry);

        scheduler = CommandScheduler.getInstance();



        initOpCommand = new InstantCommand(() -> {
            m_shoot.zeroHood();
        });


        periodicOpCommand = new RunCommand(() -> {
            joinedTelemetry.addData("CommandScheduler", scheduler.toString());
            joinedTelemetry.addData("Intake-State", m_shoot.getState());
            joinedTelemetry.addData("ColorCombination", Container.colorCombination);
            joinedTelemetry.update();
        });

        schedule(initOpCommand.andThen(periodicOpCommand));
    }

}

