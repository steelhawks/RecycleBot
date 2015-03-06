package org.usfirst.frc.team2601.robot.commands.rollerCommands;

import org.usfirst.frc.team2601.robot.OI;
import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.util.F310;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualControlRollers extends Command {

	private double xValue = 0.0;
	private double yValue = 0.0;
	private double leftValue = 0.0;
	private double rightValue = 0.0;
	
    public ManualControlRollers() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.rollers);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.rollers.manualControlRollers(OI.elevatorandRollerStick);
    	/*
    	xValue = OI.gamepad.getRawAxis(F310.kGamepadAxisLeftStickX);
    	yValue = OI.gamepad.getRawAxis(F310.kGamepadAxisLeftStickY);
    	if(Math.abs(xValue)>Math.abs(yValue)){
    		leftValue = xValue;
    		rightValue = xValue;
    	}
    	else{
    		leftValue = yValue;
    		rightValue = -yValue;
    	}
    	Robot.rollers.manualControlRollers(leftValue, rightValue);
    	*/
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
