/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.sub;

import common.DictionaryEnum;
import common.LanguageAppEnum;
import dao.WordDao;
import dao.WordDaoImpl;
import entities.Word;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.JTextComponent;
import org.apache.commons.io.FilenameUtils;
import utils.HTMLCodeUtils;
import utils.SizeUtils;
import utils.VoiceUtils;

/**
 *
 * @author USER
 */
public class PanelCenter extends javax.swing.JPanel {

    private static JEditorPane epSelected = new JEditorPane();
    private static String voiceWord;
    private final String noResultText = "<h1 style=\"color:red;font-size:20px;font-family:tahoma\"><b>KHÔNG CÓ TỪ NÀY TRONG TỪ ĐIỂN</b></h1>";
    private final String typeOfSaveFile = "html";
    private final String htmlNullWord = "<i style=\"color:yellow;font-size:14px;font-family:tahoma\">Không có từ này trong từ điển</i>";
    private final char extendsionSeparator = '.';
    private String curWord;
    private Map<DictionaryEnum, Map<String, List<Word>>> dictionarySets;
    private String curWordToHTML;
    final Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
    private LanguageAppEnum languageApp;
    private DictionaryEnum dicEnum;

    public PanelCenter(DictionaryEnum dicEnum, LanguageAppEnum languageApp) {
        this.dicEnum = dicEnum;
        this.languageApp = languageApp;
        loadDictionariesData();

        initComponents();
        initComponentManuallys();
        initEvents();

        // cài để khi mở app thì nút btStartHomepage sẽ thực hiện để hiển thị StartHomepagePanel
        btStartPage.doClick();
    }

    private void loadDictionariesData() {
        dictionarySets = new HashMap<>();
        Map<String, List<Word>> dictionaries = new HashMap<>();
        WordDao wd = new WordDaoImpl();
        
        File[] files = new File(dicEnum.getPath()).listFiles();
        Arrays.stream(files).forEach(file -> {
            dictionaries.put(FilenameUtils.getBaseName(file.getName()), wd.getWords(dicEnum, file.getName()));
        });
        dictionarySets.put(dicEnum, dictionaries);
    }

    private void initComponentManuallys() {
        setLanguageApp(languageApp);
        scpCenterCenter.getVerticalScrollBar().setUnitIncrement(16);
    }
    
    public void setLanguageApp(LanguageAppEnum languageApp){
        this.languageApp = languageApp;
        btSpeaker.setToolTipText(languageApp.getValue("speak_hint"));
        btCopy.setToolTipText(languageApp.getValue("copy_hint"));
        btSave.setToolTipText(languageApp.getValue("save_hint"));
        btPrint.setToolTipText(languageApp.getValue("print_hint"));
        btFind.setToolTipText(languageApp.getValue("find_hint"));
        btStartPage.setToolTipText(languageApp.getValue("startpage_hint"));
        btTranslate.setText(languageApp.getValue("translate_text"));
    }

    private void initEvents() {
        initPnTopButtonEvents();
        initBtSpeakerEvents();
        initBtStartPageEvents();
        initBtTranslateEvents();
        initBtSaveEvents();
        initBtFindEvents();
        initBtCopyEvents();
    }

