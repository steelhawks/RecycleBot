package org.usfirst.frc.team2601.robot.subsystems;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.ElevatorDoNothing;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.ManualElevator;

/**
 *
 */
public class Elevator extends Subsystem {

	Constants myConstants = Constants.getInstance();
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon elevatorCANTalonI = new CANTalon(myConstants.elevatorTalonAddressI);
	CANTalon elevatorCANTalonII = new CANTalon(myConstants.elevatorTalonAddressII);
	
	Talon elevatorTalonI = new Talon(myConstants.elevatorTalonAddressI);
	Talon elevatorTalonII = new Talon(myConstants.elevatorTalonAddressII);
	
	Ultrasonic chuteSonar = new Ultrasonic(myConstants.sonarInputPort, myConstants.sonarOutputPort);
	
	//Encoder elevatorEncoder = new Encoder(myConstants.elevatorEncoderPortI,myConstants.elevatorEncoderPortII, false, Encoder.EncodingType.k4X);
	DigitalInput bottomLimitSwitch = new DigitalInput(myConstants.bottomLimitSwitchPort);
	DigitalInput topLimitSwitch = new DigitalInput(myConstants.topLimitSwitchPort);
	Boolean CSVstart;
	String elevatorStatsFilename = "/logs/elevatorlogs.csv";
	DriverStation station;
	PIDController control;// = new PIDController(myConstants.elevatorP, myConstants.elevatorI, myConstants.elevatorD, elevatorEncoder, elevatorCANTalonI);
	//DoubleSolenoid ejectionPiston = new DoubleSolenoid(myConstants.ejectionSolenoidOnPort,myConstants.ejectionSolenoidOffPort);
	
