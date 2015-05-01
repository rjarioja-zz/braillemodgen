package com.wordnet;
import java.util.*;

import net.didion.jwnl.*;
import net.didion.jwnl.data.*;
import net.didion.jwnl.data.relationship.*;

/**
*
* @author Kevin Zurita
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


public class WordNet {
    public static double getHypernymDepth(String word1, String word2) throws JWNLException {
    	POS[] pos1 = WordNetHelper.getPOS(word1);
    	POS[] pos2 = WordNetHelper.getPOS(word2);
    	Relationship rel;
    	POS partOfSpeech = null;
    	double depth = 0.0;
    	
    	if (pos1.length > 0) {
	        for (int i = 0; i < pos1.length; i++) {
	        	for (int j = 0; j < pos2.length; j++) {
	                if (pos1[i].getLabel().toUpperCase().compareTo(pos2[j].getLabel().toUpperCase()) == 0) {
	                    switch (pos1[i].getLabel().toUpperCase()) {
	                        case "NOUN":
	                            partOfSpeech = POS.NOUN;
	                            break;
	                        case "VERB":
	                        	partOfSpeech = POS.VERB;
	                            break;
	                        case "ADJECTIVE":
	                            partOfSpeech = POS.ADJECTIVE;
	                            break;
	                        case "ADVERB":
	                        	partOfSpeech = POS.ADVERB;
	                        	break;
	                    }
	                    
	                    IndexWord index1 = WordNetHelper.getWord(partOfSpeech, word1);
	                    IndexWord index2 = WordNetHelper.getWord(partOfSpeech, word2);
	                    if (partOfSpeech != null) {
	                    	if (index1 == null) index1 = WordNetHelper.getWord(partOfSpeech, WordNet.stem(word1));
	                    	if (index2 == null) index2 = WordNetHelper.getWord(partOfSpeech, WordNet.stem(word2));
	                    	if ((index1 != null) && (index2 != null)) {
	                    		rel = WordNetHelper.getRelationship(index1, index2, PointerType.HYPERNYM);
	                    		if (rel == null) break;
		                    	depth = rel.getDepth();
	                    	}
	                    }
	                }
	            }
	        }
	    }
    	return depth;
    }
    
    public void lookupPartsOfSpeech(String word) throws JWNLException {
    	POS[] pos = WordNetHelper.getPOS(word);

    	System.out.println("\nFinding parts of speech for " + word + "...");

        if (pos.length > 0) {
            for (int i = 0; i < pos.length; i++) System.out.println("POS: " + pos[i].getLabel());
        } else System.out.println(word + " cannot be found in WordNet");
    }

    public static LinkedList<IndexWord> listPartsOfSpeech(String word) throws JWNLException {
        LinkedList<IndexWord> o = new LinkedList<IndexWord>();
        System.out.println("\nFinding parts of speech for " + word + ".");
        POS[] pos = WordNetHelper.getPOS(word);
        if (pos.length > 0) {
            for (int i = 0; i < pos.length; i++) {
                switch (pos[i].getLabel().toUpperCase()) {
                    case "NOUN":
                        o.addLast(WordNetHelper.getWord(POS.NOUN, word));
                        break;
                    case "VERB":
                        o.addLast(WordNetHelper.getWord(POS.VERB, word));
                        break;
                    case "ADJECTIVE":
                        o.addLast(WordNetHelper.getWord(POS.ADJECTIVE, word));
                        break;
                    case "ADVERB":
                        o.addLast(WordNetHelper.getWord(POS.ADVERB, word));  
                        break;
                }
            }
        } 
        return o;
    }
    
    public static void lookupHypernyms(String word) throws JWNLException {
        POS[] pos = WordNetHelper.getPOS(word);

        if (pos.length > 0) {
            for (int i = 0; i < pos.length; i++) {
                System.out.println("Finding related related word(s) for the word \"" + word + "\" that is used as a " + pos[i].getLabel().toUpperCase() +".");
                switch (pos[i].getLabel().toUpperCase()) {
                    case "NOUN":
                        findRelatedWordsDemo(WordNetHelper.getWord(POS.NOUN, word), PointerType.HYPERNYM);
                        break;
                    case "VERB":
                        findRelatedWordsDemo(WordNetHelper.getWord(POS.VERB, word), PointerType.HYPERNYM);
                        break;
                    case "ADJECTIVE":
                        findRelatedWordsDemo(WordNetHelper.getWord(POS.ADJECTIVE, word), PointerType.SIMILAR_TO);
                        break;
                    case "ADVERB":
                        System.out.println();
                        System.out.println("There are no related word(s) found for the word \"" + word + "\" that is used as a " + pos[i].getLabel().toUpperCase() +".");
                        System.out.println();  
                        break;
                }
            }
        } 
    }
    
	public static void lookupHypernyms(String word1, String word2) throws JWNLException {
	    POS[] pos1 = WordNetHelper.getPOS(word1);
	    POS[] pos2 = WordNetHelper.getPOS(word2);
	    
	    if (pos1.length > 0) {
	        for (int i = 0; i < pos1.length; i++) {
	        	for (int j = 0; j < pos2.length; j++) {
	                if (pos1[i].getLabel().toUpperCase().compareTo(pos2[j].getLabel().toUpperCase()) == 0) {
	                    switch (pos1[i].getLabel().toUpperCase()) {
	                        case "NOUN":
	                            findRelationshipsDemo(WordNetHelper.getWord(POS.NOUN, word1), WordNetHelper.getWord(POS.NOUN, word2), PointerType.HYPERNYM);
	                            break;
	                        case "VERB":
	                            findRelationshipsDemo(WordNetHelper.getWord(POS.VERB, word1), WordNetHelper.getWord(POS.VERB, word2), PointerType.HYPERNYM);
	                            break;
	                        case "ADJECTIVE":
	                            findRelationshipsDemo(WordNetHelper.getWord(POS.ADJECTIVE, word1), WordNetHelper.getWord(POS.ADJECTIVE, word2), PointerType.SIMILAR_TO);
	                            break;
	                        case "ADVERB":
	                            System.out.println();
	                            System.out.println("There are no related word1(s) found for the word1 \"" + word1 + "\" that is used as a " + pos1[i].getLabel().toUpperCase() +".");
	                            System.out.println();  
	                            break;
	                    }
	                }
	            }
	        }
	    } 
	}
    
    public static void lookupHyponyms(String word) throws JWNLException {
        POS[] pos = WordNetHelper.getPOS(word);

        if (pos.length > 0) {
            for (int i = 0; i < pos.length; i++) {
                System.out.println("Finding related word(s) to the word \"" + word + "\" that is used as a " + pos[i].getLabel().toUpperCase() +".");
                switch (pos[i].getLabel().toUpperCase()) {
                    case "NOUN":
                        findRelatedWordsDemo(WordNetHelper.getWord(POS.NOUN, word), PointerType.HYPONYM);
                        break;
                    case "VERB":
                        System.out.println();
                        System.out.println("There are no related word(s) found for the word \"" + word + "\" that is used as a " + pos[i].getLabel().toUpperCase() +".");
                        System.out.println();
                        break;
                    case "ADJECTIVE":
                        findRelatedWordsDemo(WordNetHelper.getWord(POS.ADJECTIVE, word), PointerType.SIMILAR_TO);
                        break;
                    case "ADVERB":
                        System.out.println();
                        System.out.println("There are no related word(s) found for the word \"" + word + "\" that is used as a " + pos[i].getLabel().toUpperCase() +".");
                        System.out.println();  
                        break;
                }
            }
        } 
    }

	public static void lookupHyponyms(String word1, String word2) throws JWNLException {
        POS[] pos1 = WordNetHelper.getPOS(word1);
        POS[] pos2 = WordNetHelper.getPOS(word2);

        if (pos1.length > 0) {
            for (int i = 0; i < pos1.length; i++) {
                for (int j = 0; j < pos2.length; j++) {
                    if (pos1[i].getLabel().toUpperCase().compareTo(pos2[j].getLabel().toUpperCase()) == 0) {
	                    switch (pos1[i].getLabel().toUpperCase()) {
		                    case "NOUN":
		                        findRelationshipsDemo(WordNetHelper.getWord(POS.NOUN, word1), WordNetHelper.getWord(POS.NOUN, word2), PointerType.HYPONYM);
		                        break;
		                    case "VERB":
		                        System.out.println();
		                        System.out.println("There are no relationship word1(s) found for the word1 \"" + word1 + "and word1" + word2 + ".");
		                        System.out.println();
		                        break;
		                    case "ADJECTIVE":
		                        findRelatedWordsDemo(WordNetHelper.getWord(POS.ADJECTIVE, word1), PointerType.SIMILAR_TO);
		                        break;
		                    case "ADVERB":
		                        System.out.println();
		                        System.out.println("There are no relationship word1(s) found for the word1 \"" + word1 + "and word1" + word2 + ".");
		                        System.out.println();  
		                        break;
                        }        
                    } else System.out.println("There is no relationship.");
                }
            }
        } 
	}
	
    public void listGlosses(IndexWord word) throws JWNLException {
        System.out.println("\n\nDefinitions for " + word.getLemma() + ":");
        Synset[] senses = word.getSenses();
        for (int i = 0; i < senses.length; i++) {
            System.out.println(word + ": " + senses[i].getGloss());
        }    
    }

    public static void findRelatedWordsDemo(IndexWord w, PointerType type) throws JWNLException {
        System.out.println("\n\nI am now going to find related words for " + w.getLemma() + ", listed in groups by sense.");
        System.out.println("We'll look for relationships of type " + type.getLabel() + ".");
        
        ArrayList<?> a = WordNetHelper.getRelated(w,type);
        if (a.isEmpty()) {
            System.out.println("Hmmm, I didn't find any related words.");
        } else {
            for (int i = 0; i < a.size(); i++) {
                Synset s = (Synset) a.get(i);
                Word[] words = s.getWords();
                for (int j = 0; j < words.length; j++ ) {
                    System.out.print(words[j].getLemma());
                    if (j != words.length-1) System.out.print(", ");
                }
                System.out.println();
            }
        }
    }
    
    public static void findRelationshipsDemo(IndexWord start, IndexWord end, PointerType type) throws JWNLException {
        System.out.println("\n\nTrying to find a relationship between \"" + start.getLemma() + "\" and \"" + end.getLemma() +"\".");
        System.out.println("Looking for relationship of type " + type.getLabel() + ".");

        Relationship rel = WordNetHelper.getRelationship(start, end, type);
        if (rel != null) {
            System.out.println("The depth of this relationship is: " + rel.getDepth());
            System.out.println("Here is how the words are related: ");
            ArrayList<?> a = WordNetHelper.getRelationshipSenses(rel);
            System.out.println("Start: " + start.getLemma());
            
            for (int i = 0; i < a.size(); i++) {
                Synset s = (Synset) a.get(i);
                Word[] words = s.getWords();
                System.out.print(i + ": ");
                for (int j = 0; j < words.length; j++ ) {
                    System.out.print(words[j].getLemma());
                    if (j != words.length-1) System.out.print(", ");
                }
                System.out.println();
            }
        } else System.out.println("I could not find a relationship between these words!");
    }
    
    public static String stem(String word)	{
		String stemmedword = (String) WordNetHelper.allWords.get(word);
		
		if (stemmedword != null) return stemmedword;
		if (word.matches(".*\\d+.*")) stemmedword = null;
		else stemmedword = WordNetHelper.stemmer(word);
		
		if (stemmedword != null) {
			WordNetHelper.allWords.put(word, stemmedword);
			return stemmedword;
		}

		WordNetHelper.allWords.put(word, word);
		return word;
	}
    
    public Vector<String> stemmer(Vector<String> words)	{
		if (!WordNetHelper.isInitialized) return words;
		
		for ( int i = 0; i < words.size(); i++ ) words.set(i, stem((String)words.get(i)));
		return words;		
	}
}