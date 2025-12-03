package org.firstinspires.ftc.teamcode.Utils;

//import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

import java.util.List;

public class LimelightHandler {
    private Limelight3A ll;
    private LLResult result;

    public LimelightHandler(HardwareMap hardwareMap) {
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
        ll.pipelineSwitch(1);
        return result.getBotpose();
    }
    public String getCombination() {
        ll.pipelineSwitch(0);
        String combination = "x";
        if (result != null) {
            List<LLResultTypes.FiducialResult> fiducials = result.getFiducialResults();
            for (LLResultTypes.FiducialResult fiducial : fiducials) {
                int id = fiducial.getFiducialId(); // The ID number of the fiducial
                if (id == 21) {
                    combination = "GPP";
                } else if (id == 22) {
                    combination = "PGP";
                } else {
                    combination = "PPG";
                }
            }
        }
        return combination;
    }

    public LLResult getResult() {
        return result;
    }
}
