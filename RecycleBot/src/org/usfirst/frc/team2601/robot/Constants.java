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
	public enum Robot_Type { PRACTICE_BOT, COMPETITION_BOT}; 
	public static final Robot_Type robotType = Robot_Type.PRACTICE_BOT;
	
	// TODO: Convert this to enum
	//public enum Drive_Type { TANK, ARCADE, CUSTOMARCADE}; 
	public static final int TANK = 0;
	public static final int ARCADE = 1;
	public static final int CUSTOMARCADE = 2;
	public static final int drivetype = 2;
	//public static Drive_Type driveType;
	
	// TODO: Convert this to enum
	//public enum Controller_Type { GAMEPAD, JOYSTICK, TANKDRIVE}; 
	public static final boolean GAMEPAD = false;
	public static final boolean JOYSTICK = true;
	public static final boolean TANKDRIVE = false;
	//public static Controller_Type controllerType;
	
	// TODO: Convert this to enum
	//public enum Chassis_Type { OMNI, SQUARE}; 
	public static final int OMNI =0;
	public static final int SQUARE = 1;
	public static final int chassisType = 0;
	//public static Chassis_Type chassisType;
	
	public static final boolean CAMERA_ON = true;
	public static final boolean PNEUMATICS_ON = true;
	
	//public static int joystickPort;
	//public static int secondaryJoystickPort;
	//joystick
	public Constants(){
		if (GAMEPAD){
			joystickPort = 1;
			secondaryJoystickPort = 0;
		}
		else if (JOYSTICK){
			joystickPort = 0;
			secondaryJoystickPort = 1;
		}
	}
	
	public static int joystickPort=0;
	public static int secondaryJoystickPort=1;
	public static int thirdJoystickPort=2;
	public static int leftDriveStick=3;
	public static int rightDriveStick=4;
	
	//camera buttons
	public static int cam = 9;
	public static int topCam = 10;
	
	//Roller control buttons
	public static int closeOrOpenRollersButton=1;
	public static int intakeRollersButton=3;
	public static int outtakeRollersButton=2;
	public static int stopRollersButton=4;
	
	//PID control buttons
	public static int startDrivetrainPID=1;
	public static int stopDrivetrainPID=2;
	public static int startElevatorPID=8;
	public static int stopElevatorPID=7;
	
	//general control buttons
	public static int fineDriveButton=1;
	public static int stopMotors=4;
	public static int restartDumbDriveButton=3;
	
	//pneumatics buttons
	public static int extend=7;
	public static int retract=8;
	
	//elevator ejection piston buttons
	public static int automaticEjectTotesButton=11;
	public static int manualOpenEjectionPistonButton=9;
	public static int manualCloseEjectionPistonButton=10;
	
	//Talon SRX addresses
	public static int leftTalonAddressI=10;
	public static int leftTalonAddressII=9;
	public static int centerTalonAddress=6;
	public static int rightTalonAddressI=4;
	public static int rightTalonAddressII=5;
	public static int elevatorTalonAddressI=7;
	public static int elevatorTalonAddressII=8;
	
	//Talon ports (PWM)
	public static int leftRollerTalonPort=0;
	public static int rightRollerTalonPort=1;
	
	//drivetrain PID variables
	public static double drivetrainP=0.0;
	public static double drivetrainI=0.0;
	public static double drivetrainD= 0.0;
	public static double drivetrainSetpoint = 0.0;
	public static double drivetrainAbsoluteTolerance = 15.0;
	public static double drivetrainDistancePerPulse = 7.97965;
	public static double drivetrainMaxOutput= 0.5;
	public static double drivetrainMinOutput= -0.5;
	
	//elevator PID variables
	public static double elevatorP= 0.0;
	public static double elevatorI=0.0;
	public static double elevatorD=0.0;
	public static double elevatorSetpoint=0.0;
	public static double elevatorAbsoluteTolerance = 15.0;
	public static double elevatorDistancePerPulse = 17.0;
	public static double elevatorMaxOutput=0.5;
	public static double elevatorMinOutput=-0.5;
	
	//encoders
	public static int leftEncoderPortI=0;
	public static int leftEncoderPortII=1;
	public static int rightEncoderPortI=2;
	public static int rightEncoderPortII=3;
	public static int elevatorEncoderPortI=4;
	public static int elevatorEncoderPortII=5;
	
	//limit switches
	public static int bottomLimitSwitchPort=6;
	public static int topLimitSwitchPort=7;
	
	//Talon multipliers
	public static double leftDrivetrainTalonMultiplier=1;
	public static double rightDrivetrainTalonMultiplier=-1;
	public static int centerDrivetrainTalonMultiplier=1;
	public static int elevatorTalonMultiplier=1;
	public static int leftRollerTalonMultiplier=-1;
	public static int rightRollerTalonMultiplier=1;

	// speeds
	public static double drivetrainSpeed=0.75;
	public static double drivetrainFineSpeed=0.25;
	public static double drivetrainTurnSpeed=0.5;
	public static double rollerSpeed=1.0;
	public static double rollerStopSpeed=0.0;
	public static double elevatorSpeed=1.0;
	public static double autonElevatorSpeed=1.0;
	public static double autonTurnSpeed = 0.35;
	
	//NetworkTable keys
	public static String drivetrainPKey = "DrivetrainP";
	public static String drivetrainIKey= "DrivetrainI";
	public static String drivetrainDKey="DrivetrainD";
	public static String drivetrainSetpointKey =  "DrivetrainSetpoint";
	public static String elevatorPKey = "ElevatorP";
	public static String elevatorIKey = "ElevatorI";
	public static String elevatorDKey = "ElevatorD";
	public static String elevatorSetpointKey = "ElevatorSetpoint";
	
	//Solenoid Ports
	public static int leftSolenoidOnPort=0;
	public static int leftSolenoidOffPort=2;
	public static int rightSolenoidOnPort=1;
	public static int rightSolenoidOffPort=3;
	public static int ejectionSolenoidOnPort=4;
	public static int ejectionSolenoidOffPort=5;
	
	
	//Constants() {
		//driveType = Drive_Type.CUSTOMARCADE;
		
		//controllerType = Controller_Type.JOYSTICK;
		
		//chassisType = Chassis_Type.OMNI;
		
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
		
		/*joystickPort = 0;
		secondaryJoystickPort = 1;
		thirdJoystickPort = 2;
		leftDriveStick = 3;
		rightDriveStick = 4;
		
		//Roller control buttons
		closeOrOpenRollersButton = 1;
		intakeRollersButton = 3;
		outtakeRollersButton = 2;
		stopRollersButton = 4;
		
		//PID control buttons
		startDrivetrainPID = 1;
		stopDrivetrainPID = 2;
		startElevatorPID = 8;
		stopElevatorPID = 7;
		
		//general control buttons
		fineDriveButton = 1;
		stopMotors = 4;
		restartDumbDriveButton = 3;
		
		//pneumatics buttons
		extend = 9;
		retract = 10;
		
		//elevator ejection piston buttons
		automaticEjectTotesButton = 11;
		manualOpenEjectionPistonButton = 9;
		manualCloseEjectionPistonButton = 10;
		
		//Talon SRX addresses
		leftTalonAddressI = 10;
		leftTalonAddressII = 9;
		centerTalonAddress = 6;
		rightTalonAddressI = 4;
		rightTalonAddressII = 5;
		elevatorTalonAddressI = 7;
		elevatorTalonAddressII = 8;
		
		//Talon ports (PWM)
		leftRollerTalonPort = 0;
		rightRollerTalonPort = 1;
		
		//drivetrain PID variables
		drivetrainP = 0.0;
		drivetrainI = 0.0;
		drivetrainD = 0.0;
		drivetrainSetpoint = 0.0;
		drivetrainAbsoluteTolerance = 15.0;
		drivetrainDistancePerPulse = 7.97965;
		drivetrainMaxOutput = 0.5;
		drivetrainMinOutput = -0.5;
		
		//elevator PID variables
		elevatorP = 0.0;
		elevatorI = 0.0;
		elevatorD = 0.0;
		elevatorSetpoint = 0.0;
		elevatorAbsoluteTolerance = 15.0;
		elevatorDistancePerPulse = 0.17;
		elevatorMaxOutput = 0.5;
		elevatorMinOutput = -0.5;
		
		//encoders
		leftEncoderPortI = 0;
		leftEncoderPortII = 1;
		rightEncoderPortI = 2;
		rightEncoderPortII  = 3;
		elevatorEncoderPortI = 4;
		elevatorEncoderPortII = 5;
		
		//limit switches
		bottomLimitSwitchPort = 6;
		topLimitSwitchPort = 7;
		
		//Talon multipliers
		leftDrivetrainTalonMultiplier = 1;
		rightDrivetrainTalonMultiplier = -1;
		centerDrivetrainTalonMultiplier = 1;
		elevatorTalonMultiplier = 1;
		leftRollerTalonMultiplier = -1;
		rightRollerTalonMultiplier = 1;

		// speeds
		drivetrainSpeed = 0.75;
		drivetrainFineSpeed = 0.25;
		rollerSpeed = 1.0;
		rollerStopSpeed = 0.0;
		elevatorSpeed = 0.5;
		autonElevatorSpeed = 0.25;
		
		//NetworkTable keys
		drivetrainPKey = "DrivetrainP";
		drivetrainIKey = "DrivetrainI";
		drivetrainDKey = "DrivetrainD";
		drivetrainSetpointKey = "DrivetrainSetpoint";
		elevatorPKey = "ElevatorP";
		elevatorIKey = "ElevatorI";
		elevatorDKey = "ElevatorD";
		elevatorSetpointKey = "ElevatorSetpoint";
		
		//Solenoid Ports
		leftSolenoidOnPort = 0;
		leftSolenoidOffPort = 2;
		rightSolenoidOnPort = 1;
		rightSolenoidOffPort = 3;
		ejectionSolenoidOnPort = 4;
		ejectionSolenoidOffPort = 5;
	*/
	
}
