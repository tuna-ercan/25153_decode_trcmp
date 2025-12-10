package org.firstinspires.ftc.teamcode.Utils;

/**
 * AllStates class containing enums for the various states of different robot subsystems.
 */
public class AllStates {

    /**
     * States for The Machine (Super-subsystem).
     */
    public enum MachineStates
    {
        IDLE,
        REST,
        PREP_P1,
        PREP_P2,
        PREP_P3,
        PREP_P4,
        SHOOT_FROM_P1,
        SHOOT_FROM_P2,
        SHOOT_FROM_P3,
        SHOOT_FROM_P4,
        SHOOT_FROM_POSE,
        INTAKE,
        OUTTAKE,
        PARK,
        SHAKE,
        TEST
    }

    /**
     * States for the Drivetrain subsystem.
     */
    public enum DrivetrainStates
    {
        IDLE,
        TELEOP,
        TELEOP_PATH,
        AUTO,
        TEST

    };

    /**
     * States for the Intake subsystem.
     */
    public enum IntakeStates
    {
        IDLE,
        INTAKE,
        OUTTAKE,
        SHAKE,
        TEST
    }

    /**
     * States for the Funnel subsystem.
     */
    public enum FunnelStates
    {
        HOME,
        PREP,
        FEED,
        SHAKE,
        TEST
    }

    /**
     * States for the Shooter subsystem.
     */
    public enum ShooterStates
    {
        ZERO,
        REST,
        SHOOT_P1,
        SHOOT_P2,
        SHOOT_P3,
        SHOOT_P4,
        SHOOT_FROM_POSE,
        REVERSE,
        SHAKE,
        TEST
    }

    /**
     * States for the RGB subsystem.
     */
    public enum RGBStates
    {
        WHITE,
        RED,
        GREEN,
        BLUE,
        OFF

    };
}
