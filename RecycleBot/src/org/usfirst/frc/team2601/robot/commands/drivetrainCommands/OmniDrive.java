package org.usfirst.frc.team2601.robot.commands.drivetrainCommands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.OI;
/**
 *
 */
public class OmniDrive extends Command {

    public OmniDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Constants.GAMEPAD){
    		Robot.drivetrain.gamepadOmniDrive(OI.stick);
    	}
    	else if (Constants.JOYSTICK){
    		Robot.drivetrain.omniDrive(OI.stick);
    	}
    	//Robot.drivetrain.gamepadOmniDrive(OI.stick);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
