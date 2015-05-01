package com.extraction;
import java.io.*;
import java.net.URL;
import java.util.*;

import com.wordnet.WordNetHelper;

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

public class Keyphrase {
	
	private static TagReader tr = new TagReader();
	
	public static void tagger() throws IOException {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter filename: ");
		String filename = scan.nextLine();
		scan.close();
		
		try {
			tr.tagFile(filename);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void start(String filename) throws IOException {
		WordNetHelper.initialize(URL.class.getResourceAsStream("/com/resources/file_properties.xml"));
		try {
			tr.tagFile(filename);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}