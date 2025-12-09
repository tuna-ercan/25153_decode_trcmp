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

    public Command initOpCommand;

    public Command periodicOpCommand;

    public Command x;

    @Override
    public void initialize()
    {
        m_drive = new DrivetrainSubsystem(hardwareMap);
        m_machine = new TheMachineSubsystem(hardwareMap);
        mouth = new Mouth(m_machine,m_drive, telemetry);

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
        //m_drive.driveToShootP1().
         );
    }

    public void configureBindingsGamepad1() {
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenHeld(m_machine.intakeRequest(AllStates.IntakeStates.TEST));
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenHeld(m_machine.shooterRequest(AllStates.ShooterStates.TEST));
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenHeld(m_machine.funnelRequest(AllStates.FunnelStates.TEST));

        gamepadEx1.getGamepadButton(GamepadKeys.Button.X)
                .whenHeld(m_machine.prepP1Request());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.Y)
                .whenHeld(m_machine.idleRequest());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.A)
                .whenHeld(m_machine.shootFromP1Request());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.B)
                .whenHeld(m_machine.intakeRequest());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenHeld(
                        m_drive.driveToShootP1().alongWith(m_machine.prepP4Request())
                                .andThen(m_machine.shootFromP4Request())
                ).whenReleased(m_machine.idleRequest());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenHeld(m_machine.testRequest())
                .whenReleased(m_machine.idleRequest()); ;

        gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_STICK_BUTTON)
                .whenHeld(m_machine.outtakeRequest())
                .whenReleased(m_machine.idleRequest()); ;

    }
    public void configureBindingsGamepad2() {

    }

}

