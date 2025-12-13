package org.firstinspires.ftc.teamcode.Utils;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

/**
 * Custom PID controller wrapper for the shooter.
 * Extends the FTCLib PIDController.
 */
public class ShooterPIDFController extends PIDFController
{
    /**
     * Creates a new ShooterPIDController using PIDCoefficients.
     * @param pidfCoefficients The PID coefficients (p, i, d).
     */
    public ShooterPIDFController(PIDFCoefficients pidfCoefficients) {
        super(pidfCoefficients.p, pidfCoefficients.i, pidfCoefficients.d,pidfCoefficients.f);
    }

    public void setPidfCoefficients(PIDFCoefficients pidfCoefficients)
    {
        setPIDF(pidfCoefficients.p, pidfCoefficients.i, pidfCoefficients.d, pidfCoefficients.f);
    }

    public PIDFCoefficients getPidfCoefficients()
    {
        return new PIDFCoefficients(getP(), getI(), getD(), getF());
    }

    public void  checkAndUpdateCoefficients(PIDFCoefficients pidfCoefficients)
    {
        if (pidfCoefficients.p != getP() || pidfCoefficients.i != getI() || pidfCoefficients.d != getF() || pidfCoefficients.f != getF()) setPidfCoefficients(pidfCoefficients);
    }

}
