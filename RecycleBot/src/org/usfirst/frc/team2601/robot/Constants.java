package org.usfirst.frc.team2601.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class Constants {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
	// configuration
	public static final int TANK = 0;
	public static final int ARCADE = 1;
	public static int driveType = ARCADE;
	
	public static final boolean GAMEPAD = false;
	public static final boolean JOYSTICK = true;
	
	public static final int OMNI =0;
	public static final int SQUARE = 1;
	public static int chassisType = OMNI;
	
	public static final boolean CAMERA_ON = true;
	public static final boolean PNEUMATICS_ON = true;
	
	//public static int joystickPort;
	//public static int secondaryJoystickPort;
	//joystick
	/*public Constants(){
		if (GAMEPAD){
			joystickPort = 1;
			secondaryJoystickPort = 0;
		}
		else if (JOYSTICK){
			joystickPort = 0;
			secondaryJoystickPort = 1;
		}
	}*/
	
	public static int joystickPort = 0;
	public static int secondaryJoystickPort = 1;
	public static int thirdJoystickPort = 2;
	
	//Roller control buttons
	public static int closeOrOpenRollersButton = 1;
	public static int intakeRollersButton = 3;
	public static int outtakeRollersButton = 2;
	
	//PID control buttons
	public static int startDrivetrainPID = 1;
	public static int stopDrivetrainPID = 2;
	public static int startElevatorPID = 8;
	public static int stopElevatorPID = 7;
	
	//general control buttons
	public static int stopMotors = 4;
	public static int restartDumbDriveButton = 3;
	
	//pneumatics buttons
	public static int extend = 9;
	public static int retract = 10;
	
	//elevator ejection piston buttons
	public static int automaticEjectTotesButton = 1;
	public static int manualOpenEjectionPistonButton = 3;
	public static int manualCloseEjectionPistonButton = 4;
	
	//Talon SRX addresses
	public static int leftTalonAddressI = 10;
	public static int leftTalonAddressII = 9;
	public static int centerTalonAddress = 6;
	public static int rightTalonAddressI = 4;
	public static int rightTalonAddressII = 5;
	public static int elevatorTalonAddressI = 7;
	public static int elevatorTalonAddressII = 8;
	
	//Talon ports (PWM)
	public static int leftRollerTalonPort = 0;
	public static int rightRollerTalonPort = 1;
	
	//drivetrain PID variables
	public static double drivetrainP = 0.0;
	public static double drivetrainI = 0.0;
	public static double drivetrainD = 0.0;
	public static double drivetrainSetpoint = 0.0;
	public static double drivetrainAbsoluteTolerance = 15.0;
	public static double drivetrainDistancePerPulse = 0.17;
	public static double drivetrainMaxOutput = 0.5;
	public static double drivetrainMinOutput = -0.5;
	
	//elevator PID variables
	public static double elevatorP = 0.0;
	public static double elevatorI = 0.0;
	public static double elevatorD = 0.0;
	public static double elevatorSetpoint = 0.0;
	public static double elevatorAbsoluteTolerance = 15.0;
	public static double elevatorDistancePerPulse = 0.17;
	public static double elevatorMaxOutput = 0.5;
	public static double elevatorMinOutput = -0.5;
	
	//encoders
	public static int leftEncoderPortI = 0;
	public static int leftEncoderPortII = 1;
	public static int elevatorEncoderPortI = 2;
	public static int elevatorEncoderPortII  = 3;
	
	//limit switches
	public static int limitSwitchIPort = 4;
	public static int limitSwitchIIPort = 5;
	
	//Talon multipliers
	public static int leftDrivetrainTalonMultiplier = 1;
	public static int rightDrivetrainTalonMultiplier = -1;
	public static int centerDrivetrainTalonMultiplier = 1;
	public static int elevatorTalonMultiplier = 1;
	public static int leftRollerTalonMultiplier = -1;
	public static int rightRollerTalonMultiplier = 1;

	// speeds
	public static double drivetrainSpeed = 1.0;
	public static double rollerSpeed = 1.0;
	public static double rollerStopSpeed = 0.0;
	public static double elevatorSpeed = 1.0;
	
	//NetworkTable keys
	public static String drivetrainPKey = "DrivetrainP";
	public static String drivetrainIKey = "DrivetrainI";
	public static String drivetrainDKey = "DrivetrainD";
	public static String drivetrainSetpointKey = "DrivetrainSetpoint";
	public static String elevatorPKey = "ElevatorP";
	public static String elevatorIKey = "ElevatorI";
	public static String elevatorDKey = "ElevatorD";
	public static String elevatorSetpointKey = "ElevatorSetpoint";
	
	//Solenoid Ports
	public static int leftSolenoidOnPort = 0;
	public static int leftSolenoidOffPort = 2;
	public static int rightSolenoidOnPort = 1;
	public static int rightSolenoidOffPort = 3;
	public static int ejectionSolenoidOnPort = 4;
	public static int ejectionSolenoidOffPort = 5;
	
}