    private void initBtTranslateEvents() {
        btTranslate.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                TranslatorPanel translatorPanel = new TranslatorPanel(dicEnum);
                scpCenterCenter.setViewportView(translatorPanel);
                scpCenterCenter.revalidate();
            }
        });
    }
    
     private void initBtCopyEvents() {
        btCopy.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String selection = curWord;
                StringSelection data = new StringSelection(selection);
                clip.setContents(data, data);
            }

        });
    }

    private void initPnTopButtonEvents() {
        final Component[] components = pnCenterTop.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                final JButton button = (JButton) component;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        button.setContentAreaFilled(true);
                        button.setBorder(new LineBorder(new Color(102, 102, 102)));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        button.setContentAreaFilled(false);
                        button.setBorder(null);
                    }
                });
            }
        }
    }

    private void initBtSpeakerEvents() {
        btSpeaker.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (epSelected.getSelectedText() == null) {
                    VoiceUtils.ConvertTextToSpeech(voiceWord);
                } else {
                    VoiceUtils.ConvertTextToSpeech(epSelected.getSelectedText());
                }
            }
        });
    }

    public void setLbWord(String s) {
        voiceWord = s;
    }

    public void loadVocabulary(String vocabulary) {
        loadDictionariesData();
        curWord = vocabulary;

        JEditorPane epWordView = new JEditorPane();
        epWordView.setName("Word_EditorPane");
        epWordView.setContentType("text/html");
        epWordView.setEditable(false);
        
        StringBuilder htmlText = new StringBuilder();
        Map<String, Word> searchedWords = searchWordInAllDictionary(vocabulary);
        switch (dicEnum){
            case EN_NATIONS:
                htmlText.append("<u style=\"color:red;font-size:22px;font-family:tahoma\"> TỪ: ").append(vocabulary.toUpperCase()).append("</u>");
                searchedWords.keySet().forEach(dictionaryName -> {
                    String htmlTitle = "<p style=\"font-size:16px;font-family:tahoma\">" + "Dịch theo tiếng: " + dictionaryName + "</p>";
                    htmlText.append(htmlTitle);
                    if (searchedWords.get(dictionaryName) != null) {
                        htmlText.append(searchedWords.get(dictionaryName).toHTMLString());
                    } else {
                        htmlText.append(htmlNullWord);
                    }
                }); 
                break;
            case ONLY_EN_VI:
                searchedWords.keySet().forEach(dictionaryName -> {
                    htmlText.append(searchedWords.get(dictionaryName).toHTMLString());
                }); 
                break;
        }

        epSelected = epWordView;
        curWordToHTML = htmlText.toString();
        epWordView.setText(curWordToHTML);
        epWordView.setCaretPosition(0);

        scpCenterCenter.setViewportView(epWordView);
    }

    private Map<String, Word> searchWordInAllDictionary(String vocabulary) {
        Map<String, Word> result = new LinkedHashMap<>();

        Map<String, List<Word>> dictionaries = dictionarySets.get(dicEnum);
        dictionaries.keySet().forEach(dictionaryName -> {
            Word tmpWord = null;
            for (Word word : dictionaries.get(dictionaryName)) {
                if (word.getVocabulary().equals(vocabulary)) {
                    tmpWord = word;
                    break;
                }
            }
            result.put(dictionaryName, tmpWord);
        });

        return result;
    }

    public void showHTMLFile(String path) {
        File file = new File(path);
        JEditorPane ep = new JEditorPane();
        try {
            ep.setPage(file.toURI().toURL());
        } catch (IOException ex) {
        }

        scpCenterCenter.setViewportView(ep);

    }

    private void initBtStartPageEvents() {
        btStartPage.addActionListener((ActionEvent arg0) -> {
            StartPagePanel pnStartPage = new StartPagePanel();
            scpCenterCenter.setViewportView(pnStartPage);
            scpCenterCenter.revalidate();
        });
    }

    public void loadNoResult() {
        JEditorPane epWordView = new JEditorPane();
        epWordView.setContentType("text/html");
        epWordView.setText(noResultText);
        epWordView.setBounds(0, 0, SizeUtils.getPreWidth(epWordView), SizeUtils.getPreHeight(epWordView));
        epWordView.setEditable(false);

        scpCenterCenter.setViewportView(epWordView);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnCenterTop = new javax.swing.JPanel();
        btSpeaker = new javax.swing.JButton();
        btCopy = new javax.swing.JButton();
        btSave = new javax.swing.JButton();
        btPrint = new javax.swing.JButton();
        btFind = new javax.swing.JButton();
        btTranslate = new javax.swing.JButton();
        btStartPage = new javax.swing.JButton();
        scpCenterCenter = new javax.swing.JScrollPane();

        setBackground(new java.awt.Color(253, 247, 250));
        setLayout(new java.awt.BorderLayout());

        pnCenterTop.setBackground(new java.awt.Color(204, 204, 255));
        pnCenterTop.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));

        btSpeaker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icon-speaker-16px.png"))); // NOI18N
        btSpeaker.setContentAreaFilled(false);
        btSpeaker.setFocusPainted(false);

        btCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icon-copy-16px.png"))); // NOI18N
        btCopy.setContentAreaFilled(false);
        btCopy.setFocusPainted(false);

        btSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icon-svae-16px.png"))); // NOI18N
        btSave.setContentAreaFilled(false);
        btSave.setFocusPainted(false);

        btPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icon-printer-16px.png"))); // NOI18N
        btPrint.setContentAreaFilled(false);
        btPrint.setFocusPainted(false);

        btFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icon-find-16px.png"))); // NOI18N
        btFind.setContentAreaFilled(false);
        btFind.setFocusPainted(false);

        btTranslate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btTranslate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icon-translate-16px.png"))); // NOI18N
        btTranslate.setText("Dịch văn bản");
        btTranslate.setContentAreaFilled(false);
        btTranslate.setFocusPainted(false);

        btStartPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icon-homepage2-16px.png"))); // NOI18N
        btStartPage.setContentAreaFilled(false);
        btStartPage.setFocusPainted(false);

        javax.swing.GroupLayout pnCenterTopLayout = new javax.swing.GroupLayout(pnCenterTop);
        pnCenterTop.setLayout(pnCenterTopLayout);
        pnCenterTopLayout.setHorizontalGroup(
            pnCenterTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCenterTopLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btSpeaker, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btCopy, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btSave, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btFind, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btTranslate, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btStartPage, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnCenterTopLayout.setVerticalGroup(
            pnCenterTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCenterTopLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnCenterTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btSpeaker, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCopy, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSave, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btFind, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btTranslate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btStartPage, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        add(pnCenterTop, java.awt.BorderLayout.PAGE_START);
        add(scpCenterCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCopy;
    private javax.swing.JButton btFind;
    private javax.swing.JButton btPrint;
    private javax.swing.JButton btSave;
    private javax.swing.JButton btSpeaker;
    private javax.swing.JButton btStartPage;
    private javax.swing.JButton btTranslate;
    private javax.swing.JPanel pnCenterTop;
    private javax.swing.JScrollPane scpCenterCenter;
    // End of variables declaration//GEN-END:variables

    private void initBtFindEvents() {
        btFind.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                Component tmpComponent = scpCenterCenter.getViewport().getView();
                if (!(tmpComponent instanceof JEditorPane)) {
                    JOptionPane.showMessageDialog(null, "This is nothing to find");
                    return;
                }
                new FindPanel((JTextComponent) scpCenterCenter.getViewport().getView()).setVisible(true);
            }
        });
    }

    private void initBtSaveEvents() {
        btSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                Component tmpComponent = scpCenterCenter.getViewport().getView();

                // if the showing is not a word -> show a dialog to announce and return
                if (!(tmpComponent instanceof JEditorPane)) {
                    JOptionPane.showMessageDialog(null, "This is not a word to save");
                    return;
                }

                // show saveDialog and get Path to save file
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
                FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("HTML file (*.html)", typeOfSaveFile);
                fileChooser.addChoosableFileFilter(extensionFilter);
                fileChooser.setFileFilter(extensionFilter);
                fileChooser.setSelectedFile(new File(curWord + extendsionSeparator + typeOfSaveFile));

                String pathToSave = "";
                int selection = fileChooser.showSaveDialog(null);

                if (selection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    pathToSave = getValidPath(fileToSave);

                    // create file
                    File saveFile = new File(pathToSave);

                    // if file existed -> change file name 
                    try {
                            // add value into created file
                            FileWriter fileWriter = new FileWriter(saveFile);
                            fileWriter.write(HTMLCodeUtils.convertToHTMLCodes(curWordToHTML));
                            fileWriter.close();
                            JOptionPane.showMessageDialog(null, "Word was saved");
                    } catch (IOException ex) {
                    }
                }
            }
        });
    }

    /*
    user can type wrong file name (etc: test.g.sad.fsf.dfwe)
    -> this method will turn wrong file name to right with file extension is html (etc: test.html)
     */
    private String getValidPath(File fileToSave) {
        String parentPath = fileToSave.getParent();
        String validFileName = fileToSave.getPath();

        do {
            validFileName = FilenameUtils.getBaseName(validFileName);
        } while (validFileName.contains(extendsionSeparator + ""));

        return parentPath + File.separator + validFileName + extendsionSeparator + typeOfSaveFile;
    }
    
    public void setDicEnum(DictionaryEnum dicEnum){
        this.dicEnum = dicEnum;
    }
}
