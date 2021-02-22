/**
 * 
 */
package edu.mhu.cs.cs322.nicholas_thorpe.yahtzee;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

/**
 * @author nicholas_thorpe
 *
 */
public class Yahtzee {
	private int rollCount = 0;
	private int results[] = {0, 0, 0, 0, 0, 0};
	private ArrayList<Dice> diceList = new ArrayList<Dice>();
	private ArrayList<ButtonHandler> handlers = new ArrayList<ButtonHandler>();
	
	private JFrame frame;
	private GridBagLayout gb;
	private GridBagConstraints buttonLayout;
	private GridBagConstraints textFieldLayout;
	
	private JPanel combinedPanel;
	private JPanel upperPanel;
	private JPanel lowerPanel;
	private JPanel dicePanel;
	private TitledBorder upperTitle;
	private TitledBorder lowerTitle;
	
	private JButton rollButton;
	
	private JButton onesButton;
	private JButton twosButton;
	private JButton threesButton;
	private JButton foursButton;
	private JButton fivesButton;
	private JButton sixesButton;
	private JTextField onesTextField;
	private JTextField twosTextField;
	private JTextField threesTextField;
	private JTextField foursTextField;
	private JTextField fivesTextField;
	private JTextField sixesTextField;
	
	private JButton threeOfAKindButton;
	private JButton fourOfAKindButton;
	private JButton fullHouseButton;
	private JButton smallStraightButton;
	private JButton largeStraightButton;
	private JButton yahtzeeButton;
	private JButton chanceButton;
	private JTextField threeOfAKindTextField;
	private JTextField fourOfAKindTextField;
	private JTextField fullHouseTextField;
	private JTextField smallStraightTextField;
	private JTextField largeStraightTextField;
	private JTextField yahtzeeTextField;
	private JTextField chanceTextField;
	
	private JLabel upperLabel;
	private JLabel bonusLabel;
	private JLabel upperTotalLabel;
	private JLabel lowerTotalLabel;
	private JLabel grandTotalLabel;
	private JTextField upperTextField;
	private JTextField bonusTextField;
	private JTextField upperTotalTextField;
	private JTextField lowerTotalTextField;
	private JTextField grandTotalTextField;
	
	/**
	 * @param args ARGS I'M A PIRATE
	 */
	public static void main(String[] args) {
		new Yahtzee().go();
	}
	
	/**
	 * Starts the program
	 */
	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CreateUpper();
		CreateLower();
		CreateHandlers();
		
		CreatePanels();
		SetupHandlers();
		CreateScores();
		
		frame.getContentPane().add(combinedPanel, BorderLayout.CENTER);
		
		setupDicePanel();
		
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Creation and setup method for the GUI panels
	 */
	private void CreatePanels() {
		gb = new GridBagLayout();
		
		upperPanel = new JPanel(gb);
		upperTitle = BorderFactory.createTitledBorder("Upper Section");
		upperPanel.setBorder(upperTitle);
		upperPanel.setPreferredSize(new Dimension(280, 275));
		
		lowerPanel = new JPanel(gb);
		lowerTitle = BorderFactory.createTitledBorder("Lower Section");
		lowerPanel.setBorder(lowerTitle);
		lowerPanel.setPreferredSize(new Dimension(280, 275));
		
		buttonLayout = new GridBagConstraints();
		buttonLayout.gridx = 0;
		buttonLayout.weightx = .5;
		
		textFieldLayout = new GridBagConstraints();
		textFieldLayout.gridx = 1;
		textFieldLayout.weightx = .5;
		
		combinedPanel = new JPanel();
		combinedPanel.add(upperPanel);
		combinedPanel.add(lowerPanel);
	}
	
	/**
	 * Creation method for the upper things
	 */
	private void CreateUpper() {
		onesButton = new JButton("Ones");
		twosButton = new JButton("Twos");
		threesButton = new JButton("Threes");
		foursButton = new JButton("Fours");
		fivesButton = new JButton("Fives");
		sixesButton = new JButton("Sixes");
		
		onesTextField = new JTextField(5);
		twosTextField = new JTextField(5);
		threesTextField = new JTextField(5);
		foursTextField = new JTextField(5);
		fivesTextField = new JTextField(5);
		sixesTextField = new JTextField(5);
	}
	
	/**
	 * Creation method for the lower things
	 */
	private void CreateLower() {
		threeOfAKindButton = new JButton("Three of a Kind");
		fourOfAKindButton = new JButton("Four of a Kind");
		fullHouseButton = new JButton("Full House");
		smallStraightButton = new JButton("Small Straight");
		largeStraightButton = new JButton("Large Straight");
		yahtzeeButton = new JButton("Yahtzee");
		chanceButton = new JButton("Chance");
		
		threeOfAKindTextField= new JTextField(5);
		fourOfAKindTextField = new JTextField(5);
		fullHouseTextField = new JTextField(5);
		smallStraightTextField = new JTextField(5);
		largeStraightTextField = new JTextField(5);
		yahtzeeTextField = new JTextField(5);
		chanceTextField = new JTextField(5);
	}
	
