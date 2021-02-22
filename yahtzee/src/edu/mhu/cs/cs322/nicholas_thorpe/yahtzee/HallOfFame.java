package edu.mhu.cs.cs322.nicholas_thorpe.yahtzee;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class HallOfFame {
	private ArrayList<HallOfFameEntry> entries = new ArrayList<HallOfFameEntry>();
	private final String FILENAME = "HallOfFame.dat";
	private final int MAX = 100;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HallOfFame hall = new HallOfFame();
		
		/*
		hall.addEntry("no", 250);
		hall.addEntry("sandbag", 10);
		hall.addEntry("hacks", 1000);
		hall.addEntry("Marfram", 500);
		hall.addEntry("your name", 200);
		hall.addEntry("Anonymous", 300);
		hall.addEntry("ABC", 123);
		
		hall.writeHallOfFame();
		*/
		
		hall.loadHallOfFame();
		hall.sortHallOfFame();
		System.out.println(hall);
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
	private void addEntry(String name, int score) {
		entries.add(new HallOfFameEntry(name, score));
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
