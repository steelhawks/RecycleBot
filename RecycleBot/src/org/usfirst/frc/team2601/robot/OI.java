package org.usfirst.frc.team2601.robot;

import org.usfirst.frc.team2601.robot.Constants;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team2601.robot.commands.DumbDrive;
import org.usfirst.frc.team2601.robot.commands.ExtendPiston;
import org.usfirst.frc.team2601.robot.commands.RetractPiston;
import org.usfirst.frc.team2601.robot.commands.StartDrivetrainPID;
import org.usfirst.frc.team2601.robot.commands.StartElevatorPID;
import org.usfirst.frc.team2601.robot.commands.StopElevatorPID;
import org.usfirst.frc.team2601.robot.commands.StopMotors;
import org.usfirst.frc.team2601.robot.commands.StopDrivetrainPID;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
	public static  Joystick stick = new Joystick(Constants.joystickPort);
	public static Joystick elevatorStick = new Joystick(Constants.secondaryJoystickPort);
	
	public OI(){
	//PID buttons
	
	JoystickButton startDrivetrainPID = new JoystickButton(stick, Constants.startDrivetrainPID);
	startDrivetrainPID.whenPressed(new StartDrivetrainPID());
	
	JoystickButton stopDrivetrainPID = new JoystickButton(stick, Constants.stopDrivetrainPID);
	stopDrivetrainPID.cancelWhenPressed(new StartDrivetrainPID());
	stopDrivetrainPID.whenPressed(new StopDrivetrainPID());
	
	JoystickButton startElevatorPID = new JoystickButton(elevatorStick, Constants.startElevatorPID);
	startElevatorPID.whenPressed(new StartElevatorPID());
	
	JoystickButton stopElevatorPID = new JoystickButton(elevatorStick, Constants.stopElevatorPID);
	stopElevatorPID.cancelWhenPressed(new StartElevatorPID());
	stopElevatorPID.whenPressed(new StopElevatorPID());
	
	/*JoystickButton stopMotors = new JoystickButton(stick, Constants.stopMotors);
	stopMotors.cancelWhenPressed(new StartPID());
	stopMotors.whenPressed(new StopPID());
	stopMotors.whenPressed(new StopMotors());
	
	//general buttons
	JoystickButton restartDumbDrive = new JoystickButton(stick, Constants.restartDumbDriveButton);
	restartDumbDrive.whenPressed(new DumbDrive());*/
	
	//pneumatics buttons
	JoystickButton extend = new JoystickButton(stick, Constants.extend);
	extend.whenPressed(new ExtendPiston(1.5));
	JoystickButton retract = new JoystickButton(stick, Constants.retract);
	retract.whenPressed(new RetractPiston(1.5));
	
	}
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
}

