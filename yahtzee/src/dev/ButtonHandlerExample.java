package dev;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonHandlerExample {
	public static void main(String[] args) {
		new ButtonHandlerExample().go();
	}
	
	private ArrayList<ButtonHandler> handlers = new ArrayList<ButtonHandler>();
	
	public void go() {
		JFrame frame = new JFrame("ButtonHandlerExample");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel buttonPanel = new JPanel();
		
		// Create 4 buttons
		JButton btnOne = new JButton("Button 1");
		JButton btnTwo = new JButton("Button 2");
		JButton btnThree = new JButton("Button 3");
		JButton btnFour = new JButton("Button 4");
		
		// Add the AllButtonListener to each button
		AllButtonListener allListener = new AllButtonListener();
		btnOne.addActionListener(allListener);
		btnTwo.addActionListener(allListener);
		btnThree.addActionListener(allListener);
		btnFour.addActionListener(allListener);
		
		// Add them to the JPanel
		buttonPanel.add(btnOne);
		buttonPanel.add(btnTwo);
		buttonPanel.add(btnThree);
		buttonPanel.add(btnFour);
		
		// Add all of the handlers to the handlers ArrayList
		handlers.add(new ButtonOneHandler(btnOne));
		handlers.add(new ButtonTwoHandler(btnTwo));
		handlers.add(new ButtonThreeHandler(btnThree));
		handlers.add(new ButtonFourHandler(btnFour));
		
		
		frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	class AllButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			// Which JButton was clicked?
			JButton clicked = (JButton) e.getSource();
			
			// Iterate through the handlers, and see if any 
			// can handle the 'clicked' button.
			for(ButtonHandler bh : handlers) {
				if (bh.canHandle(clicked)) {
					bh.handle();
				}
			}
		}	
	}
	
	/** Set up our Super/Subclasses */
	class ButtonHandler {
		protected JButton myButton;
		protected String myMsg;
		
		/**
		 * Constructor
		 */
		public ButtonHandler (JButton button) {
			myButton = button;
			myMsg = "My Message Not Set";
		}
		
		public boolean canHandle(JButton button) {
			return (myButton == button);
		}
		
		public void handle() {
			System.out.println(myMsg);
		}
	}
	
	class ButtonOneHandler extends ButtonHandler {
		public ButtonOneHandler(JButton button) {
			super(button);
			myMsg = "Hi, I'm Button 1. How are you?";
		}
	}
	
	class ButtonTwoHandler extends ButtonHandler {
		public ButtonTwoHandler(JButton button) {
			super(button);
			myMsg = "Yo. Button 2 here.";
		}
	}
	
	class ButtonThreeHandler extends ButtonHandler {
		public ButtonThreeHandler(JButton button) {
			super(button);
			myMsg = "Where am I?";
		}
	}
	
	class ButtonFourHandler extends ButtonHandler {
		public ButtonFourHandler(JButton button) {
			super(button);
			myMsg = "Factory method, FTW!";
			
		}
	}
	
}
