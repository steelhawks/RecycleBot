package org.usfirst.frc.team2601.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.ManualControlRollers;

/**
 *
 */
public class Rollers extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Constants myConstants = Constants.getInstance();
	Talon leftRollerTalon = new Talon(myConstants.leftRollerTalonPort);
	Talon rightRollerTalon = new Talon(myConstants.rightRollerTalonPort);
	DoubleSolenoid leftSolenoid = new DoubleSolenoid(myConstants.leftSolenoidOnPort, myConstants.leftSolenoidOffPort);
	DoubleSolenoid rightSolenoid = new DoubleSolenoid(myConstants.rightSolenoidOnPort, myConstants.rightSolenoidOffPort);
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ManualControlRollers());
    }
    
    public void manualControlRollers(Joystick stick){
    	manualControlRollers(stick.getTwist(), -stick.getTwist());
    }
    public void twistRC(){
    	leftRollerTalon.set(-myConstants.leftRollerTalonMultiplier*myConstants.rollerSpeed);
    	rightRollerTalon.set(-myConstants.rightRollerTalonMultiplier*myConstants.rollerSpeed);
    }
    public void manualControlRollers(double leftValue, double rightValue){
    	leftRollerTalon.set(leftValue*myConstants.leftRollerTalonMultiplier*myConstants.rollerSpeed);
    	rightRollerTalon.set(rightValue*myConstants.rightRollerTalonMultiplier*myConstants.rollerSpeed);
    }
    public void intakeRollers(){
    	leftRollerTalon.set(-myConstants.leftRollerTalonMultiplier*myConstants.rollerSpeed);
    	rightRollerTalon.set(myConstants.rightRollerTalonMultiplier*myConstants.rollerSpeed);
    }
    
    public void outtakeRollers(){
    	leftRollerTalon.set(myConstants.leftRollerTalonMultiplier*myConstants.rollerSpeed);
    	rightRollerTalon.set(-myConstants.rightRollerTalonMultiplier*myConstants.rollerSpeed);
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
    	leftRollerTalon.set(myConstants.rollerStopSpeed);
    	rightRollerTalon.set(myConstants.rollerStopSpeed);
    }
    
}

