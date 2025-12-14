package org.firstinspires.ftc.teamcode.OpModes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.DrivetrainCommands.AimToFocus;
import org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants;
import org.firstinspires.ftc.teamcode.Container;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;
import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Utils.Mouth;

@TeleOp(name = "TeleopBlue")
public class TeleopBlue extends CommandOpMode {
    private TheMachineSubsystem m_machine;
    private DrivetrainSubsystem m_drive;
    private Mouth mouth;

    private GamepadEx gamepadEx1;
    private GamepadEx gamepadEx2;

    private Command initOpCommand;

    private Command periodicOpCommand;

    private Command driveAndShootP5;
    private Command driveAndShootP2;
    private Command driveAndShootP3;
    private Command driveAndShootP4;
    private Command manualShootP5;
    private Command manualShootP2;
    private Command shootFromPose;

    private Command setRobotPoseLL;


    private PathChain x;

    @Override
    public void initialize()
    {
        Container.isBlue = true;
        Container.isTeleop = true;
        Container.colorCombination = "x";

        m_drive = new DrivetrainSubsystem(hardwareMap);
        m_machine = new TheMachineSubsystem(hardwareMap, m_drive::getPose);
        mouth = new Mouth(m_machine,m_drive, telemetry);

        x = m_drive.getFollower().pathBuilder()
                .addPath(                        new BezierCurve(
                        new Pose(63.398, 11.073),
                        new Pose(63.849, 60.528),
                        new Pose(50.566, 93.434)
                ))
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(136))
                .build();

        driveAndShootP5 = m_drive.driveToShootP5().alongWith(m_machine.prepP5Request()).withTimeout(DrivetrainConstants.AimTimeOut)
                .andThen(m_machine.shootFromPoseRequest());


        driveAndShootP2 = m_drive.driveToShootP2().alongWith(m_machine.prepP2Request()).withTimeout(DrivetrainConstants.AimTimeOut)
                .andThen(m_machine.shootFromPoseRequest());

        driveAndShootP3 = m_drive.driveToShootP3().alongWith(m_machine.prepP3Request()).withTimeout(DrivetrainConstants.AimTimeOut)
                .andThen(m_machine.shootFromPoseRequest());

        driveAndShootP4 = m_drive.driveToShootP4().alongWith(m_machine.prepP4Request()).withTimeout(DrivetrainConstants.AimTimeOut)
                .andThen(m_machine.shootFromP4Request());

        manualShootP5 = new AimToFocus(m_drive).alongWith(m_machine.prepP5Request()).withTimeout(DrivetrainConstants.AimTimeOut)
                .andThen(m_machine.shootFromPoseRequest());

        manualShootP2 = m_machine.prepP2Request().withTimeout(DrivetrainConstants.AimTimeOut)
                .andThen(m_machine.shootFromPoseRequest());


        setRobotPoseLL = m_drive.setRobotPose(m_machine.getLLPoseMT());

        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        configureBindingsGamepad1();
        configureBindingsGamepad2();

        initOpCommand = new InstantCommand(() -> {
            m_drive.startTeleopDrive();
            m_machine.funnelRequest(AllStates.FunnelStates.HOME);
        });

        periodicOpCommand = new RunCommand(() -> {
            m_drive.setTeleopDriveFieldCentricBlue(gamepadEx1);
            mouth.speak();
        });

        schedule(
                initOpCommand.andThen(periodicOpCommand)
        );

    }

    public void configureBindingsGamepad1() {

        gamepadEx1.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(m_machine.restRequest().andThen(new InstantCommand(()->{
                    m_drive.setRobotPose(m_machine.getLLPoseMT());
                    //m_drive.zeroPose();
                })));


        gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                        .whenHeld(manualShootP2)
                        .whenReleased(m_machine.restRequest());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenHeld(manualShootP5)
                .whenReleased(m_machine.restRequest());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.B)
                .whenHeld(driveAndShootP5)
                .whenReleased(m_machine.restRequest());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenHeld(driveAndShootP2)
                .whenReleased(m_machine.restRequest());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                        .whenPressed(Container.ShoutTriggerComand());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.A)
                .whenHeld(driveAndShootP3)
                .whenReleased(m_machine.restRequest());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.X)
                .whenHeld(driveAndShootP4)
                .whenReleased(m_machine.restRequest());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_STICK_BUTTON)
                .whenPressed(m_machine.outtakeRequest())
                .whenReleased(m_machine.restRequest());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.LEFT_STICK_BUTTON)
                .whenPressed(m_machine.intakeRequest());

        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(m_machine.getShooterSubsystem().zeroRequest().alongWith(m_machine.getIntakeSubsystem().idleRequest()));

    }
    public void configureBindingsGamepad2() {

    }

}

