package dev;

import javax.swing.ImageIcon;

public class Dice {
	private int value;
	private final ImageIcon DICE_1 = new ImageIcon(getClass().getResource("/1.png"));
	private final ImageIcon DICE_2 = new ImageIcon(getClass().getResource("/2.png"));
	private final ImageIcon DICE_3 = new ImageIcon(getClass().getResource("/3.png"));
	private final ImageIcon DICE_4 = new ImageIcon(getClass().getResource("/4.png"));
	private final ImageIcon DICE_5 = new ImageIcon(getClass().getResource("/5.png"));
	private final ImageIcon DICE_6 = new ImageIcon(getClass().getResource("/6.png"));
	private final ImageIcon DICE_IMAGES[] = {DICE_1, DICE_2, DICE_3, DICE_4, DICE_5, DICE_6};
	
	/**
	 * Default constructor for dice
	 * Initialize the roll value to a random number 1-6
	 */
	public Dice() {
		roll();
	}
	
	/**
	 * @return a random number 1-6
	 */
	public void roll() {
		value = (int) (Math.random() * 6 + 1);
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
		return DICE_IMAGES[value - 1];
	}
}
