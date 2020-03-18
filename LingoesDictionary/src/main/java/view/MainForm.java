/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.WordAndIndex;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.BiPredicate;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRootPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.metal.MetalLookAndFeel;
import utils.MyDefaultMetalTheme;
import view.sub.PanelCenter;
import view.sub.PanelLeft;

/**
 *
 * @author USER
 */
public class MainForm extends javax.swing.JFrame {
    private int indexOfCurWord;
    private final Container container = getContentPane();
    private String ICON_PATH = getClass().getResource("/pictures/icon-lingoes-16px.jpg").getPath();
    private final BiPredicate<String, String> searchWordFunc = (BiPredicate<String, String>) (s1, s2) -> s1.startsWith(s2);
    private final BiPredicate<String, String> searchFullWordFunc = (BiPredicate<String, String>) (s1, s2) -> s1.equalsIgnoreCase(s2);
    private final Font normalWordFont = new Font("Tahoma", Font.PLAIN, 16);
    private DefaultComboBoxModel<WordAndIndex> cbbModel;
    
    private final JSplitPane splitPane = new JSplitPane();
    private PanelLeft pnLeft;
    private PanelCenter pnCenter;
    private final MyDefaultMetalTheme defaultMetalTheme = new MyDefaultMetalTheme();
    private JTextField tfSearch;

    private final Border border = new CompoundBorder(
            new MatteBorder(0, 5, 0, 5, new Color(201, 208, 240)), new MatteBorder(1, 1, 0, 1, Color.GRAY));

