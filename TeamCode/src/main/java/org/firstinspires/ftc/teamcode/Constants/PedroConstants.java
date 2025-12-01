
package org.firstinspires.ftc.teamcode.Constants;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class PedroConstants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .forwardZeroPowerAcceleration(-30.52833)
            .forwardZeroPowerAcceleration(-61.22487)
            .useSecondaryTranslationalPIDF(true)
            .useSecondaryHeadingPIDF(false)
            .useSecondaryDrivePIDF(false)
            .mass(15.2407)
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.0026,0,0.00075,0.06,0.001))
            //.secondaryDrivePIDFCoefficients(new FilteredPIDFCoefficients(0.001,0,0.0005,0.06,0.001))
            .headingPIDFCoefficients(new PIDFCoefficients(0.7,0,0.007,0.028))
            .translationalPIDFCoefficients(new PIDFCoefficients(0.042,0,0.0031,0.05))
            .secondaryTranslationalPIDFCoefficients(new PIDFCoefficients(0.07,0,0.007,0.038));

    public static MecanumConstants driveConstants;

    static {
        driveConstants = new MecanumConstants();
        driveConstants.maxPower(1);
        driveConstants.xVelocity(76.72564);
        driveConstants.yVelocity(60.4115797);
        driveConstants.rightFrontMotorName(DrivetrainConstants.RightFrontName);
        driveConstants.rightRearMotorName(DrivetrainConstants.RightBackName);
        driveConstants.leftRearMotorName(DrivetrainConstants.LeftBackName);
        driveConstants.leftFrontMotorName(DrivetrainConstants.LeftFrontName);
        driveConstants.leftFrontMotorDirection(DrivetrainConstants.LeftFrontDirection);
        driveConstants.leftRearMotorDirection(DrivetrainConstants.LeftBackDirection);
        driveConstants.rightFrontMotorDirection(DrivetrainConstants.RightFrontDirection);
        driveConstants.rightRearMotorDirection(DrivetrainConstants.RightBackDirection);
    }

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);
    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(org.firstinspires.ftc.teamcode.Constants.OdometryConstants.X_OFFSET_INCH)
            .strafePodX(org.firstinspires.ftc.teamcode.Constants.OdometryConstants.Y_OFFSET_INCH)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);
    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)

                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .pinpointLocalizer(localizerConstants)
                .build();
    }
    public static Pose Position(Pose startingPose, Pose goalPose) {
        return new Pose(goalPose.getX()- startingPose.getX(), startingPose.getY()- goalPose.getY(),goalPose.getHeading());
    }
}
