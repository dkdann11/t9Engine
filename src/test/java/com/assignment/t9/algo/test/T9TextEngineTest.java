package com.assignment.t9.algo.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.assignment.t9.algo.T9TextEngine;
import com.assignment.t9.ds.Word;

public class T9TextEngineTest {
	
	private T9TextEngine textEngine;
	
	@Before
	public void setUp(){
		textEngine = new T9TextEngine();
	}
	
	@Test
	public void testMostPopularWord(){		
		int[] pattern = {9};
		String assertOnString = "yahoo";
		List<Word> words = textEngine.autoSuggestWords(pattern);
		System.out.println(words);
		Assert.assertEquals(assertOnString, words.get(0).getWordString());
	}	
	
	@Test
	public void testPopularWordsUsing9(){
		int[] pattern = {9};
		String[] assertOnStrings = {"yahoo","yeast","zen","water","word"};
		List<Word> words = textEngine.autoSuggestWords(pattern);
		assertWords(words,assertOnStrings);
	}	
	
	@Test
	public void testPatternContainsHello(){
		int[] pattern = {4,3,5,5,6};
		String assertOnString = "hello";
		List<Word> words = textEngine.autoSuggestWords(pattern);
		Assert.assertEquals(assertOnString, words.get(0).getWordString());
	}
	
	
	@Test
	public void testPopularWords(){
		int[] pattern = {};
		String[] assertOnStrings = {"yahoo","fuck","chat","pussy","sex"};
		List<Word> words = textEngine.autoSuggestWords(pattern);
		System.out.println(words);
		assertWords(words,assertOnStrings);
	}	

	private void assertWords(List<Word> words, String[] assertOnStrings) {
		for (int i = 0; i < assertOnStrings.length; i++) {
			Assert.assertEquals(assertOnStrings[i], words.get(i).getWordString());
		}
		
	}

}
