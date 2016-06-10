/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Final_Project_Algo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Smeet
 */
public class CalculateScores {

    Index indexing;
    Map<Integer, Map> wordLink;
    private List<String> stopwords;

    private Map<String, List<No_And_Position>> index;
    private List<String> files;

    private Map<File, Integer> terms;
    private ArrayList<File> fileList;
    
     private Map<String, Map> retrieveFile ;
    private Map<Integer, String> retrieveWord ;

    public CalculateScores() throws FileNotFoundException, IOException {

        indexing = Index.getMainMaps();
        wordLink = indexing.getWordLink();
        stopwords = indexing.getStopwords();
        index = indexing.getIndex();
        files = indexing.getFiles();
        terms = indexing.getTerms();
        fileList = indexing.getFileList();
        retrieveFile = indexing.getRetrieveFile();
        retrieveWord = indexing.getRetrieveWord();
    }

    public void calcScore() throws FileNotFoundException, IOException {

        String word = "";
        for (File f : fileList) {
            int tot = terms.get(f);

            int count = 0;
            // System.out.println(f.getName());
            Map<String, TFIDF> wordScore = new HashMap<>(); //HashMap for calculating score of word
            BufferedReader reader = new BufferedReader(new FileReader(f));
            for (String line = reader.readLine(); line != null; line = reader
                    .readLine()) {
                for (String _word : line.split("\\W+")) {

                    word = _word.toLowerCase();

                    int pos = 1;
                    count++;
                    if (stopwords.contains(word)) {
                     
                        continue;
                    }
                    List<No_And_Position> indexing = index.get(word);  //List for getting no and position in a file
                    //System.out.println(count);
                    if (wordScore.get(word) == null) {

                       
                        Set fileno = new HashSet();   // Putting file no in new Hash Set 
                       
                        for (No_And_Position t : indexing) {
                            fileno.add(t.getFileno());
                        }

                        TFIDF tf = new TFIDF(pos, tot, 20, fileno.size());  //calculates TFIDF parameters
                      
                        wordScore.put(word, tf);    //putting word score and  term score in hashmap 
                       
                    } else {
                        TFIDF tf = wordScore.get(word);
                        int t1 = tf.getNumOfOccurrences();
                        t1++;
                        tf.setNumOfOccurrences(t1);   //Setting no of times word has came

                        wordScore.replace(word, tf);    //if a searched word appeares more times replace it witht he new word
                        
                        TFIDF tt = wordScore.get(word);    // calculate score
                        
                    }
                }
            }
            wordLink.put(files.indexOf(f.getPath()), wordScore);   

        }

    }

}
