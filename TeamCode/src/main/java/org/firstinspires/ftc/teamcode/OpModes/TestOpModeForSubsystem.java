package org.firstinspires.ftc.teamcode.OpModes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;
import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Utils.Mouth;

@TeleOp(name = "TEST_SUBSYSTEM")
public class TestOpModeForSubsystem extends CommandOpMode {

    private IntakeSubsystem m_intake;
    private GamepadEx gamepadEx1;
    private GamepadEx gamepadEx2;
    @Override
    public void initialize()
    {
        m_intake = new IntakeSubsystem();

        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        configureBindingsGamepad1();
        configureBindingsGamepad2();

        schedule();
    }

    public void configureBindingsGamepad1() {
        gamepadEx1.getGamepadButton(GamepadKeys.Button.LEFT_STICK_BUTTON)
                .whenPressed(m_intake.intakeRequest());
    }
    public void configureBindingsGamepad2() {

    }

}

