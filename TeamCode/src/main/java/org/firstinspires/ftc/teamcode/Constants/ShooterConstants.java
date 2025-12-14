package org.firstinspires.ftc.teamcode.Constants;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

//@Configurable
public class ShooterConstants {
    @IgnoreConfigurable
    public static String RightMotorName = "rightShooter";
    @IgnoreConfigurable
    public static String LeftMotorName = "leftShooter";
    @IgnoreConfigurable
    public static String MiddleMotorName = "middleShooter";
    public static DcMotorEx.Direction RightDirection = DcMotorSimple.Direction.REVERSE;
    public static DcMotorEx.Direction MiddleDirection = DcMotorSimple.Direction.REVERSE;

    public static DcMotorEx.Direction LeftDirection = DcMotorSimple.Direction.REVERSE;
    @IgnoreConfigurable
    public static String HoodServoName = "hoodServo1";
    @IgnoreConfigurable
    public static Servo.Direction HoodServoDirection = Servo.Direction.FORWARD;

    public static double MinHoodPosition = 0;
    public static double MaxHoodPosition = 0.18;

    public static double LastRpmCoefficient = 0.5;


    public static double KP = 0.00026 ; //attım

    public static double KD = 0.0002; //attım

    public static PIDFCoefficients LeftPIDFCoefficients = new PIDFCoefficients(KP, 0, KD, 0);
    public static PIDFCoefficients MiddlePIDFCoefficients = new PIDFCoefficients(KP, 0, KD, 0);
    public static PIDFCoefficients RightPIDFCoefficients = new PIDFCoefficients(KP, 0, KD, 0);

    public static double MultiplierLeft = 1;
    public static double MultiplierRight = 1;
    public static double MultiplierMiddle = 1;

    public static double RpmTol = 50;
    public static double HoodTol = 0.0025;

    public static double RestRPM = 1000;
    public static double RestRPMFar = 3000;

    public static double farDistanceY = 50;
    public static double RestRPMAuto = 2800;

    public static double RestHoodPos = 5;

    public static double TestRpm = 2650;
    public static double TestHoodPos = 5;

    public static double P1Rpm = 2650;
    public static double P1HoodPos = 15;

    public static double P2Rpm = 2600;
    public static double P2HoodPos = 18;

    public static double P3Rpm = 2850;
    public static double P3HoodPos = 15;

    public static double P4Rpm = 3400;
    public static double P4HoodPos = 7;
    public static double P5Rpm = 2800;
    public static double P5HoodPos = 18;


    public static double rpmThreshold;
    public static double thresholdPower = 0.5;
    public static double HoodStartTol = 0.5;

    public static double HoodGearRatio = 7;
 // Max hood angle 37 degrees
    public static double ServoAngleCapacity = 1800;

    public static long ShooterRestDelay = 2000;

    public static double CalculateRpmFromDistance(double distance)
    {
        return 2750 + (distance-164)/57.5 * 250; // 2600'ü değiştir, oto şut konumuna göre
    }

    public static double CalculateHoodFromDistance(double distance)
    {
        return  0.0027 - (0.0025*(int)((distance-164)/20)); // 2600'ü değiştir, oto şut konumuna göre
    }

    public static double rpmInterpolator(double x) {
        return ((0.0072788*Math.pow(x, 3))+((-1.90187)*Math.pow(x, 2))+((166.47677)*x)+(-2030.0971));
    }

    public static double hoodInterpolator(double x) {
        return ((0.0000892583*Math.pow(x, 3))+((-0.0252587)*Math.pow(x, 2))+((2.11195)*x)+(-36.69552));
    }

    public static double getBluekY(double heading){
        double h = (180-Math.toDegrees(heading));

        return Math.exp(-Math.pow(((h-15)/2),2));
    }

    public static double getBlueFocusPointOffset(double heading){
        return (10.16)*getBluekY(heading);
    }

    public static double getRedkY(double heading){
        double h = (Math.toDegrees(heading));

        return Math.exp(-Math.pow(((h-15)/2),2));
    }

    public static double getRedFocusPointOffset(double heading){
        return (10.16)*getRedkY(heading);
    }

}
