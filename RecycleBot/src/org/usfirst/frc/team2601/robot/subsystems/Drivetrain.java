package org.usfirst.frc.team2601.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.DumbDrive;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.ExponentialInputsArcadeOmniDrive;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.OmniDrive;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.TankOmniDrive;
import org.usfirst.frc.team2601.robot.util.F310;
import org.usfirst.frc.team2601.robot.util.HawkDrive;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;

import org.usfirst.frc.team2601.robot.Robot;

import java.lang.Math;
/**
 *
 */
public class Drivetrain extends Subsystem {
    //set up all of the things involved in the drivetrain
	String motorStatsFilename = "/logs/motorstatslog" + Timer.getFPGATimestamp() +".csv";
	CANTalon leftTalonI = new CANTalon(Constants.leftTalonAddressI);
	CANTalon rightTalonI = new CANTalon(Constants.rightTalonAddressI);
	CANTalon leftTalonII = new CANTalon(Constants.leftTalonAddressII);
	CANTalon rightTalonII = new CANTalon(Constants.rightTalonAddressII);
	CANTalon centerTalon = new CANTalon(Constants.centerTalonAddress);
	//RobotDrive drive = new RobotDrive(leftTalonI, leftTalonII, rightTalonI, rightTalonII);
	
	Encoder leftEncoder = new Encoder(Constants.leftEncoderPortI,Constants.leftEncoderPortII, false, Encoder.EncodingType.k4X);
	Encoder rightEncoder = new Encoder(Constants.rightEncoderPortI, Constants.rightEncoderPortII, true, Encoder.EncodingType.k4X);
	HawkDrive drive = new HawkDrive(leftTalonI,leftTalonII,rightTalonI,rightTalonII,centerTalon,leftEncoder,rightEncoder);
	
	//DriverStation driver
	DriverStation driver;
	Boolean CSVstart = false;
	
	//setup first version of PID controller
	PIDController control = new PIDController(Constants.drivetrainP,Constants.drivetrainI,Constants.drivetrainD, leftEncoder, leftTalonI);
	//boolean CSVstart; 
	
	PIDController leftVelocityPID = new PIDController(Constants.drivetrainP, Constants.drivetrainI, Constants.drivetrainD, leftEncoder, leftTalonI);
	PIDController rightVelocityPID = new PIDController(Constants.drivetrainP,Constants.drivetrainI,Constants.drivetrainD, rightEncoder, rightTalonI);
	
	SmartDashboard dash;
	FileWriter writer;
	ArrayList<String> printstats;
	ArrayList <String> keys =  new ArrayList<String>();
	ArrayList<Double> stats = new ArrayList<Double>();
	ArrayList<String> headers = new ArrayList<String>();
	HashMap<String, CANTalon> dataHashMap = new HashMap<String, CANTalon>();
	
	
	public Drivetrain(){
	try {
	    writer = new FileWriter(motorStatsFilename);
	    }

	catch(IOException e)
	{
	     e.printStackTrace();
	} 
	}

