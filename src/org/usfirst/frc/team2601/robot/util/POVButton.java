package org.usfirst.frc.team2601.robot.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class POVButton extends Trigger {
    
	private Joystick stick;
	private int povState;
	
	public POVButton(Joystick stick, int pov){
		this.stick = stick;
		this.povState = pov;
	}
	
    public boolean get() {
        if (stick.getPOV() == povState){
        	return true;
        }
        else{
        	return false;
        }
    }
}
