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
    
	//joystick
	public static int joystickPort = 0;
	
	//PID control buttons
	public static int startPID = 1;
	public static int stopPID = 2;
	
	//general control buttons
	public static int stopMotors = 4;
	public static int restartDumbDriveButton = 3;
	
	//pneumatics buttons
	public static int extend = 9;
	public static int retract = 10;
	
	//Talon SRX addresses
	public static int leftTalonAddress = 1;
	public static int rightTalonAddress = 2;
	public static int centerTalonAddress = 3;
	
	//PID variables
	public static double kP = 0.0;
	public static double kI = 0.0;
	public static double kD = 0.0;
	public static double setpoint = 0.0;
	public static double absoluteTolerance = 15.0;
	public static double distancePerPulse = 0.17;
	public static double maxOutput = 0.5;
	public static double minOutput = -0.5;
	
	//encoders
	public static int leftEncoderPortI = 0;
	public static int leftEncoderPortII = 1;
	
	public static int leftTalonMultiplier = 1;
	public static int rightTalonMultiplier = -1;
	public static int centerTalonMultiplier = 1;
   
}
