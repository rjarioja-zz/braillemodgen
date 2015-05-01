package com.extraction;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import net.didion.jwnl.JWNLException;

import com.objects.*;
import com.objects.collections.*;
import com.wordnet.WordNet;

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

public class Scorer {
	public static String output = "";
	
	public static double sentenceStatus = 0;
	private static double sentenceScore = 0;
	private static double sentenceMultiplier = 1;
	
	public static void start() {
		System.out.println("\nScoring sentences...\n");
		
		for (Paragraph paragraph : ReferenceMaterial.getParagraphList()) {
			double paragraphStatus = 0;

			for (Sentence sentence : paragraph.getSentences()) {
				scoreSentences(paragraph, sentence);	
				paragraphStatus = ((double)(paragraph.indexOf(sentence) + 1) / (double)(paragraph.getSentences().size())) * 100;
				sentenceMultiplier = 1;
				System.out.format("\nParagraph " + (ReferenceMaterial.indexOf(paragraph) + 1) + " - " + "%.2f" + " percent complete.\n", paragraphStatus);
			}
			
			renderOutput(paragraph);
			System.out.format("\nTarget Score: %.2f, Average Score: %.2f, Average Sentence Length: %.2f" +  "\n", paragraph.getTargetScore(), paragraph.getAverageScore(),paragraph.getAverageLength());
		}
		
		System.out.println("\nScoring complete...\n");
	}
	
	public static void scoreSentences(Paragraph paragraph, Sentence sentence) {
		if ((sentence.getLength() > paragraph.getAverageLength()) || (paragraph.getSentences().size() == 1)) {
			sentenceMultiplier -= .25;
		}
		scoreWords(sentence);
		sentence.setMultiplier(sentenceMultiplier);
	}
	
	public static void scoreWords(Sentence sentence) {
		double properNounScore = 0, properNouns = 0;
		TagReader tr = new TagReader();
		
		for (Word word : sentence.getWords()) {
			if (tr.isAcceptedPOS(word)) {
				if (word.getPartOfSpeech().contains("NNP")) properNouns++;
				compareWords(sentence, word);
			}
		}
		
		properNounScore = properNouns/sentence.getLength();
		if ((properNounScore > 0.50) && (properNounScore != 0)) sentenceMultiplier -= .125;
		
		sentence.setWordsScore(sentenceScore);
	}
	
	public static void compareWords(Sentence sentence, Word word) {
		double wordScore = 0, wordPoolCount = 0, wordPoolRatio = 0;
		
		for (Word w : WordPool.getWordList()) {
			try {
				if (word.getWord().equals(w.getWord())) {
					w.setCount(w.getCount() + 1);
					wordPoolCount++;
				}
				wordScore = WordNet.getHypernymDepth(w.getWord(), word.getWord());
				if (wordScore != 0) sentenceScore += wordScore;
				if (wordScore == 1.0) wordPoolCount++;
			} catch (JWNLException e) {
				e.printStackTrace();
			}
		}
		
		wordPoolRatio = wordPoolCount/sentence.getLength(); 
		if ((wordPoolRatio > .5) && (wordPoolRatio != 0.0)) sentenceMultiplier -= .125;
	}
	
	public static void renderOutput(Paragraph paragraph) {
		for (Sentence sentence : paragraph.getSentences()) {
			if (sentence.getMultipliedScore() <= paragraph.getAverageScore()) {
				output += sentence.getSentence() +  "\n";
			}
		}
		output = output + "\n";
	}
	
	public static void writeFile(String filename, String output) throws IOException {	
		File myFile = new File("Summarized_".concat(filename));
		PrintWriter out = new PrintWriter(new FileWriter(myFile), false);
		out.println(output);
		out.close();
	}
}