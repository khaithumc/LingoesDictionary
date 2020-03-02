/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Word;
import java.io.File;
import java.util.List;

/**
 *
 * @author huyvi
 */
public interface WordDao {
    public List<Word> getWords(File file);
}
