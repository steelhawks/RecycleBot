package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.Constants;

import com.ni.vision.VisionException;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Camera extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Servo panServo = new Servo(Constants.getInstance().panServoPort);
	Servo tiltServo = new Servo(Constants.getInstance().tiltServoPort);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new Cam0Capture());
    }
    
    public void panLeft(){
    	panServo.set(panServo.get()-Constants.getInstance().servoChange);
    }
    
    public void panRight(){
    	panServo.set(panServo.get()+Constants.getInstance().servoChange);
    }
    
    public void tiltUp(){
    	tiltServo.set(tiltServo.get()-Constants.getInstance().servoChange);
    }
    
    public void tiltDown(){
    	tiltServo.set(tiltServo.get()+Constants.getInstance().servoChange);
    }
    
    public void startPos(){
    	tiltServo.set(0.0);
    	panServo.set(0.0);
    }
    
}

