/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Final_Project_Algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Smeet
 */
public class Search {

    Map<Integer, Map> linking_word;
    Map<Integer, Float> ranking = new HashMap<>();
    Map<Integer, List<Integer>> test1 = new HashMap<>();
    private List<String> stopwords;
    Index indexing;
    private Map<String, List<No_And_Position>> index;
    private List<String> files;

    private Map<File, Integer> terms;
    private ArrayList<File> fileList;
    private Map<String, Map> retrieveFile;
    private Map<Integer, String> retrieveWord;

    public Search() throws FileNotFoundException, IOException {
        ranking = new HashMap<>();
        test1 = new HashMap<>();
        indexing = Index.getMainMaps();
        stopwords = indexing.getStopwords();
        index = indexing.getIndex();
        files = indexing.getFiles();
        terms = indexing.getTerms();
        fileList = indexing.getFileList();
        linking_word = indexing.getWordLink();
        retrieveFile = indexing.getRetrieveFile();
        retrieveWord = indexing.getRetrieveWord();
    }
    
    //Search fucntion

    public HashMap search(List<String> words) {
        Set<Integer> cc = new HashSet<>();
        cc.addAll(linking_word.keySet());
        //System.out.println(cc.size());
        for (String _word : words) {
            Set<String> answer = new HashSet<String>();     //put input search word in SET
            Set<Integer> file = new HashSet<>();
            String word = _word.toLowerCase();     //converting to lower case for easy search
            List<No_And_Position> indexing = index.get(word);    //getting index of a word and storing it in LISt
            //System.out.println(idx);
            if (indexing != null) {
                file = new HashSet();             //store file in new HashSet

                
                
                // traversing index to get the no and position of the searched word
                
                for (No_And_Position t : indexing) {

                    file.add(t.getFileno());      
                    ArrayList<Integer> l = (ArrayList<Integer>) test1.get(t.getFileno());
                    if (l == null) {
                        l = new ArrayList<>();
                        l.add(t.getPosition());
                        test1.put(t.getFileno(), l);
                    } else {
                        l.add(t.getPosition());
                        test1.put(t.getFileno(), l);
                    }
                }
                
                
                //traversing related words in a document 
                
                for (int o : linking_word.keySet()) {

                    Map temp = linking_word.get(o);
                    System.out.println(o + "//" + word + "//" + temp.containsKey(word));
                    if (!temp.containsKey(word)) {
                        cc.remove(o);
                        System.out.println(o + word);
                    }
                }
                
                //  traversing the file and ranking as per the words occured and putting most relevant at top
                
                for (int o : file) {
                    Map temp = linking_word.get(o);

                    TFIDF t = (TFIDF) temp.get(word);
                    //System.out.println(word+"/"+o+"/"+t.getValue());
                    if (ranking.get(o) == null) {
                        ranking.put(o, t.getValue());
                    } else {
                        ranking.put(o, ranking.get(o) + t.getValue());
                    }
                }

            } else {
                System.out.println("nothing found");
            }
        }
        
        

        Map<Integer, Calculations> tm = new HashMap<>();
        if (words.size() > 1) {

            for (int i : ranking.keySet()) {

                ArrayList<Integer> l = (ArrayList<Integer>) test1.get(i);

                Collections.sort(l);    // calculating distance matrix in between the words
                int minDiff = 0;
                int min = l.get(0);
                int max = l.get(0);
                
                if (l.size() == 2) {
                    minDiff = l.get(1) - l.get(0);
                    min = l.get(0);
                    max = l.get(1);
                    
                } else if (l.size() >= 3) {
                    minDiff = l.get(2) - l.get(1);
                    min = 1;
                    max = 2;
                    for (int i2 = 2; i2 != l.size(); i2++) {
                        int t = l.get(i2) - l.get(i2 - 1);

                        if (t < minDiff) {
                            minDiff = t;
                              min = l.get(i2);
                            max = l.get(i2 - 1);
                        }

                    }
                }

                // calculating score of a word and setting the rank as per relevant 
                
                Calculations t2 = new Calculations();
                if (cc.contains(i)) {
                    t2.setDiff(minDiff);
                } else {
                    t2.setDiff(0);
                }
                t2.setScore(ranking.get(i));
                t2.setName(files.get(i));
                t2.setNum(i);
                t2.setMax(max);
                t2.setMin(min);

                tm.put(i, t2);

            }
        }
        
        

        Set set1 = ranking.entrySet();
        // Get an iterator
        Iterator i1 = set1.iterator();
        // Display elements
        while (i1.hasNext()) {
            Map.Entry me = (Map.Entry) i1.next();

            int ii = (int) me.getKey();
            if (!tm.containsKey(ii)) {
                Calculations t2 = new Calculations();
                t2.setDiff(0);
                t2.setScore(ranking.get(ii));
                t2.setName(files.get(ii));
                t2.setNum(ii);
                tm.put(ii, t2);
            }
            //System.out.println(files.get(ii));

        }
        
        
        //searching word in file and making the entry in the file using iterator and returning hashmap 
         // which will hold the result of new calculated score in a file.

        for (String _word : words) {
            String word = _word.toLowerCase();
            List<No_And_Position> lt = index.get(word);
            if (lt != null) {
                for (No_And_Position tt : lt) {
                    if (tm.containsKey(tt.getFileno())) {

                    }
                }
            }
            Set set = tm.entrySet();
            // Get an iterator
            Iterator i = set.iterator();

            // Display elements
            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();
                int itm = (int) me.getKey();
                Calculations t = (Calculations) me.getValue();

            }
        }
        return (HashMap) tm;

    }
}
