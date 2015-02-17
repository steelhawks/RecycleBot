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
	
	Talon leftRollerTalon = new Talon(Constants.getInstance().leftRollerTalonPort);
	Talon rightRollerTalon = new Talon(Constants.getInstance().rightRollerTalonPort);
	DoubleSolenoid leftSolenoid = new DoubleSolenoid(Constants.getInstance().leftSolenoidOnPort, Constants.getInstance().leftSolenoidOffPort);
	DoubleSolenoid rightSolenoid = new DoubleSolenoid(Constants.getInstance().rightSolenoidOnPort, Constants.getInstance().rightSolenoidOffPort);
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ManualControlRollers());
    }
    
    public void manualControlRollers(Joystick stick){
    	leftRollerTalon.set(stick.getTwist()*Constants.getInstance().leftRollerTalonMultiplier*Constants.getInstance().rollerSpeed);
    	rightRollerTalon.set(-stick.getTwist()*Constants.getInstance().rightRollerTalonMultiplier*Constants.getInstance().rollerSpeed);
    }
    
    public void intakeRollers(){
    	leftRollerTalon.set(-Constants.getInstance().leftRollerTalonMultiplier*Constants.getInstance().rollerSpeed);
    	rightRollerTalon.set(-Constants.getInstance().rightRollerTalonMultiplier*Constants.getInstance().rollerSpeed);
    }
    
    public void outtakeRollers(){
    	leftRollerTalon.set(Constants.getInstance().leftRollerTalonMultiplier*Constants.getInstance().rollerSpeed);
    	rightRollerTalon.set(Constants.getInstance().rightRollerTalonMultiplier*Constants.getInstance().rollerSpeed);
    }
    
    public void closeRollers(){
    	leftSolenoid.set(DoubleSolenoid.Value.kReverse);
    	rightSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void openRollers(){
    	leftSolenoid.set(DoubleSolenoid.Value.kForward);
    	rightSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void stop(){
    	leftRollerTalon.set(Constants.getInstance().rollerStopSpeed);
    	rightRollerTalon.set(Constants.getInstance().rollerStopSpeed);
    }
    
}

