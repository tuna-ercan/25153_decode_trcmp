package org.firstinspires.ftc.teamcode.Utils;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

/**
 * Custom PID controller wrapper for the shooter.
 * Extends the FTCLib PIDController.
 */
public class ShooterPIDController extends PIDController
{
    /**
     * Creates a new ShooterPIDController using PIDCoefficients.
     * @param pidCoefficients The PID coefficients (p, i, d).
     */
    public ShooterPIDController(PIDCoefficients pidCoefficients) {
        super(pidCoefficients.p, pidCoefficients.i, pidCoefficients.d);
    }

}
