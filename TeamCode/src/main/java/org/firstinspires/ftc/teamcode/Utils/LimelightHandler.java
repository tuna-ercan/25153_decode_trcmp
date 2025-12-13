package org.firstinspires.ftc.teamcode.Utils;

//import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.pedropathing.ftc.FTCCoordinates;
import com.pedropathing.geometry.Pose;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.Container;

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
        ll.updateRobotOrientation(Math.toDegrees(Container.headingRadians));
        LLResult result = ll.getLatestResult();
        if (result != null) {
            if (result.isValid()) {
                this.result = result;
            }
        }
        if (Container.colorCombination==null&&(getCombination().length() > 1)){
            Container.colorCombination = getCombination();
        }
    }

    public Pose3D getPose() {
        ll.pipelineSwitch(1);
        Pose3D botpose = new Pose3D(new Position(), new YawPitchRollAngles(AngleUnit.RADIANS,0,0,0, 0));
        if (result != null){
        if ( result.getBotpose_MT2() != null) botpose = result.getBotpose_MT2();}
        return botpose;
    }


    public Pose getPosePedro() {
        Pose3D botpose = getPose();
        return FTCCoordinates.INSTANCE.convertToPedro(new Pose(botpose.getPosition().x*39.3700787 , botpose.getPosition().y*39.3700787 , botpose.getOrientation().getYaw(AngleUnit.RADIANS)));
    }

    public Pose3D getCamPose()
    {
        ll.pipelineSwitch(1);
        Pose3D camPose = new Pose3D(new Position(), new YawPitchRollAngles(AngleUnit.RADIANS,0,0,0, 0));
        if (result != null){
            if ( result.getFiducialResults().get(0).getCameraPoseTargetSpace() != null) camPose = result.getFiducialResults().get(0).getCameraPoseTargetSpace();}
        return camPose;
    }

    public Pose getCamPosePedro()
    {
        Pose3D camPose = getCamPose();
        return FTCCoordinates.INSTANCE.convertToPedro(new Pose(camPose.getPosition().x*39.3700787 , camPose.getPosition().y*39.3700787 , camPose.getOrientation().getYaw(AngleUnit.RADIANS)));
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
                    } else if (id == 23){
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
