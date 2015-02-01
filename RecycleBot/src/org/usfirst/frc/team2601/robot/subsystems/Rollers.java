package org.usfirst.frc.team2601.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.ManualControlRollers;

/**
 *
 */
public class Rollers extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Talon leftRollerTalon = new Talon(Constants.leftRollerTalonPort);
	Talon rightRollerTalon = new Talon(Constants.rightRollerTalonPort);
	DoubleSolenoid leftSolenoid = new DoubleSolenoid(Constants.leftSolenoidOnPort, Constants.leftSolenoidOffPort);
	DoubleSolenoid rightSolenoid = new DoubleSolenoid(Constants.rightSolenoidOnPort, Constants.rightSolenoidOffPort);
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand (new ManualControlRollers());
    }
    
    public void manualControlRollers(Joystick stick){
    	leftRollerTalon.set(stick.getY()*Constants.leftRollerTalonMultiplier*Constants.rollerSpeed);
    	rightRollerTalon.set(stick.getY()*Constants.rightRollerTalonMultiplier*Constants.rollerSpeed);
    	Timer.delay(0.02);
    }
    
    public void closeRollers(){
    	leftSolenoid.set(DoubleSolenoid.Value.kOff);
    	rightSolenoid.set(DoubleSolenoid.Value.kOff);
    }
    
    public void openRollers(){
    	leftSolenoid.set(DoubleSolenoid.Value.kForward);
    	rightSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
}

