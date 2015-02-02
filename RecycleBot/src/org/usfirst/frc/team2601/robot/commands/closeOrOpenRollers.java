package org.usfirst.frc.team2601.robot.commands;

import org.usfirst.frc.team2601.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class closeOrOpenRollers extends Command {

	private static boolean open = true;
	
    public closeOrOpenRollers() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.rollers);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(open) Robot.rollers.closeRollers();
    	else Robot.rollers.openRollers();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	open = !open;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
