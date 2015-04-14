package com.assignment.t9.ds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieNode {
	
	private Integer value;
	private boolean isEnd;
	private Map<Integer, TrieNode> childNodes;
	
	private List<Word> wordList;
	
	public TrieNode() {
		this.value = -1;
		childNodes = new HashMap<Integer, TrieNode>();
	}

	public TrieNode(Integer value) {
		super();
		this.value = value;
		childNodes = new HashMap<Integer, TrieNode>();
	}

	public Integer getValue() {
		return value;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public Map<Integer, TrieNode> getChildNodes() {
		return childNodes;
	}

	public List<Word> getWordList() {
		return wordList;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public void setWordList(List<Word> wordList) {
		this.wordList = wordList;
	}
	
}
