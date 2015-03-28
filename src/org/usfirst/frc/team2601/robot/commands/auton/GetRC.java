package org.usfirst.frc.team2601.robot.commands.auton;

import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonDriveBackward;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonDriveFastForward;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonDriveForward;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonTurnLeft;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.AutonDown;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.AutonLift;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.AutonCloseRollers;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.AutonIntakeRollers;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.AutonOpenRollers;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GetRC extends CommandGroup {
    
    public  GetRC() {
    	
    	addSequential(new AutonOpenRollers(0.2));
    	//addSequential(new TwistRC(0.35));
    	addSequential(new AutonCloseRollers(0.2));
    	//Timer.delay(0.2);
    	addSequential(new AutonDriveForward(0.35));
    	Timer.delay(0.4);
    	addSequential(new AutonLift(0.70));
    	addSequential(new AutonOpenRollers(0.2));
    	addSequential(new AutonTurnLeft(0.3));
    	Timer.delay(0.06);
    	addSequential(new AutonDriveFastForward(1.7));
    	addSequential(new AutonDriveForward(0.65));
    	

    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

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
