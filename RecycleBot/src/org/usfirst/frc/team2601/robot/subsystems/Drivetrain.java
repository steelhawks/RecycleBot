package org.usfirst.frc.team2601.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.DumbDrive;
import org.usfirst.frc.team2601.robot.commands.OmniDrive;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;

import org.usfirst.frc.team2601.robot.Robot;



/**
 *
 */
public class Drivetrain extends Subsystem {
    //set up all of the things involved in the drivetrain
	String filename = "/logs/drivetrainlog.csv";
	CANTalon leftTalon = new CANTalon(Constants.leftTalonAddress);
	CANTalon rightTalon = new CANTalon(Constants.rightTalonAddress);
	CANTalon centerTalon = new CANTalon(Constants.centerTalonAddress);
	RobotDrive drive = new RobotDrive(leftTalon, rightTalon);
	Encoder leftEncoder = new Encoder(Constants.leftEncoderPortI,Constants.leftEncoderPortII, true, Encoder.EncodingType.k4X);
	DriverStation driver;
	
	//setup first version of PID controller
	PIDController control = new PIDController(Constants.kP,Constants.kI,Constants.kD, leftEncoder, leftTalon);
	boolean CSVstart;
	
	SmartDashboard dash;
		
    public void initDefaultCommand() {
    	//DumbDrive on start
    	setDefaultCommand(new OmniDrive());
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
    	
    }
    public void getSetpoint(){
    	Constants.setpoint = Robot.table.getNumber("setpoint");
    }
    
    public void startPID(){
    	//get PID values from Net Tables
    	getPIDvalues();
    	getSetpoint();
    	
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
    
    public void distanceDriveForwardPID(){
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
    		return;
    	} 	
    }
    
    public void moveForward(){
    	leftTalon.set(0.5 * Constants.leftTalonMultiplier);
    	rightTalon.set(-0.5 * Constants.rightTalonMultiplier);
    }
    
    public void moveBackward(){
    	leftTalon.set(-0.5 * Constants.leftTalonMultiplier);
    	rightTalon.set(0.5 * Constants.rightTalonMultiplier);
    }
    
    public void omniDrive(Joystick stick){
    	double yval = stick.getY();
    	double xval = stick.getX();
    	double twist = stick.getTwist();
    	centerTalon.set(xval * Constants.centerTalonMultiplier);
    	
    	//begin screwing around with rolling my own method
    	
    	/*if (twist == 0){
    		rightTalon.set(yval*Constants.rightTalonMultiplier);
    		leftTalon.set(yval*Constants.leftTalonMultiplier);
    	}
    	else if (yval == 0 && twist != 0){
    		rightTalon.set(twist * -1 * Constants.rightTalonMultiplier);
    		leftTalon.set(twist * Constants.leftTalonMultiplier);
    	}
    	else if (yval != 0 && twist != 0){
    		rightTalon.set(twist * yval * -1 * Constants.rightTalonMultiplier);
    		leftTalon.set(twist * yval * Constants.leftTalonMultiplier);
    	}*/
    	
    	//this line below pretty much does everything i was trying to do in the method above ~Marcus
    	
    	drive.arcadeDrive(yval, twist);
    	
    }
    

    public void logStart(String param){
    	try
    	{
    	    FileWriter writer = new FileWriter(filename);
     
    	    writer.append("Time");
    	    writer.append(',');
    	    writer.append(param);
    	    writer.append('\n');
     
    	    //generate whatever data you want
     
    	    writer.flush();
    	    writer.close();
    	}
    	catch(IOException e)
    	{
    	     e.printStackTrace();
    	} 
    	CSVstart = true;
        }
    
    public void logData(double val){
    	driver = DriverStation.getInstance();
    	if (CSVstart){
    		try
    		{
    		    FileWriter writer = new FileWriter(filename);
    	 String value = Double.toString(val);
    		    writer.append(Double.toString(driver.getMatchTime()));
    		    writer.append(',');
    		    writer.append(value);
    		    writer.append('\n');
    	 
    		    writer.flush();
    		    writer.close();
    		}
    		catch(IOException e)
    		{
    		     e.printStackTrace();
    		} 
    	    }
    	}
    }
	
    
    
    
    
    















