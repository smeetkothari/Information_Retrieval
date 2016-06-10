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
public class TFIDF implements Comparable<TFIDF>{
        private Number numOfOccurrences;
        private Number totalTermsInDocument;
        private Number totalDocuments;
        private Number numOfDocumentsWithTerm;
        
        public TFIDF(Number occ, Number totTerms, Number totDocs, Number docsWithTerms) {
                numOfOccurrences = occ;
                totalTermsInDocument = totTerms;
                totalDocuments = totDocs;
                numOfDocumentsWithTerm = docsWithTerms;
        }

    public void setNumOfOccurrences(int numOfOccurrences) {
        this.numOfOccurrences = numOfOccurrences;
    }
        
        /**
         * Calculates the tf-idf value of the current term. For description of tf-idf 
         * refer to <a href="http://en.wikipedia.org/wiki/Tfidf">^ wikipedia.org/Tfidf</a> <br />
         * Because there can be many cases where the current term is not present in any other 
         * document in the repository, Float.MIN_VALUE is added to the denominator to avoid
         * DivideByZero exception
         * @return
         */
        public Float getValue(){
                float tf = numOfOccurrences.floatValue() /  totalTermsInDocument.floatValue();
                float idf = (float) Math.log10(totalDocuments.floatValue() /  numOfDocumentsWithTerm.floatValue());
                return (tf * idf * 1000);
        }
        
        public int getNumOfOccurrences() {
                return this.numOfOccurrences.intValue();
        }

    public void setTotalTermsInDocument(int totalTermsInDocument) {
        this.totalTermsInDocument = totalTermsInDocument;
    }
        
        public String toString() {
                //return this.getValue().toString();
                
             return "numOfOccurrences : " + this.numOfOccurrences.intValue() + "\n"
                      + "totalTermsInDocument : " + this.totalTermsInDocument.intValue() + "\n"
                      + "numOfDocumentsWithTerm : " + this.numOfDocumentsWithTerm.intValue() + "\n"
                      + "totalDocuments : " + this.totalDocuments.intValue() ;
                   
                        
        }
        
        @Override
        public int compareTo(TFIDF o) {
                return (int) ((this.getValue() - o.getValue()) * 100);
        }
        
}


