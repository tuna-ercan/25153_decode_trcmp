package org.firstinspires.ftc.teamcode.Constants;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.qualcomm.robotcore.hardware.Servo;

@Configurable
public class LiftConstants {
    @IgnoreConfigurable
    public static String LeftLiftName = "servoLeftLift";
    @IgnoreConfigurable
    public static String RightLiftName = "servoRightLift";
    @IgnoreConfigurable
    public static Servo.Direction LeftLiftDirection = Servo.Direction.FORWARD;
    @IgnoreConfigurable
    public static Servo.Direction RightLiftDirection = Servo.Direction.FORWARD;

    public static double HomePos = 0;
    public static double OpenPos = 0;
    public static double TestPos = 0;
    public static double Tol = 0.001;
}
