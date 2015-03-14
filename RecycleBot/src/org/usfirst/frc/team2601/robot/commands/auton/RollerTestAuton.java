package org.usfirst.frc.team2601.robot.commands.auton;

import org.usfirst.frc.team2601.robot.commands.rollerCommands.moveToPositionRollers;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RollerTestAuton extends CommandGroup{
	public RollerTestAuton(){
		addSequential(new moveToPositionRollers((18.0*Math.PI)/4));
	}
}
