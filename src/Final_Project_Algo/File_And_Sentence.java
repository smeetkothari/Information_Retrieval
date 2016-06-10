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
public class File_And_Sentence {
    private String file;
    private String sentence;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    @Override
    public String toString() {
        return "file=" + file + ", sentence=" + sentence ;
    }
    
}
