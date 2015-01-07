package org.usfirst.frc.team1732.systems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Creates all IO. (Joysticks and buttons)
 */
public class IO {
	
	/*
	 * Create Joysticks
	 */
	public Joystick m_leftJoystick = new Joystick(0);
	public Joystick m_rightJoystick = new Joystick(1);
	
	
	/*
	 * Create Joystick Buttons
	 */
	private Button m_shifterLeft = new JoystickButton(m_leftJoystick, 0);
	private Button m_shifterRight = new JoystickButton(m_leftJoystick, 0);
	
	/**
	 * Gets Button state
	 * @return boolean value of button
	 */
	public boolean getShifter() {
		return m_leftJoystick.getTrigger() || m_rightJoystick.getTrigger();
	}
	
}
