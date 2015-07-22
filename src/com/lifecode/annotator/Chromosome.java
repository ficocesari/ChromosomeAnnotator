
package com.lifecode.annotator;

class Chromosome {
	Interval interval;
	Integer id;

	Chromosome() {
	}

	Chromosome(String line) throws Exception {
		String[] tokens = line.split(",");
		if (tokens.length != 3) throw new Exception("Bad format in line: " + line);
		init(tokens[0], tokens[1], tokens[2]);
	}

	protected void init(String id, String start, String end) {
		this.id = new Integer(id);
		this.interval = new Interval(start, end);
	}
}
