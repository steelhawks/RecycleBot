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
	//CameraServer cam=null;
	//CameraServer topCam =null;;
    //set up all of the things involved in the drivetrain
	Constants myConstants = Constants.getInstance();
	
	String motorStatsFilename = "/logs/motorstatslog" + Timer.getFPGATimestamp() +".csv";
	CANTalon leftTalonI = new CANTalon(myConstants.leftTalonAddressI);
	CANTalon rightTalonI = new CANTalon(myConstants.rightTalonAddressI);
	CANTalon leftTalonII = new CANTalon(myConstants.leftTalonAddressII);
	CANTalon rightTalonII = new CANTalon(myConstants.rightTalonAddressII);
	CANTalon centerTalon = new CANTalon(myConstants.centerTalonAddress);
	//RobotDrive drive = new RobotDrive(leftTalonI, leftTalonII, rightTalonI, rightTalonII);
	
	Encoder leftEncoder= new Encoder(myConstants.leftEncoderPortI,myConstants.leftEncoderPortII, false, Encoder.EncodingType.k4X);
	Encoder rightEncoder= new Encoder(myConstants.rightEncoderPortI, myConstants.rightEncoderPortII, true, Encoder.EncodingType.k4X);
	HawkDrive drive = new HawkDrive(leftTalonI,leftTalonII,rightTalonI,rightTalonII,centerTalon,leftEncoder,rightEncoder);
	
	//DriverStation driver
	DriverStation driver;
	Boolean CSVstart = false;
	
	//setup first version of PID controller
	PIDController controlLeft = new PIDController(myConstants.drivetrainP,myConstants.drivetrainI,myConstants.drivetrainD, leftEncoder, leftTalonI);
	PIDController controlRight = new PIDController(myConstants.drivetrainP,myConstants.drivetrainI, myConstants.drivetrainD,rightEncoder,rightTalonI);
	//boolean CSVstart; 
	
	//PIDController leftVelocityPID = new PIDController(myConstants.drivetrainP, myConstants.drivetrainI, myConstants.drivetrainD, leftEncoder, leftTalonI);
	//PIDController rightVelocityPID = new PIDController(myConstants.drivetrainP,myConstants.drivetrainI,myConstants.drivetrainD, rightEncoder, rightTalonI);
	
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
    //camera
	/*public void cam(){
			
        	cam.startAutomaticCapture("cam0");
        
	}
	public void topCam(){
			cam = CameraServer.getInstance();
        	cam.startAutomaticCapture("cam1");
        
	}*/
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
    	myConstants.drivetrainP = Robot.table.getNumber(myConstants.drivetrainPKey);
    	myConstants.drivetrainI = Robot.table.getNumber(myConstants.drivetrainIKey);
    	myConstants.drivetrainD = Robot.table.getNumber(myConstants.drivetrainDKey);
    	
    }
    public void getSetpoint(){
    	myConstants.drivetrainSetpoint = Robot.table.getNumber(myConstants.drivetrainSetpointKey);
    }
   
    public void driveWithVelocityPID(Joystick stick){
    	/*getPIDvalues();
    	leftEncoder.setDistancePerPulse(myConstants.drivetrainDistancePerPulse);
    	rightEncoder.setDistancePerPulse(myConstants.drivetrainDistancePerPulse);
    	
    	leftEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kRate);
    	rightEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kRate);
    
    	
    	leftVelocityPID.setOutputRange(myConstants.drivetrainMinOutput, myConstants.drivetrainMaxOutput);
    	rightVelocityPID.setOutputRange(myConstants.drivetrainMinOutput, myConstants.drivetrainMaxOutput);
    	
    	leftVelocityPID.setPID(myConstants.drivetrainP, myConstants.drivetrainI, myConstants.drivetrainD);
    	rightVelocityPID.setPID(myConstants.drivetrainP, myConstants.drivetrainI, myConstants.drivetrainD);
    	
    	myConstants.drivetrainSetpoint = stick.getY();
    	SmartDashboard.putNumber("Setpoint", myConstants.drivetrainSetpoint);
    	leftVelocityPID.setSetpoint(myConstants.drivetrainSetpoint);
    	rightVelocityPID.setSetpoint(myConstants.drivetrainSetpoint);
    	
    	
    	leftVelocityPID.enable();
    	rightVelocityPID.enable();
    	
    	SmartDashboard.putNumber("leftPID", leftVelocityPID.get());
    	SmartDashboard.putNumber("P", leftVelocityPID.getP());
    	
    	leftTalonII.set(leftVelocityPID.get());
    	rightTalonII.set(rightVelocityPID.get());
    	centerTalon.set(stick.getX());*/
    	
    }
    public void stopVelocityPID(){
    	//leftVelocityPID.disable();
    	//rightVelocityPID.disable();
    }
    
    public void startPID(){
    	//get PID values from Net Tables
    	getPIDvalues();
    	getSetpoint();
    	
    	//set up encoders
    	leftEncoder.setDistancePerPulse(myConstants.drivetrainDistancePerPulse);
    	leftEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
    	rightEncoder.setDistancePerPulse(myConstants.drivetrainDistancePerPulse);
    	rightEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
    	
    	//set up PID loop parameters
    	controlLeft.setPID(myConstants.drivetrainP, myConstants.drivetrainI, myConstants.drivetrainD);
    	controlLeft.setSetpoint(myConstants.drivetrainSetpoint);
    	controlLeft.setAbsoluteTolerance(myConstants.drivetrainAbsoluteTolerance);
    	controlLeft.setOutputRange(myConstants.drivetrainMinOutput, myConstants.drivetrainMaxOutput);
    	
    	
    	//enable loop, match motors
    	controlLeft.enable();
    	matchMotors();
    	
    	//check if it's ok to stop
    	if (controlLeft.onTarget()){
    		stopPID();
    	}
    	
    	//let's see if this is working
    	SmartDashboard.putNumber("LeftEncoder Distance", leftEncoder.getDistance());
    	SmartDashboard.putNumber("Error", controlLeft.getError());
    	SmartDashboard.putNumber("setpoint", myConstants.drivetrainSetpoint);
    	
    }
    public boolean areYouThereYet(){
    	return(controlLeft.onTarget());
    }
    
    public void resetEncoders(){
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    
    public void matchMotors(){
    	//keep motors together for straight line PID
    	leftTalonII.set(leftTalonI.get()*1);
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
    	controlLeft.disable();
    	controlRight.disable();
    }
    
    public boolean distanceDriveForwardPID(Double setpoint){
    	//get PID values from Net Tables
    	getPIDvalues();
    	
    	//set up encoders
    	leftEncoder.setDistancePerPulse(myConstants.drivetrainDistancePerPulse);
    	leftEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
    	
    	rightEncoder.setDistancePerPulse(myConstants.drivetrainDistancePerPulse);
    	rightEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
    	
    	//set up PID loop parameters
    	controlLeft.setPID(myConstants.drivetrainP, myConstants.drivetrainI, myConstants.drivetrainD);
    	controlLeft.setSetpoint(setpoint);
    	controlLeft.setAbsoluteTolerance(myConstants.drivetrainAbsoluteTolerance);
    	controlLeft.setOutputRange(myConstants.drivetrainMinOutput, myConstants.drivetrainMaxOutput);
    	
    	controlRight.setPID(myConstants.drivetrainP, myConstants.drivetrainI, myConstants.drivetrainD);
    	controlRight.setSetpoint(setpoint);
    	controlRight.setAbsoluteTolerance(myConstants.drivetrainAbsoluteTolerance);
    	controlRight.setOutputRange(myConstants.drivetrainMinOutput, myConstants.drivetrainMaxOutput);
    	
    	//enable loop, match motors
    	controlLeft.enable();
    	leftTalonII.set(leftTalonI.get());
    	controlRight.enable();
    	rightTalonII.set(rightTalonI.get());
    	//matchMotors();
    	
    	System.out.println("lmI" + leftTalonI.get());
    	System.out.println("lmII" + leftTalonII.get());
    	System.out.println("rmI" + rightTalonI.get());
    	System.out.println("rmII" + rightTalonII.get());
    	
    	//TODO fix the goddamn pid
    	
    	//check if it's ok to stop
    	if (controlLeft.onTarget() && controlRight.onTarget()){
    		stopPID();
    		return true;
    	} 	
    	return false;
    }
    public void autonFastMoveForward(){
    	drive.autonomousStraight(1.0, 15.0);
    }
    public void autonFastMoveBackward(){
    	drive.autonomousStraight(-0.75, 15.0);
    }
    public void autonMoveFoward(){
    	drive.autonomousStraight(0.4, 15.0);
    }
    public void autonMoveBackward(){
    	drive.autonomousStraight(-0.50, 15.0);
    }
    public void autonTurnLeft(){
    	leftTalonI.set(-myConstants.autonTurnSpeed * myConstants.leftDrivetrainTalonMultiplier);
    	rightTalonI.set(myConstants.autonTurnSpeed * myConstants.rightDrivetrainTalonMultiplier);
    	leftTalonII.set(-myConstants.autonTurnSpeed * myConstants.leftDrivetrainTalonMultiplier);
    	rightTalonII.set(myConstants.autonTurnSpeed * myConstants.rightDrivetrainTalonMultiplier);
    }
    public void autonTurnRight(){
    	leftTalonI.set(myConstants.autonTurnSpeed * myConstants.leftDrivetrainTalonMultiplier);
    	rightTalonI.set(-myConstants.autonTurnSpeed * myConstants.rightDrivetrainTalonMultiplier);
    	leftTalonII.set(myConstants.autonTurnSpeed * myConstants.leftDrivetrainTalonMultiplier);
    	rightTalonII.set(-myConstants.autonTurnSpeed * myConstants.rightDrivetrainTalonMultiplier);
    }
    public void autonStrafeRight(){
    	double xval = 2.0;
    	centerTalon.set(xval*myConstants.drivetrainSpeed);
    }
    public void autonStrafeLeft(){
    	double xval = -2.0;
    	centerTalon.set(xval*myConstants.drivetrainSpeed);
    }
    public void moveForward(){
    	
    	leftTalonI.set(myConstants.drivetrainSpeed * myConstants.leftDrivetrainTalonMultiplier);
    	rightTalonI.set(myConstants.drivetrainSpeed * myConstants.rightDrivetrainTalonMultiplier);
    	leftTalonII.set(myConstants.drivetrainSpeed * myConstants.leftDrivetrainTalonMultiplier);
    	rightTalonII.set(myConstants.drivetrainSpeed * myConstants.rightDrivetrainTalonMultiplier);
    	printStats(leftTalonI,"leftTalonI");
    	printStats(rightTalonI,"rightTalonI");
    	printStats(leftTalonII,"leftTalonII");
    	printStats(rightTalonII,"rightTalonII");
    	
    }
    
    public void moveBackward(){
    	leftTalonI.set(myConstants.drivetrainSpeed * myConstants.leftDrivetrainTalonMultiplier);
    	rightTalonI.set(myConstants.drivetrainSpeed * myConstants.rightDrivetrainTalonMultiplier);
    	leftTalonII.set(myConstants.drivetrainSpeed * myConstants.leftDrivetrainTalonMultiplier);
    	rightTalonII.set(myConstants.drivetrainSpeed * myConstants.rightDrivetrainTalonMultiplier);
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
    	centerTalon.set(xval * myConstants.drivetrainSpeed);
    	double leftEncoderSpeed = leftEncoder.getRate();
		double rightEncoderSpeed = rightEncoder.getRate();
    	//drive.arcadeDrive(yval * myConstants.drivetrainSpeed,twist * myConstants.drivetrainFineSpeed/*myConstants.drivetrainSpeed*/,false);
    
    	drive.customArcade(yval * myConstants.drivetrainSpeed, twist * myConstants.drivetrainTurnSpeed/*myConstants.drivetrainSpeed*/, xval* myConstants.drivetrainSpeed, false, leftEncoderSpeed, rightEncoderSpeed, 15);
		drive.customArcade(stick, 15.0);
		//System.out.println(twist);
    	SmartDashboard.putNumber("leftencoder", leftEncoder.getRate());
    	SmartDashboard.putNumber("Rightencoder", rightEncoder.getRate());
    	/*printStats(leftTalonI,"leftTalonI");
    	printStats(rightTalonI,"rightTalonI");
    	printStats(leftTalonII,"leftTalonII");
    	printStats(rightTalonII,"rightTalonII");
    	dataHashMap.clear();
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
    	drive.tankDrive(left*myConstants.drivetrainSpeed, right*myConstants.drivetrainSpeed);
    	centerTalon.set(strafe*myConstants.drivetrainSpeed);
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
    	drive.arcadeDrive(-move*myConstants.drivetrainSpeed, -twist*myConstants.drivetrainFineSpeed, false);
    	
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
    	double left = leftStick.getY()*myConstants.leftDrivetrainTalonMultiplier*myConstants.drivetrainSpeed;
    	double right = rightStick.getY()*myConstants.rightDrivetrainTalonMultiplier*myConstants.drivetrainSpeed;
    	double strafe = Math.max(leftStick.getX(), rightStick.getX())*myConstants.centerDrivetrainTalonMultiplier*myConstants.drivetrainSpeed;
    	tankOmniDrive(left, right, strafe);
    }
    
    public void fineTankOmniDrive(Joystick leftStick, Joystick rightStick){
    	double left = leftStick.getY()*myConstants.leftDrivetrainTalonMultiplier*myConstants.drivetrainFineSpeed;
    	double right = rightStick.getY()*myConstants.rightDrivetrainTalonMultiplier*myConstants.drivetrainFineSpeed;
    	double strafe = Math.max(leftStick.getX(), rightStick.getX())*myConstants.centerDrivetrainTalonMultiplier*myConstants.drivetrainFineSpeed;
    	tankOmniDrive(left, right, strafe);
    }
    public void fineArcadeOmniDrive(Joystick stick){
    	
    	double yval = -stick.getY();
    	double xval = stick.getX();
    	double twist = -stick.getTwist();
    	centerTalon.set(xval * myConstants.drivetrainFineSpeed);
    	drive.arcadeDrive(yval * myConstants.drivetrainFineSpeed,twist * myConstants.drivetrainFineSpeed,false);
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
		leftTalonI.set(0.5*myConstants.leftDrivetrainTalonMultiplier);
		leftTalonII.set(0.5*myConstants.leftDrivetrainTalonMultiplier);
		rightTalonI.set(0.5*myConstants.rightDrivetrainTalonMultiplier);
		rightTalonII.set(0.5*myConstants.rightDrivetrainTalonMultiplier);
	}
    

}
	