	//gets output values of Talons
	public void printStats(CANTalon Talon, String name){
		
		double currentAmps = Talon.getOutputCurrent();
		double outputV = Talon.getOutputVoltage();
		double inputV = Talon.getBusVoltage();
		double dualEncoderPos = Talon.getEncPosition();
		double dualEncoderVelocity = Talon.getEncVelocity();
		int analogPos = Talon.getAnalogInPosition();
		int analogVelocity = Talon.getAnalogInVelocity();
		double selectedSensorPos = Talon.getPosition();
		double selectedSensorVelocity = Talon.getSpeed();
		int closeLoopErr = Talon.getClosedLoopError();
		double talonGetData = Talon.get();
		double currentTime = Timer.getFPGATimestamp();

		//encoder
		double leftEncoderDistance = leftEncoder.getDistance();
		double leftEncoderSpeed = leftEncoder.getRate();
		
		ArrayList<Double> stats = new ArrayList<Double>();
		
		
		String cA = name + " currentAmps";
		String oV = name + " outputV";
		String iV = name + " inputV";
		String dEP = name + " dualEncoderPos";
		String dEV = name + " dualEncoderVelocity";
		String aP = name + " analogPos";
		String aV = name + " analogVelocity";
		String sSP = name + " selectedSensorPos";
		String sSV = name + " selectedSensorVelocity";
		String cLE = name + " closeLoopErr";
		String getTalon = name + " get";
		
		//encoder
		String getLeftEncoderDistance = name + " getEncoderDistance";
		String getLeftEncoderSpeed = name + " getSpeed()"; 
		
		if (!CSVstart){
			ArrayList<String> printstats = new ArrayList<String>();
			printstats.add(cA);
			printstats.add(oV);
			printstats.add(iV);
			printstats.add(dEP);
			printstats.add(dEV);
			//printstats.add(aP);
			//printstats.add(aV);
			//printstats.add(sSP);
			//printstats.add(sSV);
			printstats.add(cLE);
			printstats.add(getTalon);
			printstats.add(getLeftEncoderDistance);
			printstats.add(getLeftEncoderSpeed);
			System.out.println(printstats);
			startCSV(motorStatsFilename, printstats);
			endLine(motorStatsFilename);
		}
		
		SmartDashboard.putNumber(getTalon, talonGetData);
		SmartDashboard.putNumber(cA, currentAmps);
		SmartDashboard.putNumber(oV, outputV);
		SmartDashboard.putNumber(iV, inputV);
		SmartDashboard.putNumber(dEP, dualEncoderPos);
		SmartDashboard.putNumber(dEV,dualEncoderVelocity);
		SmartDashboard.putNumber(aP, analogPos);
		SmartDashboard.putNumber(aV, analogVelocity);
		SmartDashboard.putNumber(sSP, selectedSensorPos);
		SmartDashboard.putNumber(sSV, selectedSensorVelocity);
		SmartDashboard.putNumber(cLE, closeLoopErr);
		
		//encoder
		SmartDashboard.getNumber(getLeftEncoderDistance, leftEncoderDistance);
		SmartDashboard.getNumber(getLeftEncoderSpeed, leftEncoderSpeed);
		stats.clear();
		
		stats.add(currentTime);
		stats.add(currentAmps);
		stats.add(outputV);
		stats.add(inputV);
		stats.add(dualEncoderPos);
		stats.add(dualEncoderVelocity);
		stats.add((double) closeLoopErr);
		stats.add(talonGetData);
		stats.add(leftEncoderDistance);
		stats.add(leftEncoderSpeed);
		addData(motorStatsFilename,stats);
		
		endLine(motorStatsFilename);
		
		
	}
	
