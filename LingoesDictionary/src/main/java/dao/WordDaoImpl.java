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
        words = new ArrayList<>();
    }
    
    @Override
    public List<Word> getWords(File file) {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    new FileInputStream(file), "UTF-8"));
 
		String str;
                // word
                Word word = null;
                List<TypeOfWord> typeOfWords = null;
                TypeOfWord typeOfWord = null;
                List<MeaningOfWord> mows = null;
                MeaningOfWord meaningOfWord = null;
                List<String> examples = null;
                List<String> attachedPrepositions = null;
 
		while ((str = in.readLine()) != null) {
                    if(str.startsWith("@")){
                        word = new Word();
                        word.setVocabulary(str.substring(1, str.indexOf(" /")));
                        String tmpString = str.substring(str.indexOf('/'), str.lastIndexOf('/') + 1);
                        String[] tmpListString = tmpString.split(" ");
                        word.setPronounce(tmpListString[0]);
                        
                        if(tmpListString.length > 1){
                            word.setSameWord(tmpListString[1]);
                        }
                        
                        typeOfWords = new ArrayList<>();
                        word.setTypeOfWord(typeOfWords);
                        words.add(word);
                        continue;
                    }
                    
                    if(str.startsWith("* ")){
                        mows = new ArrayList<>();
                        typeOfWord = new TypeOfWord();
                        typeOfWord.setType(str.substring(2));
                        typeOfWord.setMeans(mows);
                        typeOfWords.add(typeOfWord);
                        
                        continue;
                    }
                    
                    if(str.startsWith("- ")){
                        examples = new ArrayList<>();
                        attachedPrepositions = new ArrayList<>();
                        meaningOfWord = new MeaningOfWord();
                        meaningOfWord.setMeaning(str.substring(2));
                        meaningOfWord.setExamples(examples);
                        meaningOfWord.setAps(attachedPrepositions);
                        mows.add(meaningOfWord);
                        
                        continue;
                    }
                    
                    if(str.startsWith("=")){
                        examples.add(str.substring(1));
                        continue;
                    }
                    
                    if(str.startsWith("!")){
                        attachedPrepositions.add(str.substring(1));
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
}
