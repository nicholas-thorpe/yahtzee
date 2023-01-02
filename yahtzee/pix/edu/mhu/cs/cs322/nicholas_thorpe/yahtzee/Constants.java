package edu.mhu.cs.cs322.nicholas_thorpe.yahtzee;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

public class Constants {
	public static void center(Window window) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		int w = window.getSize().width;
		int h = window.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		
		window.setLocation(x, y);
	}
}