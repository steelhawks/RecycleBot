package org.usfirst.frc.team2601.robot.commands.elevatorCommands;

import org.usfirst.frc.team2601.robot.OI;
import org.usfirst.frc.team2601.robot.Robot;
import org.usfirst.frc.team2601.robot.util.F310;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualElevator extends Command {

    public ManualElevator() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.elevator.moveWithJoystick(OI.elevatorandRollerStick);
    	Robot.elevator.moveWithJoystick(OI.gamepad.getRightY());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
