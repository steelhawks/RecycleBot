package org.usfirst.frc.team2601.robot.util;

import edu.wpi.first.wpilibj.Joystick;

public class F310 extends Joystick{
		
	public F310(int port){
		super(port);
	}
	
	public F310(int port, boolean presetAxes){
		super(port, 5, 10);
	}
	
	public F310(int port, int numAxes, int numButtons){
		super(port, numAxes, numButtons);
	}
	
	public final static int kGamepadAxisLeftStickX = 0;
    public final static int kGamepadAxisLeftStickY = 1;
    public final static int kGamepadAxisRightStickX = 4;
    public final static int kGamepadAxisRightStickY = 5;

    public final static int kGamepadButtonA = 1; // Bottom button
    public final static int kGamepadButtonB = 2; // Right button
    public final static int kGamepadButtonX = 3; // Left button
    public final static int kGamepadButtonY = 4; // Top button
    public final static int kGamepadButtonShoulderL = 5;
    public final static int kGamepadButtonShoulderR = 6;
    public final static int kGamepadButtonBack = 7;
    public final static int kGamepadButtonStart = 8;
    public final static int kGamepadButtonLeftStick = 9;
    public final static int kGamepadButtonRightStick = 10;
    public final static int kGamepadButtonMode = -1; // unknown
    public final static int kGamepadButtonLogitech = -1;  // unknown
    
    public double getRawAxis(int axis){
    	return super.getRawAxis(axis);
    }
    
    public double getLeftX(){
    	return super.getRawAxis(kGamepadAxisLeftStickX);
    }
    
    public double getLeftY(){
    	return super.getRawAxis(kGamepadAxisLeftStickY);
    }
    
    public double getRightX(){
    	return super.getRawAxis(kGamepadAxisRightStickX);
    }
    
    public double getRightY(){
    	return super.getRawAxis(kGamepadAxisRightStickY);
    }
}