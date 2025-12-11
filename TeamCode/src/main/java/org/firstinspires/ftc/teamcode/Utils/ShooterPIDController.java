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

    public void setPidCoefficients(PIDCoefficients pidCoefficients)
    {
        setPID(pidCoefficients.p, pidCoefficients.i, pidCoefficients.d);
    }

    public PIDCoefficients getPidCoefficients()
    {
        return new PIDCoefficients(getP(), getI(), getD());
    }

    public void  checkAndUpdateCoefficients(PIDCoefficients pidCoefficients)
    {
        if (pidCoefficients.p != getP() || pidCoefficients.i != getI() || pidCoefficients.d != getD()) setPidCoefficients(pidCoefficients);
    }

}
