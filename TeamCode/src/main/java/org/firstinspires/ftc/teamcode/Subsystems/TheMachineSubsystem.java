package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions.MachineIdleAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions.MachineIntakeAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions.MachineOuttakeAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions.MachineParkAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions.MachinePrepP1Action;
import org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions.MachinePrepP2Action;
import org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions.MachinePrepP3Action;
import org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions.MachinePrepP4Action;
import org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions.MachineRestAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions.MachineShakeAction;
import org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions.MachineShootFromP1Action;
import org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions.MachineShootFromP2Action;
import org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions.MachineShootFromP3Action;
import org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions.MachineShootFromP4Action;
import org.firstinspires.ftc.teamcode.Commands.StateActions.TheMachineActions.MachineShootFromPoseAction;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests.MachineIdleRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests.MachineIntakeRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests.MachineOuttakeRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests.MachineParkRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests.MachinePrepP1Request;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests.MachinePrepP2Request;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests.MachinePrepP3Request;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests.MachinePrepP4Request;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests.MachineRestRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests.MachineShakeRequest;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests.MachineShootFromP1Request;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests.MachineShootFromP2Request;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests.MachineShootFromP3Request;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests.MachineShootFromP4Request;
import org.firstinspires.ftc.teamcode.Commands.StateRequests.TheMachineRequests.MachineShootFromPoseRequest;
import org.firstinspires.ftc.teamcode.Utils.AllStates;
import org.firstinspires.ftc.teamcode.Utils.AllStates.MachineStates;
import org.firstinspires.ftc.teamcode.Constants.TheMachineConstants;

/**
 * TheMachineSubsystem acts as a supervisor or a "super-subsystem" that coordinates
 * the high-level states and interactions between the individual subsystems:
 * Intake, Funnel, Lift, and Shooter.
 */
public class TheMachineSubsystem extends SubsystemBase {

    // Subsystems managed by The Machine
    private final IntakeSubsystem m_intake;
    private final FunnelSubsystem m_funnel;
    private final LiftSubsystem m_lift;
    private final ShooterSubsystem m_shooter;
    
    // State tracking
    private MachineStates currentState;
    private MachineStates lastState;

    // Command requests (triggers for state changes)
    private final Command requestIdle;
    private final Command requestRest;
    private final Command requestPrepP1;
    private final Command requestPrepP2;
    private final Command requestPrepP3;
    private final Command requestPrepP4;
    private final Command requestShootFromP1;
    private final Command requestShootFromP2;
    private final Command requestShootFromP3;
    private final Command requestShootFromP4;
    private final Command requestShootFromPose;
    private final Command requestIntake;
    private final Command requestOuttake;
    private final Command requestPark;
    private final Command requestShake;


    // Actions corresponding to states
    private final Command idleAction;
    private final Command restAction;
    private final Command prepP1Action;
    private final Command prepP2Action;
    private final Command prepP3Action;
    private final Command prepP4Action;
    private final Command shootFromP1Action;
    private final Command shootFromP2Action;
    private final Command shootFromP3Action;
    private final Command shootFromP4Action;
    private final Command shootFromPoseAction;
    private final Command intakeAction;
    private final Command outtakeAction;
    private final Command parkAction;
    private final Command shakeAction;


