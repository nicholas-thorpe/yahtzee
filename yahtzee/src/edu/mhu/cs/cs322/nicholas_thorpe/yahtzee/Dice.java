package edu.mhu.cs.cs322.nicholas_thorpe.yahtzee;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class Dice {
	private int value;
	private JToggleButton diceButton;
	
	private final ImageIcon DICE_BLANK = new ImageIcon(getClass().getResource("/blank.png"));
	private final ImageIcon DICE_1 = new ImageIcon(getClass().getResource("/1.png"));
	private final ImageIcon DICE_2 = new ImageIcon(getClass().getResource("/2.png"));
	private final ImageIcon DICE_3 = new ImageIcon(getClass().getResource("/3.png"));
	private final ImageIcon DICE_4 = new ImageIcon(getClass().getResource("/4.png"));
	private final ImageIcon DICE_5 = new ImageIcon(getClass().getResource("/5.png"));
	private final ImageIcon DICE_6 = new ImageIcon(getClass().getResource("/6.png"));
	private final ImageIcon DICE_IMAGES[] = {DICE_BLANK, DICE_1, DICE_2, DICE_3, DICE_4, DICE_5, DICE_6};
	
	/**
	 * Default constructor for dice
	 * Initialize the roll value to a random number 1-6
	 */
	public Dice(JToggleButton button) {
		diceButton = button;
		reset();
	}
	
	/**
	 * @return a random number 1-6
	 */
	public int roll() {
		if (!diceButton.isEnabled()) {
			System.out.println("Disabled");
			return value;
		}
		
		value = (int) (Math.random() * 6 + 1);
		setImage();
		return value;
	}
	
	/**
	 * Resets the Dice
	 */
	public void reset() {
		value = 0;
		setImage();
	}
	
	/**
	 * Enables or disables the toggle button
	 */
	public void setEnabled(boolean flag) {
		diceButton.setEnabled(flag);
	}
	
	/**
	 * @return the current value of the dice
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * @return the image associated with the current value of the dice
	 */
	public ImageIcon getImage() {
		return DICE_IMAGES[value];
	}
	
	/**
	 * Set the image on the toggle button
	 */
	public void setImage() {
		diceButton.setIcon(getImage());
	}
}
