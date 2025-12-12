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
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.teamcode.Container;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.FunnelSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;
import org.firstinspires.ftc.teamcode.Utils.PanelsFieldDrawing;
@Disabled
@TeleOp(name = "TEST_SUBSYSTEM")
public class TestOpModeForSubsystem extends CommandOpMode {
    private TelemetryManager.TelemetryWrapper panelsTelemetry;
    private JoinedTelemetry joinedTelemetry;
    private CommandScheduler scheduler;

    private ShooterSubsystem m_shoot;
    private DrivetrainSubsystem m_drive;

    private IntakeSubsystem m_intake;

    private TheMachineSubsystem m_machine;

    private FunnelSubsystem m_funnel;

    private GamepadEx gamepadEx1;
    private GamepadEx gamepadEx2;

    public Command initOpCommand;

    public Command periodicOpCommand;

    //Follower follower;



    @Override
    public void initialize()
    {
        m_drive = new DrivetrainSubsystem(hardwareMap);
        m_machine = new TheMachineSubsystem(hardwareMap);

        m_shoot = m_machine.getShooterSubsystem();
        m_intake = m_machine.getIntakeSubsystem();
        m_funnel = m_machine.getFunnelSubsystem();



        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);
        panelsTelemetry = PanelsTelemetry.INSTANCE.getFtcTelemetry();

        joinedTelemetry = new JoinedTelemetry(panelsTelemetry, telemetry);

        scheduler = CommandScheduler.getInstance();
        //follower = follower = PedroConstants.createFollower(hardwareMap);



        initOpCommand = new InstantCommand(() -> {
            Container.colorCombination = null;
            //m_drive.startTeleopDrive();
            PanelsFieldDrawing.init();
        });


        periodicOpCommand = new RunCommand(() -> {
            //m_drive.setTeleopDriveFieldCentric(gamepadEx1);
            joinedTelemetry.addData("Funnel-State", m_funnel.getState());
            joinedTelemetry.addData("ColorCombination", Container.colorCombination);

            String fComb = m_funnel.getFunnelFeedOrder()[0] + " " + m_funnel.getFunnelFeedOrder()[1] + " " + m_funnel.getFunnelFeedOrder()[2];
            joinedTelemetry.addData("FeedCombination", fComb);

            String gComb = m_funnel.getFunnelGreenRatioOrder()[0] + " " + m_funnel.getFunnelGreenRatioOrder()[1] + " " + m_funnel.getFunnelGreenRatioOrder()[2];
            joinedTelemetry.addData("GreenCombination", gComb);


            joinedTelemetry.addData("L-Distance", m_funnel.getDistanceL());
            joinedTelemetry.addData("M-Distance", m_funnel.getDistanceM());
            joinedTelemetry.addData("R-Distance", m_funnel.getDistanceR());
            joinedTelemetry.addData("IsFull", m_funnel.isFull());
            joinedTelemetry.addData("L-Diff", m_funnel.getLeftColorRatio());
            joinedTelemetry.addData("M-Diff", m_funnel.getMiddleColorRatio());
            joinedTelemetry.addData("R-Diff", m_funnel.getRightColorRatio());

            String lColor = m_funnel.getDetectedColorL()[0] + " " + m_funnel.getDetectedColorL()[1] + " " + m_funnel.getDetectedColorL()[2];
            String mColor = m_funnel.getDetectedColorM()[0] + " " + m_funnel.getDetectedColorM()[1] + " " + m_funnel.getDetectedColorM()[2];
            String rColor = m_funnel.getDetectedColorR()[0] + " " + m_funnel.getDetectedColorR()[1] + " " + m_funnel.getDetectedColorR()[2];

            joinedTelemetry.addData("L-Color", lColor);
            joinedTelemetry.addData("M-Color", mColor);
            joinedTelemetry.addData("R-Color", rColor);

            joinedTelemetry.addData("L-Green", m_funnel.isGreenBallL());
            joinedTelemetry.addData("M-Green", m_funnel.isGreenBallM());
            joinedTelemetry.addData("R-Green", m_funnel.isGreenBallR());

            Position camPose = m_machine.getLimelightHandler().getCamPose().getPosition();
            Pose robotPose = m_drive.getPose();

            Position cameraFromRobotMainFrame = new Position();
            cameraFromRobotMainFrame.x = camPose.x - robotPose.getX();
            cameraFromRobotMainFrame.y = camPose.y - robotPose.getY();
            cameraFromRobotMainFrame.z = camPose.z;

            Position cameraFromRobot = new Position();
            cameraFromRobot.x = cameraFromRobotMainFrame.x*Math.cos(robotPose.getHeading()) - cameraFromRobotMainFrame.y*Math.sin(robotPose.getHeading());
            cameraFromRobot.y = cameraFromRobotMainFrame.x*Math.sin(robotPose.getHeading()) + cameraFromRobotMainFrame.y*Math.cos(robotPose.getHeading());
            cameraFromRobot.z = cameraFromRobotMainFrame.z;

            joinedTelemetry.addData("CamX", cameraFromRobot.x);
            joinedTelemetry.addData("CamY", cameraFromRobot.y);
            joinedTelemetry.addData("CamZ", cameraFromRobot.z);

            //joinedTelemetry.addData("L-Green", m_funnel.getGreenL());
            //joinedTelemetry.addData("M-Green", m_funnel.getGreenM());
            //joinedTelemetry.addData("R-Green", m_funnel.getGreenR());
            //joinedTelemetry.addData("L-Blue", m_funnel.getBlueL());
            //joinedTelemetry.addData("M-Blue", m_funnel.getBlueM());
            //joinedTelemetry.addData("R-Blue", m_funnel.getBlueR());
            //joinedTelemetry.addData("L-Red", m_funnel.getRedL());
            //joinedTelemetry.addData("M-Red", m_funnel.getRedM());
            //joinedTelemetry.addData("R-Red", m_funnel.getRedR());
            joinedTelemetry.update();

            PanelsFieldDrawing.drawRobot(m_drive.getPose());
            PanelsFieldDrawing.sendPacket();
        });

        schedule(initOpCommand.andThen(periodicOpCommand));

        configureBindingsGamepad1();
        //configureBindingsGamepad2();
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
                .whenPressed(m_funnel.prepRequest());
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(m_funnel.homeRequest());
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(m_funnel.feedRequest());





    }
    public void configureBindingsGamepad2() {

    }

}

