package org.usfirst.frc.team2601.robot.commands.drivetrainCommands;

import org.usfirst.frc.team2601.robot.Constants;
import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonDrivetrainPID extends Command {
	
	Double setpoint;
    public AutonDrivetrainPID(double setpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.distanceDriveForwardPID(setpoint);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return Robot.drivetrain.areYouThereYet();
    	return Robot.drivetrain.distanceDriveForwardPID(setpoint);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stopPID();
    	Robot.drivetrain.stopMotors();
    	Robot.drivetrain.resetEncoders();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.stopPID();
    }

	}
