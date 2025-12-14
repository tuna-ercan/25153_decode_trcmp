package org.firstinspires.ftc.teamcode.OpModes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Commands.AutoCommands.SecondAutoCommandBlue;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.ThirdAutoCommandBlue;
import org.firstinspires.ftc.teamcode.Container;
import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;
import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Utils.Mouth;

@Autonomous(name = "AUTO-Blue-FAR2")
public class AutoBlueFARR2 extends CommandOpMode {
    private TheMachineSubsystem m_machine;
    private DrivetrainSubsystem m_drive;
    private Mouth mouth;
    public Command initOpCommand;

    public Command periodicOpCommand;
    public SequentialCommandGroup autoCommand;


    @Override
    public void initialize()
    {
        Container.isBlue = true;
        Container.isTeleop = false;

        m_drive = new DrivetrainSubsystem(hardwareMap);
        m_machine = new TheMachineSubsystem(hardwareMap, m_drive::getPose);
        mouth = new Mouth(m_machine,m_drive, telemetry);

        initOpCommand = new InstantCommand(() -> {
            m_machine.funnelRequest(AllStates.FunnelStates.HOME);
        });

        periodicOpCommand = new RunCommand(() -> {
            //mouth.speak();
            });

        autoCommand = new ThirdAutoCommandBlue(m_machine,m_drive);

        schedule(
                initOpCommand.andThen(periodicOpCommand),
                m_machine.idleRequest().andThen(new WaitCommand(500).andThen(autoCommand))
        );
    }


}

