package com.test.wordcount;

import java.io.Console;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WordCount {

	public static void main(String[] args) {
		WordCount wc = new WordCount();
		
		Console console = System.console();
		console.printf("Please input or press return to exit:\n");
		String sentence = null;
		while((sentence = console.readLine()) != null && sentence.length() > 0) {
			Map<String, Integer> map = wc.countWords(sentence);
			console.printf("Output:\n");
			try {
				wc.printMap(new TreeMap<String, Integer>(map), console.writer());
			}catch(IOException e) {
				console.printf("IO error: %s", e.getMessage());
			}
			console.printf("Please input or press return to exit:\n");
		}
	}
	
	/**
	 * Count the number of each distinct word occurred in a given sentence.
	 * @param sentence
	 * @return A Map object that contains the counting result, in which key and value
	 * 		   are word and number of that word respectively. The content of map is empty if no word is found.
	 * @throws NullPointerException if the given sentence is null
	 */
	public Map<String, Integer> countWords(String sentence) {
		if(sentence == null) {
			throw new NullPointerException("The sentence is null");
		}
		
		Map<String, Integer> map = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while(i < sentence.length()) {
			// Search for the first character of the next word
			while(i < sentence.length() && !Character.isLetter(sentence.charAt(i))) {
				i++;
			}
			
			if(i >= sentence.length()) {
				break;
			}
			
			// Have found the first character, now search for the left characters in the word
			while(i < sentence.length() && Character.isLetter(sentence.charAt(i))) {
				sb.append(Character.toLowerCase(sentence.charAt(i)));
				i++;
			}
			
			// Have found the whole word, now increment its number by one
			String word = sb.toString();
			map.put(word, map.getOrDefault(word, 0) + 1);
			sb.delete(0, sb.length());
		}
		
		return map;
	}
	
	/**
	 * Print the content of given Map with a given Writer in a tabular form.
	 * @param map
	 * @param writer
	 * @throws IOException if any IO error occurs
	 */
	public void printMap(Map<String, Integer> map, Writer writer) throws IOException {
		// Print line
		writer.write("-----------------------------------\n");
		// Print header
		writer.write(String.format("%-15s \t %s \n", "Word", "Count"));
		// Print line
		writer.write("-----------------------------------\n");
		// Print each map entry in a row
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			writer.write(String.format("%-15s \t %s \n", entry.getKey(), entry.getValue()));
		}
		writer.write("-----------------------------------\n");
	}
}
