package org.firstinspires.ftc.teamcode.Constants;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class DrivetrainConstants {

    public static String LeftFrontName = "frontLeftMotor";
    public static String RightFrontName = "frontRightMotor";
    public static String LeftBackName = "backLeftMotor";
    public static String RightBackName = "backRightMotor";
    public static String ImuName = "imu";
    public static DcMotorSimple.Direction LeftFrontDirection = DcMotorSimple.Direction.REVERSE;
    public static DcMotorSimple.Direction RightFrontDirection = DcMotorSimple.Direction.FORWARD;
    public static DcMotorSimple.Direction LeftBackDirection = DcMotorSimple.Direction.REVERSE;
    public static DcMotorSimple.Direction RightBackDirection = DcMotorSimple.Direction.FORWARD;
    public static RevHubOrientationOnRobot RevHubOrientation = new RevHubOrientationOnRobot(
            RevHubOrientationOnRobot.LogoFacingDirection.UP,
            RevHubOrientationOnRobot.UsbFacingDirection.LEFT);

    public static double TeleopRotationCoefficient = 0.5;
    public static double TolX = 2;
    public static double TolY = 2;
    public static double TolH = 0.5;

}
