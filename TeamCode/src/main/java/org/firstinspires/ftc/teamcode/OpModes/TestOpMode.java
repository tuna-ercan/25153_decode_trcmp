package org.firstinspires.ftc.teamcode.OpModes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;
import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Utils.Mouth;

@TeleOp(name = "TEST")
public class TestOpMode extends CommandOpMode {
    private TheMachineSubsystem m_machine;
    private DrivetrainSubsystem m_drive;
    private Mouth mouth;

    private GamepadEx gamepadEx1;
    private GamepadEx gamepadEx2;

    private Command initOpCommand;

    private Command periodicOpCommand;

    private Command driveAndShootP1;
    private Command driveAndShootP2;
    private Command driveAndShootP3;
    private Command driveAndShootP4;


    @Override
    public void initialize()
    {
        m_drive = new DrivetrainSubsystem(hardwareMap);
        m_machine = new TheMachineSubsystem(hardwareMap);
        mouth = new Mouth(m_machine,m_drive, telemetry);

        driveAndShootP1 = m_drive.driveToShootP1().alongWith(m_machine.prepP1Request())
                            .andThen(m_machine.shootFromP1Request());

        driveAndShootP2 = m_drive.driveToShootP2().alongWith(m_machine.prepP2Request())
                .andThen(m_machine.shootFromP2Request());

        driveAndShootP3 = m_drive.driveToShootP3().alongWith(m_machine.prepP3Request())
                .andThen(m_machine.shootFromP3Request());

        driveAndShootP4 = m_drive.driveToShootP4().alongWith(m_machine.prepP4Request())
                .andThen(m_machine.shootFromP4Request());

        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        configureBindingsGamepad1();
        configureBindingsGamepad2();

        initOpCommand = new InstantCommand(() -> {
            m_drive.startTeleopDrive();
            m_machine.funnelRequest(AllStates.FunnelStates.HOME);
        });

        periodicOpCommand = new RunCommand(() -> {
            m_drive.setTeleopDriveFieldCentric(gamepadEx1);
            mouth.speak();
        });

        schedule(
                initOpCommand.andThen(periodicOpCommand)
                //m_machine.idleRequest());
         );
    }

    public void configureBindingsGamepad1() {
        gamepadEx1.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(m_machine.prepP1Request());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(m_machine.idleRequest());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(m_machine.shootFromP1Request());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(m_machine.testRequest());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(driveAndShootP1)
                .whenReleased(m_machine.idleRequest());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(driveAndShootP2)
                .whenReleased(m_machine.idleRequest());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(driveAndShootP3)
                .whenReleased(m_machine.idleRequest());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(driveAndShootP4)
                .whenReleased(m_machine.idleRequest());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_STICK_BUTTON)
                .whenPressed(m_machine.outtakeRequest())
                .whenReleased(m_machine.idleRequest());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.LEFT_STICK_BUTTON)
                .whenPressed(m_machine.intakeRequest())
                .whenReleased(m_machine.idleRequest());

    }
    public void configureBindingsGamepad2() {

    }

}

