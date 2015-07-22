
package com.lifecode.annotator;

import java.io.IOException;

public class Annotator {
	private int numChromosomes;
	private Csv.InputFile inputCsv;
	private Csv.InputFile annotatedCsv;
	private Csv.OutputFile outputCsv;

	private Object[] chromosomes;

	public Annotator(int numChromosomes) {
		this.numChromosomes = numChromosomes;
	}

	@SuppressWarnings("unchecked")
	public void annotate (String inputPairsFile, String annotationsFile, String outputFile) throws IOException {
		inputCsv = new Csv.InputFile(inputPairsFile);
		annotatedCsv = new Csv.InputFile(annotationsFile);
		outputCsv = new Csv.OutputFile(outputFile);

		chromosomes = new Object[numChromosomes];
		for(int i=0; i<numChromosomes; i++) {
			chromosomes[i] = new RedBlackIntervalBST<Integer, Interval>();
		}

		//1. Read input file into an array for RedBlackIntervalBST
		Chromosome c = null;
		try {
			do {
				c = inputCsv.readLine();
				if (c==null) break ;
				//The key for insertion is start
				((RedBlackIntervalBST<Integer, Interval>)chromosomes[c.id.intValue()-1]).put(
						c.interval.start, c.interval);
			} while(c != null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputCsv.close();
		}

		//2. Read annotations and insert in the interval trees such that Start � Start� � End or Start� � Start � End�.
		AnnotatedChromosome ac = null;
		try {
			do {
				ac = annotatedCsv.readLineAnnotated();
				if (ac==null) break;
				((RedBlackIntervalBST<Integer, Interval>)chromosomes[ac.id.intValue()-1]).annotate(
						ac.interval, ac.annotation);
			} while(ac != null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			annotatedCsv.close();
		}

		//3. Do an inorder traversal of the trees for each chromosome and print out the annotations
		try {
			for (int i=0; i<numChromosomes; i++) {
				((RedBlackIntervalBST<Integer, Interval>)chromosomes[i]).inorderWrite(outputCsv, i+1);
			}
		} finally {
			outputCsv.close();
		}
	}

	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Need three arguments");
			return;
		}

		int numChromosomes = 22;
		Annotator a = new Annotator(numChromosomes);
		try {
			a.annotate(args[0], args[1], args[2]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
