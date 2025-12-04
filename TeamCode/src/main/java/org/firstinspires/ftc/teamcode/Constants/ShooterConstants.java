package org.firstinspires.ftc.teamcode.Constants;

import androidx.annotation.NonNull;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

@Configurable
public class ShooterConstants {
    @IgnoreConfigurable
    public static String RightMotorName = "rightShooter";
    @IgnoreConfigurable
    public static String LeftMotorName = "leftShooter";
    @IgnoreConfigurable
    public static String MiddleMotorName = "middleShooter";
    public static DcMotorEx.Direction RightDirection = DcMotorSimple.Direction.FORWARD;
    public static DcMotorEx.Direction MiddleDirection = DcMotorSimple.Direction.REVERSE;

    public static DcMotorEx.Direction LeftDirection = DcMotorSimple.Direction.REVERSE;
    @IgnoreConfigurable
    public static String HoodServoName = "hoodServo1";
    @IgnoreConfigurable
    public static Servo.Direction HoodServoDirection = Servo.Direction.FORWARD;

    public static double MinHoodPosition = 0;
    public static double MaxHoodPosition = 0.18;


    public static double KP = 0.0002 ; //attım

    public static double KD = 0.00015; //attım

    public static PIDCoefficients LeftPIDCoefficients = new PIDCoefficients(KP, 0, KD);
    public static PIDCoefficients MiddlePIDCoefficients = new PIDCoefficients(KP, 0, KD);
    public static PIDCoefficients RightPIDCoefficients = new PIDCoefficients(KP, 0, KD);

    public static double MultiplierLeft = 1;
    public static double MultiplierRight = 1;
    public static double MultiplierMiddle = 1;

    public static double RpmTol = 60;
    public static double HoodTol = 0.0025;

    public static double RestRPM = 2000;
    public static double RestHoodPos = 0.1;

    public static double TestRpm = 2000;
    public static double TestHoodPos = 0.0275;

    public static double CalculateRpmFromDistance(double distance)
    {
        return 2750 + (distance-164)/57.5 * 250; // 2600'ü değiştir, oto şut konumuna göre
    }

    public static double CalculateHoodFromDistance(double distance)
    {
        return  0.0027 - (0.0025*(int)((distance-164)/20)); // 2600'ü değiştir, oto şut konumuna göre
    }
}
