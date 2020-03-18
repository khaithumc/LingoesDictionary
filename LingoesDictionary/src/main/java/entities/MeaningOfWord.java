/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author huyvi
 */
public class MeaningOfWord {
    private String description;
    private String meaning;

    public MeaningOfWord() {
    }

    public MeaningOfWord(String description, String meaning) {
        this.description = description;
        this.meaning = meaning;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
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
        tmp.append("    - ");
        if(description != null){
            tmp.append(description).append(" : "); 
        }
        tmp.append(this.meaning).append("\n");
        
        return tmp.toString();
    }
    
    public String toHTMLString(){
        StringBuilder tmp = new StringBuilder();
        tmp.append("<p style=\"font-size:14px;font-family:tahoma\">&emsp;- ");
                                
        
        if(description != null){
            tmp.append("<i>").append(description).append("</i> : ");
        }
        
        tmp.append(this.meaning).append("</p>");
        
        return tmp.toString();
    }
}
