package org.firstinspires.ftc.teamcode.Constants;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Constants related to the drivetrain hardware and control.
 */
@Configurable
public class RGBConstants {

    @IgnoreConfigurable
    public static String RGBName = "RGB ";

    public static double shooterReadyColor = 0.5;
    public static double shooterNotReadyColor = 0.444;
    public static double intakeColor = 0.611;
    public static double outtakeColor = 0.277;
    public static double prepColor = 0.388;
    public static double idleColor = 0.666;
    public static double restColor = 1;
}
