/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.sub;

import java.awt.Image;
import javax.swing.ImageIcon;
import utils.ImageUtils;
import utils.SizeUtils;

/**
 *
 * @author huyvi
 */
public class StartPagePanel extends javax.swing.JPanel {
    private final String curVersion = "1.0";
    private final String iconLingoesPath = getClass().getResource("/pictures/icon-lingoes-larger.jpg").getPath();
    private final String detailText = "<p  style=\"font-size:14px;font-family:tahoma\">Version hiện tại: <b>" + curVersion + "</b></p>"
                                        + "<p  style=\"font-size:14px;font-family:tahoma\">Các tính năng hỗ trợ:</p>"
                                        + "<ul  style=\"font-size:14px;font-family:tahoma\">"
                                        + "<li>Dịch</li>"
                                        + "<li>Bảng động từ bất quy tắc</li>"
                                        + "<li>Các từ viết tắt thông dụng</li>"
                                        + "<li>Tìm từ trong đoạn</li>"
                                        + "<li>Đọc từ</li></ul>";
    
    /**
     * Creates new form StartPagePanel
     */
    public StartPagePanel() {
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

        lbIcon = new javax.swing.JLabel();
        lbWelcome = new javax.swing.JLabel();
        scpDetail = new javax.swing.JScrollPane();
        epDetail = new javax.swing.JEditorPane();

        lbIcon.setPreferredSize(new java.awt.Dimension(71, 71));

        lbWelcome.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbWelcome.setForeground(new java.awt.Color(204, 51, 0));
        lbWelcome.setText("WELCOME TO LINGOES");

        scpDetail.setBorder(null);

        epDetail.setBorder(null);
        scpDetail.setViewportView(epDetail);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scpDetail)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scpDetail, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane epDetail;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbWelcome;
    private javax.swing.JScrollPane scpDetail;
    // End of variables declaration//GEN-END:variables

    private void initComponentManuallys() {
        // set icon into lbIcon
        Image iconLingoes = ImageUtils.load(iconLingoesPath).getScaledInstance(SizeUtils.getPreWidth(lbIcon), SizeUtils.getPreHeight(lbIcon), Image.SCALE_SMOOTH);
        lbIcon.setIcon(new ImageIcon(iconLingoes));
        
        // set text into epDetail
        epDetail.setContentType("text/html");
        epDetail.setText(detailText);
        epDetail.setEditable(false);
    }
}
