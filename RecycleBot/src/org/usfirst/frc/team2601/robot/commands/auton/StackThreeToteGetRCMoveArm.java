package org.usfirst.frc.team2601.robot.commands.auton;

import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonDriveFastForward;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonDriveForward;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonTurnRight;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.AutonDown;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.AutonLift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StackThreeToteGetRCMoveArm extends CommandGroup {
    
    public  StackThreeToteGetRCMoveArm() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

    	addSequential(new AutonLift(1.60));
    	addSequential(new AutonDriveForward(0.75));
    	addSequential(new AutonDown(3.25));
    	addSequential(new AutonLift(0.86));
    	addSequential(new AutonDriveForward(1.75));
    	addSequential(new AutonDown(1.25));
    	addSequential(new AutonLift(0.86));
    	addSequential(new AutonDriveForward(1.75));
    	addSequential(new AutonDown(1.25));
    	addSequential(new AutonLift(0.86));
    	addSequential(new AutonTurnRight(1.15));
    	addSequential(new AutonDriveFastForward(2.60));
    	addSequential(new AutonDown(0.25));
    	
    	
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
