package org.firstinspires.ftc.teamcode.Positions;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;

//@Configurable
public class RedPositions {

    /*
    public static Pose START_POSE = new Pose(82.665, 11.318, Math.toRadians(90));
    public static Pose SHOOT_FOCUS_POINT = new Pose(134, 130);
    public static Pose SHOOT_P1 = new Pose(96.3, 95.3,0.76794487); //0.65
    public static Pose SHOOT_P2 = new Pose(81, 130, 1.51);
    public static Pose SHOOT_P3 = new Pose(64, 88, 1.03);
    public static Pose SHOOT_P4 = new Pose(89, 12, 0.3592); //0.48
    public static Pose SHOOT_P5 = new Pose(89.2, 62, 0.739);
    public static Pose SHOOT_TEST = new Pose(60, 84);
    public static Pose SHOOT_TEST_FOCUS_POINT = new Pose(8, 130);
    */

    public static Pose START_POSE = mirrorPose(BluePositions.START_POSE);
    public static Pose ZERO_POSE = new Pose(0.5+17.126,11.318,Math.toRadians(90));  //new Pose(63.437,11.28,Math.toRadians(90));

    public static Pose SHOOT_FOCUS_POINT = mirrorPose(BluePositions.SHOOT_FOCUS_POINT);
    public static Pose SHOOT_P1 = mirrorPose(BluePositions.SHOOT_P1);
    public static Pose SHOOT_P2 = mirrorPose(BluePositions.SHOOT_P2);
    public static Pose SHOOT_P3 = mirrorPose(BluePositions.SHOOT_P3);
    public static Pose SHOOT_P4 = mirrorPose(BluePositions.SHOOT_P4);

    public static Pose MirrorP5 = mirrorPose(BluePositions.SHOOT_P5);
    public static Pose SHOOT_P5 = MirrorP5.withX(MirrorP5.getX() - 2);
    public static Pose SHOOT_TEST = mirrorPose(BluePositions.SHOOT_TEST);
    public static Pose SHOOT_TEST_FOCUS_POINT = mirrorPose(BluePositions.SHOOT_TEST_FOCUS_POINT);

    public static Pose FOCUS_POINT = mirrorPose(BluePositions.FOCUS_POINT);

    public static Pose SHOOT_P4_2 = mirrorPose(BluePositions.SHOOT_P4_2);

    public static Pose mirrorPose(Pose p)
    {
        return new Pose(141-p.getX(), p.getY(), Math.PI - p.getHeading());
    }
}