    /**
     * Constructor initializes all subsystems and their corresponding commands.
     */
    public TheMachineSubsystem(HardwareMap hardwareMap)
    {
        m_intake = new IntakeSubsystem(hardwareMap);
        m_funnel = new FunnelSubsystem(hardwareMap);
        m_lift = new LiftSubsystem(hardwareMap);
        m_shooter = new ShooterSubsystem(hardwareMap);

        currentState = MachineStates.IDLE;
        lastState = MachineStates.IDLE;

        // Initialize requests
        requestIdle = new MachineIdleRequest(this);
        requestRest = new MachineRestRequest(this);
        requestPrepP1 = new MachinePrepP1Request(this);
        requestPrepP2 = new MachinePrepP2Request(this);
        requestPrepP3 = new MachinePrepP3Request(this);
        requestPrepP4 = new MachinePrepP4Request(this);
        requestShootFromP1 = new MachineShootFromP1Request(this);
        requestShootFromP2 = new MachineShootFromP2Request(this);
        requestShootFromP3 = new MachineShootFromP3Request(this);
        requestShootFromP4 = new MachineShootFromP4Request(this);
        requestShootFromPose = new MachineShootFromPoseRequest(this);
        requestIntake = new MachineIntakeRequest(this);
        requestOuttake = new MachineOuttakeRequest(this);
        requestPark = new MachineParkRequest(this);
        requestShake = new MachineShakeRequest(this);

        // Initialize actions
        idleAction = new MachineIdleAction(this);
        restAction = new MachineRestAction(this);
        prepP1Action = new MachinePrepP1Action(this);
        prepP2Action = new MachinePrepP2Action(this);
        prepP3Action = new MachinePrepP3Action(this);
        prepP4Action = new MachinePrepP4Action(this);
        shootFromP1Action = new MachineShootFromP1Action(this);
        shootFromP2Action = new MachineShootFromP2Action(this);
        shootFromP3Action = new MachineShootFromP3Action(this);
        shootFromP4Action = new MachineShootFromP4Action(this);
        shootFromPoseAction = new MachineShootFromPoseAction(this);
        intakeAction = new MachineIntakeAction(this);
        outtakeAction = new MachineOuttakeAction(this);
        parkAction = new MachineParkAction(this);
        shakeAction = new MachineShakeAction(this);


    }

    /**
     * Periodic method called by the scheduler.
     * Runs the state machine logic.
     */
    @Override
    public void periodic() {
        stateMachine();
    }

    /**
     * State machine logic that schedules the appropriate action based on the current state.
     * If the action for the current state is not running, it schedules it.
     */
    public void stateMachine()
    {
        switch (currentState)
        {
            case IDLE:
                if(!idleAction.isScheduled()) idleAction.schedule();
                break;
            case REST:
                if(!restAction.isScheduled()) restAction.schedule();
                break;
            case PREP_P1:
                if(!prepP1Action.isScheduled()) prepP1Action.schedule();
                break;
            case PREP_P2:
                if(!prepP2Action.isScheduled()) prepP2Action.schedule();
                break;
            case PREP_P3:
                if(!prepP3Action.isScheduled()) prepP3Action.schedule();
                break;
            case PREP_P4:
                if(!prepP4Action.isScheduled()) prepP4Action.schedule();
                break;
            case SHOOT_FROM_P1:
                if(!shootFromP1Action.isScheduled()) shootFromP1Action.schedule();
                break;
            case SHOOT_FROM_P2:
                if(!shootFromP2Action.isScheduled()) shootFromP2Action.schedule();
                break;
            case SHOOT_FROM_P3:
                if(!shootFromP3Action.isScheduled()) shootFromP3Action.schedule();
                break;
            case SHOOT_FROM_P4:
                if(!shootFromP4Action.isScheduled()) shootFromP4Action.schedule();
                break;
            case SHOOT_FROM_POSE:
                if(!shootFromPoseAction.isScheduled()) shootFromPoseAction.schedule();
                break;
            case INTAKE:
                if(!intakeAction.isScheduled()) intakeAction.schedule();
                break;
            case OUTTAKE:
                if(!outtakeAction.isScheduled()) outtakeAction.schedule();
                break;
            case PARK:
                if(!parkAction.isScheduled()) parkAction.schedule();
                break;
            case SHAKE:
                if(!shakeAction.isScheduled()) shakeAction.schedule();
                break;

        }
    }

    /**
     * Sets the current state of the machine.
     * Updates the last state before changing.
     * @param requestedState The new state to set.
     */
    public void setState(MachineStates requestedState)
    {
        if(currentState != requestedState) lastState = currentState;
        currentState = requestedState;
    }

    /**
     * Returns the current state of the machine.
     * @return The current MachineStates.
     */
    public MachineStates getState()
    {
        return currentState;
    }

    /**
     * Returns the previous state of the machine.
     * @return The last MachineStates.
     */
    public MachineStates getLastState()
    {
        return lastState;
    }

    public Command idleRequest()
    {
        return requestIdle;
    }

    public Command restRequest()
    {
        return requestRest;
    }

    public Command intakeRequest()
    {
        return requestIntake;
    }

