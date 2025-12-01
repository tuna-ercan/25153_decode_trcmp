package org.firstinspires.ftc.teamcode.Utils;

public class AllStates {

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

    public enum DrivetrainStates
    {
        IDLE,
        TELEOP,
        TELEOP_PATH,
        AUTO

    };

    public enum IntakeStates
    {
        IDLE,
        INTAKE,
        OUTTAKE,
        SHAKE,
        TEST
    }

    public enum FunnelStates
    {
        HOME,
        PREP,
        FEED,
        SHAKE,
        TEST
    }

    public enum LiftStates
    {
        HOME,
        OPEN,
        SHAKE,
        TEST

    }

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
}
