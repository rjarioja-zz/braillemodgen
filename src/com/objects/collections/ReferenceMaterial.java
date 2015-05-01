package com.objects.collections;
import java.util.LinkedList;
import com.objects.Paragraph;

/**
*
* @author Ryan Arioja, Theodore Merin, Kevin Zurita
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

public class ReferenceMaterial {
	private static LinkedList<Paragraph> paragraphs = new LinkedList<Paragraph>();

	public static void add(Paragraph paragraph) {
		paragraphs.add(paragraph);
	}
	
	public static LinkedList<Paragraph> getParagraphList() {
		return paragraphs;
	}
	
	public static String getParagraphs() {
		String s = "";
		for (Paragraph p : paragraphs) s = s + p.toString() + "\n";
		return s;
	}
	
	public static Paragraph getParagraph(int index) {
		return paragraphs.get(index);
	}
	
	public static int indexOf(Paragraph paragraph) {
		return paragraphs.indexOf(paragraph);
	}
	
	public static boolean isEmpty() {
		return paragraphs.isEmpty();
	}
	
	public String toString() {
		String s = "";
		int ctr = 1;
		for (Paragraph p : paragraphs) {
			s = s + ctr + ") " + p.toString() + "\n";
			ctr++;
		}
		return s;
	}
}