/**
 * 
 */
package edu.mhu.cs.cs322.nicholas_thorpe.yahtzee;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * @author nicholas_thorpe
 *
 */
public class Yahtzee {
	private int results[] = {0, 0, 0, 0, 0, 0};
	private ArrayList<Dice> diceList = new ArrayList<Dice>();
	
	/**
	 * @param args ARGS I'M A PIRATE
	 */
	public static void main(String[] args) {
		new Yahtzee().go();
	}
	
	/**
	 * 
	 */
	public void go() {
		
	}
	
	/**
	 * @return the total of the dice values
	 */
	private int diceTotal() {
		int sum = 0;
		
		for(Dice d : diceList) {
			sum += d.getValue();
		}
		
		return sum;
	}
	
	private void clearResults() {
		for (int i = 0; i < 6; i++) {
			results[i] = 0;
		}
	}
	
	private void findResults() {
		clearResults();
		
		for (Dice d : diceList) {
			results[d.getValue() - 1]++;
		}
	}
	
	/**
	 * Calculates the total score of the upper section
	 */
	private void calculateUpper() {
		
	}
	
	/**
	 * Calculates the total score of the lower section
	 */
	private void calculateLower() {
		
	}
	
	/**
	 * Calculates the total score
	 */
	private void calculateTotal() {
		
	}
	
	/**
	 * Resets the dice
	 */
	private void resetDice() {
		
	}
	
	abstract class ButtonHandler {
		protected JButton button;
		protected JTextField textField;
		protected boolean isLower = true;
		
		/**
		 * Constructor
		 * @param button associated button with this handler
		 * @param textField associated text field with button
		 */
		public ButtonHandler(JButton button, JTextField textField) {
			this.button = button;
			this.textField = textField;
		}
		
		/**
		 * Checks if the button clicked matches the handler's button 
		 * @param button that was clicked
		 * @return true if the button matches, false if not
		 */
		public boolean canHandle(JButton button) {
			return (this.button == button);
		}
		
		/**
		 * Checks if the roll is valid
		 * @return true
		 */
		public boolean validRoll() {
			return true;
		}
		
		/**
		 * Reset the JButton and JTextField
		 */
		public void reset() {
			button.setEnabled(true);
			textField.setText("");
		}
		
		/**
		 * Updates the score
		 * look man it's not that complicated and I am tired
		 */
		public void updateScore() {
			resetDice();
			button.setEnabled(false);
			
			if (isLower) calculateLower();
			else calculateUpper();
			calculateTotal();
		}
	}
	
	abstract class UpperButtonHandler extends ButtonHandler {
		protected int val = 0;
		
		/**
		 * Constructor
		 * @param button associated button with this handler
		 * @param textField associated text field with button
		 */
		public UpperButtonHandler(JButton button, JTextField textField) {
			super(button, textField);
			isLower = false;
		}
		
		@Override
		public void updateScore() {
			findResults();
			textField.setText(String.valueOf(results[val - 1] * val));
			
			super.updateScore();
		}
	}
	
	class OnesButtonHandler extends UpperButtonHandler {
		/**
		 * Constructor
		 * @param button associated button with this handler
		 * @param textField associated text field with button
		 */
		public OnesButtonHandler(JButton button, JTextField textField) {
			super(button, textField);
			val = 1;
		}
	}
	
	class TwosButtonHandler extends UpperButtonHandler {
		/**
		 * Constructor
		 * @param button associated button with this handler
		 * @param textField associated text field with button
		 */
		public TwosButtonHandler(JButton button, JTextField textField) {
			super(button, textField);
			val = 2;
		}
	}
	
	class ThreesButtonHandler extends UpperButtonHandler {
		/**
		 * Constructor
		 * @param button associated button with this handler
		 * @param textField associated text field with button
		 */
		public ThreesButtonHandler(JButton button, JTextField textField) {
			super(button, textField);
			val = 3;
		}
	}
	
	class FoursButtonHandler extends UpperButtonHandler {
		/**
		 * Constructor
		 * @param button associated button with this handler
		 * @param textField associated text field with button
		 */
		public FoursButtonHandler(JButton button, JTextField textField) {
			super(button, textField);
			val = 4;
		}
	}
	
	class FivesButtonHandler extends UpperButtonHandler {
		/**
		 * Constructor
		 * @param button associated button with this handler
		 * @param textField associated text field with button
		 */
		public FivesButtonHandler(JButton button, JTextField textField) {
			super(button, textField);
			val = 5;
		}
	}
	
