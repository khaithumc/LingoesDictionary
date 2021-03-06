/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.sub;

import common.LanguageAppEnum;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSplitPane;

/**
 *
 * @author USER
 */
public class AddendumPanel extends javax.swing.JPanel {

    /**
     * Creates new form AddendumPanel
     */
    private JSplitPane splitPane;
    private final Color normalColor = Color.BLACK;
    private final Color clickedColor = Color.LIGHT_GRAY;
    private final Font wordFont = new Font("Tahoma", Font.BOLD, 14);
    private final String pathToIrreVerbs = getClass().getResource("/documents/IRREGULAR_VERBS.html").getPath();
    private final String pathToAbbreviations = getClass().getResource("/documents/ABBREVIATIONS.html").getPath();
    private LanguageAppEnum languageApp;
    
    public AddendumPanel(JSplitPane splitPane, LanguageAppEnum languageApp) {
        this.splitPane = splitPane;
        this.languageApp = languageApp;
        
        initComponents();
        initComponentsManuallys();
        initLbIVerbEvents();
        initLbAbbreviationsEvents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbIVerbs = new javax.swing.JLabel();
        lbAbbreviations = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        lbIVerbs.setText("IRREGULAR VERBS");

        lbAbbreviations.setText("ABBREVIATIONS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbIVerbs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbAbbreviations, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbIVerbs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbAbbreviations)
                .addContainerGap(248, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbAbbreviations;
    private javax.swing.JLabel lbIVerbs;
    // End of variables declaration//GEN-END:variables

    private void initLbIVerbEvents() {
        lbIVerbs.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PanelCenter pnCenter = (PanelCenter) splitPane.getRightComponent();
                pnCenter.showHTMLFile(pathToIrreVerbs);
                lbAbbreviations.setForeground(normalColor);
                lbIVerbs.setForeground(clickedColor);
            }
        });
    }

    private void initLbAbbreviationsEvents() {
        lbAbbreviations.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PanelCenter pnCenter = (PanelCenter) splitPane.getRightComponent();
                pnCenter.showHTMLFile(pathToAbbreviations);
                lbIVerbs.setForeground(normalColor);
                lbAbbreviations.setForeground(clickedColor);
            }
        });
    }

    private void initComponentsManuallys() {
        lbIVerbs.setFont(wordFont);
        lbAbbreviations.setFont(wordFont);
        setLanguageApp(languageApp);
    }
    
    public void setLanguageApp(LanguageAppEnum languageApp){
        this.languageApp = languageApp;
        lbAbbreviations.setText(languageApp.getValue("abbreviations"));
        lbIVerbs.setText(languageApp.getValue("irregular_vebs"));
    }
    
    public void setNormalAllLabel(){
        lbAbbreviations.setForeground(Color.BLACK);
        lbIVerbs.setForeground(Color.BLACK);
    }
}