	/**
	 * Creation method for the handlers
	 */
	private void CreateHandlers() {
		handlers.add(new OnesButtonHandler(onesButton, onesTextField));
		handlers.add(new TwosButtonHandler(twosButton, twosTextField));
		handlers.add(new ThreesButtonHandler(threesButton, threesTextField));
		handlers.add(new FoursButtonHandler(foursButton, foursTextField));
		handlers.add(new FivesButtonHandler(fivesButton, fivesTextField));
		handlers.add(new SixesButtonHandler(sixesButton, sixesTextField));
		
		handlers.add(new ThreeOfAKindButtonHandler(threeOfAKindButton, threeOfAKindTextField));
		handlers.add(new FourOfAKindButtonHandler(fourOfAKindButton, fourOfAKindTextField));
		handlers.add(new FullHouseButtonHandler(fullHouseButton, fullHouseTextField));
		handlers.add(new SmallStraightButtonHandler(smallStraightButton, smallStraightTextField));
		handlers.add(new LargeStraightButtonHandler(largeStraightButton, largeStraightTextField));
		handlers.add(new YahtzeeButtonHandler(yahtzeeButton, yahtzeeTextField));
		handlers.add(new ChanceButtonHandler(chanceButton, chanceTextField));
	}
	
	/**
	 * Setup method for the handlers
	 */
	private void SetupHandlers() {
		AllButtonListener all = new AllButtonListener();
		int upperRow = 0;
		int lowerRow = 0;
		
		for (ButtonHandler b : handlers) {
			JButton btn = b.getButton();
			JTextField txt = b.getTextField();
			
			btn.addActionListener(all);
			btn.setPreferredSize(new Dimension(150, 26));
			
			txt.setEditable(false);
			txt.setHorizontalAlignment(JTextField.RIGHT);
			
			if (b.isLower()) {
				textFieldLayout.gridy = lowerRow;
				buttonLayout.gridy = lowerRow++;
				
				lowerPanel.add(btn, buttonLayout);
				lowerPanel.add(txt, textFieldLayout);
				
				lowerRow++;
			} else {
				textFieldLayout.gridy = upperRow;
				buttonLayout.gridy = upperRow++;
				
				upperPanel.add(btn, buttonLayout);
				upperPanel.add(txt, textFieldLayout);	
			}	
		}
	}
	
	/**
	 * Creation and setup method for the score labels
	 */
	private void CreateScores() {
		upperLabel = new JLabel("Total Score:");
		bonusLabel = new JLabel("Bonus:");
		upperTotalLabel = new JLabel("Upper Total:");
		lowerTotalLabel = new JLabel("Lower Total:");
		grandTotalLabel = new JLabel("Grand Total:");
		
		upperTextField = new JTextField(5);
		bonusTextField = new JTextField(5);
		upperTotalTextField = new JTextField(5);
		lowerTotalTextField = new JTextField(5);
		grandTotalTextField = new JTextField(5);
		
		upperTextField.setEditable(false);
		bonusTextField.setEditable(false);
		upperTotalTextField.setEditable(false);
		lowerTotalTextField.setEditable(false);
		grandTotalTextField.setEditable(false);
		
		upperPanel.add(upperTextField);
	}
	
	/**
	 * @return the total of the dice values
	 */
	private int diceTotal() {
		int sum = 0;
		
		for (Dice d : diceList) {
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
		for (Dice d : diceList) {
			d.reset();
			d.setEnabled(true);
		}
	}
	
	private void setupDicePanel() {
		rollButton = new JButton("Roll");
		rollButton.setPreferredSize(new Dimension(90, 66));
		rollButton.addActionListener(new RollButtonListener());
		
		dicePanel = new JPanel();
		dicePanel.add(rollButton);
		
		for (int i = 0; i < 5; i++) {
			JToggleButton db = new JToggleButton();
			
			Dice d = new Dice(db);
			diceList.add(d);
			d.setImage();
			d.setEnabled(false);
			
			dicePanel.add(db);
		}
		
		frame.getContentPane().add(dicePanel, BorderLayout.SOUTH);
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
		 * Overridedededed by most of the things in the lower section
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
		 */
		public void updateScore() {
			resetDice();
			button.setEnabled(false);
			
			if (isLower) calculateLower();
			else calculateUpper();
			calculateTotal();
			
			rollCount = 0;
			rollButton.setEnabled(true);
		}
		
		/**
		 * @return the score from the text field
		 */
		public int getScore() {
			try {
				return Integer.parseInt(textField.getText());
			} catch (NumberFormatException nfe) {
				return 0;
			}
		}
		/**
		 * @return is the handler for a lower item 
		 */
		public boolean isLower() {
			return isLower;
		}

		/**
		 * @return the associated button
		 */
		public JButton getButton() {
			return button;
		}

		/**
		 * @return the associated textField
		 */
		public JTextField getTextField() {
			return textField;
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
			textField.setText(String.valueOf(diceTotal()));
			
			super.updateScore();
		}
	}
	
	class AllButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton clicked = (JButton) e.getSource();
			
			for (ButtonHandler bh: handlers) {
				if (bh.canHandle(clicked)) {
					bh.updateScore();
				}
			}
		}
	}
	
	class RollButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (Dice d : diceList) {
				if (rollCount == 0) d.setEnabled(true);
				
				d.roll();
			}
			
			rollCount++;
			
			if (rollCount >= 3) rollButton.setEnabled(false);
		}
	}
}