    public MainForm() {
        indexOfCurWord = -1;

        initComponents();
        initComponentManuallys();
        
        initEvents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnTop = new javax.swing.JPanel();
        btSearch = new javax.swing.JButton();
        btBack = new javax.swing.JButton();
        btNext = new javax.swing.JButton();
        cbbSearch = new javax.swing.JComboBox<>();
        pnBottom = new javax.swing.JPanel();
        lbFooter = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LINGOES");
        setUndecorated(true);

        pnTop.setBackground(new java.awt.Color(201, 208, 240));

        btSearch.setBackground(new java.awt.Color(255, 255, 255));
        btSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/arrow-icon-16px.png"))); // NOI18N
        btSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        btSearch.setFocusPainted(false);

        btBack.setBackground(new java.awt.Color(255, 255, 255));
        btBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icon-back-16px.png"))); // NOI18N
        btBack.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        btBack.setFocusPainted(false);

        btNext.setBackground(new java.awt.Color(255, 255, 255));
        btNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icon-next-16px.png"))); // NOI18N
        btNext.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        btNext.setFocusPainted(false);

        cbbSearch.setEditable(true);
        cbbSearch.setToolTipText("");
        cbbSearch.setBorder(null);

        javax.swing.GroupLayout pnTopLayout = new javax.swing.GroupLayout(pnTop);
        pnTop.setLayout(pnTopLayout);
        pnTopLayout.setHorizontalGroup(
            pnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTopLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(btBack, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btNext, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbSearch, 0, 294, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        pnTopLayout.setVerticalGroup(
            pnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTopLayout.createSequentialGroup()
                .addGroup(pnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNext, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btBack, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnTopLayout.createSequentialGroup()
                .addComponent(cbbSearch)
                .addGap(11, 11, 11))
        );

        pnTopLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btBack, btNext, btSearch});

        getContentPane().add(pnTop, java.awt.BorderLayout.PAGE_START);

        pnBottom.setBackground(new java.awt.Color(201, 208, 240));
        pnBottom.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 5, 5, new java.awt.Color(201, 208, 240)), javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(102, 102, 102))));

        lbFooter.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbFooter.setText("Copyright © 2019-2020  Lingoes Project");

        javax.swing.GroupLayout pnBottomLayout = new javax.swing.GroupLayout(pnBottom);
        pnBottom.setLayout(pnBottomLayout);
        pnBottomLayout.setHorizontalGroup(
            pnBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBottomLayout.createSequentialGroup()
                .addComponent(lbFooter)
                .addGap(0, 159, Short.MAX_VALUE))
        );
        pnBottomLayout.setVerticalGroup(
            pnBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBottomLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lbFooter)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(pnBottom, java.awt.BorderLayout.PAGE_END);

        getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });

    }

    private void initComponentManuallys() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setSize(800, 500);
        setTitle("Lingoes");
        splitPane.setOneTouchExpandable(true);
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setForeground(new Color(204,204,204));
        splitPane.setBorder(border);

        pnCenter = new PanelCenter();
        pnLeft = new PanelLeft(splitPane);

        splitPane.add(pnLeft, JSplitPane.LEFT);
        splitPane.add(pnCenter, JSplitPane.RIGHT);

        container.add(splitPane, BorderLayout.CENTER);

        initTitleBar();
        
        // init cbbSearch
        cbbModel = new DefaultComboBoxModel<>();
        cbbSearch.setModel(cbbModel);
        
        
        
        // init tfSearch
        tfSearch = (JTextField) cbbSearch.getEditor().getEditorComponent();
        tfSearch.setFont(normalWordFont);
    }

    // KHÔNG HIỂU
    private void initTitleBar() {
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

        MetalLookAndFeel.setCurrentTheme(defaultMetalTheme);
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBack;
    private javax.swing.JButton btNext;
    private javax.swing.JButton btSearch;
    private javax.swing.JComboBox<WordAndIndex> cbbSearch;
    private javax.swing.JLabel lbFooter;
    private javax.swing.JPanel pnBottom;
    private javax.swing.JPanel pnTop;
    // End of variables declaration//GEN-END:variables

    private void initEvents() {
        initTfSearchEvents();
        initBtBackEvents();
        initBtNextEvents();
        initBtSearchEvents();
    }

    private void initTfSearchEvents() {
        cbbSearch.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                // call method of homepagePanel to search word
                pnLeft.activeBtHomepage();
                String typingText = tfSearch.getText();
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    // nếu không tồn tại từ đang tra trong ds từ đã lưu thì sẽ thực hiện tìm kiếm và thêm mới vào ds từ
                    // getIndexOf sẽ dựa vào hàm equals trong WordAndIndex để xác định (chỉ so sánh 2 từ, không so sánh 2 index)
                    WordAndIndex wai = new WordAndIndex(typingText, -1);
                    int indexOfTypingText = cbbModel.getIndexOf(wai);
                    if(indexOfTypingText == -1){
                        wai.setIndex(pnLeft.searchWord(typingText, searchFullWordFunc));
                        cbbModel.addElement(wai);
                    } else {
                        wai = cbbModel.getElementAt(indexOfTypingText);
                        cbbModel.removeElementAt(indexOfTypingText);
                        cbbModel.addElement(wai);
                        pnLeft.showWordAtIndex(wai.getIndex());
                    }
                    
                } else {
                    pnLeft.searchWord(typingText, searchWordFunc);
                }
                
                tfSearch.setText(typingText);
                
                // set indexOfCurWord to last index of word list
                indexOfCurWord = cbbModel.getSize() - 1;
            }
            
        });
    }

    private void initBtBackEvents() {
        btBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(indexOfCurWord > 0){
                    WordAndIndex wai = cbbModel.getElementAt(--indexOfCurWord);
                    tfSearch.setText(wai.getWord());
                    pnLeft.activeBtHomepage();
                    pnLeft.showWordAtIndex(wai.getIndex());
                }
            }
        });
    }

    private void initBtNextEvents() {
        btNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(indexOfCurWord < cbbModel.getSize() - 1){
                    WordAndIndex wai = cbbModel.getElementAt(++indexOfCurWord);
                    tfSearch.setText(wai.getWord());
                    pnLeft.activeBtHomepage();
                    pnLeft.showWordAtIndex(wai.getIndex());
                }
            }
        });
    }

    private void initBtSearchEvents() {
        btSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pnLeft.activeBtHomepage();
                String typingText = tfSearch.getText();
                cbbModel.addElement(new WordAndIndex(typingText, pnLeft.searchWord(typingText, searchFullWordFunc)));
                tfSearch.setText(typingText);
                indexOfCurWord = cbbModel.getSize() - 1;
            }
        });
    }
}
