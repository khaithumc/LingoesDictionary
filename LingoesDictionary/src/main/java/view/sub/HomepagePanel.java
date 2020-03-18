/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.sub;

import dao.WordDao;
import dao.WordDaoImpl;
import entities.Word;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import utils.SizeUtils;

/**
 *
 * @author USER
 */
public class HomepagePanel extends javax.swing.JPanel {

    private PanelCenter pnCenter;
    private JSplitPane splitPane;
    private JButton[] btWords;
    
    private List<String> vocabularies;
    private final String pathToVocabularyFile = getClass().getResource("/documents/vocabulary_list.txt").getPath();
    private final int maximumWordLength = 200;
    private final Font wordFont = new Font("Tahoma", Font.PLAIN, 16);
    private int indexOfCurBtWord = 0;
    private final Color normalColor = Color.BLACK;
    private final Color clickedColor = Color.LIGHT_GRAY;
    private int verLengthOfPnWords;
    private int heightOfBtWord;
    
    public HomepagePanel(JSplitPane splitPane) {
        this.splitPane = splitPane;
        
        vocabularies = getVocabularies();
        initComponents();
        initComponentManuallys();
        
        btWords = getBtWords();
    }
    
    private void initComponentManuallys() {
        scpWords.getVerticalScrollBar().setUnitIncrement(16);
        pnCenter = new PanelCenter();
        
        JButton tmpButton = new JButton("Word");
        heightOfBtWord = SizeUtils.getPreHeight(tmpButton);
        verLengthOfPnWords = (tmpButton.getPreferredSize().height + 5) * vocabularies.size();
        pnWords.setPreferredSize(new Dimension(pnWords.getPreferredSize().width, verLengthOfPnWords));
        
        vocabularies.forEach((vocabulary) -> {
            JButton btWord = new JButton(vocabulary);
            btWord.setPreferredSize(new Dimension(maximumWordLength, btWord.getPreferredSize().height));
            btWord.setName(indexOfCurBtWord + "");
            btWord.setBorder(null);
            btWord.setFocusPainted(false);
            btWord.setContentAreaFilled(false);
            btWord.setMargin(new Insets(0, 0, 0, 0));
            btWord.setBorderPainted(false);
            btWord.setOpaque(false);
            btWord.setHorizontalAlignment(SwingConstants.LEFT);
            
            indexOfCurBtWord++;
            pnWords.add(btWord);
            
            setEventBtWord(btWord);
        });
        
        indexOfCurBtWord = 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scpWords = new javax.swing.JScrollPane();
        pnWords = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        pnWords.setMinimumSize(new java.awt.Dimension(100, 100));
        scpWords.setViewportView(pnWords);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scpWords, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scpWords)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnWords;
    private javax.swing.JScrollPane scpWords;
    // End of variables declaration//GEN-END:variables

    private void setEventBtWord(JButton btWord) {
        btWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                pnCenter.setLbWord(btWord.getText());
                
                btWords[indexOfCurBtWord].setForeground(normalColor);
                indexOfCurBtWord = Integer.parseInt(btWord.getName());
                btWord.setForeground(clickedColor);
                
                PanelCenter pnCenter = (PanelCenter) splitPane.getRightComponent();
                pnCenter.loadVocabulary(btWord.getText());
            }
        });
    }

    private JButton[] getBtWords() {
        JButton[] btWords = new JButton[vocabularies.size()];
        Component[] tmp = pnWords.getComponents();
        
        for(int i = 0; i < vocabularies.size(); i++) {
            btWords[i] = (JButton) tmp[i];
        }
        
        return btWords;
    }
    
    public int searchWord(String text, BiPredicate func){
        for(int i = 0; i < btWords.length; i++){
            if(func.test(btWords[i].getText(), text)){
                // active MouseListener of this lbWord
                btWords[i].doClick();
                splitPane.getRightComponent().revalidate();
                scpWords.getVerticalScrollBar().setValue(i * (heightOfBtWord + 5));
                // then break this loop
                return i;
            }
        }
        
        // if no lbWord contain text -> pnCenterCenter show no result
        PanelCenter pnCenterOfSplitPane = (PanelCenter) splitPane.getRightComponent();
        pnCenterOfSplitPane.loadNoResult();
        
        return -1;
    }
    
    public void showWordAtIndex(int index){
        if(index > -1){
            btWords[index].doClick();
            splitPane.getRightComponent().revalidate();
            scpWords.getVerticalScrollBar().setValue(index * (heightOfBtWord + 5));
        } else {
            // if no lbWord contain text -> pnCenterCenter show no result
            PanelCenter pnCenterOfSplitPane = (PanelCenter) splitPane.getRightComponent();
            pnCenterOfSplitPane.loadNoResult();
        }
    }
    
    private List<String> getVocabularies(){
        List<String> vocabularies = new ArrayList<>();
        
        File file = new File(pathToVocabularyFile);
        try{
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    new FileInputStream(file), "UTF-8"));
 
		String line;
                
		while ((line = in.readLine()) != null) {
                    vocabularies.add(line);
                }
        } catch (UnsupportedEncodingException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        
        return vocabularies;
    }
}