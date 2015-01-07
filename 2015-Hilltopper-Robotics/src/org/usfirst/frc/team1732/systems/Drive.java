package org.usfirst.frc.team1732.systems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

public class Drive {
	private Talon m_leftFrontMotor = new Talon(0);
	private Talon m_rightFrontMotor = new Talon(1);
	private Talon m_leftBackMotor = new Talon(2);
	private Talon m_rightBackMotor = new Talon(4);
	
	private Solenoid m_shifter = new Solenoid(0);

	private RobotDrive m_drive = new RobotDrive(m_leftFrontMotor, m_leftBackMotor, m_rightFrontMotor, m_rightBackMotor);
	
	/**
	 * Set the drive.
	 * @param left
	 * @param right
	 */
	public void drive(Joystick left, Joystick right, boolean shift) {
		
		//if the button is depressed
		if (shift) {
			
			//enable solenoid, on mecanum wheels
			m_shifter.set(true);
			
			//drive mecanum
			m_drive.mecanumDrive_Polar(
					(left.getMagnitude() + 			right.getMagnitude()) / 2.0, 
					(left.getDirectionRadians() + 	right.getDirectionDegrees()) / 2.0, 
					(left.getY() + 					(-1.0 * right.getY())) / 2.0
					);
		} else {
			
			//disable solenoid, on tread
			m_shifter.set(false);
			
			// drive tank
			m_drive.tankDrive(left, right);
		}
	}
	
	/**
	 * Make safe the drive train.
	 */
	public void makeSafe() {
		m_leftFrontMotor.set(0);
		m_leftFrontMotor.free();
		m_leftFrontMotor.disable();
		
		m_rightFrontMotor.set(0);
		m_rightFrontMotor.free();
		m_rightFrontMotor.disable();

		m_leftBackMotor.set(0);
		m_leftBackMotor.free();
		m_leftBackMotor.disable();
		
		m_rightBackMotor.set(0);
		m_rightBackMotor.free();
		m_rightBackMotor.disable();
		
		m_shifter.set(false);
		m_shifter.free();
	}
}
