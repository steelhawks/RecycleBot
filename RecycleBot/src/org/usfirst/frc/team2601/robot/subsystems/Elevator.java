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
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.ElevatorDoNothing;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.ManualElevator;

/**
 *
 */
public class Elevator extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon elevatorTalonI = new CANTalon(Constants.elevatorTalonAddressI);
	CANTalon elevatorTalonII = new CANTalon(Constants.elevatorTalonAddressII);
	Encoder elevatorEncoder = new Encoder(Constants.elevatorEncoderPortI,Constants.elevatorEncoderPortII, false, Encoder.EncodingType.k4X);
	DigitalInput bottomLimitSwitch = new DigitalInput(Constants.bottomLimitSwitchPort);
	DigitalInput topLimitSwitch = new DigitalInput(Constants.topLimitSwitchPort);
	Boolean CSVstart;
	String elevatorStatsFilename = "/logs/elevatorlogs.csv";
	DriverStation station;
	PIDController control = new PIDController(Constants.elevatorP, Constants.elevatorI, Constants.elevatorD, elevatorEncoder, elevatorTalonI);
	DoubleSolenoid ejectionPiston = new DoubleSolenoid(Constants.ejectionSolenoidOnPort,Constants.ejectionSolenoidOffPort);
	
	ArrayList<String> printstats;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setup();
    	setDefaultCommand(new ManualElevator());
    	
    }
    
    public void setup(){
    	elevatorEncoder.setDistancePerPulse(Constants.elevatorDistancePerPulse);
    	elevatorEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
    	//manualCloseEjectionPiston();
    	elevatorTalonII.changeControlMode(ControlMode.Follower);
    	elevatorTalonII.set(Constants.elevatorTalonAddressI);
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
    public Boolean moveWithJoystick(Joystick stick){
    	boolean bottomLimitSwitchValue = bottomLimitSwitch.get();
    	boolean topLimitSwitchValue = topLimitSwitch.get();
    	
    	if (bottomLimitSwitchValue == false){
    		if(stick.getY()<0){
    			return true;
    		}
    	}
    	else if (topLimitSwitchValue == false){
    		if(stick.getY()>0){
    			return true;
    		}
    	}
    	
    	elevatorTalonI.set(stick.getY()*Constants.elevatorTalonMultiplier*Constants.elevatorSpeed);
    	//elevatorTalonII.set(stick.getY()*Constants.elevatorTalonMultiplier*Constants.elevatorSpeed);
    	
    	SmartDashboard.putNumber("elevator encoder", elevatorEncoder.getDistance());
    	
    	return false;

    	
    }
    public void autonLift(){
    	elevatorTalonI.set(Constants.autonElevatorSpeed /* Constants.elevatorTalonMultiplier*/);
    	//elevatorTalonII.set(Constants.autonElevatorSpeed /* Constants.elevatorTalonMultiplier*/);
    }
    public void autonDown(){
    	elevatorTalonI.set(-Constants.autonElevatorSpeed /* Constants.elevatorTalonMultiplier*/);
    	//elevatorTalonII.set(-Constants.autonElevatorSpeed /* Constants.elevatorTalonMultiplier*/);
    }
    
    public void getPIDvalues(){
    	//retrieve PID vals from NetTables
    	Constants.elevatorP = Robot.table.getNumber(Constants.elevatorPKey);
    	Constants.elevatorI = Robot.table.getNumber(Constants.elevatorIKey);
    	Constants.elevatorD = Robot.table.getNumber(Constants.elevatorDKey);
    	
    }
    public void getSetpoint(){
    	Constants.elevatorSetpoint = Robot.table.getNumber(Constants.elevatorSetpointKey);
    }
    
    public void stopPID(){
    	control.disable();
    }
    
    public void startPID(){
    	//get PID values from Net Tables
    	getPIDvalues();
    	getSetpoint();
    	
    	//set up PID loop parameters
    	control.setPID(Constants.elevatorP, Constants.elevatorI, Constants.elevatorD);
    	control.setSetpoint(Constants.elevatorSetpoint);
    	control.setAbsoluteTolerance(Constants.elevatorAbsoluteTolerance);
    	control.setOutputRange(Constants.elevatorMinOutput, Constants.elevatorMaxOutput);
    	
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
    	elevatorEncoder.setDistancePerPulse(Constants.drivetrainDistancePerPulse);
    	elevatorEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
    	
    	//set up PID loop parameters
    	control.setPID(Constants.drivetrainP, Constants.drivetrainI, Constants.drivetrainD);
    	control.setSetpoint(setpoint);
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
    public boolean areYouThereYet(){
    	return(control.onTarget());
    }
    public void matchMotors(){
    	elevatorTalonII.set(elevatorTalonI.get()* -1);
    }
    public void automaticEjectTotes(){
    	ejectionPiston.set(DoubleSolenoid.Value.kForward);
    	Timer.delay(0.75);
    	ejectionPiston.set(DoubleSolenoid.Value.kOff);
    }
    
    public void manualOpenEjectionPiston(){
    	ejectionPiston.set(DoubleSolenoid.Value.kForward);
    }
    
    public void manualCloseEjectionPiston(){
    	ejectionPiston.set(DoubleSolenoid.Value.kOff);
    }

    public void stopMotors(){
    	//stop everything
    
    	elevatorTalonI.set(0.0);
    	elevatorTalonII.set(0.0);
    }
    
}