	public void printStats(HashMap<String, CANTalon> hash){
				
		//maybe a better way than clearing keys everytime, F it, ship it.
		//keys.clear();
		
		if(keys.isEmpty()){
			Set talonSet = hash.keySet();
			Iterator i = talonSet.iterator();
			while(i.hasNext()){
				keys.add(i.next().toString());
			}
		}
		
		stats.clear();
		headers.clear();
		
		
		ArrayList <CANTalon> talons= new ArrayList<CANTalon>();
		for (int x = 0; x<keys.size(); x++){
			talons.add(hash.get(keys.get(x)));
		}

		double currentTime = Timer.getFPGATimestamp();
		double leftEncoderDistance = leftEncoder.getDistance();
		double rightEncoderDistance = rightEncoder.getDistance();
		double leftEncoderSpeed = leftEncoder.getRate();
		double rightEncoderSpeed = rightEncoder.getRate();
		String getRightEncoderDistance = "right encoder getEncoderDistance";
		String getRightEncoderSpeed = "right encoder getSpeed()";
		String getLeftEncoderDistance = "left encoder" + " getEncoderDistance";
		String getLeftEncoderSpeed = "left encoder" + " getSpeed()"; 
		
		
		//headers.add("time");
		if(!CSVstart){
			headers.add(getLeftEncoderDistance);
			headers.add(getLeftEncoderSpeed);
			headers.add(getRightEncoderDistance);
			headers.add(getRightEncoderSpeed);}
		
		stats.add(currentTime);
		stats.add(leftEncoderDistance);
		stats.add(leftEncoderSpeed);
		stats.add(rightEncoderDistance);
		stats.add(rightEncoderSpeed);
		
		//int d = 0;
		
		for (int d = 0; d<talons.size(); d++){
			CANTalon Talon = talons.get(d);
			String name = keys.get(d);
			
			double currentAmps = Talon.getOutputCurrent();
			double outputV = Talon.getOutputVoltage();
			double inputV = Talon.getBusVoltage();
			double dualEncoderPos = Talon.getEncPosition();
			double dualEncoderVelocity = Talon.getEncVelocity();
			int analogPos = Talon.getAnalogInPosition();
			int analogVelocity = Talon.getAnalogInVelocity();
			double selectedSensorPos = Talon.getPosition();
			double selectedSensorVelocity = Talon.getSpeed();
			int closeLoopErr = Talon.getClosedLoopError();
			double talonGetData = Talon.get();
			
			String cA = name + " currentAmps";
			String oV = name + " outputV";
			String iV = name + " inputV";
			String dEP = name + " dualEncoderPos";
			String dEV = name + " dualEncoderVelocity";
			String aP = name + " analogPos";
			String aV = name + " analogVelocity";
			String sSP = name + " selectedSensorPos";
			String sSV = name + " selectedSensorVelocity";
			String cLE = name + " closeLoopErr";
			String getTalon = name + " get";
			
			
			if (!CSVstart){
				headers.add(cA);
				headers.add(oV);
				headers.add(iV);
				headers.add(dEP);
				headers.add(dEV);
				//printstats.add(aP);
				//printstats.add(aV);
				//printstats.add(sSP);
				//printstats.add(sSV);
				headers.add(cLE);
				headers.add(getTalon);
				//headers.add(getLeftEncoderDistance);
				//headers.add(getLeftEncoderSpeed);
				System.out.println(headers);
			}
			
			SmartDashboard.putNumber(getTalon, talonGetData);
			SmartDashboard.putNumber(cA, currentAmps);
			SmartDashboard.putNumber(oV, outputV);
			SmartDashboard.putNumber(iV, inputV);
			SmartDashboard.putNumber(dEP, dualEncoderPos);
			SmartDashboard.putNumber(dEV,dualEncoderVelocity);
			SmartDashboard.putNumber(aP, analogPos);
			SmartDashboard.putNumber(aV, analogVelocity);
			SmartDashboard.putNumber(sSP, selectedSensorPos);
			SmartDashboard.putNumber(sSV, selectedSensorVelocity);
			SmartDashboard.putNumber(cLE, closeLoopErr);
			
			//encoder
			SmartDashboard.putNumber(getLeftEncoderDistance, leftEncoderDistance);
			SmartDashboard.putNumber(getLeftEncoderSpeed, leftEncoderSpeed);
			SmartDashboard.putNumber(getRightEncoderDistance, rightEncoderDistance);
			SmartDashboard.putNumber(getRightEncoderSpeed, rightEncoderSpeed);
			
			//stats.add(currentTime);
			stats.add(currentAmps);
			stats.add(outputV);
			stats.add(inputV);
			stats.add(dualEncoderPos);
			stats.add(dualEncoderVelocity);
			stats.add((double) closeLoopErr);
			stats.add(talonGetData);
			//stats.add(leftEncoderDistance);
			//stats.add(leftEncoderSpeed);
			//d++;
			/*if (talons.get(d).equals(null)){
				break;*/
			
		}
		if (!CSVstart){
			startCSV(motorStatsFilename, headers);
			endLine(motorStatsFilename);}
		
		addData(motorStatsFilename,stats);
		endLine(motorStatsFilename);
		
		
	}
    
	public void initDefaultCommand() {
    	setDefaultCommand(new OmniDrive());
    	//setDefaultCommand(new ExponentialInputsArcadeOmniDrive());
		//setDefaultCommand(new TankOmniDrive());
	}
    
    public void dumbDrive(Joystick stick){
    	//start code here, simple arcadedrive
    	//leftEncoder.reset();
    	drive.arcadeDrive(stick);
    }
    
