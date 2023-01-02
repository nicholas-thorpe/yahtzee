package edu.mhu.cs.cs322.nicholas_thorpe.yahtzee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class HallOfFame {
	private ArrayList<HallOfFameEntry> entries = new ArrayList<HallOfFameEntry>();
	private final String FILENAME = "HallOfFame.dat";
	private final int MAX = 100;
	
	
	/**
	 * @param args
	 */
	/*
	public static void main(String[] args) {
		HallOfFame hall = new HallOfFame();
		
		JDialog hallDialog = hall.getHallOfFameDialog();
		hallDialog.pack();
		hallDialog.setVisible(true);
	}
	*/
	
	public final JDialog getHallOfFameDialog(String name, int score) {
		if (entries.size() == 0) loadHallOfFame();
		
		int rank = addEntry(name, score);
		
		JDialog hallDialog = getHallOfFameDialog();
		
		JLabel rankLabel = new JLabel("Your score of " + score + " ranks " + rank + " out of " + entries.size() + " entries.");
		
		Font f = new Font("Verdana", Font.BOLD, 14);
		rankLabel.setFont(f);
		rankLabel.setForeground(new Color(255, 0, 0));
		rankLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		hallDialog.getContentPane().add(rankLabel, BorderLayout.NORTH);
		hallDialog.pack();
		Constants.center(hallDialog);
		
		return hallDialog;
	}
	
	public final JDialog getHallOfFameDialog() {
		if (entries.size() == 0) loadHallOfFame();
		
		JDialog hallDialog = new JDialog();
		hallDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		hallDialog.setTitle("Yahtzee - Hall of Fame");
		
		if (entries.size() > 0) {
			GridBagLayout gb = new GridBagLayout();
			JPanel scoresPanel = new JPanel(gb);
			
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.weightx = 0.5;
			gbc.ipady = 7;
			
			TitledBorder title = BorderFactory.createTitledBorder("Top " + MAX + " Scores");
			scoresPanel.setBorder(title);
			
			gbc.gridx = gbc.gridy = 1;
			
			gbc.anchor = GridBagConstraints.NORTHWEST;
			scoresPanel.add(new JLabel("     Name"), gbc);
			
			gbc.gridx++;
			gbc.anchor = GridBagConstraints.CENTER;
			scoresPanel.add(new JLabel("Date Achieved"), gbc);
			
			gbc.gridx++;
			gbc.anchor = GridBagConstraints.NORTHEAST;
			scoresPanel.add(new JLabel("Score"), gbc);
			
			int row = 1;
			for (HallOfFameEntry e : entries) {
				gbc.gridx = 1;
				gbc.gridy++;
				
				gbc.anchor = GridBagConstraints.NORTHWEST;
				scoresPanel.add(new JLabel(String.format("%3d. %-20s", row, e.getName())), gbc);
				
				gbc.gridx++;
				gbc.anchor = GridBagConstraints.CENTER;
				scoresPanel.add(new JLabel(e.getDateDisplay()), gbc);
				
				gbc.gridx++;
				gbc.anchor = GridBagConstraints.NORTHEAST;
				scoresPanel.add(new JLabel(String.format("%5d", e.getScore())), gbc);
				
				row++;
				
				if (row > MAX) break;
			}
			
			JScrollPane scroller = new JScrollPane(scoresPanel);
			
			hallDialog.getContentPane().add(scroller, BorderLayout.CENTER);
		} else {
			JLabel none = new JLabel("No Hall of Fame entries yet");
			
			hallDialog.getContentPane().add(none, BorderLayout.CENTER);
		}
		
		JPanel dismissPanel = new JPanel();
		JButton dismissButton = new JButton("Dismiss");
		dismissPanel.add(dismissButton);
		
		dismissButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hallDialog.dispose();
			}
		});
		
		hallDialog.getContentPane().add(dismissPanel, BorderLayout.SOUTH);
		
		hallDialog.setPreferredSize(new Dimension(500, 375));
		hallDialog.pack();
		Constants.center(hallDialog);
		hallDialog.setModal(true);
		
		return hallDialog;
	}
	
	/**
	 * 
	 */
	private void writeHallOfFame() {
		try (
				FileOutputStream fos = new FileOutputStream(FILENAME);
				ObjectOutputStream oos = new ObjectOutputStream(fos)
						) {
			oos.writeObject(entries);
			
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			System.err.println("I/O Error writing to file. Permissions error?");
			
			ioe.printStackTrace();
		} catch (Exception e) {
			System.err.println("General error writing Hall of Fame");
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	private void loadHallOfFame() {
		try (
				FileInputStream fis = new FileInputStream(FILENAME);
				ObjectInputStream ois = new ObjectInputStream(fis)
						) {
			entries = (ArrayList<HallOfFameEntry>) ois.readObject();
			
			ois.close();
			fis.close();
		} catch (FileNotFoundException fnfe) {
			System.err.println("File " + FILENAME + " does not exist.");
			
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			System.err.println("I/O Error reading Hall of Fame from " + FILENAME);
			
			ioe.printStackTrace();
		} catch (Exception e) {
			System.err.println("General error reading Hall of Fame from " + FILENAME);
			
			e.printStackTrace();
		}
	}
	
	/**
	 * @param name
	 * @param score
	 */
	private int addEntry(String name, int score) {
		HallOfFameEntry e = new HallOfFameEntry(name, score);
		
		if (entries.size() == 0) loadHallOfFame();
		
		entries.add(e);
		
		sortHallOfFame();
		int rank = entries.indexOf(e);
		
		writeHallOfFame();
		
		return (rank + 1);
	}
	
	/**
	 * 
	 */
	private void sortHallOfFame() {
		Collections.sort(entries);
	}

	/**
	 * @return the entries
	 */
	public ArrayList<HallOfFameEntry> getEntries() {
		return entries;
	}
	
	/**
	 * 
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (HallOfFameEntry entry : entries) {
			sb.append(entry.toString() + "\n");
		}
		
		return sb.toString();
	}
}
