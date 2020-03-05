/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.WordAndIndex;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author huyvi
 */
public class TestComboBox extends JFrame{

    Container con = getContentPane();
    
    public TestComboBox(String title){
        super(title);
        
        initComponents();
    }
    
    public static void main(String[] args) {
        TestComboBox test = new TestComboBox("Test");
        test.setVisible(true);
    }

    private void initComponents() {
        setLocationRelativeTo(null);
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        DefaultComboBoxModel<WordAndIndex> cbbModel = new DefaultComboBoxModel<>();
        
        JComboBox<WordAndIndex> cbbTest = new JComboBox<>();
        cbbTest.setModel(cbbModel);
        
        cbbTest.setEnabled(true);
        cbbTest.setEditable(true);
        
        cbbModel.addElement(new WordAndIndex("something", 88));
        cbbModel.addElement(new WordAndIndex("one", 80));
        cbbModel.addElement(new WordAndIndex("get it", 98));
        cbbModel.addElement(new WordAndIndex("ono", 100));
        cbbModel.addElement(new WordAndIndex("yeah", 108));
        
        System.out.println(cbbModel.getIndexOf(new WordAndIndex("on1", 110)));
        
        cbbTest.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                JTextField tf = (JTextField)cbbTest.getEditor().getEditorComponent();
                System.out.println(tf.getText());
            }
            
        });
        
//        cbbTest.add
        
        con.add(cbbTest);
    }
    
}
