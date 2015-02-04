package org.usfirst.frc.team2601.robot;

import org.usfirst.frc.team2601.robot.Constants;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team2601.robot.commands.ExtendPiston;
import org.usfirst.frc.team2601.robot.commands.ManualControlRollers;
import org.usfirst.frc.team2601.robot.commands.RetractPiston;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.DumbDrive;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.FineOmniDrive;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.StartDrivetrainPID;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.StopDrivetrainMotors;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.StopDrivetrainPID;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.AutomaticEjectTotes;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.ManualCloseEjectionPiston;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.ManualOpenEjectionPiston;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.StartElevatorPID;
import org.usfirst.frc.team2601.robot.commands.elevatorCommands.StopElevatorPID;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.closeOrOpenRollers;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.closeRollers;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.intakeRollers;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.openRollers;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.outtakeRollers;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.stopRollers;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
	
	public static Joystick stick = new Joystick(Constants.joystickPort);
	public static Joystick elevatorandRollerStick = new Joystick(Constants.secondaryJoystickPort);
	public static Joystick leftDriveStick = new Joystick(Constants.leftDriveStick);
	public static Joystick rightDriveStick = new Joystick(Constants.rightDriveStick);
	//public static Joystick rollerStick = new Joystick(Constants.secondaryJoystickPort);
	
	public OI(){
		//Drivetrain buttons
		JoystickButton fineOmniDrive = new JoystickButton(leftDriveStick, Constants.fineDriveButton);
		fineOmniDrive.whileHeld(new FineOmniDrive());
		
		//Roller buttons
		JoystickButton closeOrOpenRollersButton = new JoystickButton(elevatorandRollerStick, Constants.closeOrOpenRollersButton);
		closeOrOpenRollersButton.whenPressed(new closeOrOpenRollers());
		JoystickButton intakeRollersButton = new JoystickButton(elevatorandRollerStick, Constants.intakeRollersButton);
		intakeRollersButton.whenPressed(new intakeRollers());
		JoystickButton outtakeRollersButton = new JoystickButton(elevatorandRollerStick, Constants.outtakeRollersButton);
		outtakeRollersButton.whenPressed(new outtakeRollers());
		JoystickButton stopRollersButton = new JoystickButton(elevatorandRollerStick, Constants.stopRollersButton);
		stopRollersButton.whenPressed(new stopRollers());
		
		//ElevatorButtons
		JoystickButton automaticEjectTotesButton = new JoystickButton(elevatorandRollerStick, Constants.automaticEjectTotesButton);
		automaticEjectTotesButton.whenPressed(new AutomaticEjectTotes());
		JoystickButton manualOpenEjectionPiston = new JoystickButton(elevatorandRollerStick, Constants.manualOpenEjectionPistonButton);
		manualOpenEjectionPiston.whenPressed(new ManualOpenEjectionPiston());
		JoystickButton manualCloseEjectionPiston = new JoystickButton(elevatorandRollerStick, Constants.manualCloseEjectionPistonButton);
		manualCloseEjectionPiston.whenPressed(new ManualCloseEjectionPiston());
			
		//PID buttons
		
		JoystickButton startDrivetrainPID = new JoystickButton(stick, Constants.startDrivetrainPID);
		startDrivetrainPID.whenPressed(new StartDrivetrainPID());
		
		JoystickButton stopDrivetrainPID = new JoystickButton(stick, Constants.stopDrivetrainPID);
		stopDrivetrainPID.cancelWhenPressed(new StartDrivetrainPID());
		stopDrivetrainPID.whenPressed(new StopDrivetrainPID());
		
		JoystickButton startElevatorPID = new JoystickButton(elevatorandRollerStick, Constants.startElevatorPID);
		startElevatorPID.whenPressed(new StartElevatorPID());
		
		JoystickButton stopElevatorPID = new JoystickButton(elevatorandRollerStick, Constants.stopElevatorPID);
		stopElevatorPID.cancelWhenPressed(new StartElevatorPID());
		stopElevatorPID.whenPressed(new StopElevatorPID());
		
		/*JoystickButton stopMotors = new JoystickButton(stick, Constants.stopMotors);
		stopMotors.cancelWhenPressed(new StartPID());
		stopMotors.whenPressed(new StopPID());
		stopMotors.whenPressed(new StopMotors());
		
		//general buttons
		JoystickButton restartDumbDrive = new JoystickButton(stick, Constants.restartDumbDriveButton);
		restartDumbDrive.whenPressed(new DumbDrive());*/
		
		//pneumatics buttons
		JoystickButton extend = new JoystickButton(stick, Constants.extend);
		extend.whenPressed(new ExtendPiston(1.5));
		JoystickButton retract = new JoystickButton(stick, Constants.retract);
		retract.whenPressed(new RetractPiston(1.5));
		
	}
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
}

