package org.usfirst.frc.team1732.systems;

import org.usfirst.frc.team1732.systems.IO.Joysticks;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

public class Drive {
	private Talon m_leftFrontMotor = new Talon(3);
	private Talon m_rightFrontMotor = new Talon(2);
	private Talon m_leftBackMotor = new Talon(1);
	private Talon m_rightBackMotor = new Talon(0);
	
	private Solenoid m_shifter = new Solenoid(0);

	private RobotDrive m_drive = new RobotDrive(m_leftFrontMotor, m_leftBackMotor, m_rightFrontMotor, m_rightBackMotor);
	
	public Drive() {
		m_drive.setInvertedMotor(MotorType.kFrontLeft, false);
		m_drive.setInvertedMotor(MotorType.kFrontRight, false);
		m_drive.setInvertedMotor(MotorType.kRearLeft, false);
		m_drive.setInvertedMotor(MotorType.kRearRight, false);
	}
	
	/**
	 * Set the drive.
	 * @param left: left joystick
	 * @param right: right joystick
	 * @param shift: if shifted
	 * @param arcade: arcade is left, right, or both
	 */
	public void drive(Joysticks joysticks) {
		
		//if the button is depressed
		if (joysticks.getShift()) {
			
			m_drive.setInvertedMotor(MotorType.kFrontLeft, true);
			m_drive.setInvertedMotor(MotorType.kRearLeft, true);
			
			//enable solenoid, on mecanum wheels
			m_shifter.set(true);
			
			//drive mecanum
			if (joysticks.getArcadeMode() == 0) {
			m_drive.mecanumDrive_Polar(joysticks.getMagnitude(), joysticks.getDirection(), joysticks.getRotation());
			} else if (joysticks.getArcadeMode() == 1) {
				m_drive.mecanumDrive_Polar(joysticks.getLeftMagnitude(), joysticks.getLeftDirection(), joysticks.getLeftRotation());
			} else {
				m_drive.mecanumDrive_Polar(joysticks.getRightMagnitude(), joysticks.getRightDirection(), joysticks.getRightRotation());
			}
			
		} else {

			m_drive.setInvertedMotor(MotorType.kFrontLeft, false);
			m_drive.setInvertedMotor(MotorType.kRearLeft, false);

			
			//disable solenoid, on tread
			m_shifter.set(false);
			
			// drive tank
			if (joysticks.getArcadeMode() == 0) {
				m_drive.tankDrive(joysticks.getLeftY(), joysticks.getRightY());
			} else if (joysticks.getArcadeMode() == 1) {
				m_drive.arcadeDrive(joysticks.getLeftY(), joysticks.getLeftX());
			} else {
				m_drive.arcadeDrive(joysticks.getRightY(), joysticks.getRightX());
			}
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
