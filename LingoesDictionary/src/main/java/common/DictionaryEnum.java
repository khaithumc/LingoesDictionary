/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author huyvi
 */
public enum DictionaryEnum {
    ONLY_EN_VI ("en_vi", "OnlyEVVocabularies.txt"),
    EN_NATIONS ("en_nations", "NationsVocabularies.txt");

    private String path;
    private String name;
    private String vocabularyPath;
    
    private DictionaryEnum(String name, String vocabularyFilename) {
        this.name = name;
        this.path = getClass().getResource("/documents/" + name).getPath();
        this.vocabularyPath = getClass().getResource("/documents/" + vocabularyFilename).getPath();
    }
    
    public String getPath(){
        return this.path;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getVocabularyPath(){
        return vocabularyPath;
    }
}
