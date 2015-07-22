package com.lifecode.annotator;

public class Interval {
	public Integer start;
	public Integer end;
	
	public Interval(String start, String end) {
		this.start = new Integer(start);
		this.end = new Integer(end);
	}
	
	//Start ² StartÕ ² End or StartÕ ² Start ² EndÕ.
	public Boolean overlaps(Interval other) {
		if (start.intValue() <= other.start.intValue() && other.start.intValue() <= end.intValue()) {
			return true;
		} else if (other.start.intValue() <= start.intValue() && start.intValue() <= other.end.intValue()) {
			return true;
		}
		return false;
	}
}