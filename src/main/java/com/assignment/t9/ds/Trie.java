package com.assignment.t9.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Represents a trie structure , the digits are represented as trie nodes, the
 * leaf node(last digit of pattern) stores the possible words with the pattern.
 * For Example : good and hood both represent 4-6-6-3. 4-6-6-3 is path in trie ,
 * the leaf node(3) stores the possible words that lead to this path, i.e it
 * stores good,hood,goof,hoof,etc all possible words from dictionary
 * 
 * @author Deekshith
 *
 */
public class Trie {

	private static final Map<Character, Integer> LETTER_NUMBER_MAP = new HashMap<Character, Integer>();

	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	public void add(Word w) {
		String word = w.getWordString();
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			Integer num = LETTER_NUMBER_MAP.get(ch);
			Map<Integer, TrieNode> childNodes = current.getChildNodes();
			if (childNodes.containsKey(num)) {
				current = childNodes.get(num);
			} else {
				TrieNode node = new TrieNode(num);
				childNodes.put(num, node);
				current = node;
			}
		}
		current.setEnd(true);
		if (current.getWordList() == null) {
			List<Word> wordsList = new ArrayList<Word>();
			wordsList.add(w);
			current.setWordList(wordsList);
		} else
			current.getWordList().add(w);

	}

	/**
	 * searches for the given pattern and returns all possible words possible
	 * from the pattern
	 * 
	 * @param pattern
	 * @return
	 */
	public List<Word> search(int[] pattern) {
		List<Word> possibleWords = new ArrayList<Word>();
		TrieNode current = root;
		String result = "";
		for (int i = 0; i < pattern.length; i++) {
			int ch = pattern[i];
			Map<Integer, TrieNode> childNodes = current.getChildNodes();
			if (childNodes.containsKey(ch)) {
				current = childNodes.get(ch);
			} else {
				return possibleWords;
			}
		}
		possibleWords = findWordsFromNode(current, possibleWords, result);
		return possibleWords;
	}

	private List<Word> findWordsFromNode(TrieNode node, List<Word> wordsList,
			String result) {
		if (node == null)
			return new ArrayList<Word>();
		result = result + node.getValue();
		if (node.isEnd()) {
			// get all the words and add to result
			wordsList.addAll(node.getWordList());
		}
		if (node.getChildNodes().isEmpty())
			return wordsList;
		else {
			for (Entry<Integer, TrieNode> entry : node.getChildNodes()
					.entrySet()) {
				wordsList = findWordsFromNode(entry.getValue(), wordsList,
						result);
			}
		}
		return wordsList;

	}

	static {
		LETTER_NUMBER_MAP.put('a', 2);
		LETTER_NUMBER_MAP.put('b', 2);
		LETTER_NUMBER_MAP.put('c', 2);

		LETTER_NUMBER_MAP.put('d', 3);
		LETTER_NUMBER_MAP.put('e', 3);
		LETTER_NUMBER_MAP.put('f', 3);

		LETTER_NUMBER_MAP.put('g', 4);
		LETTER_NUMBER_MAP.put('h', 4);
		LETTER_NUMBER_MAP.put('i', 4);

		LETTER_NUMBER_MAP.put('j', 5);
		LETTER_NUMBER_MAP.put('k', 5);
		LETTER_NUMBER_MAP.put('l', 5);

		LETTER_NUMBER_MAP.put('m', 6);
		LETTER_NUMBER_MAP.put('n', 6);
		LETTER_NUMBER_MAP.put('o', 6);

		LETTER_NUMBER_MAP.put('p', 7);
		LETTER_NUMBER_MAP.put('q', 7);
		LETTER_NUMBER_MAP.put('r', 7);
		LETTER_NUMBER_MAP.put('s', 7);

		LETTER_NUMBER_MAP.put('t', 8);
		LETTER_NUMBER_MAP.put('u', 8);
		LETTER_NUMBER_MAP.put('v', 8);

		LETTER_NUMBER_MAP.put('w', 9);
		LETTER_NUMBER_MAP.put('x', 9);
		LETTER_NUMBER_MAP.put('y', 9);
		LETTER_NUMBER_MAP.put('z', 9);
	}

	public static void main(String[] args) {
		Trie t = new Trie();
		t.add(new Word("good", 12));
		t.add(new Word("ho", 12));
		t.add(new Word("hotel", 12));
		t.add(new Word("zxsd", 12));

		int[] p = { 4, 6, 6 };

		System.out.println(t.search(p));
	}

}
