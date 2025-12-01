# TeamCode Module

This module contains the code for the team's robot. It follows a structured architecture using the **Command-Based** programming paradigm, heavily utilizing the **FTCLib** library.

## Architecture Overview

The robot code is organized into several key components:

### 1. Subsystems
Subsystems represent the physical hardware assemblies of the robot (e.g., Drivetrain, Intake, Lift, Shooter, Funnel). Each subsystem encapsulates the hardware (motors, servos, sensors) and provides methods to control them.

- **DrivetrainSubsystem**: Handles robot movement using PedroPathing for autonomous path following and teleop driving.
- **IntakeSubsystem**: Manages the intake mechanism (intaking, outtaking, idling).
- **ShooterSubsystem**: Controls the flywheel RPM and hood angle for accurate shooting.
- **LiftSubsystem**: Manages the lift mechanism positions.
- **FunnelSubsystem**: Controls the indexer/funneling system to feed rings/elements into the shooter.
- **TheMachineSubsystem**: A "Super-Subsystem" that coordinates high-level states and interactions between all other subsystems.

### 2. States & Enums
We use `Enums` to define the possible states for each subsystem. This makes the code readable and allows for robust state machine implementations.
- **Files**: `Utils/AllStates.java` contains definitions like `IntakeStates`, `ShooterStates`, etc.

### 3. Commands & Actions
We use a **State-Based Command System**. Instead of writing complex commands that directly control motors in a loop, we separate logic into:
- **Requests**: Commands that simply *request* a subsystem to enter a specific state (e.g., `IntakeIntakeRequest`). These are instantaneous and change the target state variable in the subsystem.
- **Actions**: Commands that *execute* the logic for a specific state (e.g., `IntakeIntakeAction`). The subsystem's `periodic()` method (or an internal state machine) ensures the correct Action is running based on the current state.

#### Syntax & Logic
The typical flow for a subsystem looks like this:

1.  **Request Command**:
    ```java
    public class IntakeIntakeRequest extends CommandBase {
        private final IntakeSubsystem subsystem;
        
        // ... constructor ...

        @Override
        public void initialize() {
            // Update the state in the subsystem
            subsystem.setState(AllStates.IntakeStates.INTAKE); 
        }

        @Override
        public boolean isFinished() {
            return true; // Instant command
        }
    }
    ```

2.  **Action Command**:
    ```java
    public class IntakeIntakeAction extends CommandBase {
        private final IntakeSubsystem subsystem;
        
        // ... constructor ...

        @Override
        public void initialize() {
            subsystem.intake(); // Set motor powers
        }

        @Override
        public void execute() {
            // Verify we are still in the correct state
            if (subsystem.getState() != AllStates.IntakeStates.INTAKE) {
                isFinished = true;
            }
        }
        // ...
    }
    ```

3.  **Subsystem State Machine**:
    Inside the subsystem's `periodic()` or `stateMachine()` method, the code checks the `currentState` and schedules the corresponding Action if it's not already running.

### 4. Constants
All tuning values (PID coefficients, motor ports, servo positions, timeouts) are stored in `Constants` files. This allows for easy tuning without digging into logic code.
- **Files**: `Constants/DrivetrainConstants.java`, `Constants/ShooterConstants.java`, etc.
- **Configurable**: We use `@Configurable` annotations (likely from `FTC-Dashboard` or similar) to allow real-time tuning.

## Key Features
- **PedroPathing**: Advanced path following for autonomous.
- **Automated Shooting**: The `ShooterSubsystem` uses PID controllers to maintain RPM and calculates hood position based on robot pose.
- **Super-Subsystem Coordination**: `TheMachineSubsystem` acts as a central brain to manage complex sequences involving multiple subsystems (e.g., "Prep and Shoot").

## Directory Structure
- `Commands/`: Contains all Request and Action commands.
- `Constants/`: Configuration files for hardware and tuning.
- `Subsystems/`: Hardware abstraction layers.
- `Utils/`: Helper classes (Math, Enums, PID controllers).
