package com.extraction;
import java.io.*;
import java.util.*;

import com.objects.*;
import com.objects.collections.ReferenceMaterial;

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


public class ReferenceReader {
	static File myFile;
	
	public static void start() throws IOException {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter filename: ");
		String filename = scan.nextLine();
		scan.close();
		try {
			parseFile(filename);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void parseFile(String filename) throws IOException, ClassNotFoundException {
		myFile = new File(filename);
		BufferedReader in = new BufferedReader(new FileReader(myFile));
		String text;
		
		while((text = in.readLine()) != null) { parseString(text); }
		in.close();
	}
	
	public static void parseString(String text) {
		String s[] = text.split("\n");
		for (int i = 0; i < s.length; i++) {
			if (s[i].contains("\n")) s[i].replace("\n", "");
			if (s[i].length() > 0) ReferenceMaterial.add(new Paragraph(s[i]));
		}
	}
	
	public String getParagraphs() {
		String s = "";
		int ctr = 1;
		for (Paragraph p : ReferenceMaterial.getParagraphList()) {
			s = s + ctr + ") " + p.toString() + "\n";
			ctr++;
		}
		return s;
	}
	
	public void showParsing() {
		for (Paragraph p : ReferenceMaterial.getParagraphList()) {
			for (Sentence s : p.getSentences()) {
				System.out.println(s.toString());
				for (Word w : s.getWords()) System.out.print(w.getWord() + " " + w.getCount() + " | ");
			}
			System.out.println("\n");
		}
	}
}