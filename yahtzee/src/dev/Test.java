package dev;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Test {
	public static void main(String args[]){
		Test t = new Test();
		t.go();
	}
	
	public void go(){
		JFrame frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JButton btnTest = new JButton("Test");
		frame.getContentPane().add(btnTest);
		
		frame.setSize(150, 150);
		frame.setVisible(true);
		
	}
}
