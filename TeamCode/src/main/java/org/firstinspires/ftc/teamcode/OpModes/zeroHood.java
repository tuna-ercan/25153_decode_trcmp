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
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Container;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystem;

@Autonomous(name = "HOOD_ZERO")
public class zeroHood extends CommandOpMode {
    private ShooterSubsystem m_shoot;
    public Command initOpCommand;

    @Override
    public void initialize()
    {
        m_shoot = new ShooterSubsystem(hardwareMap, this::fakePose);

        initOpCommand = new InstantCommand(() -> {
            m_shoot.zeroHood();
        });



        schedule(initOpCommand);
    }

    private Pose fakePose()
    {
        return new Pose(0,0,0);
    }

}

