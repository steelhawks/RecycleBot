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
	
	//pneumatics buttons
	public static int extend = 9;
	public static int retract = 10;
	
	//Talon SRX addresses
	public static int leftTalonAddress = 1;
	public static int rightTalonAddress = 2;
	
	
	
	
   
}
