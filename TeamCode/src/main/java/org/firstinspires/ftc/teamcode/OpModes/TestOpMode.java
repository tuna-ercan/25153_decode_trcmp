package org.firstinspires.ftc.teamcode.OpModes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
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

    @Override
    public void initialize()
    {

        m_machine = new TheMachineSubsystem();
        m_drive = new DrivetrainSubsystem();
        mouth = new Mouth(m_machine,m_drive);

        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        configureBindingsGamepad1();
        configureBindingsGamepad2();

        initOpCommand = new InstantCommand(() -> {
            m_drive.startTeleopDrive();
        });

        periodicOpCommand = new RunCommand(() -> {
            mouth.speak();
        });

        schedule(
                initOpCommand.andThen(periodicOpCommand),
                m_machine.machineRequest(AllStates.MachineStates.IDLE));
    }

    public void configureBindingsGamepad1() {
        m_drive.setTeleopDriveFieldCentric(gamepadEx1);
        gamepadEx1.getGamepadButton(GamepadKeys.Button.LEFT_STICK_BUTTON)
                .whenPressed(m_machine.intakeRequest(AllStates.IntakeStates.INTAKE));
    }
    public void configureBindingsGamepad2() {

    }

}

