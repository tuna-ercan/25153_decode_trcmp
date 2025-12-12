package org.firstinspires.ftc.teamcode.Constants;

import com.bylazar.configurables.annotations.Configurable;

/**
 * Constants for the "The Machine" super-subsystem.
 */
//@Configurable
public class TheMachineConstants {
    /**
     * Time in milliseconds to wait for the shooter to reach target velocity/hood position
     * before proceeding with the shot in an automated sequence.
     */
    public static long timeoutForShooterToBeReady = 2000;

    public static long waitAfterFeed = 750;

    public static double shootTimeoutConstraint = 100;

}
