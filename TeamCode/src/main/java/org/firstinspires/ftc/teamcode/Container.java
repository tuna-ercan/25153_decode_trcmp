package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.teamcode.Utils.AllStates;

import java.util.List;

/**
 * Container class to hold global variables and states for the robot.
 * This class acts as a central repository for shared data.
 */
public class Container
{
    /**
     * Flag to indicate if the robot is on the Blue alliance.
     * Default is true.
     */
    public static boolean isBlue = true;

    public static boolean isTeleop = true;

    /**
     * Current shooting combination
     * Updated by the LimelightHandler.
     */
    public static String colorCombination = null;

    public static boolean useColor = false;

    /**
     * Current pose of the robot on the field.
     * Updated by the DrivetrainSubsystem.
     */
    public static Pose robotPose = null;

    /**
     * The pose of the robot at the end of the autonomous period.
     * Used to transfer pose to TeleOp.
     */
    public static Pose autoEndPose = null;
}
