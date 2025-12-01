package org.firstinspires.ftc.teamcode.Constants;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

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

    public static double TeleopRotationCoefficient = 0.5;
    public static double TolX = 2;
    public static double TolY = 2;
    public static double TolH = 0.5;

}
