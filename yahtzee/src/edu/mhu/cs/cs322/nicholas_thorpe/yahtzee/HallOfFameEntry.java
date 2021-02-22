package edu.mhu.cs.cs322.nicholas_thorpe.yahtzee;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HallOfFameEntry implements Serializable, Comparable<HallOfFameEntry> {
	private static final long serialVersionUID = 6944977218691690004L;
	
	private String name;
	private long timestamp;
	private int score;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm");
	
	/**
	 * @param args
	 */
	public static void main (String[] args) {
		HallOfFameEntry entry = new HallOfFameEntry("no", 300);
	}
	
	/**
	 * Default constructor
	 */
	public HallOfFameEntry(String name, long timestamp, int score) {
		this.name = name;
		this.timestamp = timestamp;
		this.score = score;
	}
	
	/**
	 * Alternate constructor
	 */
	public HallOfFameEntry(String name, int score) {
		this.name = name;
		timestamp = System.currentTimeMillis();
		this.score = score;
	}
	
	/**
	 * @return toString
	 */
	public String toString() {
		return String.format("%-20s %5d %20s", name, score, getDateDisplay());
	}
	
	/**
	 * Comparator
	 */
	@Override
	public int compareTo(HallOfFameEntry o) {
		if (score > o.getScore()) {
			return -1;
		} else if (score < o.getScore()) {
			return 1;
		} else {
			if (timestamp > o.getTimestamp()) {
				return -1;
			} else if (timestamp < o.getTimestamp()) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * @return the formated date
	 */
	public String getDateDisplay() {
		Date d = new Date (timestamp);
		return sdf.format(d);
	}
}
