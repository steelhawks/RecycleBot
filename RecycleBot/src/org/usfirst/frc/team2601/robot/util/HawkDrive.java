package org.usfirst.frc.team2601.robot.util;

import org.usfirst.frc.team2601.robot.Constants;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HawkDrive extends RobotDrive {
	
	SpeedController centerMotor;
	Encoder leftEncoder;
	Encoder rightEncoder;
    double adjustment = 0.9;

	public HawkDrive(SpeedController frontLeftMotor,
			SpeedController rearLeftMotor, SpeedController frontRightMotor,
			SpeedController rearRightMotor, SpeedController centerMotor, Encoder leftEncoder, Encoder rightEncoder) {
		super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		// TODO Auto-generated constructor stub
		this.centerMotor = centerMotor;
		this.leftEncoder = leftEncoder;
		this.rightEncoder = rightEncoder;
	}
	
	public void customArcade(Joystick stick, Double tolerance){
		double moveValue = stick.getY() * Constants.drivetrainSpeed * -1;
		double rotateValue = stick.getTwist() * Constants.drivetrainFineSpeed * -1;
		double strafeValue = stick.getX()*.75 ;
		double leftEncoderRate = leftEncoder.getRate();
		double rightEncoderRate = rightEncoder.getRate();
		
		customArcade(moveValue, rotateValue, strafeValue, false, leftEncoderRate, rightEncoderRate, tolerance);
	}
	
	public void customArcade(double moveValue, double rotateValue, double strafeValue, Boolean squaredInputs, double leftEncoderRate, double rightEncoderRate, double tolerance){
		double leftMotorSpeed;
        double rightMotorSpeed;
        double centerMotorSpeed;

        moveValue = limit(moveValue);
        rotateValue = limit(rotateValue);
        strafeValue = limit(strafeValue);
        
        centerMotorSpeed = strafeValue;
        
        if (squaredInputs) {
            // square the inputs (while preserving the sign) to increase fine control while permitting full power
            if (moveValue >= 0.0) {
                moveValue = (moveValue * moveValue);
            } else {
                moveValue = -(moveValue * moveValue);
            }
            if (rotateValue >= 0.0) {
                rotateValue = (rotateValue * rotateValue);
            } else {
                rotateValue = -(rotateValue * rotateValue);
            }
        }

        if (moveValue > 0.0) {
            if (rotateValue > 0.0) {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = Math.max(moveValue, rotateValue);
            } else {
                leftMotorSpeed = Math.max(moveValue, -rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            }
        } else {
            if (rotateValue > 0.0) {
                leftMotorSpeed = -Math.max(-moveValue, rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            } else {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
            }
        }
        
        double adjustedRightRate = rightEncoderRate + tolerance;
        double adjustedLeftRate = leftEncoderRate + tolerance;
        
        if (Math.abs(rightEncoderRate)>Math.abs(adjustedLeftRate)){
        	rightMotorSpeed = rightMotorSpeed * adjustment;
        	SmartDashboard.putNumber("ModifiedRightMotorSpeed", rightMotorSpeed);
        }
        else if (Math.abs(leftEncoderRate)>Math.abs(adjustedRightRate)){
        	leftMotorSpeed = leftMotorSpeed * adjustment;
        	SmartDashboard.putNumber("ModifiedLeftMotorSpeed", leftMotorSpeed);
        }
        
        setLeftRightMotorOutputs(leftMotorSpeed,rightMotorSpeed);
        centerMotor.set(centerMotorSpeed);
	}

    protected static double limit(double num) {
        if (num > 1.0) {
            return 1.0;
        }
        if (num < -1.0) {
            return -1.0;
        }
        return num;
    }
    
    public void autonomousStraight(double speed, double leftEncoderRate, double rightEncoderRate, double tolerance){
    	double leftMotorSpeed = speed;
    	double rightMotorSpeed = speed;
    	
    	double adjustedRightRate = rightEncoderRate + tolerance;
        double adjustedLeftRate = leftEncoderRate + tolerance;
        
        if (Math.abs(rightEncoderRate)>Math.abs(adjustedLeftRate)){
        	rightMotorSpeed = rightMotorSpeed * adjustment;
        	SmartDashboard.putNumber("ModifiedRightMotorSpeed", rightMotorSpeed);
        }
        else if (Math.abs(leftEncoderRate)>Math.abs(adjustedRightRate)){
        	leftMotorSpeed = leftMotorSpeed * adjustment;
        	SmartDashboard.putNumber("ModifiedLeftMotorSpeed", leftMotorSpeed);
        }
        
        setLeftRightMotorOutputs(leftMotorSpeed,rightMotorSpeed);
    }
	
    public void autonomousStraight(double speed, double tolerance){
    	autonomousStraight(speed, leftEncoder.getRate(), rightEncoder.getRate(), tolerance);
    }
    
}
