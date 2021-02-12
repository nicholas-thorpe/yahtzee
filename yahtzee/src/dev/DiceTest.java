package dev;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class DiceTest {
	private ArrayList<Dice> dice = new ArrayList<Dice>();
	private ArrayList<JToggleButton> diceButtons = new ArrayList<JToggleButton>();
	private JButton rollButton;
	private JFrame frame;
	
	private final ImageIcon DICE_BLANK = new ImageIcon(getClass().getResource("/blank.png"));
	private int results[] = {0, 0, 0, 0, 0, 0};
	
	public static void main(String[] args) {
		new DiceTest().go();
	}
	
	public void go() {
		frame = new JFrame("Dice Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		rollButton = new JButton("Roll");
		rollButton.addActionListener(new RollButtonListener());
		
		frame.getContentPane().add(rollButton, BorderLayout.SOUTH);
		frame.getContentPane().add(setupDicePanel(), BorderLayout.NORTH);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private JPanel setupDicePanel() {
		JPanel dicePanel = new JPanel();
		
		for (int i = 0; i < 5; i++) {
			Dice d = new Dice();
			dice.add(d);
			
			JToggleButton b = new JToggleButton();
			b.setIcon(DICE_BLANK);
			diceButtons.add(b);
			
			dicePanel.add(b);
		}
		
		return dicePanel;
	}
	
	class RollButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < diceButtons.size(); i++) {
				JToggleButton current = diceButtons.get(i);
				
				if (!current.isSelected()) {
					Dice d = dice.get(i);
					d.roll();
					current.setIcon(d.getImage());
				}
			}
		}
	}
	
	private void clearResults() {
		for (int i = 0; i < 6; i++) {
			results[i] = 0;
		}
	}
	
	private void findResults() {
		clearResults();
		
		for (Dice d : dice) {
			results[d.getValue() - 1]++;
		}
	}
	
	private boolean checkThreeofaKind() {
		findResults();
		
		boolean three = false;
		
		for (int val : results) {
			if (val == 3) three = true;
		}
		
		return three;
	}
	
	private boolean checkFourofaKind() {
		findResults();
		
		boolean four = false;
		
		for (int val : results) {
			if (val == 4) four = true;
		}
		
		return four;
	}
	
	private boolean checkFullHouse() {
		findResults();
		
		boolean two = false;
		boolean three = false;
		
		for (int val : results) {
			if (val == 2) two = true;
			else if (val == 3) three = true;
			else if (val >= 0) break;
		}
		
		return (two && three);
	}
	
	private boolean checkSmallStraight() {
		findResults();
		
		boolean straight = false;
		
		if (results[0] > 0 && results[1] > 0 && results[2] > 0 && results[3] > 0) straight = true;
		if (results[1] > 0 && results[2] > 0 && results[3] > 0 && results[4] > 0) straight = true;
		if (results[2] > 0 && results[3] > 0 && results[4] > 0 && results[5] > 0) straight = true;
		
		return straight;
	}
	
	private boolean checkLargeStraight() {
		findResults();
		
		boolean straight = false;
		
		if (results[0] > 0 && results[1] > 0 && results[2] > 0 && results[3] > 0 && results[4] > 0) straight = true;
		if (results[1] > 0 && results[2] > 0 && results[3] > 0 && results[4] > 0 && results[5] > 0) straight = true;
		
		return straight;
	}
	
	private boolean checkYahtzee() {
		findResults();
		
		boolean five = false;
		
		for (int val : results) {
			if (val == 5) five = true;
			else if (val >= 0) break;
		}
		
		return five;
	}
}
