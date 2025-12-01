package org.firstinspires.ftc.teamcode.Constants;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Configurable
public class IntakeConstants {
    @IgnoreConfigurable
    public static String IntakeMotorName = "intake";
    @IgnoreConfigurable
    public static DcMotorSimple.Direction IntakeMotorDirection = DcMotorSimple.Direction.FORWARD;
    public static double IntakeIntakePower = 0.75;
    public static double IntakeReversePower = -0.7;
    public static double IntakeTestPower = 0.5;

}