    public void getPIDvalues(){
    	//retrieve PID vals from NetTables
    	Constants.drivetrainP = Robot.table.getNumber(Constants.drivetrainPKey);
    	Constants.drivetrainI = Robot.table.getNumber(Constants.drivetrainIKey);
    	Constants.drivetrainD = Robot.table.getNumber(Constants.drivetrainDKey);
    	
    }
    public void getSetpoint(){
    	Constants.drivetrainSetpoint = Robot.table.getNumber(Constants.drivetrainSetpointKey);
    }
   
    public void driveWithVelocityPID(Joystick stick){
    	/*getPIDvalues();
    	leftEncoder.setDistancePerPulse(Constants.drivetrainDistancePerPulse);
    	rightEncoder.setDistancePerPulse(Constants.drivetrainDistancePerPulse);
    	
    	leftEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kRate);
    	rightEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kRate);
    
    	
    	leftVelocityPID.setOutputRange(Constants.drivetrainMinOutput, Constants.drivetrainMaxOutput);
    	rightVelocityPID.setOutputRange(Constants.drivetrainMinOutput, Constants.drivetrainMaxOutput);
    	
    	leftVelocityPID.setPID(Constants.drivetrainP, Constants.drivetrainI, Constants.drivetrainD);
    	rightVelocityPID.setPID(Constants.drivetrainP, Constants.drivetrainI, Constants.drivetrainD);
    	
    	Constants.drivetrainSetpoint = stick.getY();
    	SmartDashboard.putNumber("Setpoint", Constants.drivetrainSetpoint);
    	leftVelocityPID.setSetpoint(Constants.drivetrainSetpoint);
    	rightVelocityPID.setSetpoint(Constants.drivetrainSetpoint);
    	
    	
    	leftVelocityPID.enable();
    	rightVelocityPID.enable();
    	
    	SmartDashboard.putNumber("leftPID", leftVelocityPID.get());
    	SmartDashboard.putNumber("P", leftVelocityPID.getP());
    	
    	leftTalonII.set(leftVelocityPID.get());
    	rightTalonII.set(rightVelocityPID.get());
    	centerTalon.set(stick.getX());*/
    	
    }
    public void stopVelocityPID(){
    	leftVelocityPID.disable();
    	rightVelocityPID.disable();
    }
    
    public void startPID(){
    	//get PID values from Net Tables
    	getPIDvalues();
    	getSetpoint();
    	
    	//set up encoders
    	leftEncoder.setDistancePerPulse(Constants.drivetrainDistancePerPulse);
    	leftEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
    	rightEncoder.setDistancePerPulse(Constants.drivetrainDistancePerPulse);
    	rightEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
    	
    	
    	//set up PID loop parameters
    	control.setPID(Constants.drivetrainP, Constants.drivetrainI, Constants.drivetrainD);
    	control.setSetpoint(Constants.drivetrainSetpoint);
    	control.setAbsoluteTolerance(Constants.drivetrainAbsoluteTolerance);
    	control.setOutputRange(Constants.drivetrainMinOutput, Constants.drivetrainMaxOutput);
    	
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
    	SmartDashboard.putNumber("setpoint", Constants.drivetrainSetpoint);
    	
    }
    
    public void matchMotors(){
    	//keep motors together for straight line PID
    	rightTalonI.set(leftTalonI.get()* -1);
    	rightTalonII.set(leftTalonII.get()* -1);
    }
    
    public void stopMotors(){
    	//stop everything
    	rightTalonI.set(0.0);
    	leftTalonI.set(0.0);
    	rightTalonII.set(0.0);
    	leftTalonII.set(0.0);
    }
    
    public void stopPID(){
    	//disable PID loop
    	control.disable();
    }
    
