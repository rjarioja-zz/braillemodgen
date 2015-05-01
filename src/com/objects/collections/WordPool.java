package com.objects.collections;
import java.util.*;

import com.objects.Word;

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

public class WordPool {
	
	private static LinkedList<Word> wordpool = new LinkedList<Word>();

	public static void add(Word word) {
		boolean isUnique = true;
		for (Word w : wordpool) {
			if (w.getWord().equals(word.getWord())) isUnique = false;
		}
		if (isUnique) wordpool.add(word);
	}
	
	public static LinkedList<Word> getWordList() {
		return wordpool;
	}
	
	public static String getWords() {
		String s = "";
		for (Word w : wordpool) s = s + w.toString() + "\n";
		return s;
	}
	
	public static Word getWord(int index) {
		return wordpool.get(index);
	}
	
	public static boolean isEmpty() {
		return wordpool.isEmpty();
	}
	
	public String toString() {
		String s = "";
		int ctr = 1;
		for (Word w : wordpool) {
			s = s + ctr + ") " + w.toString() + "\n";
			ctr++;
		}
		return s;
	}
}