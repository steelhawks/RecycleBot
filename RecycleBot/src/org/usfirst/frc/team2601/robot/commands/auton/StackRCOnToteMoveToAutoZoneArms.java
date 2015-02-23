package org.usfirst.frc.team2601.robot.commands.auton;

import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonDriveBackward;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonDriveForward;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonStrafeLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonStrafeRight;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonTurnLeft;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.AutonDown;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.AutonLift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StackRCOnToteMoveToAutoZoneArms extends CommandGroup {
    
    public  StackRCOnToteMoveToAutoZoneArms() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());

    	addSequential(new AutonLift(3.0));
    	//addSequential(new AutonLift(1.60));
    	addSequential(new AutonDriveForward(1.0));
    	addSequential(new AutonDown(3.25));
    	addSequential(new AutonTurnLeft(1.57));
    	addSequential(new AutonDriveForward(3.75));
    	addSequential(new AutonDown(0.25));
    	//addSequential(new AutonDriveBackward(0.25));
    	//addSequential(new AutonDriveForward(0.5));
    	//addSequential(new AutonLift(0.25));
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
