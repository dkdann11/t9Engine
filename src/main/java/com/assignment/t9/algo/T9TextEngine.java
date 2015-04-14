package com.assignment.t9.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.assignment.t9.ds.Trie;
import com.assignment.t9.ds.Word;

public class T9TextEngine {

	private  String wordsFile = "100kfound.txt";
	private int wordsToConsider = 5000;
	private int wordsToSuggest = 5;
	private Trie trie;

	public T9TextEngine() {
		trie = new Trie();
		List<Word> words = readWordsFromFile(wordsFile, wordsToConsider);
		addWordsToTrie(words);
	}

	public T9TextEngine(String wordsFile, int wordsToConsider,
			int wordsToSuggest) {
		trie = new Trie();
		this.wordsFile = wordsFile;
		this.wordsToConsider = wordsToConsider;
		this.wordsToSuggest = wordsToSuggest;
		List<Word> words = readWordsFromFile(wordsFile, wordsToConsider);
		addWordsToTrie(words);
	}

	private void addWordsToTrie(List<Word> words) {
		for (Word word : words) {
			trie.add(word);
		}
	}

	// read words from file  and adds to trie
	private List<Word> readWordsFromFile(String file, int wordsToConsider) {
		List<Word> wordsList = new ArrayList<Word>();
		InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String str = "";
		try {
			int count = 1;
			while ((str = br.readLine()) != null && count <= wordsToConsider) {
				String[] values = str.split(",");
				wordsList.add(new Word(values[1].trim(), Integer
						.parseInt(values[0].trim())));
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error while reading words from file");
		}
		return wordsList;
	}

	/**
	 * Suggests the best possible words based on the pattern entered in the keypad
	 * @param pattern
	 * @return
	 */
	public List<Word> autoSuggestWords(int pattern[]) {
		List<Word> output = new ArrayList<Word>();
		List<Word> possibleWords = trie.search(pattern);
		if (possibleWords == null || possibleWords.isEmpty())
			return output;
		// suggest only top five words
		Collections.sort(possibleWords);
		Collections.reverse(possibleWords);
		// return top prority words from list
		if (possibleWords.size() <= wordsToSuggest)
			return possibleWords;
		for (int i = 0; i < wordsToSuggest; i++) {
			output.add(possibleWords.get(i));
		}
		return output;
	}

	public static void main(String[] args) {
		T9TextEngine textEngine = new T9TextEngine(args[0],Integer.parseInt(args[1]),Integer.parseInt(args[2]));
		//T9TextEngine textEngine = new T9TextEngine();
		String patternString = args[3];
		int[] pattern = new int[patternString.length()];
		for (int i = 0; i < patternString.length(); i++) {
			pattern[i] = Integer.parseInt(Character.toString(patternString.charAt(i)));	
		}
		System.out.println(textEngine.autoSuggestWords(pattern));
	}

}
