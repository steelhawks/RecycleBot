package org.usfirst.frc.team2601.robot.subsystems;

import org.usfirst.frc.team2601.robot.commands.RetractPiston;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pneumatics extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	DoubleSolenoid solenoid = new DoubleSolenoid(0,1);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new RetractPiston());
    }
    
    public void extend(){
    	solenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void retract(){
    	solenoid.set(DoubleSolenoid.Value.kReverse);
    }
}