    public void distanceDriveForwardPID(){
    	//get PID values from Net Tables
    	getPIDvalues();
    	
    	//set up encoders
    	leftEncoder.setDistancePerPulse(Constants.drivetrainDistancePerPulse);
    	leftEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
    	
    	//set up PID loop parameters
    	control.setPID(Constants.drivetrainP, Constants.drivetrainI, Constants.drivetrainD);
    	control.setSetpoint(Constants.drivetrainSetpoint);
    	control.setAbsoluteTolerance(Constants.drivetrainAbsoluteTolerance);
    	control.setOutputRange(Constants.drivetrainMinOutput, Constants.drivetrainMaxOutput);
    	
    	//enable loop, match motors
    	control.enable();
    	matchMotors();
    	
    	//check if it's ok to stop
    	if (control.onTarget()){
    		stopPID();
    		return;
    	} 	
    }
    public void autonMoveFoward(){
    	drive.autonomousStraight(0.25, 15.0);
    }
    public void autonMoveBackward(){
    	drive.autonomousStraight(-0.25, 15.0);
    }
    public void autonTurnLeft(){
    	//drive.arcadeDrive(0, -0.25);
    	leftTalonI.set(-Constants.drivetrainFineSpeed * Constants.leftDrivetrainTalonMultiplier);
    	rightTalonI.set(Constants.drivetrainFineSpeed * Constants.rightDrivetrainTalonMultiplier);
    	leftTalonII.set(-Constants.drivetrainFineSpeed * Constants.leftDrivetrainTalonMultiplier);
    	rightTalonII.set(Constants.drivetrainFineSpeed * Constants.rightDrivetrainTalonMultiplier);
    }
    public void autonTurnRight(){
    	//drive.arcadeDrive(0,0.25);
    	leftTalonI.set(Constants.drivetrainFineSpeed * Constants.leftDrivetrainTalonMultiplier);
    	rightTalonI.set(-Constants.drivetrainFineSpeed * Constants.rightDrivetrainTalonMultiplier);
    	leftTalonII.set(Constants.drivetrainFineSpeed * Constants.leftDrivetrainTalonMultiplier);
    	rightTalonII.set(-Constants.drivetrainFineSpeed * Constants.rightDrivetrainTalonMultiplier);
    }
    public void autonStrafeRight(){
    	double xval = 2.0;
    	centerTalon.set(xval*Constants.drivetrainFineSpeed);
    }
    public void autonStrafeLeft(){
    	double xval = -2.0;
    	centerTalon.set(xval*Constants.drivetrainFineSpeed);
    }
    public void moveForward(){
    	
    	leftTalonI.set(Constants.drivetrainSpeed * Constants.leftDrivetrainTalonMultiplier);
    	rightTalonI.set(Constants.drivetrainSpeed * Constants.rightDrivetrainTalonMultiplier);
    	leftTalonII.set(Constants.drivetrainSpeed * Constants.leftDrivetrainTalonMultiplier);
    	rightTalonII.set(Constants.drivetrainSpeed * Constants.rightDrivetrainTalonMultiplier);
    	printStats(leftTalonI,"leftTalonI");
    	printStats(rightTalonI,"rightTalonI");
    	printStats(leftTalonII,"leftTalonII");
    	printStats(rightTalonII,"rightTalonII");
    	
    }
    
    public void moveBackward(){
    	leftTalonI.set(Constants.drivetrainSpeed * Constants.leftDrivetrainTalonMultiplier);
    	rightTalonI.set(Constants.drivetrainSpeed * Constants.rightDrivetrainTalonMultiplier);
    	leftTalonII.set(Constants.drivetrainSpeed * Constants.leftDrivetrainTalonMultiplier);
    	rightTalonII.set(Constants.drivetrainSpeed * Constants.rightDrivetrainTalonMultiplier);
    	printStats(leftTalonI,"leftTalonI");
    	printStats(rightTalonI,"rightTalonI");
    	printStats(leftTalonII,"leftTalonII");
    	printStats(rightTalonII,"rightTalonII");
    }
    
