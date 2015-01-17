package org.usfirst.frc.team2601.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.DumbDrive;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2601.robot.Robot;



/**
 *
 */
public class Drivetrain extends Subsystem {
    //set up all of the things involved in the drivetrain
	CANTalon leftTalon = new CANTalon(Constants.leftTalonAddress);
	CANTalon rightTalon = new CANTalon(Constants.rightTalonAddress);
	RobotDrive drive = new RobotDrive(leftTalon, rightTalon);
	Encoder leftEncoder = new Encoder(Constants.leftEncoderPortI,Constants.leftEncoderPortII, true, Encoder.EncodingType.k4X);
	
	//setup first version of PID controller
	PIDController control = new PIDController(Constants.kP,Constants.kI,Constants.kD, leftEncoder, leftTalon);
	
	SmartDashboard dash;

    public void initDefaultCommand() {
    	//DumbDrive on start
    	setDefaultCommand(new DumbDrive());
    }
    
    public void dumbDrive(Joystick stick){
    	//start code here, simple arcadedrive
    	leftEncoder.reset();
    	drive.arcadeDrive(stick);
    }
    
    public void getPIDvalues(){
    	//retrieve PID vals from NetTables
    	Constants.kP = Robot.table.getNumber("P");
    	Constants.kI = Robot.table.getNumber("I");
    	Constants.kD = Robot.table.getNumber("D");
    	Constants.setpoint = Robot.table.getNumber("setpoint");
    }
    
    public void startPID(){
    	//get PID values from Net Tables
    	getPIDvalues();
    	
    	//set up encoders
    	leftEncoder.setDistancePerPulse(Constants.distancePerPulse);
    	leftEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
    	
    	//set up PID loop parameters
    	control.setPID(Constants.kP, Constants.kI, Constants.kD);
    	control.setSetpoint(Constants.setpoint);
    	control.setAbsoluteTolerance(Constants.absoluteTolerance);
    	control.setOutputRange(Constants.minOutput, Constants.maxOutput);
    	
    	//enable loop, match motors
    	control.enable();
    	matchMotors();
    	
    	//check if it's ok to stop
    	if (control.onTarget()){
    		stopPID();
    	}
    	
    	//let's see if this is working
    	SmartDashboard.putNumber("LeftEncoder Distance", leftEncoder.getDistance());
    	SmartDashboard.putNumber("Error", control.getError());
    	SmartDashboard.putNumber("setpoint", Constants.setpoint);
    	
    }
    
    public void matchMotors(){
    	//keep motors together for straight line PID
    	rightTalon.set(leftTalon.get()* -1);
    }
    
    public void stopMotors(){
    	//stop everything
    	rightTalon.set(0.0);
    	leftTalon.set(0.0);
    }
    
    public void stopPID(){
    	//disable PID loop
    	control.disable();
    }
    
    
}














