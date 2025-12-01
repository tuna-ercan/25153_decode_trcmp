package org.firstinspires.ftc.teamcode.Constants;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.qualcomm.robotcore.hardware.Servo;

@Configurable
public class FunnelConstants {
    @IgnoreConfigurable
    public static String ColorSensorL = "c1";
    @IgnoreConfigurable
    public static String ColorSensorR = "c2";
    @IgnoreConfigurable
    public static String ServoR = "servoR";
    @IgnoreConfigurable
    public static String ServoM = "servoM";
    @IgnoreConfigurable
    public static String ServoL = "servoL";
    @IgnoreConfigurable
    public static Servo.Direction RightDirection = Servo.Direction.FORWARD;
    @IgnoreConfigurable
    public static Servo.Direction LeftDirection = Servo.Direction.FORWARD;
    @IgnoreConfigurable
    public static Servo.Direction MiddleDirection = Servo.Direction.FORWARD;
    public static float Gain = 8;
    public static double PosTol = 0.005;

    // Right -0.02 offset
    public static double RightFeed = 0.6, MiddleFeed = 0.625, LeftFeed = 0.625;
    public static double RightPrep = 0.525, MiddlePrep = 0.55, LeftPrep = 0.55;

    public static double RightPreFeedPrep = 0.44, MiddlePreFeedPrep = 0.465, LeftPreFeedPrep = 0.465;

    public static double RightHome = 0.395, MiddleHome= 0.41, LeftHome = 0.41;
    public static double RightTest = 0.41, MiddleTest = 0.41, LeftTest = 0.41;
    public static double Tol = 0.001;
    public static long FeedDelay = 150;
    public static long FeedColorDelay = 150;
    public static long PrepDelay = 150;
    public static long PrepColorDelay = 150;

}