    // OMNI 
    public void omniDrive(Joystick stick){
    	double yval = -stick.getY();
    	double xval = stick.getX();
    	double twist = -stick.getTwist();
    	centerTalon.set(xval * Constants.drivetrainSpeed);
    	double leftEncoderSpeed = leftEncoder.getRate();
		double rightEncoderSpeed = rightEncoder.getRate();
    	//drive.arcadeDrive(yval * Constants.drivetrainSpeed,twist * Constants.drivetrainFineSpeed/*Constants.drivetrainSpeed*/,false);
    
    	//drive.customArcade(yval * Constants.drivetrainSpeed, twist * Constants.drivetrainFineSpeed/*Constants.drivetrainSpeed*/, xval* Constants.drivetrainSpeed, false, leftEncoderSpeed, rightEncoderSpeed, 15);
		drive.customArcade(stick, 15.0);
    	SmartDashboard.putNumber("leftencoder", leftEncoder.getRate());
    	SmartDashboard.putNumber("Rightencoder", rightEncoder.getRate());
    	printStats(leftTalonI,"leftTalonI");
    	printStats(rightTalonI,"rightTalonI");
    	printStats(leftTalonII,"leftTalonII");
    	printStats(rightTalonII,"rightTalonII");
    	/*dataHashMap.clear();
    	dataHashMap.put("leftTalonI", leftTalonI);
    	dataHashMap.put("leftTalonII", leftTalonI);
    	dataHashMap.put("rightTalonI", rightTalonI);
    	dataHashMap.put("rightTalonII", rightTalonII);
    	dataHashMap.put("centerTalon", centerTalon);
    	printStats(dataHashMap);  */
    	//Timer.delay(0.02);
    }
   /* public void calculateDriveTrainSpeed(Joystick stick){
    	double maxChange = 0.5;
    	double lastXval = ;
    	double lastYval = ;
    	double speed = 0;
    	
    	while(){
    		double input = stick.getX();
    		
    	}
    }*/
    public void gamepadOmniDrive(Joystick stick){
    	double left = -stick.getRawAxis(F310.kGamepadAxisLeftStickY);
    	double right = -stick.getRawAxis(F310.kGamepadAxisRightStickY);
    	double strafe = Math.max(stick.getRawAxis(F310.kGamepadAxisLeftStickX), stick.getRawAxis(F310.kGamepadAxisRightStickX));
    	drive.tankDrive(left*Constants.drivetrainSpeed, right*Constants.drivetrainSpeed);
    	centerTalon.set(strafe*Constants.drivetrainSpeed);
    	dataHashMap.clear();
    	dataHashMap.put("leftTalonI", leftTalonI);
    	dataHashMap.put("leftTalonII", leftTalonI);
    	dataHashMap.put("rightTalonI", rightTalonI);
    	dataHashMap.put("rightTalonII", rightTalonII);
    	dataHashMap.put("centerTalon", centerTalon);
    	printStats(dataHashMap);
    }
    
    public void exponentiaInputArcadeOmni(Joystick stick, double power){
    	double move = Math.pow(stick.getY(),power);
    	double twist = Math.pow(stick.getTwist(), power);
    	double strafe = Math.pow(stick.getX(), power);
    	
    	arcadeOmniDrive(move,twist,strafe);
    	/*double move = 0;
    	double twist = 0;
    	double strafe = 0;
    	SmartDashboard.putNumber("move", move);
    	SmartDashboard.putNumber("twist", twist);
    	SmartDashboard.putNumber("strafe", strafe);
    	    	
    	while(Robot.isReal()==true){
    		double newMove = move + 0.25;
    		double newTwist = twist + 0.25;
    		double newStrafe = strafe + 0.25;
    	}
    	double xval = newMove;
    	double 
    	arcadeOmniDrive(newMove, newTwist, newStrafe);*/
    }
    
    
    public void arcadeOmniDrive(double move, double twist, double strafe){
    	drive.arcadeDrive(-move*Constants.drivetrainSpeed, -twist*Constants.drivetrainFineSpeed, false);
    	
    	centerTalon.set(strafe);
    	//dataHashMap.clear();
    	//dataHashMap.put("leftTalonI", leftTalonI);
    	//dataHashMap.put("leftTalonII", leftTalonI);
    	//dataHashMap.put("rightTalonI", rightTalonI);
    	//dataHashMap.put("rightTalonII", rightTalonII);
    	//dataHashMap.put("centerTalon", centerTalon);
    	//printStats(dataHashMap);
    }
    
