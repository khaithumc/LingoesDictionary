/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.MeaningOfWord;
import entities.TypeOfWord;
import entities.Word;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huyvi
 */
public class WordDaoImpl implements WordDao{

    private List<Word> words;

    public WordDaoImpl() {
    }
    
    @Override
    public List<Word> getWords(File file) {
        words = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    new FileInputStream(file), "UTF-8"));
 
		String line;
                // word
                Word word = null;
                List<TypeOfWord> typeOfWords = null;
                TypeOfWord typeOfWord = null;
                List<MeaningOfWord> mows = null;
                MeaningOfWord meaningOfWord = null;
                String vocabulary = null;
                
		while ((line = in.readLine()) != null) {
                    String curVocabulary = getVocabulary(line);
                    
                    if(!curVocabulary.equals(vocabulary)){
                        // nếu từ hiện tại đang xét không trùng với từ ở hàng trên thì tạo từ mới
                        setAllToNull(word, typeOfWords, typeOfWord, mows, meaningOfWord, vocabulary);
                        word = new Word();
                        vocabulary = curVocabulary;
                        word.setVocabulary(vocabulary);
                        
                        // nếu str kết thúc bằng '::' thì nó chỉ đến 1 từ đồng nghĩa
                        if(line.endsWith("::")){
                            word.setSameWord(getSameWord(line));
                        } else {
                            typeOfWords = new ArrayList<>();
                            typeOfWord = new TypeOfWord();
                            typeOfWord.setType(getType(line));
                            mows = new ArrayList<>();
                            meaningOfWord = setDataForMOW(line);
                            mows.add(meaningOfWord);
                            typeOfWord.setMeans(mows);
                            typeOfWords.add(typeOfWord);
                            word.setTypeOfWord(typeOfWords);
                        }
                        
                        words.add(word);
                    } else {
                        // nếu từ hiện tại trùng với từ đã có thì xét tiếp 2 trường hợp
                        String type = getType(line);
                        
                        // nếu từ đã tồn tại nhưng chưa tạo list loại từ thì khởi tạo
                        if(typeOfWords == null){
                            typeOfWords = new ArrayList<>();
                            word.setTypeOfWord(typeOfWords);
                            // khởi tạo 1 biến typeOfWord tạm thời nhưng không thêm vào list typeOfWords
                            typeOfWord = new TypeOfWord("none", null);
                        }
                        
                        if(!type.equals(typeOfWord.getType())){
                            
                            // nếu từ đã có và khác loại từ thì tạo ra loại từ mới và thêm vào list
                            typeOfWord = new TypeOfWord();
                            typeOfWord.setType(type);
                            mows = new ArrayList<>();
                            meaningOfWord = setDataForMOW(line);
                            mows.add(meaningOfWord);
                            typeOfWord.setMeans(mows);
                            typeOfWords.add(typeOfWord);
                        } else {
                            // nếu từ đã có và từ loại trùng thì ta thêm nghĩa mới vào list meaningOfWord của từ loại
                            meaningOfWord = setDataForMOW(line);
                            mows.add(meaningOfWord);
                        }
                    }
		}
 
                in.close();
	    }
        catch (UnsupportedEncodingException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return words;
    }
    
    private String getMeaning(String line){
        return line.substring((line.indexOf(":: ") + 3));
    }
    
    private String getType(String line){
        return line.substring(line.indexOf('{') + 1, line.indexOf('}')).toUpperCase();
    }
    
    private String getDescription(String line){
        return line.substring(line.indexOf('('), line.indexOf(')') + 1);
    }
    
    private String getSameWord(String line){
        return line.substring(line.indexOf("SEE: ") + 4, line.indexOf("::"));
    }
    
    private String getVocabulary(String line){
        return line.substring(0, line.indexOf('{') - 1);
    }
    
    private MeaningOfWord setDataForMOW(String line){
        MeaningOfWord meaningOfWord = new MeaningOfWord();
        meaningOfWord = new MeaningOfWord();
        if(line.substring(0, line.indexOf(':')).contains("(")){
            meaningOfWord.setDescription(getDescription(line));
        }
        meaningOfWord.setMeaning(getMeaning(line));
        
        return meaningOfWord;
    }
    
    private void setAllToNull(Word word, List<TypeOfWord> typeOfWords, TypeOfWord typeOfWord, List<MeaningOfWord> mows, MeaningOfWord meaningOfWord, String vocabulary){
        word = null;
        typeOfWords = null;
        typeOfWord = null;
        mows = null;
        meaningOfWord = null;
        vocabulary = null;
    }
}
