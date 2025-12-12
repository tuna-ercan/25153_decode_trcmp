package org.firstinspires.ftc.teamcode.Utils;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.pedropathing.control.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

/**
 * Custom PID controller wrapper for the shooter.
 * Extends the FTCLib PIDController.
 */
public class DrivePIDController extends PIDFController
{
    /**
     * Creates a new ShooterPIDController using PIDCoefficients.
     * @param pidfCoefficients The PID coefficients (p, i, d).
     */
    public DrivePIDController(PIDFCoefficients pidfCoefficients) {
        super(pidfCoefficients.P, pidfCoefficients.I, pidfCoefficients.D, pidfCoefficients.F);
    }

    public void setPidCoefficients(PIDFCoefficients pidfCoefficients)
    {
        setPIDF(pidfCoefficients.P, pidfCoefficients.I, pidfCoefficients.D, pidfCoefficients.F);
    }

    public PIDCoefficients getPidCoefficients()
    {
        return new PIDCoefficients(getP(), getI(), getD());
    }

    public void  checkAndUpdateCoefficients(PIDFCoefficients pidfCoefficients)
    {
        if (pidfCoefficients.P != getP() || pidfCoefficients.I != getI() || pidfCoefficients.D != getD()|| pidfCoefficients.F != getF()) setPidCoefficients(pidfCoefficients);
    }

}
