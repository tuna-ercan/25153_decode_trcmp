package org.firstinspires.ftc.teamcode.Constants;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Constants for the Intake subsystem.
 */
//@Configurable
public class IntakeConstants {
    @IgnoreConfigurable
    public static String IntakeMotorName = "intake";
    @IgnoreConfigurable
    public static DcMotorSimple.Direction IntakeMotorDirection = DcMotorSimple.Direction.REVERSE;
    
    /**
     * Power for the intake motor when intaking game elements.
     */
    public static double IntakeIntakePower = 0.7;
    
    /**
     * Power for the intake motor when ejecting game elements (reverse).
     */
    public static double IntakeReversePower = -0.7;
    
    /**
     * Power for the intake motor during testing.
     */
    public static double IntakeTestPower = 0.5;

    public static long IntakeIdleDelay = 1750;

}
