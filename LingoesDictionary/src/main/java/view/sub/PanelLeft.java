/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.sub;

import common.DictionaryEnum;
import common.LanguageAppEnum;
import entities.WordAndIndex;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BiPredicate;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author USER
 */
public class PanelLeft extends javax.swing.JPanel {

    private final CardLayout cardLayout = new CardLayout();
    
    private HomepagePanel homepagePanel;
    private SettingPanel settingPanel;
    private AddendumPanel addendumPanel;
    private JSplitPane splitPane;
    
    private final Border defaultBorder = new JButton().getBorder();
    private final Border pnLeftButtonHighLightBorder = BorderFactory
            .createCompoundBorder(new LineBorder(new Color(153,153,153), 1), new LineBorder(Color.WHITE, 5));
    private final String homepageKey = getClass().getResource("/pictures/icon-homepage-16px.png").toString();
    private DefaultComboBoxModel<WordAndIndex> cbbModelOfMainForm;
    private DictionaryEnum dicEnum;
    private LanguageAppEnum languageApp;
    
    public PanelLeft(JSplitPane splitPane, DefaultComboBoxModel<WordAndIndex> cbbModel, DictionaryEnum dicEnum, LanguageAppEnum languageApp) {
        this.dicEnum = dicEnum;
        this.splitPane = splitPane;
        this.languageApp = languageApp;
        this.splitPane.setEnabled(false);
        this.cbbModelOfMainForm = cbbModel;
        
        initComponents();
        initComponentManuallys();
        initEvents();
    }
    
    private void initComponentManuallys() {
        pnLeftCenter.setLayout(cardLayout);
        
        homepagePanel = new HomepagePanel(dicEnum, splitPane, languageApp);
        settingPanel = new SettingPanel(this, languageApp);
        addendumPanel = new AddendumPanel(splitPane, languageApp);
        
        btHomepage.setBorder(pnLeftButtonHighLightBorder);
        
        pnLeftCenter.add(homepagePanel, btHomepage.getIcon().toString());
        pnLeftCenter.add(settingPanel, btSetting.getIcon().toString());
        pnLeftCenter.add(addendumPanel, btAddendum.getIcon().toString());
    }
    
    private void initEvents() {
        pnLeftLeftButtonsEvents();
    }
    
    private void pnLeftLeftButtonsEvents() {
        final Component[] components = pnLeftLeft.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                final JButton button = (JButton) component;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        // Đưa các nút đang được click ở panel khác về trạng thái bình thường
                        homepagePanel.setNormalAllLabel();
                        settingPanel.setNormalAllLabel();
                        addendumPanel.setNormalAllLabel();

                        String key = button.getIcon().toString();
                        cardLayout.show(pnLeftCenter, key);
                        
                        disableHighLightButtons(components);
                        button.setBorder(pnLeftButtonHighLightBorder);
                        
                        if(key.equals(homepageKey)){
                            splitPane.setEnabled(false);
                        } else {
                            splitPane.setEnabled(true);
                        }
                    }
                });
            }
        }
    }
    
    private void disableHighLightButtons(Component... components) {
        for (Component component : components) {
            if (component instanceof JButton) {
                final JButton button = (JButton) component;
                button.setBorder(defaultBorder);
            }
        }
    }
    
    public int searchWord(String text, BiPredicate func){
        return homepagePanel.searchWord(text, func);
    }
    
    public boolean isBtHomepageActive(){
        System.out.println(btHomepage.isEnabled());
        return btHomepage.isShowing();
    }
    
    public void activeBtHomepage(){
        if(homepagePanel.isVisible() == false){
            btHomepage.doClick();
            pnLeftCenter.revalidate();
        }
    }
    
    public void showWordAtIndex(int index){
        homepagePanel.showWordAtIndex(index);
    }
    
    public void setNewDictionary(){
        if(homepagePanel.setNewDictionary()){
            cbbModelOfMainForm.removeAllElements();
        }
    }
    
    public void setNewLanguageApp(){
        LanguageAppEnum choosenLanguageApp = (LanguageAppEnum) JOptionPane.showInputDialog(null, 
                                            languageApp.getValue("set_dic_kind_mess"), 
                                            languageApp.getValue("set_dic_kind_title"), 
                                            JOptionPane.PLAIN_MESSAGE, 
                                            null, 
                                            LanguageAppEnum.values(), 
                                            languageApp);
        if(choosenLanguageApp == null){
            return;
        }
        
        if(choosenLanguageApp == languageApp){
            JOptionPane.showMessageDialog(null, languageApp.getValue("already_set_new_language"));
            setNewLanguageApp();
        } else {
            this.languageApp = choosenLanguageApp;
            settingPanel.setLanguageApp(languageApp);
            addendumPanel.setLanguageApp(languageApp);
            homepagePanel.setLanguageApp(languageApp);
            PanelCenter panelCenter = (PanelCenter) splitPane.getRightComponent();
            panelCenter.setLanguageApp(languageApp);
            JOptionPane.showMessageDialog(null, languageApp.getValue("complete_set_new_language"));
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnLeftLeft = new javax.swing.JPanel();
        btHomepage = new javax.swing.JButton();
        btSetting = new javax.swing.JButton();
        btAddendum = new javax.swing.JButton();
        pnLeftCenter = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        pnLeftLeft.setBackground(new java.awt.Color(204, 204, 255));
        pnLeftLeft.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(153, 153, 153)));

        btHomepage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icon-homepage-16px.png"))); // NOI18N
        btHomepage.setBorder(null);
        btHomepage.setContentAreaFilled(false);

        btSetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icon-setting-16px.png"))); // NOI18N
        btSetting.setBorder(null);
        btSetting.setContentAreaFilled(false);

        btAddendum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icon-addendum.png"))); // NOI18N
        btAddendum.setBorder(null);
        btAddendum.setContentAreaFilled(false);

        javax.swing.GroupLayout pnLeftLeftLayout = new javax.swing.GroupLayout(pnLeftLeft);
        pnLeftLeft.setLayout(pnLeftLeftLayout);
        pnLeftLeftLayout.setHorizontalGroup(
            pnLeftLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLeftLeftLayout.createSequentialGroup()
                .addGroup(pnLeftLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btAddendum, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSetting, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btHomepage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        pnLeftLeftLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btAddendum, btHomepage, btSetting});

        pnLeftLeftLayout.setVerticalGroup(
            pnLeftLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLeftLeftLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btHomepage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btSetting, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btAddendum, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(222, Short.MAX_VALUE))
        );

        pnLeftLeftLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btAddendum, btHomepage, btSetting});

        add(pnLeftLeft, java.awt.BorderLayout.LINE_START);

        pnLeftCenter.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout pnLeftCenterLayout = new javax.swing.GroupLayout(pnLeftCenter);
        pnLeftCenter.setLayout(pnLeftCenterLayout);
        pnLeftCenterLayout.setHorizontalGroup(
            pnLeftCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 219, Short.MAX_VALUE)
        );
        pnLeftCenterLayout.setVerticalGroup(
            pnLeftCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 326, Short.MAX_VALUE)
        );

        add(pnLeftCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddendum;
    private javax.swing.JButton btHomepage;
    private javax.swing.JButton btSetting;
    private javax.swing.JPanel pnLeftCenter;
    private javax.swing.JPanel pnLeftLeft;
    // End of variables declaration//GEN-END:variables
}
