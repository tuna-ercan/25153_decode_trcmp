package org.firstinspires.ftc.teamcode.Constants;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Constants related to the drivetrain hardware and control.
 */
//@Configurable
public class RGBConstants {

    @IgnoreConfigurable
    public static String RGBName = "led";

    public static double shooterReadyColor = 0.500;
    public static double shooterNotReadyColor = 0.420;
    public static double intakeColor = 0.611;
    public static double outtakeColor = 0.285;
    public static double prepColor = 0.350;
    public static double idleColor = 0.720;
    public static double restColor = 1;
}
