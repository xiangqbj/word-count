package com.test.wordcount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class WordCountTest {
	@Test
	public void testCountWords() {
		WordCount wc = new WordCount();
		String sentence = "This is a statement, and so is this.";
		Map<String, Integer> expected = createMap(new String[]{"this", "is", "a", "statement", "and", "so"},
												new int[]{2, 2, 1, 1, 1, 1});
		assertEquals(expected, wc.countWords(sentence));
		
		assertTrue(wc.countWords("").isEmpty());
		assertTrue(wc.countWords(", .").isEmpty());
	}
	
	@Test
	public void testCountWordsException() {
		WordCount wc = new WordCount();
		try {
			wc.countWords(null);
			fail("NullPointerException expected to be thrown");
		}catch(NullPointerException e) {
			assertTrue(true);
		}
	}
	
	private Map<String, Integer> createMap(String[] keys, int[] values) {
		Map<String, Integer> map = new HashMap<>();
		for(int i = 0; i < keys.length; i++) {
			map.put(keys[i], values[i]);
		}
		
		return map;
	}
}
