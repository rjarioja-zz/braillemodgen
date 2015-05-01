package com.objects;
import java.net.URL;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

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

enum PunctuationTags {
	POUND("#"), DOLLAR("$"), 
	OPENPAR("("), CLOSEPAR(")"), COMMA(","), PERIOD("."),
	COLON(":"),	GRAVE("`"), DASH("-"), QUOTE("\""), NEWLINE("\\n");
	
	private String value;
	private PunctuationTags(String value) {
		this.value = value;
	}
	
	public String toString() {
		return this.value;
	}
}

public class Word {
	private String word = null;
	private String partOfSpeech = null;
	private double multiplier = 0.0;
	private int count = 0;
	private static MaxentTagger tagger 
		= new MaxentTagger(URL.class.getResource("/com/resources/english-bidirectional-distsim.tagger").toString());
	
	public Word(String word) {
		for (PunctuationTags p : PunctuationTags.values()) {
			if (word.contains(p.toString())) word = word.replace(p.toString(), "");
		}
		this.setWord(word);;
		String s[] = tagger.tagString(word).split("_");
		partOfSpeech = s[s.length - 1];
	}
	
	public Word(String word, String partOfSpeech) {
		this(word);
		if (partOfSpeech == null) {
			String s[] = tagger.tagString(word).split("_");
			partOfSpeech = s[s.length - 1];
		}
		else this.setPartOfSpeech(partOfSpeech);
	}
	
	public Word(String word, double multiplier) {
		this(word);
		this.setMultiplier(multiplier);
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public void setPartOfSpeech(String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}
	
	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getWord() {
		return word;
	}
	
	public String getPartOfSpeech() {
		return partOfSpeech;
	}
	
	public double getMultiplier() {
		return multiplier;
	}
	
	public int getCount() {
		return count;
	}
	
	public String toString() {
		return word + " " + partOfSpeech + multiplier;
	}
}