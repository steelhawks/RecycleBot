package org.usfirst.frc.team2601.robot.util;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HawkDrive extends RobotDrive {
	
	SpeedController centerMotor;
    double adjustment;

	public HawkDrive(SpeedController frontLeftMotor,
			SpeedController rearLeftMotor, SpeedController frontRightMotor,
			SpeedController rearRightMotor, SpeedController centerMotor) {
		super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		// TODO Auto-generated constructor stub
		this.centerMotor = centerMotor;
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
        
        if (rightEncoderRate>adjustedLeftRate){
        	rightMotorSpeed = rightMotorSpeed * 0.95;
        	SmartDashboard.putNumber("ModifiedRightMotorSpeed", rightMotorSpeed);
        }
        else if (leftEncoderRate>adjustedRightRate){
        	leftMotorSpeed = leftMotorSpeed * 0.85;
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
	
}
