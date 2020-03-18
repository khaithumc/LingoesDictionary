package entities;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author huyvi
 */
public class Word {
    private String vocabulary;
    private List<TypeOfWord> typeOfWords;
    private String sameWord;

    public void setSameWord(String sameWord) {
        this.sameWord = sameWord;
    }

    public void setTypeOfWord(List<TypeOfWord> typeOfWords) {
        this.typeOfWords = typeOfWords;
    }

    public void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
    }

    public String getSameWord() {
        return sameWord;
    }

    public List<TypeOfWord> getTypeOfWords() {
        return typeOfWords;
    }

    public String getVocabulary() {
        return vocabulary;
    }

    public Word() {
    }
    
    public Word(String vocabulary, List<TypeOfWord> typeOfWords, String sameWord) {
        this.vocabulary = vocabulary;
        this.typeOfWords = typeOfWords;
        this.sameWord = sameWord;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.vocabulary);
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder();
        tmp.append("Từ: ").append(this.vocabulary).append("\n");
        if(this.sameWord != null){
            tmp.append("* Từ đồng nghĩa: ").append(this.sameWord).append("\n");
        } 
        if(typeOfWords != null){
            typeOfWords.forEach(typeOfWord -> tmp.append(typeOfWord));
        }
        
        return tmp.toString();
    }
    
    public String toHTMLString(){
        StringBuilder tmp = new StringBuilder();
//        tmp.append("<p style=\"color:blue;font-size:20px;font-family:tahoma\"><b>Từ: ").append(vocabulary).append("</b></p>");
        if(sameWord != null){
            tmp.append("<p style=\"color:green;font-size:14px;font-family:tahoma\">* Xem từ đồng nghĩa: ").append(sameWord).append("</p>");
        }
        if(typeOfWords != null){
            typeOfWords.forEach(type -> tmp.append(type.toHTMLString()));
        }
        
        return tmp.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        
        if(obj == null){
            return false;
        }
        
        Word tmp = (Word) obj;
        
        return tmp.getVocabulary().equalsIgnoreCase(this.vocabulary);
    }
}
