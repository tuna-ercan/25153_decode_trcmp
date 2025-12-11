package org.firstinspires.ftc.teamcode.Positions;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;

@Configurable
public class RedPositions {
    public static Pose START_POSE = new Pose(82.665, 11.318, Math.toRadians(90));
    public static Pose SHOOT_FOCUS_POINT = new Pose(134, 130);
    public static Pose SHOOT_P1 = new Pose(96.3, 95.3,0.76794487); //0.65
    public static Pose SHOOT_P2 = new Pose(81, 130, 1.58);
    public static Pose SHOOT_P3 = new Pose(64, 88, 1.03);
    public static Pose SHOOT_P4 = new Pose(89, 12, 0.48);
    public static Pose SHOOT_P5 = new Pose(89.2, 62, 0.739);
    public static Pose SHOOT_TEST = new Pose(60, 84);
    public static Pose SHOOT_TEST_FOCUS_POINT = new Pose(8, 130);
}

