package com.lifecode.annotator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Csv {
	static public class InputFile {
		
		BufferedReader reader;
		public InputFile(String filename) {
			try {
				reader = new BufferedReader(new FileReader(filename));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Chromosome readLine() throws Exception {
			String line = reader.readLine();
			if (line == null) return null;
			return new Chromosome(line);
		}
		
		AnnotatedChromosome readLineAnnotated() throws Exception {
			String line = reader.readLine();
			if (line == null) return null;
			return new AnnotatedChromosome(line);
		}
		
		public void close() throws IOException {
			if (reader != null) reader.close();
		}
	}
	
	static public class OutputFile {
		
		PrintWriter writer; 
	
		public OutputFile(String filename) {
			try {
				writer = new PrintWriter(filename);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		void writeln(String str) {
			if (writer != null) {
				writer.println(str);
			}
		}
	
		public void close() {
			if (writer != null) writer.close();
		}
	}
}
	