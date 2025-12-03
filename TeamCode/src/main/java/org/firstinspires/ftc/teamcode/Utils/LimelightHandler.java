package org.firstinspires.ftc.teamcode.Utils;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

public class LimelightHandler {
    private Limelight3A ll;
    private LLResult result;

    public LimelightHandler() {
        ll = hardwareMap.get(Limelight3A.class, "limelight");
        ll.start();
    }

    public void updateResult()
    {
        LLResult result = ll.getLatestResult();
        if (result != null) {
            if (result.isValid()) {
                this.result = result;
        }
    }
    }

    public Pose3D getPose() {
        return result.getBotpose();
    }

    public LLResult getResult() {
        return result;
    }
}
