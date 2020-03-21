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
public class OnlyEVTypeOfWord {
    private String type;
    private List<OnlyEVMeaningOfWord> means;

    public OnlyEVTypeOfWord() {
    }

    public OnlyEVTypeOfWord(String type, List<OnlyEVMeaningOfWord> means) {
        this.type = type;
        this.means = means;
    }

    public List<OnlyEVMeaningOfWord> getMeans() {
        return means;
    }

    public String getType() {
        return type;
    }

    public void setMeans(List<OnlyEVMeaningOfWord> means) {
        this.means = means;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        
        if(obj == null){
            return false;
        }
        
        OnlyEVTypeOfWord tmp = (OnlyEVTypeOfWord) obj;
        
        return tmp.getMeans() == this.means;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.means);
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder();
        tmp.append("Từ loại: ").append(this.type).append("\n");
        means.forEach(mow -> tmp.append(mow.toString()));
        
        return tmp.toString();
    }
    
    public String toHTMLString(){
        StringBuilder tmp = new StringBuilder();
        tmp.append("<p style=\"color:red;font-size:16px;font-family:tahoma\"><b>Từ loại:</b>").append(this.type).append("</p>");
        means.forEach(mow -> tmp.append(mow.toHTMLString()));
        
        return tmp.toString();
    }
    
}
