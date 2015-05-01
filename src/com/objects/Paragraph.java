package com.objects;
import java.util.*;
import java.text.*;

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

public class Paragraph {
	private String paragraph = null;
	private double averageScore = 0;
	private double totalScore = 0;
	private double targetScore = 0;
	private double multiplier = -1;
	private double averageLength = 0;
	private double longestLength = 0;
	private int length = -1;
	private LinkedList<Sentence> sentences = new LinkedList<Sentence>();
	
	public Paragraph(String paragraph) {
		BreakIterator sentenceIterator = BreakIterator.getSentenceInstance(Locale.US);
		sentenceIterator.setText(paragraph);
		int start = sentenceIterator.first();
		int end = -1;
		
		while ((end = sentenceIterator.next()) != BreakIterator.DONE) {
			sentences.add(new Sentence(paragraph.substring(start, end)));
			start = end;
		}		
		
		this.setParagraph(paragraph);
		this.setLength(sentences.size());
		for (Sentence s : sentences) {
			totalScore += s.getWordsScore();
		}
	}

	public Paragraph(String paragraph, double multiplier) {
		this(paragraph);
		this.setMultiplier(multiplier);
	}
		
	public void setParagraph(String paragraph) {
		this.paragraph = paragraph;
	}

	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}
	
	public void setLength(int length) {
		this.length = length;
	}

	public void setAverageLength(double averageLength) {
		this.averageLength = averageLength;
	}
	
	public String getParagraph() {
		return paragraph;
	}
	
	public double getTotalScore() {
		double score = 0;
		for (Sentence s : sentences) {
			score += s.getWordsScore();
		}
		totalScore = score;
		return totalScore;
	}
	
	public double getAverageScore() {
		double score = 0;
		for (Sentence s : sentences) score += s.getWordsScore();
		totalScore = score;
		averageScore = totalScore / length;
		return averageScore;
	}
	
	public double getTargetScore() {
		targetScore = getAverageScore() * 0.8;  
		return targetScore;
	}
	
	public double getMultiplier() {
		return multiplier;
	}
	
	public double getAverageLength() {
		double totalLength = 0;
		for (Sentence s : sentences) totalLength += s.getLength();
		averageLength = totalLength / sentences.size();
		return averageLength;
	}
	
	public double getLongestLength() {
		for (Sentence s : sentences) if (s.getLength() > longestLength) longestLength = s.getLength();
		return longestLength;
	}
	
	public LinkedList<Sentence> getSentences() {
		return sentences;
	}
	
	public Sentence getSentence(int index) {
		return sentences.get(index);
	}
	
	public int indexOf(Sentence sentence) {
		return sentences.indexOf(sentence);
	}
	
	public void remove(Sentence sentence) {
		sentences.remove(sentence);
	}
	
	public void sort() {
		Collections.sort(sentences, new SentenceComparator());
	}
	
	public String toString() {
		return paragraph + " " + length + " " + multiplier + " " + totalScore + " " + averageScore;
	}
}

class SentenceComparator implements Comparator<Sentence> {
	 
    public int compare(Sentence sentence1, Sentence sentence2) {
        if(sentence1.getWordsScore() < sentence2.getWordsScore()) return 1;
        else return -1;
    }
}