package org.usfirst.frc.team2601.robot.commands.rollerCommands;

import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class moveToPositionRollers extends Command {

	private double distance;
	private double timeout = 0.0;
	private final double speed = 1.0;
	private final double tolerance = 4.0; //inches
	
    public moveToPositionRollers(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.rollers);
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double motorRPM = speed*100; //output rpm of the 1:71 9015/9G71 gearmotor
    	double largePulleyID = 2.5; //inner track diameter of the drive pulley, in inches
    	double smallPulleyID = 1.5; //inner track diameter of the driven pulleys, in inches
    	double wheelOD = 4.875; //outer diameter of the BaneBots wheels
    	double tangentialSpeed = (Math.PI*wheelOD*motorRPM*(largePulleyID/smallPulleyID))/60; //tangential speed of the BaneBots wheels, in inches/s 
    	timeout = (distance+tolerance)/tangentialSpeed; //t = d/v, set the timeout according to desired displacement
    	setTimeout(timeout);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.rollers.manualControlRollers(speed, -speed);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.rollers.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
