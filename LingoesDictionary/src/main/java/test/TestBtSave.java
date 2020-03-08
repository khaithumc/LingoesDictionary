/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 * @(#)SaveFileDialogExample.java 1.0
 * This code is written by www.codejava.net
 *
 */

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FilenameUtils;


public class TestBtSave extends JFrame {

	private JButton buttonBrowse;
        private JFileChooser fileChooser;

	public TestBtSave() {
		super("Save File Dialog Example");
		setLayout(new FlowLayout());
		buttonBrowse = new JButton("Save...");
		buttonBrowse.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				showSaveFileDialog();
			}
		});
		getContentPane().add(buttonBrowse);
		setSize(300, 100);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) throws IOException {
//            String tmp = convertToHTMLCodes("<b>Từ loại: </b>");
//            System.out.println(tmp);
//            try {
//                FileWriter fileWriter = new FileWriter(new File("C:\\Users\\huyvi\\Desktop\\LLL.html"));
//                fileWriter.write(tmp);
//                fileWriter.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
                        
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) { }

            SwingUtilities.invokeLater(new Runnable() {

                            public void run() {
                                    new TestBtSave();
                            }
                    });
	}
        
        public static String convertToHTMLCodes(String str){
            StringBuilder sb = new StringBuilder();
            int len = str.length();
            for(int i = 0; i < len; ++i) {
                char c = str.charAt(i);
               if (c > 127) {
                  sb.append("&#");
                  sb.append(Integer.toString(c, 10));
                  sb.append(";");
              } else {
                  sb.append(c);
              }
           }
             return sb.toString();
         }

	private void showSaveFileDialog() {
            File file = new File("C:\\Users\\huyvi\\Desktop\\Cest.a.b.c.d.e");
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getName());
            file = new File(file.getParentFile(), FilenameUtils.getBaseName(file.getName())+".xml");
            System.out.println(file.getName());
//            FilenameU
            
            
            
            
            fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");
            String typeOfFile = "";
//                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("HTML file (*.html)", "html");
            fileChooser.addChoosableFileFilter(extensionFilter);
            fileChooser.setFileFilter(extensionFilter);
            fileChooser.setSelectedFile(new File("test.html"));
            fileChooser.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    System.out.println(fileChooser.getSelectedFile());
                }

            });

            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            }
        }
}
