package org.usfirst.frc.team2601.robot.commands;

import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonDriveBackward;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonDriveForward;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonDrivetrainPID;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonStrafeRight;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.AutonDown;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.AutonLift;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.AutonIntakeRollers;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.AutonOpenRollers;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.AutonStopRollers;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SampleAuton extends CommandGroup {
    
    public  SampleAuton() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
    	/* this is the autonomous code used for the square chassis
     	addSequential(new AutonDriveForward(1.5));
    	Timer.delay(0.5);
    	addSequential(new AutonDriveBackward(1.5));
    	//Timer.delay(0.5);
    	addSequential(new ExtendPiston(0.5));
    	addSequential(new AutonDrivetrainPID(210.0));
    	//Timer.delay(3.0);
    	/*addSequential(new AutonDriveForward(1.0));
    	addSequential(new RetractPiston(0.5));
    	addSequential(new AutonDriveForward(1.0));*/
    	
    	addSequential(new AutonLift(0.75));
    	addSequential(new AutonIntakeRollers(1.5));
    	addSequential(new AutonDriveForward(1.5));
    	addSequential(new AutonStopRollers());
    	addSequential(new AutonDown(0.5));
    	addSequential(new AutonLift(0.75));
    	addSequential(new AutonIntakeRollers(1.5));
    	addSequential(new AutonDriveForward(1.5));
    	addSequential(new AutonStopRollers());
    	addSequential(new AutonStrafeRight(1.5));
    	addSequential(new AutonDown(1.5));
    	addSequential(new AutonOpenRollers());
    	addSequential(new AutonDriveBackward(1.5));
    	
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
