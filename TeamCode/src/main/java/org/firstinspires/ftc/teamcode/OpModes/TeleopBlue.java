package org.firstinspires.ftc.teamcode.OpModes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TheMachineSubsystem;
import org.firstinspires.ftc.teamcode.Utils.Mouth;
@TeleOp(name = "BLUE-TELEOP")
public class TeleopBlue extends CommandOpMode {
    private final TheMachineSubsystem m_machine;
    private final DrivetrainSubsystem m_drive;
    private final Mouth mouth;

    public TeleopBlue()
    {
        m_machine = new TheMachineSubsystem();
        m_drive = new DrivetrainSubsystem();
        mouth = new Mouth(m_machine,m_drive);
    }

    @Override
    public void initialize() {
    }
}
