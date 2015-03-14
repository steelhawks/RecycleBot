package org.usfirst.frc.team2601.robot.commands.auton;

import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonDriveFastForward;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonDriveForward;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonDrivetrainPID;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonStrafeLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonStrafeRight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveForwardToAutoZone extends CommandGroup {
    
    public  DriveForwardToAutoZone() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
    	//addSequential(new AutonDrivetrainPID(157.0));
    	addSequential(new AutonDriveFastForward(2.65));
    	
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
