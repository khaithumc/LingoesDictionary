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
import java.util.List;

/**
 *
 * @author huyvi
 */
public class Test{
    public static void main(String[] args) {
        File file = new File("C:\\Users\\huyvi\\Desktop\\Dictionary_trim.txt");
        WordDao wordDao = new WordDaoImpl();
    }
}