    public void tankOmniDrive(Joystick leftStick, Joystick rightStick){
    	double left = leftStick.getY()*Constants.leftDrivetrainTalonMultiplier*Constants.drivetrainSpeed;
    	double right = rightStick.getY()*Constants.rightDrivetrainTalonMultiplier*Constants.drivetrainSpeed;
    	double strafe = Math.max(leftStick.getX(), rightStick.getX())*Constants.centerDrivetrainTalonMultiplier*Constants.drivetrainSpeed;
    	tankOmniDrive(left, right, strafe);
    }
    
    public void fineTankOmniDrive(Joystick leftStick, Joystick rightStick){
    	double left = leftStick.getY()*Constants.leftDrivetrainTalonMultiplier*Constants.drivetrainFineSpeed;
    	double right = rightStick.getY()*Constants.rightDrivetrainTalonMultiplier*Constants.drivetrainFineSpeed;
    	double strafe = Math.max(leftStick.getX(), rightStick.getX())*Constants.centerDrivetrainTalonMultiplier*Constants.drivetrainFineSpeed;
    	tankOmniDrive(left, right, strafe);
    }
    public void fineArcadeOmniDrive(Joystick stick){
    	
    	double yval = -stick.getY();
    	double xval = stick.getX();
    	double twist = -stick.getTwist();
    	centerTalon.set(xval * Constants.drivetrainFineSpeed);
    	drive.arcadeDrive(yval * Constants.drivetrainFineSpeed,twist * Constants.drivetrainFineSpeed,false);
    }
    
    public void tankOmniDrive(double left, double right, double strafe){
    	drive.tankDrive(left, right);
    	centerTalon.set(strafe);
    	/*dataHashMap.clear();
    	dataHashMap.put("leftTalonI", leftTalonI);
    	dataHashMap.put("leftTalonII", leftTalonI);
    	dataHashMap.put("rightTalonI", rightTalonI);
    	dataHashMap.put("rightTalonII", rightTalonII);
    	dataHashMap.put("centerTalon", centerTalon);
    	printStats(dataHashMap);*/
    }
    
//Start csv/logging stuff
    
    //adds headers to a new CSV file
    public void startCSV(String filename, ArrayList<String> list){
		try
    	{
    	    writer.append("Time");
    	    writer.append(',');
    	    for (int i = 0; i<list.size(); i++){
    	    	writer.append(list.get(i));
    	    	if(i < list.size()-1){
    	    	writer.append(',');
    	    	}
    	    }
    
    	    writer.flush();
    	    //writer.close();
    	}
    	catch(IOException e)
    	{
    	     e.printStackTrace();
    	} 
    	CSVstart = true;
        }
	//writes in a newline to a csv file
	public void endLine(String filename){
		if (CSVstart){
		try {
			writer.append('\n');
			writer.flush();
			//writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
	//add data given in the form of a list of doubles
	public void addData(String filename, ArrayList<Double> list){
		if (CSVstart){
			try
	    	{
	    	    for (int i = 0; i<list.size(); i++){
	    	    	writer.append(list.get(i).toString());
	    	    	if(i < list.size()-1){
	    	    	writer.append(',');
	    	    	}
	    	    }
	    
	    	    writer.flush();
	    	    //writer.close();
	    	}
	    	catch(IOException e)
	    	{
	    	     e.printStackTrace();
	    	} 
		}
	}
	//add single doubles to the log	
	public void addData(String filename, Double data){
		if (CSVstart){
			try {
				writer.append(data.toString());
				writer.append(",");
				writer.flush();
				//writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public void closeWriter(){
		try{
			writer.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void bothMotorsForward(){
		leftTalonI.set(0.5*Constants.leftDrivetrainTalonMultiplier);
		leftTalonII.set(0.5*Constants.leftDrivetrainTalonMultiplier);
		rightTalonI.set(0.5*Constants.rightDrivetrainTalonMultiplier);
		rightTalonII.set(0.5*Constants.rightDrivetrainTalonMultiplier);
	}
    

}
	
    
    
    

