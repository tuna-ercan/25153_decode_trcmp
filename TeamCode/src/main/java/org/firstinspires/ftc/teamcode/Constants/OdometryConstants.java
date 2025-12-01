package org.firstinspires.ftc.teamcode.Constants;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

public class OdometryConstants {
        public static String PINPOINT_NAME = "pinpoint";
        public static GoBildaPinpointDriver.GoBildaOdometryPods POD_TYPE = GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD;
        public static double X_OFFSET_CM = 18.7225;
        public static double X_OFFSET_INCH = 7.371062992;
        public static double Y_OFFSET_CM = 1.29;
        public static double Y_OFFSET_INCH = 0.507874;
        public static GoBildaPinpointDriver.EncoderDirection ENCODER_DIRECTION_X = GoBildaPinpointDriver.EncoderDirection.REVERSED;
        public static GoBildaPinpointDriver.EncoderDirection ENCODER_DIRECTION_Y = GoBildaPinpointDriver.EncoderDirection.FORWARD;
}
