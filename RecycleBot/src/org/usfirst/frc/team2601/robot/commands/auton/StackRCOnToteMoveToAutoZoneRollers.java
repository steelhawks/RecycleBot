package org.usfirst.frc.team2601.robot.commands.auton;

import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonDriveForward;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonTurnLeft;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.AutonDown;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.AutonLift;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.closeOrOpenRollers;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StackRCOnToteMoveToAutoZoneRollers extends CommandGroup {
    
    public  StackRCOnToteMoveToAutoZoneRollers() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
    	addSequential(new AutonLift(1.45));
    	addSequential(new AutonDriveForward(2.65));
    	addSequential(new closeOrOpenRollers());
    	addSequential(new AutonDown(0.25));
    	addSequential(new AutonTurnLeft(1.15));
    	addSequential(new AutonDriveForward(2.65));
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
