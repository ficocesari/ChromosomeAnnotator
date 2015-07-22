
package com.lifecode.annotator;

class AnnotatedChromosome extends Chromosome {
	String annotation;
	
	AnnotatedChromosome(String line) throws Exception {
		String[] tokens = line.split(",");
		if (tokens.length != 4) throw new Exception("Bad format in line: " + line);
		init(tokens[0], tokens[1], tokens[2]);
		this.annotation = tokens[3];
	}
}