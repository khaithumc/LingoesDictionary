/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author huyvi
 */
public class MeaningOfWord {
    private String meaning;
    private List<String> examples;
    private List<String> attachedPrepositions;

    public MeaningOfWord() {
    }

    public MeaningOfWord(String meaning, List<String> examples, List<String> attachedPrepositions) {
        this.meaning = meaning;
        this.examples = examples;
        this.attachedPrepositions = attachedPrepositions;
    }

    public List<String> getExamples() {
        return examples;
    }

    public String getMeaning() {
        return meaning;
    }

    public List<String> getAps() {
        return attachedPrepositions;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setAps(List<String> attachedPrepositions) {
        this.attachedPrepositions = attachedPrepositions;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        
        if(obj == null){
            return false;
        }
        
        MeaningOfWord tmp = (MeaningOfWord) obj;
        
        return tmp.meaning.equals(this.meaning);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.meaning);
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder();
        tmp.append("Nghĩa: ").append(this.meaning);
        
        examples.forEach((example) -> {
            tmp.append("\n").append("Ví dụ: ").append(example);
        });
        
        attachedPrepositions.forEach((ap) -> {
            tmp.append("\n").append("Giới từ đi kèm: ").append(ap);
        });
        
        return tmp.toString();
    }
    
    
}