	class SixesButtonHandler extends UpperButtonHandler {
		/**
		 * Constructor
		 * @param button associated button with this handler
		 * @param textField associated text field with button
		 */
		public SixesButtonHandler(JButton button, JTextField textField) {
			super(button, textField);
			val = 6;
		}
	}
	
	class ThreeOfAKindButtonHandler extends ButtonHandler {
		public ThreeOfAKindButtonHandler(JButton button, JTextField textField) {
			super(button, textField);
		}
		
		@Override
		public boolean validRoll() {
			findResults();
			
			boolean three = false;
			
			for (int val : results) {
				if (val == 3) {
					three = true;
					break;
				}
			}
			
			return three;
		}
		
		@Override
		public void updateScore() {
			if (validRoll()) textField.setText(String.valueOf(diceTotal()));
			else textField.setText(String.valueOf(0));
			
			super.updateScore();
		}
	}
	
	class FourOfAKindButtonHandler extends ButtonHandler {
		public FourOfAKindButtonHandler(JButton button, JTextField textField) {
			super(button, textField);
		}
		
		@Override
		public boolean validRoll() {
			findResults();
			
			boolean four = false;
			
			for (int val : results) {
				if (val == 4) {
					four = true;
					break;
				}
			}
			
			return four;
		}
		
		@Override
		public void updateScore() {
			if (validRoll()) textField.setText(String.valueOf(diceTotal()));
			else textField.setText(String.valueOf(0));
			
			super.updateScore();
		}
	}
	
	class FullHouseButtonHandler extends ButtonHandler {
		public FullHouseButtonHandler(JButton button, JTextField textField) {
			super(button, textField);
		}
		
		@Override
		public boolean validRoll() {
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
		
		@Override
		public void updateScore() {
			if (validRoll()) textField.setText(String.valueOf(25));
			else textField.setText(String.valueOf(0));
			
			super.updateScore();
		}
	}
	
	class SmallStraightButtonHandler extends ButtonHandler {
		public SmallStraightButtonHandler(JButton button, JTextField textField) {
			super(button, textField);
		}
		
		@Override
		public boolean validRoll() {
			findResults();
			
			boolean straight = false;
			
			if (results[0] > 0 && results[1] > 0 && results[2] > 0 && results[3] > 0) straight = true;
			if (results[1] > 0 && results[2] > 0 && results[3] > 0 && results[4] > 0) straight = true;
			if (results[2] > 0 && results[3] > 0 && results[4] > 0 && results[5] > 0) straight = true;
			
			return straight;
		}
		
		@Override
		public void updateScore() {
			if (validRoll()) textField.setText(String.valueOf(30));
			else textField.setText(String.valueOf(0));
			
			super.updateScore();
		}
	}
	
	class LargeStraightButtonHandler extends ButtonHandler {
		public LargeStraightButtonHandler(JButton button, JTextField textField) {
			super(button, textField);
		}
		
		@Override
		public boolean validRoll() {
			findResults();
			
			boolean straight = false;
			
			if (results[0] > 0 && results[1] > 0 && results[2] > 0 && results[3] > 0 && results[4] > 0) straight = true;
			if (results[1] > 0 && results[2] > 0 && results[3] > 0 && results[4] > 0 && results[5] > 0) straight = true;
			
			return straight;
		}
		
		@Override
		public void updateScore() {
			if (validRoll()) textField.setText(String.valueOf(35));
			else textField.setText(String.valueOf(0));
			
			super.updateScore();
		}
	}
	
	class YahtzeeButtonHandler extends ButtonHandler {
		public YahtzeeButtonHandler(JButton button, JTextField textField) {
			super(button, textField);
		}
		
		@Override
		public boolean validRoll() {
			findResults();
			
			boolean five = false;
			
			for (int val : results) {
				if (val == 5) five = true;
				else if (val >= 0) break;
			}
			
			return five;
		}
		
		@Override
		public void updateScore() {
			if (validRoll()) {
				if (textField.getText() == "") {
					textField.setText(String.valueOf(50));
				} else {
					textField.setText(String.valueOf(Integer.parseInt(textField.getText()) + 100));
				}
				
				super.updateScore();
				
				button.setEnabled(true);
			} else {
				if (textField.getText() == "") {
					textField.setText(String.valueOf(0));
				}
				
				super.updateScore();
			}
		}
	}
	
	class ChanceButtonHandler extends ButtonHandler {
		public ChanceButtonHandler(JButton button, JTextField textField) {
			super(button, textField);
			
		}
		
		@Override
		public void updateScore() {
			if (validRoll()) textField.setText(String.valueOf(diceTotal()));
			else textField.setText(String.valueOf(0));
			
			super.updateScore();
		}
	}
}