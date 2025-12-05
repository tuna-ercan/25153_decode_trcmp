package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.StateActions.IntakeActions.IntakeIdleAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.IntakeActions.IntakeIntakeAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.IntakeActions.IntakeOuttakeAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.IntakeActions.IntakeShakeAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.IntakeActions.IntakeTestAction;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.IntakeRequests.IntakeIdleRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.IntakeRequests.IntakeIntakeRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.IntakeRequests.IntakeOuttakeRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.IntakeRequests.IntakeShakeRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.IntakeRequests.IntakeTestRequest;
import org.firstinspires.ftc.teamcode.Utils.AllStates.IntakeStates;
import org.firstinspires.ftc.teamcode.Constants.IntakeConstants;

/**
 * Subsystem responsible for the intake mechanism.
 */
public class IntakeSubsystem extends SubsystemBase {

    private final DcMotor intakeMotor;

    private IntakeStates currentState;
    private IntakeStates lastState;

    private final Command idleAction;
    private final Command intakeAction;
    private final Command outtakeAction;
    private final Command shakeAction;
    private final Command testAction;

    /**
     * Creates a new IntakeSubsystem.
     * Initializes the intake motor and commands.
     */
    public IntakeSubsystem(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, IntakeConstants.IntakeMotorName);
        intakeMotor.setDirection(IntakeConstants.IntakeMotorDirection);

        currentState = IntakeStates.IDLE;
        lastState = IntakeStates.IDLE;

        idleAction = new IntakeIdleAction(this);
        intakeAction = new IntakeIntakeAction(this);
        outtakeAction = new IntakeOuttakeAction(this);
        shakeAction = new IntakeShakeAction(this);
        testAction = new IntakeTestAction(this);
    }

    @Override
    public void periodic() {
        stateMachine();
    }

    /**
     * Executes actions based on the current state of the intake.
     */
    public void stateMachine() {
        switch (currentState) {
            case IDLE:
                if(!idleAction.isScheduled()) idleAction.schedule();
                break;
            case INTAKE:
                if(!intakeAction.isScheduled()) intakeAction.schedule();
                break;
            case OUTTAKE:
                if(!outtakeAction.isScheduled()) outtakeAction.schedule();
                break;
            case SHAKE:
                if(!shakeAction.isScheduled()) shakeAction.schedule();
                break;
            case TEST:
                if(!testAction.isScheduled()) testAction.schedule();
                break;
        }
    }


    /**
     * Requests a change in the intake state.
     * @param requestedState The new state to request.
     */
    public void requestState(IntakeStates requestedState) {
        setState(requestedState);
    }

    /**
     * Sets the intake state and updates the last state.
     * @param requestedState The new state.
     */
    public void setState(IntakeStates requestedState) {
        if(currentState != requestedState) lastState = currentState;
        currentState = requestedState;
    }

    /**
     * Gets the current state of the intake.
     * @return The current IntakeStates.
     */
    public IntakeStates getState() {
        return currentState;
    }

    /**
     * Gets the previous state of the intake.
     * @return The last IntakeStates.
     */
    public IntakeStates getLastState() {
        return lastState;
    }

    public Command idleRequest() {
        return new IntakeIdleRequest(this);
    }

    public Command intakeRequest() {
        return new IntakeIntakeRequest(this);
    }

    public Command outtakeRequest() {
        return new IntakeOuttakeRequest(this);
    }

    public Command shakeRequest() {
        return new IntakeShakeRequest(this);
    }

    public Command testRequest() {
        return new IntakeTestRequest(this);
    }

    public void stop() {
        intakeMotor.setPower(0);
    }

    public void intake() {
        intakeMotor.setPower(IntakeConstants.IntakeIntakePower);
    }

    public void outtake() {
        intakeMotor.setPower(IntakeConstants.IntakeReversePower);
    }

    public void shake() {
        intakeMotor.setPower(0);
    }

    public void test() {
        intakeMotor.setPower(IntakeConstants.IntakeTestPower);
    }
}
