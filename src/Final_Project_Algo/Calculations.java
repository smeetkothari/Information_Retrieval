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
 public class Calculations{
    private int difference;
    private Float score;
    private String name;
    private int num;
    private int min;
    private int max;
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
    
    
    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
        public int getDiff() {
            return difference;
        }

        public void setDiff(int diff) {
            this.difference = diff;
        }

        public Float getScore() {
            return score;
        }

        public void setScore(Float score) {
            this.score = score;
        }

    @Override
    public String toString() {
        return "calculate{" + "diff=" + difference + ", score=" + score + ", name=" + name + ", num=" + num + ", min=" + min + ", max=" + max + ", word=" + word + '}';
    }
   
    }