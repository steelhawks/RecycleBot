package org.usfirst.frc.team2601.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.OI;


/**
 *
 */
public class Drivetrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon leftTalon = new CANTalon(Constants.leftTalonAddress);
	CANTalon rightTalon = new CANTalon(Constants.rightTalonAddress);
	RobotDrive drive = new RobotDrive(leftTalon, rightTalon);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void dumbDrive(Joystick stick){
    	drive.arcadeDrive(stick);
    }
}

