package org.firstinspires.ftc.teamcode.OpModes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;
import org.firstinspires.ftc.teamcode.Utils.Mouth;
@Autonomous(name = "RED-AUTO")
public class AutoRed extends CommandOpMode {
    private final TheMachineSubsystem m_machine;
    private final DrivetrainSubsystem m_drive;
    private final Mouth mouth;

    public AutoRed()
    {
        m_machine = new TheMachineSubsystem();
        m_drive = new DrivetrainSubsystem();
        mouth = new Mouth(m_machine,m_drive);
    }

    @Override
    public void initialize() {
    }
}
