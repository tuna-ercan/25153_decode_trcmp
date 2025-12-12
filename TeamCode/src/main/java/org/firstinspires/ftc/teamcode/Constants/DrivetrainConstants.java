package org.firstinspires.ftc.teamcode.Constants;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.pedropathing.control.PIDFCoefficients;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.arcrobotics.ftclib.controller.PIDFController;

/**
 * Constants related to the drivetrain hardware and control.
 */
@Configurable
public class DrivetrainConstants {

    @IgnoreConfigurable
    public static String LeftFrontName = "frontLeftMotor";
    @IgnoreConfigurable
    public static String RightFrontName = "frontRightMotor";
    @IgnoreConfigurable
    public static String LeftBackName = "backLeftMotor";
    @IgnoreConfigurable
    public static String RightBackName = "backRightMotor";
    @IgnoreConfigurable
    public static String ImuName = "imu";
    @IgnoreConfigurable
    public static DcMotorSimple.Direction LeftFrontDirection = DcMotorSimple.Direction.REVERSE;
    @IgnoreConfigurable
    public static DcMotorSimple.Direction RightFrontDirection = DcMotorSimple.Direction.FORWARD;
    @IgnoreConfigurable
    public static DcMotorSimple.Direction LeftBackDirection = DcMotorSimple.Direction.REVERSE;
    @IgnoreConfigurable
    public static DcMotorSimple.Direction RightBackDirection = DcMotorSimple.Direction.FORWARD;
    @IgnoreConfigurable
    public static RevHubOrientationOnRobot RevHubOrientation = new RevHubOrientationOnRobot(
            RevHubOrientationOnRobot.LogoFacingDirection.UP,
            RevHubOrientationOnRobot.UsbFacingDirection.LEFT);

    /**
     * Coefficient for rotation speed in teleop to dampen the rotation.
     */
    public static double TeleopRotationCoefficient = 0.5;
    
    /**
     * Tolerance for X position in inches.
     */
    public static double TolX = 1;
    public static double TolcX = 10;


    /**
     * Tolerance for Y position in inches.
     */
    public static double TolY = 1;
    public static double TolcY = 10;

    /**
     * Tolerance for Heading in degrees/radians (depending on implementation, usually radians in PedroPathing).
     */
    public static double TolH = Math.toRadians(3);
    public static double TolcH = Math.toRadians(30);

    public static double autoDriveInterpolator = 0.8;
    public static double driveBrakingStrength = 0.9;
    public static boolean useVoltageCompensation = false;
    public static PIDFCoefficients xPID = new PIDFCoefficients(0.03,0,0.005 ,0);
    public static PIDFCoefficients yPID = new PIDFCoefficients(0.03,0,0.005,0);
    public static PIDFCoefficients headingPID = new PIDFCoefficients(1,0,0,0);

    public static double maxPowerX = 0.7, maxPowerY = 0.7, maxPowerHeading = 1;

    public static double TValuePathPid = 0.99;
    public static long pidTimeout = 2500;
}
