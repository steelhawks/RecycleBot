package org.usfirst.frc.team2601.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2601.robot.commands.PIDauton;
import org.usfirst.frc.team2601.robot.commands.closeWriter;
import org.usfirst.frc.team2601.robot.commands.auton.DoNothing;
import org.usfirst.frc.team2601.robot.commands.auton.DriveForwardToAutoZone;
import org.usfirst.frc.team2601.robot.commands.auton.GetToteMoveToAutoZoneArms;
import org.usfirst.frc.team2601.robot.commands.auton.GetToteMoveToAutoZoneRollers;
import org.usfirst.frc.team2601.robot.commands.auton.MotorTestAuton;
import org.usfirst.frc.team2601.robot.commands.auton.RollerTestAuton;
import org.usfirst.frc.team2601.robot.commands.auton.SampleAuton;
import org.usfirst.frc.team2601.robot.commands.auton.StackRCOnToteMoveToAutoZoneArms;
import org.usfirst.frc.team2601.robot.commands.auton.StackRCOnToteMoveToAutoZoneRollers;
import org.usfirst.frc.team2601.robot.commands.auton.StackThreeToteGetRCMoveArm;
import org.usfirst.frc.team2601.robot.commands.auton.StackThreeToteGetRCMoveRollers;
import org.usfirst.frc.team2601.robot.commands.auton.StackThreeToteMoveWithArms;
import org.usfirst.frc.team2601.robot.commands.auton.StackThreeToteMoveWithRollers;
import org.usfirst.frc.team2601.robot.subsystems.Camera;
import org.usfirst.frc.team2601.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2601.robot.subsystems.Elevator;
import org.usfirst.frc.team2601.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team2601.robot.subsystems.Pneumatics;
import org.usfirst.frc.team2601.robot.subsystems.Rollers;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

//	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	
	public static OI oi;
	
	public static Drivetrain drivetrain = new Drivetrain();
	public static final Elevator elevator = new Elevator();
	public static final Pneumatics pneumatics = new Pneumatics();
	public static final Rollers rollers = new Rollers();
	public static final Camera camera = new Camera();
	
	public static NetworkTable table;
	
	Constants myConstants = Constants.getInstance();
	Compressor compressor;
	CameraServer cam;
	
    Command autonomousCommand;
    Command closeCommand;
	SendableChooser autoChooser;
     /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
		try {			
			oi = new OI();
				
		//autonomousCommand = new StackRCOnToteMoveToAutoZoneRollers();
		autoChooser = new SendableChooser();
		autoChooser.addDefault("DriveForward", new DriveForwardToAutoZone());
		autoChooser.addObject("Arms/GetTote", new GetToteMoveToAutoZoneArms());
		autoChooser.addObject("Rollers/GetTote",new GetToteMoveToAutoZoneRollers());
		autoChooser.addObject("Arms/StackRCOnTote",new StackRCOnToteMoveToAutoZoneArms());
		autoChooser.addObject("Rollers/StackRCOnTote", new StackRCOnToteMoveToAutoZoneRollers());
		autoChooser.addObject("Arms/StackThreeTote", new StackThreeToteMoveWithArms());
		autoChooser.addObject("Rollers/StackThreeTote", new StackThreeToteMoveWithRollers());
		autoChooser.addObject("Arms/StackThreeToteGetRC", new StackThreeToteGetRCMoveArm());
		autoChooser.addObject("Rollers/StackThreeToteGetRC", new StackThreeToteGetRCMoveRollers());
		SmartDashboard.putData("AutoChooser", autoChooser);
		
        closeCommand = new closeWriter();
		
		table = NetworkTable.getTable("datatable");
		}
		catch (Exception ex) {
			//System.out.println(ex.toString());
		}
		
		if (myConstants.PNEUMATICS_ON){
	        compressor = new Compressor();
	     }
		         
		cam = CameraServer.getInstance();
        cam.startAutomaticCapture("cam0");
		
        table.putNumber(myConstants.drivetrainPKey, myConstants.drivetrainP);
        table.putNumber(myConstants.drivetrainIKey, myConstants.drivetrainI);
        table.putNumber(myConstants.drivetrainDKey, myConstants.drivetrainD);
        table.putNumber(myConstants.drivetrainSetpointKey, myConstants.drivetrainSetpoint);
        
        table.putNumber(myConstants.elevatorPKey, myConstants.elevatorP);
        table.putNumber(myConstants.elevatorIKey, myConstants.elevatorI);
        table.putNumber(myConstants.elevatorDKey, myConstants.elevatorD);
        table.putNumber(myConstants.elevatorSetpointKey, myConstants.elevatorSetpoint);
    
	}
    
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        autonomousCommand = (Command) autoChooser.getSelected(); 
        autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();

    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	closeCommand.start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
