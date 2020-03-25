/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.DictionaryEnum;
import entities.NationsMeaningOfWord;
import entities.NationsTypeOfWord;
import entities.NationsWord;
import entities.OnlyEVMeaningOfWord;
import entities.OnlyEVTypeOfWord;
import entities.OnlyEVWord;
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

    public WordDaoImpl() {
    }
    
    @Override
    public List<Word> getWords(DictionaryEnum dicEnum, String filename) {
        List<Word> words = new ArrayList<>();
        
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    new FileInputStream(dicEnum.getPath() + File.separator + filename), "UTF-8"));
 
		String line;
                
                if(dicEnum.equals(DictionaryEnum.EN_NATIONS)){
                    // Lấy dữ liệu của các bộ từ điển anh - another nations
                    NationsWord word = null;
                    List<NationsTypeOfWord> typeOfWords = null;
                    NationsTypeOfWord typeOfWord = null;
                    List<NationsMeaningOfWord> mows = null;
                    NationsMeaningOfWord meaningOfWord = null;
                    String vocabulary = null;

                    while ((line = in.readLine()) != null) {
                        String curVocabulary = getNationsVocabulary(line);

                        if(!curVocabulary.equals(vocabulary)){
                            // nếu từ hiện tại đang xét không trùng với từ ở hàng trên thì tạo từ mới
                            setAllToNull(word, typeOfWords, typeOfWord, mows, meaningOfWord, vocabulary);
                            word = new NationsWord();
                            vocabulary = curVocabulary;
                            word.setVocabulary(vocabulary);

                            // nếu str kết thúc bằng '::' thì nó chỉ đến 1 từ đồng nghĩa
                            if(line.endsWith("::")){
                                word.setSameWord(getNationsSameWord(line));
                            } else {
                                typeOfWords = new ArrayList<>();
                                typeOfWord = new NationsTypeOfWord();
                                typeOfWord.setType(getNationsType(line));
                                mows = new ArrayList<>();
                                meaningOfWord = setDataForNationsMOW(line);
                                mows.add(meaningOfWord);
                                typeOfWord.setMeans(mows);
                                typeOfWords.add(typeOfWord);
                                word.setTypeOfWord(typeOfWords);
                            }

                            words.add(word);
                        } else {
                            // nếu từ hiện tại trùng với từ đã có thì xét tiếp 2 trường hợp
                            String type = getNationsType(line);

                            // nếu từ đã tồn tại nhưng chưa tạo list loại từ thì khởi tạo
                            if(typeOfWords == null){
                                typeOfWords = new ArrayList<>();
                                word.setTypeOfWord(typeOfWords);
                                // khởi tạo 1 biến typeOfWord tạm thời nhưng không thêm vào list typeOfWords
                                typeOfWord = new NationsTypeOfWord("none", null);
                            }

                            if(!type.equals(typeOfWord.getType())){

                                // nếu từ đã có và khác loại từ thì tạo ra loại từ mới và thêm vào list
                                typeOfWord = new NationsTypeOfWord();
                                typeOfWord.setType(type);
                                mows = new ArrayList<>();
                                meaningOfWord = setDataForNationsMOW(line);
                                mows.add(meaningOfWord);
                                typeOfWord.setMeans(mows);
                                typeOfWords.add(typeOfWord);
                            } else {
                                // nếu từ đã có và từ loại trùng thì ta thêm nghĩa mới vào list meaningOfWord của từ loại
                                meaningOfWord = setDataForNationsMOW(line);
                                mows.add(meaningOfWord);
                            }
                        }
                    }
                } else {
                    // lấy dữ liệu bộ từ điển Anh - Việt
                    OnlyEVWord word = null;
                    List<OnlyEVTypeOfWord> typeOfWords = null;
                    OnlyEVTypeOfWord typeOfWord = null;
                    List<OnlyEVMeaningOfWord> mows = null;
                    OnlyEVMeaningOfWord meaningOfWord = null;
                    List<String> examples = null;
                    List<String> attachedPrepositions = null;

                    while ((line = in.readLine()) != null) {
                        if(line.startsWith("@")){
                            word = new OnlyEVWord();
                            word.setVocabulary(line.substring(1, line.indexOf(" /")));
                            String tmpString = line.substring(line.indexOf('/'), line.lastIndexOf('/') + 1);
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

                        if(line.startsWith("* ")){
                            mows = new ArrayList<>();
                            typeOfWord = new OnlyEVTypeOfWord();
                            typeOfWord.setType(line.substring(2));
                            typeOfWord.setMeans(mows);
                            typeOfWords.add(typeOfWord);

                            continue;
                        }

                        if(line.startsWith("- ")){
                            examples = new ArrayList<>();
                            attachedPrepositions = new ArrayList<>();
                            meaningOfWord = new OnlyEVMeaningOfWord();
                            meaningOfWord.setMeaning(line.substring(2));
                            meaningOfWord.setExamples(examples);
                            meaningOfWord.setAps(attachedPrepositions);
                            mows.add(meaningOfWord);

                            continue;
                        }

                        if(line.startsWith("=")){
                            examples.add(line.substring(1));
                            continue;
                        }

                        if(line.startsWith("!")){
                            attachedPrepositions.add(line.substring(1));
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
    
    private String getNationsMeaning(String line){
        return line.substring((line.indexOf(":: ") + 3));
    }
    
    private String getNationsType(String line){
        return line.substring(line.indexOf('{') + 1, line.indexOf('}')).toUpperCase();
    }
    
    private String getNationsDescription(String line){
        return line.substring(line.indexOf('('), line.indexOf(')') + 1);
    }
    
    private String getNationsSameWord(String line){
        return line.substring(line.indexOf("SEE: ") + 4, line.indexOf("::"));
    }
    
    private String getNationsVocabulary(String line){
        return line.substring(0, line.indexOf('{') - 1);
    }
    
    private NationsMeaningOfWord setDataForNationsMOW(String line){
        NationsMeaningOfWord meaningOfWord = new NationsMeaningOfWord();
        if(line.substring(0, line.indexOf(':')).contains("(")){
            meaningOfWord.setDescription(getNationsDescription(line));
        }
        meaningOfWord.setMeaning(getNationsMeaning(line));
        
        return meaningOfWord;
    }
    
    private void setAllToNull(NationsWord word, List<NationsTypeOfWord> typeOfWords, NationsTypeOfWord typeOfWord, List<NationsMeaningOfWord> mows, NationsMeaningOfWord meaningOfWord, String vocabulary){
        word = null;
        typeOfWords = null;
        typeOfWord = null;
        mows = null;
        meaningOfWord = null;
        vocabulary = null;
    }
}
