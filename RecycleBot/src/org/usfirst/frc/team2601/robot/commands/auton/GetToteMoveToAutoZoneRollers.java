package org.usfirst.frc.team2601.robot.commands.auton;

import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonDriveBackward;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonDrivetrainPID;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonStrafeLeft;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.AutonTurnLeft;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.AutonDown;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.AutonElevatorPID;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.AutonLift;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.StartElevatorPID;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.StopElevatorPID;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.AutonIntakeRollers;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GetToteMoveToAutoZoneRollers extends CommandGroup {
    
    public  GetToteMoveToAutoZoneRollers() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
    	addSequential(new AutonIntakeRollers(2.0));
    	addSequential(new AutonElevatorPID(27.0));
    	addSequential(new AutonTurnLeft(1.0));
    	addSequential(new AutonDrivetrainPID(100.0));
    	addSequential(new AutonElevatorPID(-13.0));
    	addSequential(new AutonDrivetrainPID(-65.0));
    	
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
