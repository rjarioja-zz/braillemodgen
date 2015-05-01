package com.extraction;
import java.io.*;
import java.text.BreakIterator;
import java.util.*;

import com.objects.*;
import com.objects.collections.WordPool;

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

enum AcceptedPOS {
	NNPS, NNP, NNS, NN,
	JJR, JJS, JJ;
}

public class TagReader {
	File myFile;
	
	public void tagFile(String filename) throws IOException, ClassNotFoundException {
        myFile = new File(filename);
		BufferedReader in = new BufferedReader(new FileReader(myFile));
		
		String str;
		System.out.println("\nTagging course outline...\n");
		while ((str = in.readLine()) != null) if (!str.contains("~~")) tagString(str);
		System.out.println("\nTagging complete.\n");
		in.close();
		System.out.println("Creating WordPool...");
    }
	
	public void tagString(String text) {
		BreakIterator wordIterator = BreakIterator.getWordInstance(Locale.US);
		wordIterator.setText(text);
		
		int start = wordIterator.first();
		int end = wordIterator.next();
		
		while (end != BreakIterator.DONE) {
			String word = text.substring(start,end);
			if (Character.isLetterOrDigit(word.charAt(0))) {
				Word w = new Word(word);
				if (isAcceptedPOS(w)) WordPool.add(w);
			}
			start = end;
			end = wordIterator.next();
		}
    }
	
	public void writeFile(String filename, String output) throws IOException {	
		myFile = new File("Tagged".concat(filename));
		PrintWriter out = new PrintWriter(new FileWriter(myFile), false);
		out.println(output);
		out.close();
	}
	
	public boolean isAcceptedPOS(Word word) {
		boolean val = false;
		for (AcceptedPOS a : AcceptedPOS.values()) if (word.getPartOfSpeech().contains(a.name())) val = true;
		return val;
	}
}