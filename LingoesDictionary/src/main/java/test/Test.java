/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import common.DictionaryEnum;
import dao.WordDao;
import dao.WordDaoImpl;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public Test() {
        for(DictionaryEnum dic : DictionaryEnum.values()){
            System.out.println(dic);
        }
    }

//    public Test() {
//        File file = new File(getClass().getResource("/documents").getPath());
//        file.listFiles(new FilenameFilter() {
//            @Override
//            public boolean accept(File arg0, String arg1) {
//                return arg1.startsWith("_");
//            }
//        });
//        for(File tmp : file.listFiles(new FilenameFilter() {
//            @Override
//            public boolean accept(File arg0, String arg1) {
//                return arg1.startsWith("_");
//            }
//        })){
//            System.out.println(FilenameUtils.getBaseName(tmp.getPath()));
//        }
//    }
    
    
    
}