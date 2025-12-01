package org.firstinspires.ftc.teamcode.Constants;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class ShooterConstants {
    public static String RightMotorName = "rightShooter";
    public static String LeftMotorName = "leftShooter";
    public static String MiddleMotorName = "middleShooter";
    public static DcMotorEx.Direction RightDirection = DcMotorSimple.Direction.FORWARD;
    public static DcMotorEx.Direction MiddleDirection = DcMotorSimple.Direction.REVERSE;
    public static DcMotorEx.Direction LeftDirection = DcMotorSimple.Direction.FORWARD;
    public static String HoodServo1Name = "hoodServo1";
    //public static String HoodServo2Name = "hoodServo2"; //UNCOMMENT IF NEEDED
    public static double RPMGoal = 0;


    public static double MinServo = 0;
    public static double MaxServo = 0.18;


    public static double KP = 0.0002 ; //attım

    public static double KD = 0.00015; //attım

    public static double KP_RPM_Right = KP; //attım
    public static double KP_RPM_Middle = KP; //attım
    public static double KP_RPM_Left = KP; //attım
    public static double KD_RPM_Right = KD;
    public static double KD_RPM_Middle= KD;
    public static double KD_RPM_Left= KD;


    public static double MultiplierLeft = 1;
    public static double MultiplierRight = 1;
    public static double MultiplierMiddle = 0.965;

    public static double RpmTol = 60;
    public static double HoodClosedPose;
    public static double HoodTol = 0.0025;

    public static double RestRPM = 2000;
    public static double RestHoodPos = 0.0275;
}
