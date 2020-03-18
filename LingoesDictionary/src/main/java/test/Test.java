/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.WordDao;
import dao.WordDaoImpl;
import entities.Word;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author huyvi
 */
public class Test{
    private final String pathToDocument = "/documents";
    private final String[] dataFiles = {"en-sw.txt", "en-vi.txt"};
    
    public static void main(String[] args) {
        Test test = new Test();
    }

//    public Test() {
////        File file = new File("C:\\Users\\huyvi\\Desktop\\LingoesFile\\wikidict\\english\\en-sw-enwiktionary.txt");
//        
//        String[] pathToDataFiles = Arrays.stream(dataFiles)
//                                        .map(file -> getClass().getResource(pathToDocument + "/" + file).getPath())
//                                        .toArray(String[]::new);
//        
//        WordDao wordDao = new WordDaoImpl();
//        for(String path : pathToDataFiles){
//            List<Word> words = wordDao.getWords(new File(path));
//            for(Word word : words){
//                if(word.getVocabulary().equalsIgnoreCase("a")){
//                    System.out.println(word);
//                    break;
//                }
//            }
//        }
//        
//    }

    public Test() {
        File file = new File(getClass().getResource("/documents").getPath());
        file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File arg0, String arg1) {
                return arg1.startsWith("_");
            }
        });
        for(File tmp : file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File arg0, String arg1) {
                return arg1.startsWith("_");
            }
        })){
            System.out.println(FilenameUtils.getBaseName(tmp.getPath()));
        }
    }
    
    
    
}