package org.firstinspires.ftc.teamcode.Positions;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;


@Configurable
public class BluePositions {
    public static Pose START_POSE = new Pose(61.335,11.318,Math.toRadians(90));  //new Pose(63.437,11.28,Math.toRadians(90));
    public static Pose SHOOT_FOCUS_POINT = new Pose(10,135);
    public static Pose SHOOT_P1 = new Pose(47.7, 95.3, 2.3387412 ); // 2.31 is in radians 2.22
    public static Pose SHOOT_P2 = new Pose(63, 130, 3.09); // 3.15 old
    public static Pose SHOOT_P3 = new Pose(80, 88, Math.toRadians(145));
    public static Pose SHOOT_P4 = new Pose(55, 14, 1.945);
    public static Pose SHOOT_P5 = new Pose(54.8, 82, Math.toRadians(133));
    public static Pose SHOOT_TEST = new Pose(60, 84);
    public static Pose SHOOT_TEST_FOCUS_POINT = new Pose(8,133);

}
