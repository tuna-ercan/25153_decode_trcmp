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
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Container;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.FunnelSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.Utils.PanelsFieldDrawing;

@TeleOp(name = "TEST_SUBSYSTEM")
@Disabled
public class TestOpModeForSubsystem extends CommandOpMode {
    private TelemetryManager.TelemetryWrapper panelsTelemetry;
    private JoinedTelemetry joinedTelemetry;
    private CommandScheduler scheduler;

    private ShooterSubsystem m_shoot;
    private DrivetrainSubsystem m_drive;

    private IntakeSubsystem m_intake;

    private FunnelSubsystem m_funnel;

    private GamepadEx gamepadEx1;
    private GamepadEx gamepadEx2;

    public Command initOpCommand;

    public Command periodicOpCommand;

    //Follower follower;



    @Override
    public void initialize()
    {
        m_shoot = new ShooterSubsystem(hardwareMap);
        m_drive = new DrivetrainSubsystem(hardwareMap);
        m_intake = new IntakeSubsystem(hardwareMap);
        m_funnel = new FunnelSubsystem(hardwareMap);


        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);
        panelsTelemetry = PanelsTelemetry.INSTANCE.getFtcTelemetry();

        joinedTelemetry = new JoinedTelemetry(panelsTelemetry, telemetry);

        scheduler = CommandScheduler.getInstance();
        //follower = follower = PedroConstants.createFollower(hardwareMap);



        initOpCommand = new InstantCommand(() -> {
            Container.colorCombination = null;
            m_drive.startTeleopDrive();
            PanelsFieldDrawing.init();
        });


        periodicOpCommand = new RunCommand(() -> {
            m_drive.setTeleopDriveFieldCentric(gamepadEx1);
            joinedTelemetry.addData("CommandScheduler", scheduler.toString());
            joinedTelemetry.addData("Intake-State", m_shoot.getState());
            joinedTelemetry.addData("Funnel-State", m_funnel.getState());
            joinedTelemetry.addData("SensorL", m_funnel.getSensorL().getNormalizedColors().toString());
            joinedTelemetry.addData("SensorM", m_funnel.getSensorM().getNormalizedColors().toString());
            joinedTelemetry.addData("SensorR", m_funnel.getSensorR().getNormalizedColors().toString());
            joinedTelemetry.addData("ColorCombination", Container.colorCombination);
            joinedTelemetry.update();

            PanelsFieldDrawing.drawRobot(m_drive.getPose());
            PanelsFieldDrawing.sendPacket();
        });

        schedule(initOpCommand.andThen(periodicOpCommand));

        configureBindingsGamepad1();
        configureBindingsGamepad2();
    }

    public void configureBindingsGamepad1() {

        /*gamepadEx1.getGamepadButton(GamepadKeys.Button.LEFT_STICK_BUTTON)
                .whenPressed(m_intake.intakeRequest());
        gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_STICK_BUTTON)
                .whenPressed(m_intake.outtakeRequest());
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(m_intake.idleRequest());*/
        //m_shoot.setTeleopDriveFieldCentric(gamepadEx1);
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(m_funnel.testRequest());
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(m_shoot.shootP1Request());
        gamepadEx1.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(m_funnel.homeRequest());
        gamepadEx1.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(m_funnel.feedRequest());
        /*gamepadEx1.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(m_funnel.feedRequest());*/


        gamepadEx1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(m_intake.intakeRequest());
        gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whileHeld(m_intake.outtakeRequest())
                .whenReleased(m_intake.idleRequest());




    }
    public void configureBindingsGamepad2() {

    }

}

