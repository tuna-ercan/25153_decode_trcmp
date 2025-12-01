package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathBuilder;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Utils.AllStates.DrivetrainStates;
import org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants;
import org.firstinspires.ftc.teamcode.Constants.PedroConstants;
import org.firstinspires.ftc.teamcode.Container;
import org.firstinspires.ftc.teamcode.Positions.BluePositions;
import org.firstinspires.ftc.teamcode.Positions.RedPositions;

/**
 * Subsystem responsible for the robot's drivetrain.
 * Handles path following using PedroPathing and teleop driving.
 */
public class DrivetrainSubsystem extends SubsystemBase
{
    private final Follower follower;
    private final IMU imu;
    private final IMU.Parameters parameters;
    private final Pose startPose;
    private boolean isStartPoseSet = false;
    private boolean isBusy = false;


    private DrivetrainStates currentState;
    private DrivetrainStates lastState;



    /**
     * Creates a new DrivetrainSubsystem.
     * Initializes the IMU and the Pedro Pathing Follower.
     * Sets the starting pose based on the alliance color.
     */
    public DrivetrainSubsystem()
    {
        parameters = new IMU.Parameters(DrivetrainConstants.RevHubOrientation);
        imu = hardwareMap.get(IMU.class, DrivetrainConstants.ImuName);
        imu.initialize(parameters);

        currentState = DrivetrainStates.IDLE;
        lastState = DrivetrainStates.IDLE;

        follower = PedroConstants.createFollower(hardwareMap);
        if(Container.isBlue) startPose = BluePositions.START_POSE;
        else startPose = RedPositions.START_POSE;

    }

    @Override
    public void periodic()
    {
        // This method will be called once per scheduler run

        //Setting the starting pose | Update follower
        if (!isStartPoseSet)
        {
            follower.setStartingPose(startPose);
            updateFollower();
            if (follower.atPose(startPose, 3, 3, 3)) isStartPoseSet = true;
        }
        else updateFollower();

        isBusy = follower.isBusy();

        Container.robotPose = follower.getPose();
        //stateMachine();
    }

    /**
     * Executes actions based on the current state of the drivetrain.
     * This method is called periodically.
     */
    public void stateMachine()
    {
        switch (currentState)
        {
            case IDLE:
            case TELEOP:
            case AUTO:
            case TEST:
                break;
        }
    }

    /**
     * Sets the drivetrain state and updates the last state.
     * @param requestedState The new state.
     */
    public void setState(DrivetrainStates requestedState)
    {
        if(currentState != requestedState) lastState = currentState;
        currentState = requestedState;
    }

    /**
     * Creates a PathBuilder from the follower.
     * @return A new PathBuilder instance.
     */
    public PathBuilder pathBuilder()
    {
        return follower.pathBuilder();
    }

    /**
     * Checks if the robot is at the specified pose within tolerances.
     * @param pose The target pose.
     * @return True if at pose, false otherwise.
     */
    public boolean atPose(Pose pose)
    {
        return follower.atPose(pose, DrivetrainConstants.TolX, DrivetrainConstants.TolY, DrivetrainConstants.TolH);
    }

    /**
     * Checks if the robot has reached the target heading.
     * @return True if heading error is within tolerance.
     */
    public boolean headingReached()
    {
        return Math.abs(follower.getHeadingError()) < DrivetrainConstants.TolH;

    }

    /**
     * Gets the current state of the drivetrain.
     * @return The current DrivetrainStates.
     */
    public DrivetrainStates getState()
    {
        return currentState;
    }

    /**
     * Gets the previous state of the drivetrain.
     * @return The last DrivetrainStates.
     */
    public DrivetrainStates getLastState()
    {
        return lastState;
    }

    /**
     * Gets the current pose of the robot.
     * @return The current Pose.
     */
    public Pose getPose()
    {
        return follower.getPose();
    }

    /**
     * Sets the teleop drive controls to field-centric mode.
     * @param gamepadEx The gamepad input.
     */
    public void setTeleopDriveFieldCentric(GamepadEx gamepadEx)
    {
        follower.setTeleOpDrive(
                -gamepadEx.getLeftY(),
                gamepadEx.getLeftX(),
                -gamepadEx.getRightX()*DrivetrainConstants.TeleopRotationCoefficient,
                false);
    }

    /**
     * Sets the teleop drive controls to robot-centric mode.
     * @param gamepadEx The gamepad input.
     */
    public void setTeleopDriveRobotCentric(GamepadEx gamepadEx)
    {
        follower.setTeleOpDrive(
                -gamepadEx.getLeftY(),
                gamepadEx.getLeftX(),
                -gamepadEx.getRightX()*DrivetrainConstants.TeleopRotationCoefficient,
                true);
    }

    /**
     * Starts the teleop drive control and updates the state to TELEOP.
     */
    public void startTeleopDrive()
    {
        follower.startTeleopDrive();
        setState(DrivetrainStates.TELEOP);
    }

    /**
     * Commands the drivetrain to follow a path in autonomous mode.
     * Updates the state to AUTO.
     * @param pc The PathChain to follow.
     */
    public void followPathAuto(PathChain pc)
    {
        setState(DrivetrainStates.AUTO);
        follower.followPath(pc);
    }

    /**
     * Commands the drivetrain to follow a path in teleop mode.
     * Updates the state to AUTO.
     * @param pc The PathChain to follow.
     */
    public void followPathTeleop(PathChain pc)
    {
        setState(DrivetrainStates.TELEOP_PATH);
        follower.followPath(pc);
    }

    /**
     * Commands the drivetrain to follow a path in test mode.
     * Updates the state to TEST.
     * @param pc The PathChain to follow.
     */
    public void followPathTest(PathChain pc)
    {
        setState(DrivetrainStates.TEST);
        follower.followPath(pc);
    }


    /**
     * Updates the Pedro Pathing follower.
     * This should be called in the loop/periodic method.
     */
    public void updateFollower()
    {
        follower.update();
    }

    /**
     * Checks if the drivetrain is currently busy (e.g., following a path).
     * @return true if busy, false otherwise.
     */
    public boolean isBusy()
    {
        return isBusy;
    }
}
