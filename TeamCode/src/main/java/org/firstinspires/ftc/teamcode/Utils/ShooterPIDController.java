package org.firstinspires.ftc.teamcode.Utils;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

public class ShooterPIDController extends PIDController {
    public ShooterPIDController(PIDCoefficients coefficients) {
        super(coefficients.p, coefficients.i, coefficients.d);
    }
}
