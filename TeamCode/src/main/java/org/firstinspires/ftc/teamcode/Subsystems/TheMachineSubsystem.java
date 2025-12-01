package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SubsystemBase;

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
import org.firstinspires.ftc.teamcode.Constants.AllStates;
import org.firstinspires.ftc.teamcode.Constants.AllStates.MachineStates;

public class TheMachineSubsystem extends SubsystemBase {

    private final IntakeSubsystem m_intake;
    private final FunnelSubsystem m_funnel;
    private final LiftSubsystem m_lift;
    private final ShooterSubsystem m_shooter;
    private MachineStates currentState;
    private MachineStates lastState;

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




    public TheMachineSubsystem()
    {
        m_intake = new IntakeSubsystem();
        m_funnel = new FunnelSubsystem();
        m_lift = new LiftSubsystem();
        m_shooter = new ShooterSubsystem();

        currentState = MachineStates.IDLE;
        lastState = MachineStates.IDLE;

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

    @Override
    public void periodic() {
        stateMachine();
    }

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

    public void setState(MachineStates requestedState)
    {
        if(currentState != requestedState) lastState = currentState;
        currentState = requestedState;
    }

    public MachineStates getState()
    {
        return currentState;
    }
    public MachineStates getLastState()
    {
        return lastState;
    }

    public Command machineRequest(MachineStates requestedState)
    {
        switch (requestedState)
        {
            case IDLE:
                return requestIdle;
            case REST:
                return requestRest;
            case PREP_P1:
                return requestPrepP1;
            case PREP_P2:
                return requestPrepP2;
            case PREP_P3:
                return requestPrepP3;
            case PREP_P4:
                return requestPrepP4;
            case SHOOT_FROM_P1:
                return requestShootFromP1;
            case SHOOT_FROM_P2:
                return requestShootFromP2;
            case SHOOT_FROM_P3:
                return requestShootFromP3;
            case SHOOT_FROM_P4:
                return requestShootFromP4;
            case SHOOT_FROM_POSE:
                return requestShootFromPose;
            case INTAKE:
                return requestIntake;
            case OUTTAKE:
                return requestOuttake;
            case PARK:
                return requestPark;
            case SHAKE:
                return requestShake;
            default:
                return null;
        }
    }


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





}
