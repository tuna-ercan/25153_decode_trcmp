package org.firstinspires.ftc.teamcode;

import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.teamcode.Constants.AllStates;

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
     * Current state of the drivetrain subsystem.
     */
    public static AllStates.DrivetrainStates drivetrainState = AllStates.DrivetrainStates.IDLE;

    /**
     * Current state of the intake subsystem.
     */
    public static AllStates.IntakeStates intakeState = AllStates.IntakeStates.IDLE;

    /**
     * Current state of the funnel subsystem.
     */
    public static AllStates.FunnelStates funnelState = AllStates.FunnelStates.HOME;

    /**
     * Current state of the lift subsystem.
     */
    public static AllStates.LiftStates liftState = AllStates.LiftStates.HOME;

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
