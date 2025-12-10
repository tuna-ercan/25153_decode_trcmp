package org.firstinspires.ftc.teamcode.Constants;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Constants for the Funnel subsystem, including servo names, positions, and timing delays.
 */
@Configurable
public class FunnelConstants {
    @IgnoreConfigurable
    public static String ColorSensorL = "c1";
    @IgnoreConfigurable
    public static String ColorSensorR = "c2";
    @IgnoreConfigurable
    public static String ServoR = "rightServo";
    @IgnoreConfigurable
    public static String ServoM = "middleServo";
    @IgnoreConfigurable
    public static String ServoL = "leftServo";
    @IgnoreConfigurable
    public static String ServoP = "prepServo";
    @IgnoreConfigurable
    public static String colorSensorL = "sensorL";
    @IgnoreConfigurable
    public static String colorSensorR = "sensorR";
    @IgnoreConfigurable
    public static String colorSensorM = "sensorM";

    @IgnoreConfigurable
    public static Servo.Direction RightDirection = Servo.Direction.FORWARD;
    @IgnoreConfigurable
    public static Servo.Direction LeftDirection = Servo.Direction.FORWARD;
    @IgnoreConfigurable
    public static Servo.Direction MiddleDirection = Servo.Direction.FORWARD;
    @IgnoreConfigurable
    public static Servo.Direction PrePrepDirection = Servo.Direction.FORWARD;

    public static float Gain = 8;
    public static double PosTol = 0.005;

    // Right -0.02 offset
    public static double RightFeed = 0.45, MiddleFeed = 0.42, LeftFeed = 0.42;
    public static double RightPrep = 0.68, MiddlePrep = 0.65, LeftPrep = 0.65, PrePrepPrep = 0.47;

    public static double RightHome = 0.58, MiddleHome= 0.55, LeftHome = 0.55, PrePrepHome = .62;
    //public static double RightTest = 0.41, MiddleTest = 0.41, LeftTest = 0.41, PrePrepTest = 0.41;
    public static double RightTest = 0.5, MiddleTest = 0.5, LeftTest = 0.5, PrePrepTest =  .62;
    public static double Tol = 0.001;
    public static long FeedDelay = 200;
    public static long FeedColorDelay = 150;
    public static long PrepDelay = 150;
    public static long PrepWaitDelay = 200;
    public static long PrepColorDelay = 150;

}
