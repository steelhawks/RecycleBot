package org.usfirst.frc.team2601.robot;

import org.usfirst.frc.team2601.robot.Constants;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team2601.robot.commands.Cam;
import org.usfirst.frc.team2601.robot.commands.ExtendPiston;
import org.usfirst.frc.team2601.robot.commands.PanLeft;
import org.usfirst.frc.team2601.robot.commands.PanRight;
import org.usfirst.frc.team2601.robot.commands.RetractPiston;
import org.usfirst.frc.team2601.robot.commands.TiltDown;
import org.usfirst.frc.team2601.robot.commands.TiltUp;
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
import org.usfirst.frc.team2601.robot.commands.rollerCommands.CloseOrOpenRollers;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.closeRollers;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.intakeRollers;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.openRollers;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.outtakeRollers;
import org.usfirst.frc.team2601.robot.commands.rollerCommands.stopRollers;
import org.usfirst.frc.team2601.robot.util.POVButton;
import org.usfirst.frc.team2601.robot.util.F310;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
	public static Joystick stick; 
	public static Joystick elevatorandRollerStick;
	public static Joystick leftDriveStick; 
	public static Joystick rightDriveStick;
	public static F310 gamepad;

	private Constants myConstants = Constants.getInstance();
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
		stick = new Joystick(myConstants.joystickPort);
		elevatorandRollerStick = new Joystick(myConstants.secondaryJoystickPort);
		leftDriveStick = new Joystick(myConstants.leftDriveStick);
		rightDriveStick = new Joystick(myConstants.rightDriveStick);
		gamepad = new F310(myConstants.gamepadPort, true);
		
		

		//camera buttons
	//	JoystickButton cam = new JoystickButton(stick, Constants.cam);
	//	cam.whenPressed(new Cam());
		
	//	JoystickButton topCam = new JoystickButton(stick, Constants.topCam);
	//	topCam.whenPressed(new TopCam());
		
		//Drivetrain buttons
		JoystickButton fineArcadeOmniDrive = new JoystickButton(stick, myConstants.fineDriveButton);
		fineArcadeOmniDrive.whileHeld(new FineArcadeOmniDrive());
		
		
		//Roller buttons
		//use gamepad -doesn't work 
		 JoystickButton closeOrOpenRollersButton = new JoystickButton(gamepad, gamepad.kGamepadButtonShoulderL);
		 /*
		JoystickButton intakeRollersButton = new JoystickButton(gamepad, myConstants.intakeRollersButton);
		JoystickButton outtakeRollersButton = new JoystickButton(gamepad, myConstants.outtakeRollersButton);
		JoystickButton stopRollersButton = new JoystickButton(gamepad, myConstants.stopRollersButton);
		
		JoystickButton intakeRollersButton = new JoystickButton(elevatorandRollerStick, myConstants.intakeRollersButton);
		JoystickButton closeOrOpenRollersButton = new JoystickButton(elevatorandRollerStick, myConstants.closeOrOpenRollersButton);
		JoystickButton outtakeRollersButton = new JoystickButton(elevatorandRollerStick, myConstants.outtakeRollersButton);
		JoystickButton stopRollersButton = new JoystickButton(elevatorandRollerStick, myConstants.stopRollersButton);
		*/
		 
		closeOrOpenRollersButton.whenPressed(new CloseOrOpenRollers());
		/*
		intakeRollersButton.whenPressed(new intakeRollers());
		outtakeRollersButton.whenPressed(new outtakeRollers());
		stopRollersButton.whenPressed(new stopRollers());
		*/
		
		//ElevatorButtons
		//JoystickButton fineElevatorButton = new JoystickButton(elevatorandRollerStick, myConstants.fineElevatorButton);
		//fineElevatorButton.whileHeld(new FineManualElevator());
		/*JoystickButton automaticEjectTotesButton = new JoystickButton(elevatorandRollerStick, Constants.automaticEjectTotesButton);
		automaticEjectTotesButton.whenPressed(new AutomaticEjectTotes());
		JoystickButton manualOpenEjectionPiston = new JoystickButton(elevatorandRollerStick, Constants.manualOpenEjectionPistonButton);
		manualOpenEjectionPiston.whenPressed(new ManualOpenEjectionPiston());
		JoystickButton manualCloseEjectionPiston = new JoystickButton(elevatorandRollerStick, Constants.manualCloseEjectionPistonButton);
		manualCloseEjectionPiston.whenPressed(new ManualCloseEjectionPiston());
			*/
		//PID buttons
		
		JoystickButton startDrivetrainPID = new JoystickButton(stick, myConstants.startDrivetrainPID);
		startDrivetrainPID.whenPressed(new VelocityPIDDrive());
		
		
		JoystickButton stopDrivetrainPID = new JoystickButton(stick, myConstants.stopDrivetrainPID);
		stopDrivetrainPID.cancelWhenPressed(new VelocityPIDDrive());
		stopDrivetrainPID.whenPressed(new StopVelocityPID());
		
		/*
		JoystickButton startElevatorPID = new JoystickButton(elevatorandRollerStick, myConstants.startElevatorPID);
		startElevatorPID.whenPressed(new StartElevatorPID());
		
		JoystickButton stopElevatorPID = new JoystickButton(elevatorandRollerStick, myConstants.stopElevatorPID);
		stopElevatorPID.cancelWhenPressed(new StartElevatorPID());
		stopElevatorPID.whenPressed(new StopElevatorPID());
		*/
		
		POVButton upPOV = new POVButton(stick, 0);
		upPOV.whenActive(new TiltUp());
		
		POVButton downPOV = new POVButton(stick, 2);
		downPOV.whenActive(new TiltDown());
		
		POVButton leftPOV = new POVButton(stick, 3);
		leftPOV.whenActive(new PanLeft());
		
		POVButton rightPOV = new POVButton(stick, 4);
		rightPOV.whenActive(new PanRight());
		

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
	

	public F310 getGamepad(){
		return gamepad;
	}
}

