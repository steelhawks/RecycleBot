package org.usfirst.frc.team2601.robot;

import org.usfirst.frc.team2601.robot.Constants;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team2601.robot.commands.Cam;
import org.usfirst.frc.team2601.robot.commands.ExtendPiston;
import org.usfirst.frc.team2601.robot.commands.ManualControlRollers;
import org.usfirst.frc.team2601.robot.commands.RetractPiston;
import org.usfirst.frc.team2601.robot.commands.TopCam;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.DumbDrive;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.FineArcadeOmniDrive;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.FineOmniDrive;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.StartDrivetrainPID;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.StopDrivetrainMotors;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.StopDrivetrainPID;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.StopVelocityPID;
import org.usfirst.frc.team2601.robot.commands.drivetrainCommands.VelocityPIDDrive;
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
    
	public static Joystick stick; 
	public static Joystick elevatorandRollerStick;
	public static Joystick leftDriveStick; 
	public static Joystick rightDriveStick;
	//public static Joystick rollerStick = new Joystick(Constants.secondaryJoystickPort);

	/*JoystickButton fineArcadeOmniDrive;
	
	//Roller buttons
	JoystickButton closeOrOpenRollersButton;
	JoystickButton intakeRollersButton;
	JoystickButton outtakeRollersButton;
	JoystickButton stopRollersButton;
	
	//ElevatorButtons
	JoystickButton automaticEjectTotesButton;
	JoystickButton manualOpenEjectionPiston ;
	JoystickButton manualCloseEjectionPiston;
		
	//PID buttons
	JoystickButton startDrivetrainPID;
	JoystickButton stopDrivetrainPID;
	
	JoystickButton startElevatorPID;
	JoystickButton stopElevatorPID;
	
	//pneumatics buttons
	JoystickButton extend;
	JoystickButton retract;

	*/	
	public OI(){
		stick = new Joystick(Constants.getInstance().joystickPort);
		elevatorandRollerStick = new Joystick(Constants.getInstance().secondaryJoystickPort);
		leftDriveStick = new Joystick(Constants.getInstance().leftDriveStick);
		rightDriveStick = new Joystick(Constants.getInstance().rightDriveStick);

/*
		stick = new Joystick(Constants.joystickPort);
		elevatorandRollerStick = new Joystick(Constants.secondaryJoystickPort);
		leftDriveStick = new Joystick(Constants.leftDriveStick);
		rightDriveStick = new Joystick(Constants.rightDriveStick);
	*/	
		//camera buttons
	//	JoystickButton cam = new JoystickButton(stick, Constants.cam);
	//	cam.whenPressed(new Cam());
		
	//	JoystickButton topCam = new JoystickButton(stick, Constants.topCam);
	//	topCam.whenPressed(new TopCam());
		
		//Drivetrain buttons
		JoystickButton fineArcadeOmniDrive = new JoystickButton(stick, Constants.getInstance().fineDriveButton);
		fineArcadeOmniDrive.whileHeld(new FineArcadeOmniDrive());
		
		
		//Roller buttons
		JoystickButton closeOrOpenRollersButton = new JoystickButton(elevatorandRollerStick, Constants.getInstance().closeOrOpenRollersButton);
		closeOrOpenRollersButton.whenPressed(new closeOrOpenRollers());
		JoystickButton intakeRollersButton = new JoystickButton(elevatorandRollerStick, Constants.getInstance().intakeRollersButton);
		intakeRollersButton.whenPressed(new intakeRollers());
		JoystickButton outtakeRollersButton = new JoystickButton(elevatorandRollerStick, Constants.getInstance().outtakeRollersButton);
		outtakeRollersButton.whenPressed(new outtakeRollers());
		JoystickButton stopRollersButton = new JoystickButton(elevatorandRollerStick, Constants.getInstance().stopRollersButton);
		stopRollersButton.whenPressed(new stopRollers());
		
		//ElevatorButtons
		/*JoystickButton automaticEjectTotesButton = new JoystickButton(elevatorandRollerStick, Constants.automaticEjectTotesButton);
		automaticEjectTotesButton.whenPressed(new AutomaticEjectTotes());
		JoystickButton manualOpenEjectionPiston = new JoystickButton(elevatorandRollerStick, Constants.manualOpenEjectionPistonButton);
		manualOpenEjectionPiston.whenPressed(new ManualOpenEjectionPiston());
		JoystickButton manualCloseEjectionPiston = new JoystickButton(elevatorandRollerStick, Constants.manualCloseEjectionPistonButton);
		manualCloseEjectionPiston.whenPressed(new ManualCloseEjectionPiston());
			*/
		//PID buttons
		
		JoystickButton startDrivetrainPID = new JoystickButton(stick, Constants.getInstance().startDrivetrainPID);
		startDrivetrainPID.whenPressed(new VelocityPIDDrive());
		
		
		JoystickButton stopDrivetrainPID = new JoystickButton(stick, Constants.getInstance().stopDrivetrainPID);
		stopDrivetrainPID.cancelWhenPressed(new VelocityPIDDrive());
		stopDrivetrainPID.whenPressed(new StopVelocityPID());
		
		JoystickButton startElevatorPID = new JoystickButton(elevatorandRollerStick, Constants.getInstance().startElevatorPID);
		startElevatorPID.whenPressed(new StartElevatorPID());
		
		JoystickButton stopElevatorPID = new JoystickButton(elevatorandRollerStick, Constants.getInstance().stopElevatorPID);
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
		/*JoystickButton extend = new JoystickButton(stick, Constants.extend);
		extend.whenPressed(new ExtendPiston(1.5));
		JoystickButton retract = new JoystickButton(stick, Constants.retract);
		retract.whenPressed(new RetractPiston(1.5));
		*/
	}
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
}

