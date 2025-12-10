package org.firstinspires.ftc.teamcode.Constants;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Constants related to the drivetrain hardware and control.
 */
//@Configurable
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

    /**
     * Tolerance for Y position in inches.
     */
    public static double TolY = 1;
    
    /**
     * Tolerance for Heading in degrees/radians (depending on implementation, usually radians in PedroPathing).
     */
    public static double TolH = 10;

    public static double autoDriveInterpolator = 0.9;
    public static double driveBrakingStrength = 0.9;
}
