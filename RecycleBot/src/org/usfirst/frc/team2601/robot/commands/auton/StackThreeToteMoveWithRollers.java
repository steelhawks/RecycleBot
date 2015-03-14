package org.usfirst.frc.team2601.robot.commands.auton;

import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonDriveBackward;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonDriveFastForward;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonDriveForward;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonStrafeRight;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonTurnLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonTurnRight;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.AutonDown;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.AutonLift;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.AutonCloseRollers;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.AutonIntakeRollers;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.AutonOpenRollers;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.AutonOuttakeRollers;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StackThreeToteMoveWithRollers extends CommandGroup {
    
    public  StackThreeToteMoveWithRollers() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new AutonCloseRollers(0.2));
    	addSequential(new AutonIntakeRollers(0.75));
    	addSequential(new AutonLift(2.5));
    	//addParallel(new AutonStrafeRight(1.5));
    	addParallel(new AutonOuttakeRollers(1.1));
    	addSequential(new AutonDriveForward(5.5));
    	//addParallel(new AutonDriveFastForward(0.25));
    	addSequential(new AutonOpenRollers(0.2));
    	addSequential(new AutonDriveForward(1.75));
    	addSequential(new AutonCloseRollers(0.2));
    	addSequential(new AutonIntakeRollers(2.0));
    	addSequential(new AutonDown(2.2));
    	addSequential(new AutonLift(2.5));
    	addParallel(new AutonOuttakeRollers(1.1));
    	addSequential(new AutonDriveForward(4.85));
    	addSequential(new AutonCloseRollers(0.2));
    	addSequential(new AutonIntakeRollers(2.0));
    	addSequential(new AutonTurnRight(1.4));
    	addSequential(new AutonDriveFastForward(1.8));
    	addSequential(new AutonDown(0.5));
    	addParallel(new AutonOpenRollers(0.2));
    	addSequential(new AutonDriveBackward(0.25));
    	
    	//addSequential(new AutonOuttakeRollers(2.0));
    	//addSequential(new AutonDriveForward(0.85));
    	
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