	ArrayList<String> printstats;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setup();
    	setDefaultCommand(new ManualElevator());	
    		
    }
    
    public void setup(){
    	//elevatorEncoder.setDistancePerPulse(myConstants.elevatorDistancePerPulse);
    	//elevatorEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
    	//manualCloseEjectionPiston();
    	
    	chuteSonar.setEnabled(true);
    	chuteSonar.setAutomaticMode(true);
    	
    	if(myConstants.robotType == Constants.Robot_Type.Practice){
        	//elevatorCANTalonII.changeControlMode(ControlMode.Follower);
        	//elevatorCANTalonII.set(myConstants.elevatorTalonAddressI);
        	
        //	control = new PIDController(myConstants.elevatorP, myConstants.elevatorI, myConstants.elevatorD, elevatorEncoder, elevatorCANTalonI);
    	}
    	
    	else if (myConstants.robotType == Constants.Robot_Type.Competition){
        	//control = new PIDController(myConstants.elevatorP, myConstants.elevatorI, myConstants.elevatorD, elevatorEncoder, elevatorTalonI);

    	}
    	SmartDashboard.putBoolean("ElevatorSetup", true);
    	
    }
    
    public void doNothing(){
    	return;
    }
    
   /* public void limitSwitch(){
    	while(limitSwitchI.get() || limitSwitchII.get()){
    		stopMotors();
    	}
    }
    */
    public void moveWithJoystick(Joystick stick){
    	moveWithJoystick(stick.getY());
    }
    
    public Boolean moveWithJoystick(double value){
    	boolean bottomLimitSwitchValue = bottomLimitSwitch.get();
    	boolean topLimitSwitchValue = topLimitSwitch.get();
    	
    	if (bottomLimitSwitchValue == false){
    		if(value>0){
    			return true;
    		}
    	}
    	else if (topLimitSwitchValue == false){
    		if(value<0){
    			return true;
    		}
    	}
    	
    	if(myConstants.robotType == Constants.Robot_Type.Practice){
    		elevatorCANTalonI.set(value*myConstants.elevatorTalonMultiplier*myConstants.elevatorSpeed);
    		elevatorCANTalonII.set(value*myConstants.elevatorTalonMultiplier*myConstants.elevatorSpeed);
    	}

    	else if (myConstants.robotType == Constants.Robot_Type.Competition){
    		elevatorTalonI.set(value*myConstants.elevatorTalonMultiplier*myConstants.elevatorSpeed);
			elevatorTalonII.set(value*myConstants.elevatorTalonMultiplier*myConstants.elevatorSpeed);

    	}
    	
    	//SmartDashboard.putNumber("elevator encoder", elevatorEncoder.getDistance());
    	
    	return false;
    }
    
    public void fineMoveWithJoystick(double value){
    	moveWithJoystick(value*myConstants.fineElevatorSpeed);
    }
    
    public void autonLift(){
    	if (myConstants.robotType == Constants.Robot_Type.Practice){ 
    	elevatorCANTalonI.set(myConstants.autonElevatorSpeed /* myConstants.elevatorTalonMultiplier*/);
    	elevatorCANTalonII.set(myConstants.autonElevatorSpeed);;
    	}
    	
    	else if (myConstants.robotType == Constants.Robot_Type.Competition){
    		elevatorTalonI.set(myConstants.autonElevatorSpeed);
    		elevatorTalonII.set(myConstants.autonElevatorSpeed);
    	}
    	//elevatorTalonII.set(myConstants.autonElevatorSpeed /* myConstants.elevatorTalonMultiplier*/);
    }
    public void autonDown(){
    	if(myConstants.robotType == Constants.Robot_Type.Practice){
    		elevatorCANTalonI.set(-myConstants.autonElevatorSpeed /* myConstants.elevatorTalonMultiplier*/);
    	    elevatorCANTalonII.set(-myConstants.autonElevatorSpeed);
    	}
    	
    	else if (myConstants.robotType == Constants.Robot_Type.Competition){
    		elevatorTalonI.set(-myConstants.autonElevatorSpeed);
    		elevatorTalonII.set(-myConstants.autonElevatorSpeed /* myConstants.elevatorTalonMultiplier*/);
    	}
    }
    
    public void getPIDvalues(){
    	//retrieve PID vals from NetTables
    	myConstants.elevatorP = Robot.table.getNumber(myConstants.elevatorPKey);
    	myConstants.elevatorI = Robot.table.getNumber(myConstants.elevatorIKey);
    	myConstants.elevatorD = Robot.table.getNumber(myConstants.elevatorDKey);
    	
    }
    public void getSetpoint(){
    	myConstants.elevatorSetpoint = Robot.table.getNumber(myConstants.elevatorSetpointKey);
    }
    
    public void stopPID(){
    	control.disable();
    }
    
    public void startPID(){
    	//get PID values from Net Tables
    	getPIDvalues();
    	getSetpoint();
    	
    	//set up PID loop parameters
    	control.setPID(myConstants.elevatorP, myConstants.elevatorI, myConstants.elevatorD);
    	control.setSetpoint(myConstants.elevatorSetpoint);
    	control.setAbsoluteTolerance(myConstants.elevatorAbsoluteTolerance);
    	control.setOutputRange(myConstants.elevatorMinOutput, myConstants.elevatorMaxOutput);
    	
    	//enable loop, match motors
    	control.enable();
    	matchMotors();
    	//check if it's ok to stop
    	if (control.onTarget()){
    		stopPID();
    	}
    	//printStats(elevatorTalonI, "elevatorTalonI");
    	
    }
    public void distanceLiftPID(double setpoint){
    	getPIDvalues();

    	//set up encoders
    	//elevatorEncoder.setDistancePerPulse(myConstants.drivetrainDistancePerPulse);
    	//elevatorEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
    	
    	//set up PID loop parameters
    	control.setPID(myConstants.drivetrainP, myConstants.drivetrainI, myConstants.drivetrainD);
    	control.setSetpoint(setpoint);
    	control.setAbsoluteTolerance(myConstants.drivetrainAbsoluteTolerance);
    	control.setOutputRange(myConstants.drivetrainMinOutput, myConstants.drivetrainMaxOutput);
    	
    	//enable loop, match motors
    	control.enable();
    	matchMotors();
    	
    	//check if it's ok to stop
    	if (control.onTarget()){
    		stopPID();
    		return;
    	} 	
    }
    public boolean areYouThereYet(){
    	return(control.onTarget());
    }
    public void matchMotors(){
    	elevatorTalonII.set(elevatorTalonI.get());
    }
    public void automaticEjectTotes(){
    /*	ejectionPiston.set(DoubleSolenoid.Value.kForward);
    	Timer.delay(0.75);
    	ejectionPiston.set(DoubleSolenoid.Value.kOff);*/
    }
    
    public void manualOpenEjectionPiston(){
    	//ejectionPiston.set(DoubleSolenoid.Value.kForward);
    }
    
    public void manualCloseEjectionPiston(){
    //	ejectionPiston.set(DoubleSolenoid.Value.kOff);
    }

    public double getDistance(){
    	return chuteSonar.getRangeInches();
    }
    
    public boolean isLinedUp(){
    	return (getDistance() > myConstants.distanceToChute + myConstants.distanceToFront - myConstants.distanceTolerance)
    			||(getDistance() < myConstants.distanceToChute + myConstants.distanceToFront + myConstants.distanceTolerance);
    }
    
    public void stopMotors(){
    	//stop everything
    	if(myConstants.robotType == Constants.Robot_Type.Practice){
    		elevatorCANTalonI.set(0.0);
    		elevatorCANTalonII.set(0.0);
    	}
    	
    	else if (myConstants.robotType == Constants.Robot_Type.Competition){
    		elevatorTalonI.set(0.0);
    		elevatorTalonII.set(0.0);
    	}
    }
    
}

