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
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Constants.PedroConstants;
import org.firstinspires.ftc.teamcode.Constants.ShooterConstants;
import org.firstinspires.ftc.teamcode.Container;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;
import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Utils.Mouth;

@TeleOp(name = "TEST_SUBSYSTEM")
public class TestOpModeForSubsystem extends CommandOpMode {
    private TelemetryManager.TelemetryWrapper panelsTelemetry;
    private JoinedTelemetry joinedTelemetry;
    private CommandScheduler scheduler;

    private ShooterSubsystem m_shoot;
    private DrivetrainSubsystem m_drive;
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

        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);
        panelsTelemetry = PanelsTelemetry.INSTANCE.getFtcTelemetry();

        joinedTelemetry = new JoinedTelemetry(panelsTelemetry, telemetry);

        scheduler = CommandScheduler.getInstance();
        //follower = follower = PedroConstants.createFollower(hardwareMap);



        initOpCommand = new InstantCommand(() -> {
            Container.colorCombination = null;
            //follower.setStartingPose(startPose);
            //updateFollower();
        });


        periodicOpCommand = new RunCommand(() -> {
            joinedTelemetry.addData("CommandScheduler", scheduler.toString());
            joinedTelemetry.addData("Intake-State", m_shoot.getState());
            joinedTelemetry.addData("ColorCombination", Container.colorCombination);
            joinedTelemetry.addData("ShooterRPM", m_shoot.getGoalRPM());
            joinedTelemetry.addData("ShooterHood", m_shoot.getGoalHood());
            joinedTelemetry.addData("LeftRPM", m_shoot.getLeftRPM());
            joinedTelemetry.addData("RightRPM", m_shoot.getRightRPM());
            joinedTelemetry.addData("MiddleRPM", m_shoot.getMiddleRPM());
            joinedTelemetry.update();
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
        gamepadEx1.getGamepadButton(GamepadKeys.Button.LEFT_STICK_BUTTON)
                .whenPressed(m_shoot.shootP1Request());
        gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_STICK_BUTTON)
                .whenPressed(m_shoot.reverseRequest());
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(m_shoot.zeroRequest());
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(m_shoot.restRequest());

    }
    public void configureBindingsGamepad2() {

    }

}

