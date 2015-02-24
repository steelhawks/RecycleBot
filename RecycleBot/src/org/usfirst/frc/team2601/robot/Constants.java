<<<<<<< HEAD
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

	 private static Constants instance = null;
	   
	 public static Constants getInstance() {
	      if(instance == null) {
	         instance = new Constants();
	      }
	      return instance;
	 }
	// configuration
	 public enum Robot_Type { Practice, Competition };
	 public Robot_Type robotType = Robot_Type.Competition;
	//public final boolean practice = false;
	//public  final boolean competition = true;
	
	// TODO: Convert this to enum
	public enum Drive_Type { TANK, ARCADE, CUSTOMARCADE}; 
	//public  final int TANK = 0;
	//public  final int ARCADE = 1;
	//public  final int CUSTOMARCADE = 2;
	//public  final int drivetype = 2;
	public Drive_Type driveType = Drive_Type.CUSTOMARCADE;
	
	// TODO: Convert this to enum
	public enum Controller_Type { GAMEPAD, JOYSTICK, TANKDRIVE}; 
	//public  final boolean GAMEPAD = false;
	//public  final boolean JOYSTICK = true;
	//public  final boolean TANKDRIVE = false;
	public Controller_Type controllerType = Controller_Type.JOYSTICK;
	
	// TODO: Convert this to enum
	public enum Chassis_Type { OMNI, SQUARE}; 
	//public  final int OMNI =0;
	//public  final int SQUARE = 1;
	//public  final int chassisType = 0;
	public Chassis_Type chassisType = Chassis_Type.OMNI;
	
	public  final boolean CAMERA_ON = true;
	public  final boolean PNEUMATICS_ON = true;
	
	//public static int joystickPort;
	//public static int secondaryJoystickPort;
	//joystick
	
	public  int joystickPort;
	public  int secondaryJoystickPort;
	public  int thirdJoystickPort;
	public  int leftDriveStick;
	public  int rightDriveStick;
	
	//camera buttons
	public  int cam=9;
	public  int topCam=10;
	
	//Roller control buttons
	public  int closeOrOpenRollersButton;
	public  int intakeRollersButton;
	public  int outtakeRollersButton;
	public  int stopRollersButton;
	
	//PID control buttons
	public  int startDrivetrainPID;
	public  int stopDrivetrainPID;
	public  int startElevatorPID;
	public  int stopElevatorPID;
	
	//general control buttons
	public  int fineDriveButton;
	public  int stopMotors;
	public  int restartDumbDriveButton;
	
	//pneumatics buttons
	public  int extend;
	public  int retract;
	
	//elevator ejection piston buttons
	public  int automaticEjectTotesButton;
	public  int manualOpenEjectionPistonButton;
	public  int manualCloseEjectionPistonButton;
	
	//Talon SRX addresses
	public  int leftTalonAddressI;
	public  int leftTalonAddressII;
	public  int centerTalonAddress;
	public  int rightTalonAddressI;
	public  int rightTalonAddressII;
	public  int elevatorTalonAddressI;
	public  int elevatorTalonAddressII;
	
	//Talon ports (PWM)
	public int leftRollerTalonPort;
	public int rightRollerTalonPort;
	
	//drivetrain PID variables
	public  double drivetrainP;
	public  double drivetrainI;
	public  double drivetrainD;
	public  double drivetrainSetpoint;
	public  double drivetrainAbsoluteTolerance;
	public  double drivetrainDistancePerPulse;
	public  double drivetrainMaxOutput;
	public  double drivetrainMinOutput;
	
	//elevator PID variables
	public  double elevatorP;
	public  double elevatorI;
	public  double elevatorD;
	public  double elevatorSetpoint;
	public  double elevatorAbsoluteTolerance;
	public  double elevatorDistancePerPulse;
	public  double elevatorMaxOutput;
	public  double elevatorMinOutput;
	
	//encoders
	public  int leftEncoderPortI;
	public  int leftEncoderPortII;
	public  int rightEncoderPortI;
	public  int rightEncoderPortII;
	public  int elevatorEncoderPortI;
	public  int elevatorEncoderPortII;
	
	//limit switches
	public  int bottomLimitSwitchPort;
	public  int topLimitSwitchPort;
	
	//Talon multipliers
	public  double leftDrivetrainTalonMultiplier;
	public  double rightDrivetrainTalonMultiplier;
	public  int centerDrivetrainTalonMultiplier;
	public  int elevatorTalonMultiplier;
	public  int leftRollerTalonMultiplier;
	public  int rightRollerTalonMultiplier;

	// speeds
	public  double drivetrainSpeed;
	public  double drivetrainFineSpeed;
	public  double drivetrainTurnSpeed = 0.25;
	public  double rollerSpeed;
	public  double rollerStopSpeed;
	public  double elevatorSpeed;
	public  double autonElevatorSpeed;
	public  double autonTurnSpeed;
	
	//NetworkTable keys
	public  String drivetrainPKey;
	public  String drivetrainIKey;
	public  String drivetrainDKey;
	public  String drivetrainSetpointKey;
	public  String elevatorPKey;
	public  String elevatorIKey;
	public  String elevatorDKey;
	public  String elevatorSetpointKey;
	
	//Solenoid Ports
	public  int leftSolenoidOnPort;
	public  int leftSolenoidOffPort;
	public  int rightSolenoidOnPort;
	public  int rightSolenoidOffPort;
	public  int ejectionSolenoidOnPort;
	public  int ejectionSolenoidOffPort;
	
	//Camera Stuff (servos, etc)
	public  double servoChange = 0.05;
	public  int panServoPort = 4;
	public  int tiltServoPort = 5;
	
	
	private Constants() {
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

		joystickPort = 0;
		secondaryJoystickPort = 1;
		thirdJoystickPort = 2;
		leftDriveStick = 3;
		rightDriveStick = 4;

		if(robotType == Robot_Type.Practice) {

			//if (GAMEPAD){
			if (controllerType == Controller_Type.GAMEPAD) {
				joystickPort = 1;
				secondaryJoystickPort = 0;
			}
			else if (controllerType == Controller_Type.JOYSTICK){
				joystickPort = 0;
				secondaryJoystickPort = 1;
			}
		
	
		// operator stick
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
		
		// driver stick
		//general control buttons
		fineDriveButton = 1;
		stopMotors = 4;
		restartDumbDriveButton = 3;
		
		//pneumatics buttons
//		extend = 9;
//		retract = 10;

		// operator stick
		//elevator ejection piston buttons
		/*automaticEjectTotesButton = 11;
		manualOpenEjectionPistonButton = 9;
		manualCloseEjectionPistonButton = 10;
		*/
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
		drivetrainFineSpeed = drivetrainSpeed*0.5;
		rollerSpeed = 1.0;
		rollerStopSpeed = 0.0;
		elevatorSpeed = 0.5;
		autonElevatorSpeed = 1.0;
		autonTurnSpeed = 0.5;
		
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
//		ejectionSolenoidOnPort = 4;
//		ejectionSolenoidOffPort = 5;
		}
		
	else if(robotType == Robot_Type.Competition){
			if (controllerType == Controller_Type.GAMEPAD){
				joystickPort = 1;
				secondaryJoystickPort = 0;
			}
			else if (controllerType == Controller_Type.JOYSTICK){
				joystickPort = 0;
				secondaryJoystickPort = 1;
			}
		
	
		// operator stick
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
		
		// driver stick
		//general control buttons
		fineDriveButton = 1;
		stopMotors = 4;
		restartDumbDriveButton = 3;
		
		//pneumatics buttons
//		extend = 9;
//		retract = 10;

		// operator stick
		//elevator ejection piston buttons
		/*automaticEjectTotesButton = 11;
		manualOpenEjectionPistonButton = 9;
		manualCloseEjectionPistonButton = 10;
		*/
		//Talon SRX addresses
		leftTalonAddressI = 13;
		leftTalonAddressII = 11;
		centerTalonAddress = 1;
		rightTalonAddressI = 15;
		rightTalonAddressII = 16;
		elevatorTalonAddressI = 7;
		elevatorTalonAddressII = 8;
		
		//Talon ports (PWM)
		leftRollerTalonPort = 0;
		rightRollerTalonPort = 1;
		
		//drivetrain PID variables
		drivetrainP = 0.5;
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
		elevatorSpeed = 1.0;
		autonElevatorSpeed = 1.0;
		autonTurnSpeed = 0.5;
		
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
//		ejectionSolenoidOnPort = 4;
//		ejectionSolenoidOffPort = 5;
		
		}
	}
	
}
=======
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

	 private static Constants instance = null;
	   
	 public static Constants getInstance() {
	      if(instance == null) {
	         instance = new Constants();
	      }
	      return instance;
	 }
	// configuration
	public static final boolean practice = true;
	public static final boolean competition = false;
	
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
	
	public static int joystickPort;
	public static int secondaryJoystickPort;
	public static int thirdJoystickPort;
	public static int leftDriveStick;
	public static int rightDriveStick;
	
	//camera buttons
	public static int cam=9;
	public static int topCam=10;
	
	//Roller control buttons
	public static int closeOrOpenRollersButton;
	public static int intakeRollersButton;
	public static int outtakeRollersButton;
	public static int stopRollersButton;
	
	//PID control buttons
	public static int startDrivetrainPID;
	public static int stopDrivetrainPID;
	public static int startElevatorPID;
	public static int stopElevatorPID;
	
	//general control buttons
	public static int fineDriveButton;
	public static int stopMotors;
	public static int restartDumbDriveButton;
	
	//pneumatics buttons
	public static int extend;
	public static int retract;
	
	//elevator ejection piston buttons
	public static int automaticEjectTotesButton;
	public static int manualOpenEjectionPistonButton;
	public static int manualCloseEjectionPistonButton;
	
	//Talon SRX addresses
	public static int leftTalonAddressI;
	public static int leftTalonAddressII;
	public static int centerTalonAddress;
	public static int rightTalonAddressI;
	public static int rightTalonAddressII;
	public static int elevatorTalonAddressI;
	public static int elevatorTalonAddressII;
	
	//Talon ports (PWM)
	public int leftRollerTalonPort;
	public int rightRollerTalonPort;
	
	//drivetrain PID variables
	public static double drivetrainP;
	public static double drivetrainI;
	public static double drivetrainD;
	public static double drivetrainSetpoint;
	public static double drivetrainAbsoluteTolerance;
	public static double drivetrainDistancePerPulse;
	public static double drivetrainMaxOutput;
	public static double drivetrainMinOutput;
	
	//elevator PID variables
	public static double elevatorP;
	public static double elevatorI;
	public static double elevatorD;
	public static double elevatorSetpoint;
	public static double elevatorAbsoluteTolerance;
	public static double elevatorDistancePerPulse;
	public static double elevatorMaxOutput;
	public static double elevatorMinOutput;
	
	//encoders
	public static int leftEncoderPortI;
	public static int leftEncoderPortII;
	public static int rightEncoderPortI;
	public static int rightEncoderPortII;
	public static int elevatorEncoderPortI;
	public static int elevatorEncoderPortII;
	
	//limit switches
	public static int bottomLimitSwitchPort;
	public static int topLimitSwitchPort;
	
	//Talon multipliers
	public static double leftDrivetrainTalonMultiplier;
	public static double rightDrivetrainTalonMultiplier;
	public static int centerDrivetrainTalonMultiplier;
	public static int elevatorTalonMultiplier;
	public static int leftRollerTalonMultiplier;
	public static int rightRollerTalonMultiplier;

	// speeds
	public static double drivetrainSpeed;
	public static double drivetrainFineSpeed;
	public static double drivetrainTurnSpeed = 0.5;
	public static double rollerSpeed;
	public static double rollerStopSpeed;
	public static double elevatorSpeed;
	public static double autonElevatorSpeed;
	public static double autonTurnSpeed;
	
	//NetworkTable keys
	public static String drivetrainPKey;
	public static String drivetrainIKey;
	public static String drivetrainDKey;
	public static String drivetrainSetpointKey;
	public static String elevatorPKey;
	public static String elevatorIKey;
	public static String elevatorDKey;
	public static String elevatorSetpointKey;
	
	//Solenoid Ports
	public static int leftSolenoidOnPort;
	public static int leftSolenoidOffPort;
	public static int rightSolenoidOnPort;
	public static int rightSolenoidOffPort;
	public static int ejectionSolenoidOnPort;
	public static int ejectionSolenoidOffPort;
	
	
	private Constants() {
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

		joystickPort = 0;
		secondaryJoystickPort = 1;
		thirdJoystickPort = 2;
		leftDriveStick = 3;
		rightDriveStick = 4;

		if(practice){

			if (GAMEPAD){
				joystickPort = 1;
				secondaryJoystickPort = 0;
			}
			else if (JOYSTICK){
				joystickPort = 0;
				secondaryJoystickPort = 1;
			}
		
	
		// operator stick
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
		
		// driver stick
		//general control buttons
		fineDriveButton = 1;
		stopMotors = 4;
		restartDumbDriveButton = 3;
		
		//pneumatics buttons
//		extend = 9;
//		retract = 10;

		// operator stick
		//elevator ejection piston buttons
		/*automaticEjectTotesButton = 11;
		manualOpenEjectionPistonButton = 9;
		manualCloseEjectionPistonButton = 10;
		*/
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
		elevatorSpeed = 0.95;
		autonElevatorSpeed = 1.0;
		autonTurnSpeed = 0.37;
		
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
//		ejectionSolenoidOnPort = 4;
//		ejectionSolenoidOffPort = 5;
		}
		
	else if(competition){
			if (GAMEPAD){
				joystickPort = 1;
				secondaryJoystickPort = 0;
			}
			else if (JOYSTICK){
				joystickPort = 0;
				secondaryJoystickPort = 1;
			}
		
	
		// operator stick
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
		
		// driver stick
		//general control buttons
		fineDriveButton = 1;
		stopMotors = 4;
		restartDumbDriveButton = 3;
		
		//pneumatics buttons
//		extend = 9;
//		retract = 10;

		// operator stick
		//elevator ejection piston buttons
		/*automaticEjectTotesButton = 11;
		manualOpenEjectionPistonButton = 9;
		manualCloseEjectionPistonButton = 10;
		*/
		//Talon SRX addresses
		leftTalonAddressI = 13;
		leftTalonAddressII = 11;
		centerTalonAddress = 1;
		rightTalonAddressI = 15;
		rightTalonAddressII = 16;
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
		elevatorSpeed = 1.0;
		autonElevatorSpeed = 1.0;
		autonTurnSpeed = 0.5;
		
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
//		ejectionSolenoidOnPort = 4;
//		ejectionSolenoidOffPort = 5;
		
		}
	}
	
}
>>>>>>> branch 'master' of https://github.com/steelhawks/RecycleBot.git
