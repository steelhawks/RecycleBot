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
import org.usfirst.frc.team2601.robot.commands.rollerCommands.CloseOrOpenRollers;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.TwistRC;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.moveToPositionRollers;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.openRollers;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
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
    	
    	//addSequential(new moveToPositionRollers(18.0*Math.PI/4)); //rotate RC 90 degrees, or 1/4 of its circumference
    	addSequential(new TwistRC(0.55));
    	addParallel(new AutonOpenRollers(0.2));
    	addSequential(new AutonDriveForward(0.35));
    	Timer.delay(0.2);
    	addSequential(new AutonLift(2.80));
    	addSequential(new AutonDriveForward(1.1));
    	addSequential(new AutonCloseRollers(0.2));
    	addSequential(new AutonIntakeRollers(1.5));
    	//addSequential(new AutonDown(0.25));
    	addSequential(new AutonTurnLeft(0.75));
    	addSequential(new AutonDriveForward(2.8));
    	addSequential(new AutonDown(2.80));
    	addParallel(new AutonOpenRollers(0.2));
    	addSequential(new AutonDriveBackward(0.25));

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
