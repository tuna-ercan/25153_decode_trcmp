package org.firstinspires.ftc.teamcode.PedroFiles;


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
import org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants;

public class Constants {
    /*public static FollowerConstants followerConstants = new FollowerConstants()
            .forwardZeroPowerAcceleration(-30.52833)
            .forwardZeroPowerAcceleration(-61.22487)
            .useSecondaryTranslationalPIDF(true)
            .useSecondaryHeadingPIDF(false)
            .useSecondaryDrivePIDF(false)
            .mass(16.2)
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.0026,0,0.00075,0.06,0.001))
            //.secondaryDrivePIDFCoefficients(new FilteredPIDFCoefficients(0.001,0,0.0005,0.06,0.001))
            .headingPIDFCoefficients(new PIDFCoefficients(0.7,0,0.007,0.028))
            .translationalPIDFCoefficients(new PIDFCoefficients(0.042,0,0.0031,0.05))
            .secondaryTranslationalPIDFCoefficients(new PIDFCoefficients(0.07,0,0.007,0.038));*/
    public static FollowerConstants followerConstants = new FollowerConstants()
            .forwardZeroPowerAcceleration(-48.23)
            .lateralZeroPowerAcceleration(-39.946)
            .useSecondaryTranslationalPIDF(true)
            .useSecondaryHeadingPIDF(false)
            .useSecondaryDrivePIDF(false)
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.00255,0,0.00064,0.06,0.001))
            .headingPIDFCoefficients(new PIDFCoefficients(0.7,0,0.007,0.028))
            .translationalPIDFCoefficients(new PIDFCoefficients(0.035,0,0.004,0.03))
            .secondaryTranslationalPIDFCoefficients(new PIDFCoefficients(0.073,0,0.011,0.03))
            .mass(16.2);

    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .xVelocity(71.78)
            .yVelocity(57.7)
            .rightFrontMotorName(DrivetrainConstants.RightFrontName)
            .rightRearMotorName(DrivetrainConstants.RightBackName)
            .leftRearMotorName(DrivetrainConstants.LeftBackName)
            .leftFrontMotorName(DrivetrainConstants.LeftFrontName)
            .leftFrontMotorDirection(DrivetrainConstants.LeftFrontDirection)
            .leftRearMotorDirection(DrivetrainConstants.LeftBackDirection)
            .rightFrontMotorDirection(DrivetrainConstants.RightFrontDirection)
            .rightRearMotorDirection(DrivetrainConstants.RightBackDirection);

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);
    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(-3.50881) //inch
            .strafePodX(1.24755906 ) //inch
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)

                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .pinpointLocalizer(localizerConstants)
                .build();
    }

}