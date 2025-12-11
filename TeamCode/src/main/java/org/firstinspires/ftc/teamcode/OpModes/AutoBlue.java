package org.firstinspires.ftc.teamcode.OpModes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Commands.AutoCommands.MainAutoCommandBlue;
import org.firstinspires.ftc.teamcode.Container;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;
import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Utils.Mouth;

@Autonomous(name = "AUTO")
public class AutoBlue extends CommandOpMode {
    private TheMachineSubsystem m_machine;
    private DrivetrainSubsystem m_drive;
    private Mouth mouth;
    public Command initOpCommand;

    public Command periodicOpCommand;
    public SequentialCommandGroup autoCommand;

    private Command driveAndShootP1;

    @Override
    public void initialize()
    {

        m_machine = new TheMachineSubsystem(hardwareMap);
        m_drive = new DrivetrainSubsystem(hardwareMap);
        mouth = new Mouth(m_machine,m_drive, telemetry);


        driveAndShootP1 = m_drive.driveToShootP1().alongWith(m_machine.prepP1Request()).withTimeout(1700)
                .andThen(m_machine.shootFromP1Request());

        initOpCommand = new InstantCommand(() -> {
            m_machine.funnelRequest(AllStates.FunnelStates.HOME);
            Container.isBlue = true;
            Container.isTeleop = false;
        });

        periodicOpCommand = new RunCommand(() -> {
            mouth.speak();
        });

        autoCommand = new MainAutoCommandBlue(m_machine,m_drive);

        schedule(
                initOpCommand.andThen(periodicOpCommand),
                m_machine.idleRequest().andThen(new WaitCommand(500).andThen(autoCommand))
        );
    }


}

