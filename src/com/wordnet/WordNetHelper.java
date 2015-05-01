package com.wordnet;
import java.io.*;
import java.util.*;

import net.didion.jwnl.*;
import net.didion.jwnl.data.*;
import net.didion.jwnl.data.list.*;
import net.didion.jwnl.data.relationship.*;
import net.didion.jwnl.dictionary.*;
import net.didion.jwnl.dictionary.Dictionary;

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


public class WordNetHelper {
	public static HashMap<String, String> allWords = null;
	public static Dictionary wordnet;
	private static MorphologicalProcessor morph;
	static boolean isInitialized = false;

	public static void initialize(String propsFile) throws FileNotFoundException {
		WordNetHelper.initialize(new FileInputStream(propsFile));
	}
	
    public static void initialize(InputStream propsFile) {
        allWords = new HashMap<String, String>();
    	try {
            JWNL.initialize(propsFile);
        } catch (JWNLException e) {
            e.printStackTrace();
        }
        wordnet = Dictionary.getInstance();
        morph = wordnet.getMorphologicalProcessor();
        isInitialized = true;
    }
    
    public void unload() {
    	wordnet.close();
    	Dictionary.uninstall();
    	JWNL.shutdown();
    }

    public static POS[] getPOS(String s) throws JWNLException {
        IndexWordSet set = wordnet.lookupAllIndexWords(s);
        IndexWord[] words = set.getIndexWordArray();
        POS[] pos = new POS[words.length];
        
        for (int i = 0; i < words.length; i++) {
            pos[i] = words[i].getPOS();
        }
        return pos;
    }
    
    public static ArrayList<Synset> getRelated(IndexWord word, PointerType type) throws JWNLException {
        try {
            Synset[] senses = word.getSenses();
            for (int i = 0; i < senses.length; i++) {
                ArrayList<Synset> a = getRelated(senses[i],type);
                if (a != null && !a.isEmpty()) return a;
            }
        } catch (NullPointerException e) {
             System.out.println("Oops, NULL problem: " + e);
        }
        return null;
    }

    public static ArrayList<Synset> getRelated (Synset sense, PointerType type) throws JWNLException, NullPointerException {
        PointerTargetNodeList relatedList = null;
        
        if (type == PointerType.HYPERNYM) relatedList = PointerUtils.getInstance().getDirectHypernyms(sense);
        else if (type == PointerType.HYPONYM) relatedList = PointerUtils.getInstance().getDirectHyponyms(sense);
        else if (type == PointerType.SIMILAR_TO) relatedList = PointerUtils.getInstance().getSynonyms(sense);
        else if (type == PointerType.ANTONYM) relatedList = PointerUtils.getInstance().getAntonyms(sense);
        else if (type == PointerType.MEMBER_MERONYM) relatedList = PointerUtils.getInstance().getMemberMeronyms(sense);
        else if (type == PointerType.DERIVED) relatedList = PointerUtils.getInstance().getDerived(sense);

        Iterator<?> i = relatedList.iterator();
        ArrayList<Synset> a = new ArrayList<Synset>();
        
        while (i.hasNext()) {
            PointerTargetNode related = (PointerTargetNode) i.next();
            Synset s = related.getSynset();
            a.add(s);
        }
        return a;
    }

    public static Relationship getRelationship (IndexWord start, IndexWord end, PointerType type) throws JWNLException {
        Synset[] startSenses = start.getSenses();
        Synset[] endSenses = end.getSenses();
        
        for (int i = 0; i < startSenses.length; i++) {
            for (int j = 0; j < endSenses.length; j++) {
                RelationshipList list = RelationshipFinder.getInstance().findRelationships(startSenses[i], endSenses[j], type);
                if (!list.isEmpty())  {
                    return (Relationship) list.get(0);
                }
            }
        }
        return null;
    }

    public static ArrayList<Synset> getRelationshipSenses (Relationship rel) throws JWNLException {
        ArrayList<Synset> a = new ArrayList<Synset>();
        PointerTargetNodeList nodelist = rel.getNodeList();
        Iterator<?> i = nodelist.iterator();
        
        while (i.hasNext()) {
            PointerTargetNode related = (PointerTargetNode) i.next();
            a.add(related.getSynset());
        }
        return a;
    }

    public static IndexWord getWord(POS pos, String s) throws JWNLException {
        IndexWord word = wordnet.getIndexWord(pos,s);
        return word;
    }
    
    public static void showRelatedTree(IndexWord word, int depth, PointerType type) throws JWNLException {
        showRelatedTree(word.getSense(1), depth, type);
    }

    public static void showRelatedTree(Synset sense, int depth, PointerType type) throws JWNLException {
        PointerTargetTree relatedTree;

        if (type == PointerType.HYPERNYM) relatedTree = PointerUtils.getInstance().getHypernymTree(sense,depth);
        else if (type == PointerType.HYPONYM) relatedTree = PointerUtils.getInstance().getHyponymTree(sense,depth);
        else relatedTree = PointerUtils.getInstance().getSynonymTree(sense,depth);  
        relatedTree.print();
    }
    
    public static String stemmer(String word)	{
		if (!isInitialized)	return word;
		if (word == null) return null;
		if (morph == null) morph = wordnet.getMorphologicalProcessor();
		
		IndexWord w;
		try	{
			w = morph.lookupBaseForm( POS.VERB, word );
			if ( w != null ) return w.getLemma().toString ();
			w = morph.lookupBaseForm( POS.NOUN, word );	
			if ( w != null ) return w.getLemma().toString();
			w = morph.lookupBaseForm( POS.ADJECTIVE, word );
			if ( w != null ) return w.getLemma().toString();
			w = morph.lookupBaseForm( POS.ADVERB, word );
			if ( w != null ) return w.getLemma().toString();
		} 
		catch ( JWNLException e ) {}
		return null;
	}
}
