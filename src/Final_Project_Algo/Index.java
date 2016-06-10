/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Final_Project_Algo;

/**
 *
 * @author Smeet
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Index {

    private static Index indexing;                     //singleton class
    private List<String> stopwords;

    private Map<String, List<No_And_Position>> index;
    private List<String> files;
    private Map<String, Map> retrieveFile;
    private Map<String, Map> retrieveLine ;
    private Map<Integer, String> retrieveWord ;
    private Map<File, Integer> terms;
    private ArrayList<File> fileList;
    private Map<Integer, Map> linking_word;
    
    
    
    public Index() throws IOException { 
        index = new HashMap<String, List<No_And_Position>>();
        files = new ArrayList<String>();
        linking_word = new HashMap<>();
        terms = new HashMap<>();
        fileList = new ArrayList<>();
         retrieveFile = new HashMap<>();
         
         retrieveLine = new HashMap<>();
         
         // list of stopwords 
        stopwords = Arrays.asList("a", "able", "about",
                "across", "after", "all", "almost", "also", "am", "among", "an",
                "and", "any", "are", "as", "at", "be", "because", "been", "but",
                "by", "can", "cannot", "could", "dear", "did", "do", "does",
                "either", "else", "ever", "every", "for", "from", "get", "got",
                "had", "has", "have", "he", "her", "hers", "him", "his", "how",
                "however", "i", "if", "in", "into", "is", "it", "its", "just",
                "least", "let", "like", "likely", "may", "me", "might", "most",
                "must", "my", "neither", "no", "nor", "not", "of", "off", "often",
                "on", "only", "or", "other", "our", "own", "rather", "said", "say",
                "says", "she", "should", "since", "so", "some", "than", "that",
                "the", "their", "them", "then", "there", "these", "they", "this",
                "tis", "to", "too", "twas", "us", "wants", "was", "we", "were",
                "what", "when", "where", "which", "while", "who", "whom", "why",
                "will", "with", "would", "yet", "you", "your");
    }

    public static Index getMainMaps() throws UnsupportedEncodingException, FileNotFoundException, IOException {
        if (indexing == null) {
            indexing = new Index();

           
        }
       
        return indexing;
    }
    
    //getting the index from a file from the directory test_dir
    
public void index() throws IOException{
    String target_dir = "./input_dir";
        File dir = new File(target_dir);
        File[] files= dir.listFiles();
        
        for(File f: files){
            
            indexing.indexFile(f);
        }
         
        
      //calculating the score
        
CalculateScores cs = new CalculateScores();
            cs.calcScore();
}
    public void indexFile(File file) throws IOException {
        
        fileList.add(file);
     
        int fileno = files.indexOf(file.getPath());
        if (fileno == -1) {
            files.add(file.getPath());
            fileno = files.size() - 1;
        }
        retrieveWord = new HashMap<>();
        int pos = 0;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        for (String line = reader.readLine(); line != null; line = reader
                .readLine()) {
            for (String _word : line.split("\\W+")) {
                String regex = _word.replaceAll("[!@#$%^&*(){}:;\"'](1,)", "");
                String word = regex.toLowerCase();
                //System.out.println(word);
                pos++;
                retrieveWord.put(pos, line);               //getting the word and putting its pos and line in hashmap
                

                if (stopwords.contains(word)) {
                    continue;
                }
                
                List<No_And_Position> indexing = index.get(word);

                if (indexing == null) {

                    indexing = new LinkedList<No_And_Position>();
                    
                    index.put(word, indexing); //getting the index of a word and putting it in hashmap
                }

                indexing.add(new No_And_Position(fileno, pos));

            }
           // retrieveLine.put(line, retrieveWord);
        }
        //System.out.println(file.getPath());
        
        retrieveFile.put(file.getPath(), retrieveWord);  //getting the file in which a particular word is present and putting in hashmap
        
        terms.put(file, pos); //getting the term and its pos and putting it in hashmap
        
        System.out.println("indexed " + file.getPath() + " " + pos + " words");
        

    }

    public List<String> getStopwords() {
        return stopwords;
    }

    public void setStopwords(List<String> stopwords) {
        this.stopwords = stopwords;
    }

    public Map<String, List<No_And_Position>> getIndex() {
        return index;
    }

    public void setIndex(Map<String, List<No_And_Position>> index) {
        this.index = index;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public Map<File, Integer> getTerms() {
        return terms;
    }

    public void setTerms(Map<File, Integer> terms) {
        this.terms = terms;
    }

    public Map<String, Map> getRetrieveLine() {
        return retrieveLine;
    }

    public void setRetrieveLine(Map<String, Map> retrieveLine) {
        this.retrieveLine = retrieveLine;
    }

    public ArrayList<File> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<File> fileList) {
        this.fileList = fileList;
    }

    public Map<Integer, Map> getWordLink() {
        return linking_word;
    }

    public void setWordLink(Map<Integer, Map> wordLink) {
        this.linking_word = wordLink;
    }

    public Map<String, Map> getRetrieveFile() {
        return retrieveFile;
    }

    public void setRetrieveFile(Map<String, Map> retrieveFile) {
        this.retrieveFile = retrieveFile;
    }

   

    public Map<Integer, String> getRetrieveWord() {
        return retrieveWord;
    }

    public void setRetrieveWord(Map<Integer, String> retrieveWord) {
        this.retrieveWord = retrieveWord;
    }
    
}
