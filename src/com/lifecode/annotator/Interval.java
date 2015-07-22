package com.lifecode.annotator;

public class Interval {
	public Integer start;
	public Integer end;
	
	public Interval(String start, String end) {
		this.start = new Integer(start);
		this.end = new Integer(end);
	}
	
	//Start � Start� � End or Start� � Start � End�.
	public Boolean overlaps(Interval other) {
		if (start.intValue() <= other.start.intValue() && other.start.intValue() <= end.intValue()) {
			return true;
		} else if (other.start.intValue() <= start.intValue() && start.intValue() <= other.end.intValue()) {
			return true;
		}
		return false;
	}
}