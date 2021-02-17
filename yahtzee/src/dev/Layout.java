package dev;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Layout {
	JFrame frame;
	GridBagLayout gb;
	GridBagConstraints buttonLayout;
	GridBagConstraints textFieldLayout;
	
	JPanel combinedPanel;
	JPanel upperPanel;
	JPanel lowerPanel;
	TitledBorder upperTitle;
	TitledBorder lowerTitle;
	
	ArrayList<JButton> UpperButtons = new ArrayList<JButton>();
	ArrayList<JButton> LowerButtons = new ArrayList<JButton>();
	ArrayList<JTextField> UpperTextFields= new ArrayList<JTextField>();
	ArrayList<JTextField> LowerTextFields= new ArrayList<JTextField>();
	
	public static void main(String[] args) {
		new Layout().go();
	}
	
	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		
		CreateUpperButtons();
		CreateUpperTextFields();
		CreateLowerButtons();
		CreateLowerTextFields();
		
		combinedPanel = new JPanel();
		combinedPanel.add(upperPanel);
		combinedPanel.add(lowerPanel);
		
		frame.getContentPane().add(combinedPanel, BorderLayout.CENTER);
		
		frame.pack();
		frame.setVisible(true);
	}

	private void CreateUpperButtons() {
		UpperButtons.add(new JButton("Ones"));
		UpperButtons.add(new JButton("Twos"));
		UpperButtons.add(new JButton("Threes"));
		UpperButtons.add(new JButton("Fours"));
		UpperButtons.add(new JButton("Fives"));
		UpperButtons.add(new JButton("Sixes"));
		
		for (JButton b : UpperButtons) {
			b.setPreferredSize(new Dimension(120, 26));
			upperPanel.add(b, buttonLayout);
		}
	}
	
	private void CreateUpperTextFields() {
		for (int i = 0; i < 6; i++) {
			UpperTextFields.add(new JTextField(5));
		}
		
		for (JTextField tf : UpperTextFields) {
			tf.setEditable(false);
			tf.setHorizontalAlignment(JTextField.RIGHT);
			upperPanel.add(tf, textFieldLayout);
		}
	}
	
	private void CreateLowerButtons() {
		LowerButtons.add(new JButton("Three of a Kind"));
		LowerButtons.add(new JButton("Four of a Kind"));
		LowerButtons.add(new JButton("Full House"));
		LowerButtons.add(new JButton("Small Straight"));
		LowerButtons.add(new JButton("Large Straight"));
		LowerButtons.add(new JButton("Yahtzee"));
		LowerButtons.add(new JButton("Chance"));
		
		for (JButton b : LowerButtons) {
			b.setPreferredSize(new Dimension(150, 26));
			lowerPanel.add(b, buttonLayout);
		}
	}
	
	private void CreateLowerTextFields() {
		for (int i = 0; i < 7; i++) {
			LowerTextFields.add(new JTextField(5));
		}
		
		for (JTextField tf : LowerTextFields) {
			tf.setEditable(false);
			tf.setHorizontalAlignment(JTextField.RIGHT);
			lowerPanel.add(tf, textFieldLayout);
		}
	}
}