/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.sub;

import common.LanguageEnum;
import entities.DictionaryEnum;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import utils.TranslatorUtils;

/**
 *
 * @author USER
 */
public class TranslatorPanel extends javax.swing.JPanel {
//    private PanelCenter panelCenter;
    private DictionaryEnum dicEnum;
    private Map<String, String> mapLanguage;
    private final LanguageEnum[] languages = LanguageEnum.values();
    private ComboBoxModel<String> languageModelFrom;
    private ComboBoxModel<String> languageModelTo;

    public TranslatorPanel(DictionaryEnum dicEnum) {
        this.dicEnum = dicEnum;
        initComponents();
        initComponentManuallys();
        initEvents();
        initTextAreaData();
    }

    private void initComponentManuallys() {
        initComboboxData();

    }

    private void initTextAreaData() {
//        panelCenter = new PanelCenter(dicEnum);
        String s = taFrom.getText();
        System.out.println(taFrom.getText());
        System.out.println(s);
    }

    private void initComboboxData() {
        List<String> listLanguages = new ArrayList<>();
        for (int i = 0; i < languages.length; i++) {
            listLanguages.add(languages[i].toString());
        }

        String[] arrayLanguages = listLanguages.toArray(new String[listLanguages.size()]);

        languageModelFrom = new DefaultComboBoxModel<>(arrayLanguages);
        languageModelTo = new DefaultComboBoxModel<>(arrayLanguages);
        for (int i = 0; i < arrayLanguages.length; i++) {
            if (LanguageEnum.English.toString().equals(arrayLanguages[i])) {
                languageModelFrom.setSelectedItem(languageModelFrom.getElementAt(i));
            }
            if (LanguageEnum.Vietnamese.toString().equals(arrayLanguages[i])) {
                languageModelTo.setSelectedItem(languageModelTo.getElementAt(i));
            }
        }

        cbFrom.setModel(languageModelFrom);
        cbTo.setModel(languageModelTo);
    }

    private void initEvents() {
        initBtEnterEvents();
        initBtSwapEvents();
    }

    private void initBtEnterEvents() {
        mapLanguage = mockMapData();

        btEnter.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    taTo.setText(TranslatorUtils.translate(mapLanguage.get(languageModelFrom.getSelectedItem().toString()),
                            mapLanguage.get(languageModelTo.getSelectedItem().toString()), taFrom.getText()));
                } catch (IOException ex) {
                    Logger.getLogger(TranslatorPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(taTo.getText());
            }

        });
    }

    private void initBtSwapEvents() {
        btSwap.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String selectedFrom = languageModelFrom.getSelectedItem().toString();
                String selectedTo = languageModelTo.getSelectedItem().toString();
                languageModelFrom.setSelectedItem(selectedTo);
                languageModelTo.setSelectedItem(selectedFrom);
            }

        });
    }

    private Map<String, String> mockMapData() {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < languages.length; i++) {
            map.put(languages[i].toString(), languages[i].getValue());
        }

        return map;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbFrom = new javax.swing.JComboBox<>();
        cbTo = new javax.swing.JComboBox<>();
        btSwap = new javax.swing.JButton();
        btEnter = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        taTo = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        taFrom = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(249, 226, 253));

        cbFrom.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbFrom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbFrom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        cbFrom.setFocusable(false);

        cbTo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbTo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        cbTo.setFocusable(false);

        btSwap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icon-swap-16px.png.png"))); // NOI18N
        btSwap.setBorderPainted(false);
        btSwap.setContentAreaFilled(false);
        btSwap.setFocusPainted(false);

        btEnter.setBackground(new java.awt.Color(204, 204, 255));
        btEnter.setText("Dịch");
        btEnter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        btEnter.setFocusPainted(false);

        taTo.setEditable(false);
        taTo.setColumns(20);
        taTo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        taTo.setRows(5);
        taTo.setText("Bản dịch");
        taTo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jScrollPane3.setViewportView(taTo);

        taFrom.setColumns(20);
        taFrom.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        taFrom.setRows(5);
        taFrom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        taFrom.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane4.setViewportView(taFrom);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSwap)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTo, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btEnter, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 84, Short.MAX_VALUE))
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSwap)
                    .addComponent(btEnter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btEnter, btSwap, cbFrom, cbTo});

    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEnter;
    private javax.swing.JButton btSwap;
    private javax.swing.JComboBox<String> cbFrom;
    private javax.swing.JComboBox<String> cbTo;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea taFrom;
    private javax.swing.JTextArea taTo;
    // End of variables declaration//GEN-END:variables
}
