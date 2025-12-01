package org.firstinspires.ftc.teamcode.Constants;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

/**
 * Constants for the GoBilda Pinpoint Odometry Computer.
 * Includes hardware names, pod types, directions, and offsets.
 */
@Configurable
public class OdometryConstants {
        @IgnoreConfigurable
        public static String PINPOINT_NAME = "pinpoint";
        @IgnoreConfigurable
        public static GoBildaPinpointDriver.GoBildaOdometryPods POD_TYPE = GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD;
        @IgnoreConfigurable
        public static GoBildaPinpointDriver.EncoderDirection ENCODER_DIRECTION_X = GoBildaPinpointDriver.EncoderDirection.REVERSED;
        @IgnoreConfigurable
        public static GoBildaPinpointDriver.EncoderDirection ENCODER_DIRECTION_Y = GoBildaPinpointDriver.EncoderDirection.FORWARD;
        
        public static double X_OFFSET_CM = 18.7225;
        public static double X_OFFSET_INCH = 7.371062992;
        public static double Y_OFFSET_CM = 1.29;
        public static double Y_OFFSET_INCH = 0.507874;

}
