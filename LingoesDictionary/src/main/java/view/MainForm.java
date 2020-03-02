/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JRootPane;
import javax.swing.JSplitPane;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import utils.ImageUtils;
import utils.MyDefaultMetalTheme;
import view.sub.PanelCenter;
//import utils.MyDefaultMetalTheme;
import view.sub.PanelLeft;

/**
 *
 * @author USER
 */
public class MainForm extends javax.swing.JFrame {

    private final Container container = getContentPane();
    private final String ICON_PATH = "F:\\Java Project\\LingoesDictionary\\target\\classes\\pictures\\icon-lingoes-16px.jpg";
    private final JSplitPane splitPane = new JSplitPane();
    private final String pathToDicData = getClass().getResource("/documents/Dictionary.txt").toString();

    private PanelLeft pnLeft;
    private PanelCenter pnCenter;
    private final MyDefaultMetalTheme defaultMetalTheme = new MyDefaultMetalTheme();

    private final Border border = new CompoundBorder(
            new MatteBorder(0, 5, 0, 5, new Color(201, 208, 240)), new MatteBorder(1, 1, 0, 1, Color.GRAY));

    public MainForm() {
        initComponents();
        initComponentManuallys();
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
        tfSearch = new javax.swing.JTextField();
        btSearch = new javax.swing.JButton();
        btBack = new javax.swing.JButton();
        btNext = new javax.swing.JButton();
        pnBottom = new javax.swing.JPanel();
        lbFooter = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LINGOES");
        setUndecorated(true);

        pnTop.setBackground(new java.awt.Color(201, 208, 240));

        tfSearch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tfSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        tfSearch.setPreferredSize(new java.awt.Dimension(6, 22));

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

        javax.swing.GroupLayout pnTopLayout = new javax.swing.GroupLayout(pnTop);
        pnTop.setLayout(pnTopLayout);
        pnTopLayout.setHorizontalGroup(
            pnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTopLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(btBack, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btNext, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(tfSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(btSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        pnTopLayout.setVerticalGroup(
            pnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTopLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNext, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btBack, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        pnTopLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btBack, btNext, btSearch, tfSearch});

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
        setIconImage(ImageUtils.load(ICON_PATH));

        splitPane.setOneTouchExpandable(true);
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setForeground(new Color(204,204,204));

        splitPane.setBorder(border);

        pnCenter = new PanelCenter(null);
        pnLeft = new PanelLeft(splitPane);

        splitPane.add(pnLeft, JSplitPane.LEFT);
        splitPane.add(pnCenter, JSplitPane.RIGHT);

        container.add(splitPane, BorderLayout.CENTER);

        initTitleBar();
        //initPnCenterConponents();
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
    private javax.swing.JLabel lbFooter;
    private javax.swing.JPanel pnBottom;
    private javax.swing.JPanel pnTop;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables
}
