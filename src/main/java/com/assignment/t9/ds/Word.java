package com.assignment.t9.ds;

/**
 * Abstracts the word and the popularity of the word
 * @author Deekshith
 *
 */
public class Word implements Comparable<Word> {
	
	private String wordString;
	private int popularity;
	
	public Word(String wordString, int popularity) {
		super();
		this.wordString = wordString;
		this.popularity = popularity;
	}

	public String getWordString() {
		return wordString;
	}

	public int getPopularity() {
		return popularity;
	}

	@Override
	public String toString() {
		return wordString+"("+popularity+")\n ";
	}

	public int compareTo(Word otherWord) {
		return this.popularity - otherWord.popularity;
	}
	
	

}