    public Command outtakeRequest()
    {
        return requestOuttake;
    }

    public Command parkRequest()
    {
        return requestPark;
    }

    public Command shakeRequest()
    {
        return requestShake;
    }

    public Command shootFromPoseRequest()
    {
        return requestShootFromPose;
    }

    public Command shootFromP1Request()
    {
        return requestShootFromP1;
    }

    public Command shootFromP2Request()
    {
        return requestShootFromP2;
    }

    public Command shootFromP3Request()
    {
        return requestShootFromP3;
    }

    public Command shootFromP4Request()
    {
        return requestShootFromP4;
    }

    public Command prepP1Request()
    {
        return requestPrepP1;
    }

    public Command prepP2Request()
    {
        return requestPrepP2;
    }

    public Command prepP3Request()
    {
        return requestPrepP3;
    }

    public Command prepP4Request()
    {
        return requestPrepP4;
    }

    /**
     * Delegates intake requests to the IntakeSubsystem.
     * @param requestedState The intake state.
     * @return The command for the requested intake state.
     */
    public Command intakeRequest(AllStates.IntakeStates requestedState)
    {
        switch (requestedState)
        {
            case IDLE:
                return m_intake.idleRequest();
            case INTAKE:
                return m_intake.intakeRequest();
            case OUTTAKE:
                return  m_intake.outtakeRequest();
            case SHAKE:
                return  m_intake.shakeRequest();
            default:
                return null;
        }
    }

    /**
     * Delegates funnel requests to the FunnelSubsystem.
     * @param requestedState The funnel state.
     * @return The command for the requested funnel state.
     */
    public Command funnelRequest(AllStates.FunnelStates requestedState)
    {
        switch (requestedState) {
            case HOME:
                return m_funnel.homeRequest();
            case PREP:
                return m_funnel.prepRequest();
            case FEED:
                return m_funnel.feedRequest();
            case SHAKE:
                return m_funnel.shakeRequest();
            default:
                return null;

        }
    }

    /**
     * Delegates lift requests to the LiftSubsystem.
     * @param requestedState The lift state.
     * @return The command for the requested lift state.
     */
    public Command liftRequest(AllStates.LiftStates requestedState)
    {
        switch (requestedState)
        {
            case HOME:
                return m_lift.homeRequest();
            case OPEN:
                return m_lift.openRequest();
            case SHAKE:
                return m_lift.shakeRequest();
            default:
                return null;
        }
    }

    /**
     * Delegates shooter requests to the ShooterSubsystem.
     * @param requestedState The shooter state.
     * @return The command for the requested shooter state.
     */
    public Command shooterRequest(AllStates.ShooterStates requestedState)
    {
        switch (requestedState)
        {
            case ZERO:
                return m_shooter.zeroRequest();
            case REST:
                return m_shooter.restRequest();
            case SHOOT_P1:
                return m_shooter.shootP1Request();
            case SHOOT_P2:
                return m_shooter.shootP2Request();
            case SHOOT_P3:
                return m_shooter.shootP3Request();
            case SHOOT_P4:
                return m_shooter.shootP4Request();
            case SHOOT_FROM_POSE:
                return m_shooter.shootFromPoseRequest();
            case REVERSE:
                return m_shooter.reverseRequest();
            case SHAKE:
                return m_shooter.shakeRequest();
            default:
                return null;
        }
    }

    public IntakeSubsystem getIntakeSubsystem()
    {
        return m_intake;
    }

    public FunnelSubsystem getFunnelSubsystem()
    {
        return m_funnel;
    }

    public LiftSubsystem getLiftSubsystem()
    {
        return m_lift;
    }

    public ShooterSubsystem getShooterSubsystem()
    {
        return m_shooter;
    }

    /**
     * Helper command to wait until the shooter is ready.
     * @return A command that finishes when the shooter is ready or timeouts.
     */
    public Command waitForShooterToBeReady()
    {
        return m_shooter.waitForReady().withTimeout(TheMachineConstants.timeoutForShooterToBeReady);
    }

    /**
     * Checks if all relevant subsystems are ready.
     * @return true if shooter, funnel, and lift are ready.
     */
    public boolean isReady()
    {
        return m_shooter.isReady() && m_funnel.isReady() && m_lift.isReady();
    }

}
