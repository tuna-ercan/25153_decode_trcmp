package org.firstinspires.ftc.teamcode.Constants;

import com.qualcomm.robotcore.hardware.Servo;

public class LiftConstants {
    public static String LeftLiftName = "servoLeftLift";
    public static String RightLiftName = "servoRightLift";

    public static double HomePos = 0;
    public static double OpenPos = 0;

    public static Servo.Direction LeftLiftDirection = Servo.Direction.FORWARD;
    public static Servo.Direction RightLiftDirection = Servo.Direction.FORWARD;
    public static double Tol = 0.001;
}
