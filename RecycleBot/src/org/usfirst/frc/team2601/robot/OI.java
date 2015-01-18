package org.usfirst.frc.team2601.robot;

import org.usfirst.frc.team2601.robot.Constants;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team2601.robot.commands.DumbDrive;
import org.usfirst.frc.team2601.robot.commands.ExtendPiston;
import org.usfirst.frc.team2601.robot.commands.RetractPiston;
import org.usfirst.frc.team2601.robot.commands.StartPID;
import org.usfirst.frc.team2601.robot.commands.StopMotors;
import org.usfirst.frc.team2601.robot.commands.StopPID;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
	public static  Joystick stick = new Joystick(Constants.joystickPort);
	
	public OI(){
	//PID buttons
	JoystickButton startPID = new JoystickButton(stick, Constants.startPID);
	startPID.whenPressed(new StartPID());
	
	JoystickButton stopPID = new JoystickButton(stick, Constants.stopPID);
	stopPID.cancelWhenPressed(new StartPID());
	stopPID.whenPressed(new StopPID());
	
	
	JoystickButton stopMotors = new JoystickButton(stick, Constants.stopMotors);
	stopMotors.cancelWhenPressed(new StartPID());
	stopMotors.whenPressed(new StopPID());
	stopMotors.whenPressed(new StopMotors());
	
	//general buttons
	JoystickButton restartDumbDrive = new JoystickButton(stick, Constants.restartDumbDriveButton);
	restartDumbDrive.whenPressed(new DumbDrive());
	
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

