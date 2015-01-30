package org.usfirst.frc.team2601.robot.subsystems;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDController;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.commands.ManualElevator;

/**
 *
 */
public class Elevator extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon elevatorTalon = new CANTalon(Constants.elevatorTalonAddress);
	Encoder elevatorEncoder = new Encoder(Constants.elevatorEncoderPortI,Constants.elevatorEncoderPortII, false, Encoder.EncodingType.k4X);
	DigitalInput limitSwitchI = new DigitalInput(Constants.limitSwitchIPort);
	DigitalInput limitSwitchII = new DigitalInput(Constants.limitSwitchIIPort);
	Boolean CSVstart;
	String elevatorStatsFilename = "/logs/elevatorlogs.csv";
	DriverStation station;
	PIDController control;
	
	ArrayList<String> printstats;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setup();
    	setDefaultCommand(new ManualElevator());
    }
    
    public void setup(){
    	elevatorEncoder.setDistancePerPulse(Constants.drivetrainDistancePerPulse);
    	elevatorEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
    }
    
    public void moveWithJoystick(Joystick stick){
    	elevatorTalon.set(stick.getY()*Constants.elevatorTalonMultiplier*Constants.speed);
    	printStats(elevatorTalon, "elevatorTalon");
    	elevatorEncoder.setIndexSource(limitSwitchI);
    	elevatorEncoder.setIndexSource(limitSwitchII);
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
    	
    	//check if it's ok to stop
    	if (control.onTarget()){
    		stopPID();
    	}

    	printStats(elevatorTalon, "elevatorTalon");
    	
    }

    
    public void printStats(CANTalon Talon, String name){
		
    	//retrieve values
    	double currentAmps = Talon.getOutputCurrent();
		double outputV = Talon.getOutputVoltage();
		double busV = Talon.getBusVoltage();
		double get = Talon.get();
		double getSpeed = Talon.getSpeed();
		double encDistance = elevatorEncoder.getDistance();
		double encSpeed = elevatorEncoder.getRate(); 
		double currentTime = station.getMatchTime();
		
		//init lists
		ArrayList<String> headers = new ArrayList<String>();
		ArrayList<Double> stats = new ArrayList<Double>();
		
		//clear list, create strings
		stats.clear();
		String cA = name + " currentAmps";
		String oV = name + " outputV";
		String bV = name + " busV";
		String getTalon = name + " get";
		String getTalonSpeed = name + " getSpeed()";
		
		//encoder
		String getEncDistance = name + " getDistance()";
		String getEncSpeed = name + " getSpeed()"; 
		
		//init csv file
		if (!CSVstart){
		headers.add("time");
		headers.add(cA);
		headers.add(oV);
		headers.add(bV);
		headers.add(getTalon);
		headers.add(getTalonSpeed);
		headers.add(getEncDistance);
		headers.add(getEncSpeed);
		startCSV(elevatorStatsFilename, headers);
		endLine(elevatorStatsFilename);
		
		}
		
		//live stats
		SmartDashboard.putNumber(cA, currentAmps);
		SmartDashboard.putNumber(oV, outputV);
		SmartDashboard.putNumber(bV, busV);
		SmartDashboard.putNumber(getTalon, get);
		SmartDashboard.putNumber(getTalonSpeed, getSpeed);
		
		//encoder
		SmartDashboard.putNumber(getEncDistance, encDistance);
		SmartDashboard.putNumber(getEncSpeed , encSpeed); 
		
		//list to add to csv
		stats.add(currentTime);
		stats.add(currentAmps);
		stats.add(outputV);
		stats.add(busV);
		stats.add(get);
		stats.add(getSpeed);
		addData(elevatorStatsFilename,stats);
		endLine(elevatorStatsFilename);
		
    }
    
//Start csv/logging stuff
    
    //adds headers to a new CSV file
    public void startCSV(String filename, ArrayList<String> list){
		try
    	{
    	    FileWriter writer = new FileWriter(filename);
    	    writer.append("Time");
    	    writer.append(',');
    	    for (int i = 0; i<list.size(); i++){
    	    	writer.append(list.get(i));
    	    	if(i < list.size()-1){
    	    	writer.append(',');
    	    	}
    	    }
    
    	    writer.flush();
    	    writer.close();
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
			FileWriter writer = new FileWriter(filename);
			writer.append("/n");
			writer.flush();
			writer.close();
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
	    	    FileWriter writer = new FileWriter(filename);
	    	    for (int i = 0; i<list.size(); i++){
	    	    	writer.append(list.get(i).toString());
	    	    	if(i < list.size()-1){
	    	    	writer.append(',');
	    	    	}
	    	    }
	    
	    	    writer.flush();
	    	    writer.close();
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
				FileWriter writer = new FileWriter(filename);
				writer.append(data.toString());
				writer.append(",");
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}

