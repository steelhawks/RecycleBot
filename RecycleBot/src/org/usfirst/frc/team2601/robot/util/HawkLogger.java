package org.usfirst.frc.team2601.robot.util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DigitalInput;

import java.util.Set;

public class HawkLogger {
	
	private HashMap<String, CANTalon> CANTalonHashMap = new HashMap<String, CANTalon>();
	private HashMap<String, Talon> TalonHashMap = new HashMap<String, Talon>();
	private HashMap<String, Encoder> EncoderHashMap = new HashMap<String, Encoder>();
	private HashMap<String, DoubleSolenoid> SolenoidHashMap = new HashMap<String, DoubleSolenoid>();
	private HashMap<String, DigitalInput> DigitalInputHashMap = new HashMap<String, DigitalInput>();
	private ArrayList<CANTalon>canTalons = new ArrayList<CANTalon>();
	private String filename = "";
	private ArrayList<String> headers = new ArrayList<String>();
	private ArrayList<Double> stats = new ArrayList<Double>();
	private Boolean logStarted = false;
	private Boolean canTalonsStarted = false;
	
	
	HawkLogger(){
		
	}
	
	public HawkLogger(HashMap<String, CANTalon> cANTalonHashMap,
			HashMap<String, Talon> talonHashMap,
			HashMap<String, Encoder> encoderHashMap,
			HashMap<String, DoubleSolenoid> solenoidHashMap,
			HashMap<String, DigitalInput> digitalInputHashMap,
			String filename) {
		super();
		CANTalonHashMap = cANTalonHashMap;
		TalonHashMap = talonHashMap;
		EncoderHashMap = encoderHashMap;
		SolenoidHashMap = solenoidHashMap;
		DigitalInputHashMap = digitalInputHashMap;
		this.filename = filename;
	}



	public HawkLogger(HashMap<String, CANTalon> cANTalonHashMap,
			HashMap<String, Talon> talonHashMap,
			HashMap<String, Encoder> encoderHashMap,
			HashMap<String, DoubleSolenoid> solenoidHashMap,
			String filename) {
		super();
		CANTalonHashMap = cANTalonHashMap;
		TalonHashMap = talonHashMap;
		EncoderHashMap = encoderHashMap;
		SolenoidHashMap = solenoidHashMap;
		this.filename = filename;
	}

	public HawkLogger(HashMap<String, CANTalon> cANTalonHashMap,
			HashMap<String, Encoder> encoderHashMap,
			String filename) {
		super();
		CANTalonHashMap = cANTalonHashMap;
		EncoderHashMap = encoderHashMap;
		this.filename = filename;
	}

	public HawkLogger(HashMap<String, CANTalon> cANTalonHashMap,
			HashMap<String, Talon> talonHashMap,
			HashMap<String, Encoder> encoderHashMap,
			String filename) {
		super();
		CANTalonHashMap = cANTalonHashMap;
		TalonHashMap = talonHashMap;
		EncoderHashMap = encoderHashMap;
		this.filename = filename;
	}
	
	public void setHeaders(){
		
	}
	

	private void logCANTalons(){
		String getHeader,tempHeader,ocHeader,ovHeader,bvHeader, dIDheader;
		
		
		if(!canTalonsStarted){
			ArrayList<String> keys = new ArrayList<String>();
			Set<String> canTalonSet = CANTalonHashMap.keySet();
			Iterator i = canTalonSet.iterator();
			while(i.hasNext()){
				keys.add(i.next().toString());
			}
			
			for(int x = 0; x<keys.size(); x++){
				String currentKey = keys.get(x);
				canTalons.add(CANTalonHashMap.get(currentKey));
				getHeader = (currentKey+ " get");
				tempHeader = (currentKey+" getTemp");
				ocHeader=(currentKey+" getOutputCurrent");
				ovHeader=(currentKey+" getOutputVoltage");
				bvHeader=(currentKey+" getBusVoltage");
				dIDheader=(currentKey+" getDeviceID");
				
				headers.add(getHeader);
				headers.add(tempHeader);
				headers.add(ocHeader);
				headers.add(ovHeader);
				headers.add(bvHeader);
				headers.add(dIDheader);
			}
			canTalonsStarted = true;
		}
		for(int d = 0; d<canTalons.size();d++){
			CANTalon currentCANTalon = canTalons.get(d);
			stats.add(currentCANTalon.get());
			stats.add(currentCANTalon.getTemp());
			stats.add(currentCANTalon.getOutputCurrent());
			stats.add(currentCANTalon.getOutputVoltage());
			stats.add(currentCANTalon.getBusVoltage());
			stats.add((double) currentCANTalon.getDeviceID());
			
			//TODO add smartDashboard
		}
		
		
		
	}
	
	private void logTalon(){
		
	}
	
	private void logEncoder(){
		
	}
	
	private void logSolenoid(){
		
	}
	
	private void logDigitalInput(){
		
	}
	
	private void writeToFile(){
		
	}
	
}
