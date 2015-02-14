package org.usfirst.frc.team1732.systems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Ultrasonic;

public class Intake 
{
	// motors
	//*/
	private CANTalon m_motor = new CANTalon(15);
	//*/
	
	// solenoids
	//*/
	private Solenoid m_solenoid = new Solenoid(2);
	//*/
	
	//*/
	private Ultrasonic left = new Ultrasonic(0, 1);
	private Ultrasonic right = new Ultrasonic(2, 3);
	private Ultrasonic front = new Ultrasonic(4, 5);
	//*/
	
	//*/
	private static final double STOP = 0;
	private static final double IN_SPEED = 1;
	private static final double OUT_SPEED = -1;
	//*/
	
	private boolean intake = false;
	
	/**
	 * control the intake with joysticks and buttons
	 * @param io
	 */
	public void controlIntake(IO io) {
		// checks to see if the tote is in the robot
		if (front.getRangeMM() < 30) {
			intake = false;
		}
		
		// direction control
		//override (both)
		if (io.getIntakeIn() && io.getIntakeOut()) {
			m_motor.set(STOP);
			intake = false;
		}
		// in
		else if (intake || io.getIntakeIn()) {
			m_motor.set(IN_SPEED);
			intake = true;
		}
		// out
		else if (io.getIntakeOut()) {
			m_motor.set(OUT_SPEED);
			intake = false;
		}
		// stop / do nothing
		else {
			m_motor.set(IN_SPEED);
		}
		
		// extends intakes
		m_solenoid.set(io.getIntakeInOut());
	}
	
	public Direction where() {
		return new Direction(left.getRangeInches() - right.getRangeInches(), front.getRangeInches());
	}
	
	/**
	 * make safe the intake
	 */
	//*/
	public void makeSafe()
	{
		m_motor.set(0);
		m_motor.disable();
		m_solenoid.set(false);
		m_solenoid.free();
	}
	//*/
}