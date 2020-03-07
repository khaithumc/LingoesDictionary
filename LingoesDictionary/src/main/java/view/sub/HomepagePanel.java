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
import java.io.File;
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
    
    private List<Word> words;
    private final String pathToDataFile = "C:\\Users\\VO DINH DUNG\\Desktop\\LingoesDictionary\\LingoesDictionary\\target\\classes\\documents\\Dictionary.txt";
    private final int maximumWordLength = 200;
    private final Font wordFont = new Font("Tahoma", Font.PLAIN, 16);
    private int indexOfCurBtWord = 0;
    private final Color normalColor = Color.BLACK;
    private final Color clickedColor = Color.LIGHT_GRAY;
    private int verLengthOfPnWords;
    private int heightOfBtWord;
    
    public HomepagePanel(JSplitPane splitPane) {
        this.splitPane = splitPane;
        
        WordDao wdao = new WordDaoImpl();
        File file = new File(pathToDataFile);
        words = wdao.getWords(file);
        
        initComponents();
        initComponentManuallys();
        
        btWords = getBtWords();
    }
    
    private void initComponentManuallys() {
        scpWords.getVerticalScrollBar().setUnitIncrement(16);
        pnCenter = new PanelCenter(this);
        
        JButton tmpButton = new JButton("Word");
        heightOfBtWord = SizeUtils.getPreHeight(tmpButton);
        verLengthOfPnWords = (tmpButton.getPreferredSize().height + 5) * words.size();
        pnWords.setPreferredSize(new Dimension(pnWords.getPreferredSize().width, verLengthOfPnWords));
        System.out.println("================");
        System.out.println(words.size());
        words.forEach((word) -> {
            JButton btWord = new JButton(word.getVocabulary());
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
            
            setEventBtWord(btWord, word);
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

    private void setEventBtWord(JButton btWord, Word word) {
        btWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                pnCenter.setLbWord(btWord.getText());
                
                btWords[indexOfCurBtWord].setForeground(normalColor);
                indexOfCurBtWord = Integer.parseInt(btWord.getName());
                btWord.setForeground(clickedColor);
                
                PanelCenter pnCenter = (PanelCenter) splitPane.getRightComponent();
                pnCenter.loadWord(word);
            }
        });
    }

    private JButton[] getBtWords() {
        JButton[] btWords = new JButton[words.size()];
        Component[] tmp = pnWords.getComponents();
        
        for(int i = 0; i < words.size(); i++) {
            btWords[i] = (JButton) tmp[i];
        }
        
        return btWords;
    }
    
    public void searchWord(String text, BiPredicate func){
        for(int i = 0; i < btWords.length; i++){
            if(func.test(btWords[i].getText(), text)){
                // active MouseListener of this lbWord
                btWords[i].doClick();
                splitPane.getRightComponent().revalidate();
                scpWords.getVerticalScrollBar().setValue(i * (heightOfBtWord + 5));
                // then break this loop
                return;
            }
        }
        
        // set last button back to normal
        btWords[indexOfCurBtWord].setForeground(normalColor);
        // if no lbWord contain text -> pnCenterCenter show no result
        PanelCenter pnCenterOfSplitPane = (PanelCenter) splitPane.getRightComponent();
        pnCenterOfSplitPane.loadNoResult();
    }
}