package org.firstinspires.ftc.teamcode.Positions;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;


@Configurable
public class BluePositions {
    public static Pose START_POSE = new Pose(63.437,11.28,Math.toRadians(90)); //y 6.6 is old(without intake)
    public static Pose SHOOT_FOCUS_POINT = new Pose(10,135);
    public static Pose SHOOT_P1 = new Pose(47.7, 95.3, 2.31); // 2.44 is in radians
    public static Pose SHOOT_P2 = new Pose(63, 130, 3);
    public static Pose SHOOT_P3 = new Pose(80, 88, 2.55);
    public static Pose SHOOT_P4 = new Pose(60, 13, 1.96);
    public static Pose SHOOT_TEST = new Pose(60, 84);
    public static Pose SHOOT_TEST_FOCUS_POINT = new Pose(8,133);

}
