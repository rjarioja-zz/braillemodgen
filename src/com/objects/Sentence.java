package com.objects;
import java.text.BreakIterator;
import java.util.*;

/**
*
* @author Ryan Arioja
* Braille Module Generator v.1
* Copyright (C) 2014  Ryan Arioja, Theodore Merin, Kevin Zurita
* 
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
* 
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
* 
*/

public class Sentence {
	
	private String sentence = null;
	private double wordScore = 0;
	private double multipliedScore = 0;
	private double multiplier = -1;
	private int length = -1;
	private LinkedList<Word> words = new LinkedList<Word>();
	
	public Sentence(String sentence) {
		BreakIterator wordIterator = BreakIterator.getWordInstance(Locale.US);
		this.setSentence(sentence);
		
		wordIterator.setText(sentence);
		int start = wordIterator.first();
		int end = wordIterator.next();

		while (end != BreakIterator.DONE) {
			String word = sentence.substring(start,end);
			if (Character.isLetterOrDigit(word.charAt(0))) words.add(new Word(word));
			start = end;
			end = wordIterator.next();
		}
		
		this.setLength(words.size());
	}
	
	public Sentence(String sentence, double multiplier) {
		this(sentence);
		this.setMultiplier(multiplier);
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public void setWordsScore(double score) {
		this.wordScore = score;
	}

	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}

	public void setMultipliedScore(double multipliedScore) {
		this.multipliedScore = multipliedScore;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setWords(LinkedList<Word> words) {
		this.words = words;
	}
	
	public void setWords(String[] words) {
		for (int i = 0; i < words.length; i++) {
			this.words.add(new Word(words[i]));
		}
	}
	
	public void setWords(Word[] words) {
		for (int i = 0; i < words.length; i++) this.words.add(words[i]);
	}
	
	public String getSentence() {
		return sentence;
	}

	public double getWordsScore() {
		return wordScore;
	}

	public double getMultiplier() {
		return multiplier;
	}

	public double getMultipliedScore() {
		multipliedScore = getWordsScore() * getMultiplier();
		return multipliedScore;
	}
	
	public int getLength() {
		return length;
	}

	public Word getWord(int index) {
		return words.get(index);
	}
	
	public LinkedList<Word> getWords() {
		return words;
	}
		
	public String toString() {
		return sentence + " " + length + " " + multiplier + " " + wordScore;
	}
}
