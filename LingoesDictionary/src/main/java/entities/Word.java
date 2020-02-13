package entities;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author huyvi
 */
public class Word {
    private String vocabulary;
    private String pronounce;
    private List<TypeOfWord> typeOfWords;
    private String sameWord;

    public void setSameWord(String sameWord) {
        this.sameWord = sameWord;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
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

    public String getPronounce() {
        return pronounce;
    }

    public List<TypeOfWord> getTypeOfWords() {
        return typeOfWords;
    }

    public String getVocabulary() {
        return vocabulary;
    }

    public Word() {
    }
    
    public Word(String vocabulary, String pronounce, List<TypeOfWord> typeOfWords, String sameWord) {
        this.vocabulary = vocabulary;
        this.pronounce = pronounce;
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
        tmp.append("Từ: ").append(vocabulary)
                .append("\n").append("Phát âm: ").append(pronounce);
        if(sameWord != null){
            tmp.append("\nViết cách khác: ").append(sameWord);
        }
        typeOfWords.forEach(type -> tmp.append("\n").append(type.toString()));
        
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