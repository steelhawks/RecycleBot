package org.usfirst.frc.team2601.robot.util;
import java.util.HashMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DigitalInput;

public class HawkLogger {
	
	private HashMap<String, CANTalon> CANTalonHashMap = new HashMap<String, CANTalon>();
	private HashMap<String, Talon> TalonHashMap = new HashMap<String, Talon>();
	private HashMap<String, Encoder> EncoderHashMap = new HashMap<String, Encoder>();
	private HashMap<String, DoubleSolenoid> SolenoidHashMap = new HashMap<String, DoubleSolenoid>();
	private HashMap<String, DigitalInput> DigitalInputHashMap = new HashMap<String, DigitalInput>();
	
	HawkLogger(){
		
	}
	
	public HawkLogger(HashMap<String, CANTalon> cANTalonHashMap,
			HashMap<String, Talon> talonHashMap,
			HashMap<String, Encoder> encoderHashMap,
			HashMap<String, DoubleSolenoid> solenoidHashMap,
			HashMap<String, DigitalInput> digitalInputHashMap) {
		super();
		CANTalonHashMap = cANTalonHashMap;
		TalonHashMap = talonHashMap;
		EncoderHashMap = encoderHashMap;
		SolenoidHashMap = solenoidHashMap;
		DigitalInputHashMap = digitalInputHashMap;
	}



	public HawkLogger(HashMap<String, CANTalon> cANTalonHashMap,
			HashMap<String, Talon> talonHashMap,
			HashMap<String, Encoder> encoderHashMap,
			HashMap<String, DoubleSolenoid> solenoidHashMap) {
		super();
		CANTalonHashMap = cANTalonHashMap;
		TalonHashMap = talonHashMap;
		EncoderHashMap = encoderHashMap;
		SolenoidHashMap = solenoidHashMap;
	}

	public HawkLogger(HashMap<String, CANTalon> cANTalonHashMap,
			HashMap<String, Encoder> encoderHashMap) {
		super();
		CANTalonHashMap = cANTalonHashMap;
		EncoderHashMap = encoderHashMap;
	}

	public HawkLogger(HashMap<String, CANTalon> cANTalonHashMap,
			HashMap<String, Talon> talonHashMap,
			HashMap<String, Encoder> encoderHashMap) {
		super();
		CANTalonHashMap = cANTalonHashMap;
		TalonHashMap = talonHashMap;
		EncoderHashMap = encoderHashMap;
	}

	private void logCANTalon(){
		
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
