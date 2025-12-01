(Created by gemini)
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

### 4. Constants & Positions
All tuning values (PID coefficients, motor ports, servo positions, timeouts) are stored in `Constants` files. This allows for easy tuning without digging into logic code.
- **Files**: `Constants/DrivetrainConstants.java`, `Constants/ShooterConstants.java`, etc.
- **Configurable**: We use `@Configurable` annotations (likely from `FTC-Dashboard` or similar) to allow real-time tuning.

We also have specific files for field positions:
- **Files**: `Positions/BluePositions.java`, `Positions/RedPositions.java`
- **Usage**: These store `Pose` objects (x, y, heading) for important locations on the field like shooting spots or starting positions.

## Key Features
- **PedroPathing**: Advanced path following for autonomous.
- **Automated Shooting**: The `ShooterSubsystem` uses PID controllers to maintain RPM and calculates hood position based on robot pose.
- **Super-Subsystem Coordination**: `TheMachineSubsystem` acts as a central brain to manage complex sequences involving multiple subsystems (e.g., "Prep and Shoot").

## Directory Structure
- `Commands/`: Contains all Request and Action commands.
- `Constants/`: Configuration files for hardware and tuning.
- `Positions/`: Field coordinates and poses for autonomous/teleop.
- `Subsystems/`: Hardware abstraction layers.
- `Utils/`: Helper classes (Math, Enums, PID controllers).

## Basic Syntax Guide for Programmers

This section provides a quick reference for common syntax patterns used throughout the codebase.

### 1. Defining a New Subsystem
When creating a new subsystem, extend `SubsystemBase` and initialize hardware in the constructor.

```java
public class MySubsystem extends SubsystemBase {
    private final DcMotor motor;

    public MySubsystem() {
        // Get hardware from the hardwareMap
        motor = hardwareMap.get(DcMotor.class, "motorName");
    }

    // Define methods to control the hardware
    public void spin() {
        motor.setPower(1.0);
    }
}
```

### 2. Creating a Command (FTCLib)
Commands are actions the robot performs. Extend `CommandBase`.

```java
public class MyCommand extends CommandBase {
    private final MySubsystem subsystem;

    public MyCommand(MySubsystem subsystem) {
        this.subsystem = subsystem;
        addRequirements(subsystem); // Important: Declare dependency
    }

    @Override
    public void initialize() {
        // Code to run once when the command starts
        subsystem.spin();
    }

    @Override
    public boolean isFinished() {
        // Return true when the command should end
        return true; 
    }
}
```

### 3. Using Suppliers (Lambdas)
We often use Java 8 Suppliers (lambdas) to pass values that change over time, like robot pose or sensor readings.

```java
// Syntax: () -> value
Supplier<Double> voltageSupplier = () -> hardwareMap.voltageSensor.iterator().next().getVoltage();

// Usage in a command constructor
public MyCommand(Supplier<Double> voltage) {
    this.voltage = voltage;
}
```

### 4. PedroPathing Path Construction
We use the `PathBuilder` to create paths dynamically.

```java
path = () -> drive.pathBuilder()
    .addPath(new Path(new BezierLine(startPose, endPose)))
    .setHeadingInterpolation(HeadingInterpolator.facingPoint(targetPoint))
    .build();
```

### 5. Annotations
- `@Configurable`: Marks a class so its static fields can be tuned via dashboard.
- `@IgnoreConfigurable`: Excludes specific fields from being tunable.
- `@Override`: Indicates a method is overriding a parent class method (good practice).

### 6. Defining Constants and Positions
We separate configuration from logic.

**Constants Example (`Constants/MyConstants.java`):**
```java
@Configurable
public class MyConstants {
    @IgnoreConfigurable
    public static String MotorName = "myMotor"; // Hardware map name

    public static double TargetSpeed = 0.5; // Tunable value
}
```

**Positions Example (`Positions/BluePositions.java`):**
```java
@Configurable
public class BluePositions {
    // Define a specific point on the field (X, Y, Heading)
    public static Pose SHOOTING_SPOT = new Pose(60, 84, Math.toRadians(90));
}
```
