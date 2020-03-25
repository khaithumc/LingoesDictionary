/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.sub;

import dao.WordDao;
import dao.WordDaoImpl;
import entities.DictionaryEnum;
import entities.NationsWord;
import entities.Word;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
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
    private static String translatorText;
    private final String noResultText = "<h1 style=\"color:red;font-size:20px;font-family:tahoma\"><b>KHÔNG CÓ TỪ NÀY TRONG TỪ ĐIỂN</b></h1>";
    private final String typeOfSaveFile = "html";
    private final String htmlNullWord = "<i style=\"color:yellow;font-size:14px;font-family:tahoma\">Không có từ này trong từ điển</i>";
    private final char extendsionSeparator = '.';
    private String curWord;
    private Map<String, List<Word>> dictionaries;
    private String curWordToHTML;
    private final String onlyEVDicFileName = "OnlyEVDictionary.txt";

    private DictionaryEnum dicEnum;

    // mặc định là mở từ điển Anh-Việt
    public PanelCenter() {
        this(DictionaryEnum.ONLY_EN_VI);
    }

    public PanelCenter(DictionaryEnum dicEnum) {
        this.dicEnum = dicEnum;
        loadDictionariesData();

        initComponents();
        initComponentManuallys();
        initEvents();

        // cài để khi mở app thì nút btStartHomepage sẽ thực hiện để hiển thị StartHomepagePanel
        btStartPage.doClick();
    }

    private void loadDictionariesData() {
        dictionaries = new LinkedHashMap<>();
        WordDao wd = new WordDaoImpl();

        if (dicEnum == DictionaryEnum.ONLY_EN_VI) {
            dictionaries.put(FilenameUtils.getBaseName(onlyEVDicFileName), wd.getWords(dicEnum, onlyEVDicFileName));
        } else {
            // lấy tất cả các file từ điển đang lưu trong documents/dictionary 
            File[] files = new File(getClass().getResource("/documents/en_nations").getPath()).listFiles();

            // từ các file trên đọc dữ liệu và lưu vào dictionaries
            Arrays.stream(files).forEach(file -> {
                dictionaries.put(FilenameUtils.getBaseName(file.getName()), wd.getWords(dicEnum, file.getName()));
            });
        }
    }

    private void initComponentManuallys() {
        btSpeaker.setToolTipText("Phát âm phần văn bản được chọn");
        btCopy.setToolTipText("Sao chép");
        btSave.setToolTipText("Lưu...");
        btPrint.setToolTipText("In...");
        btFind.setToolTipText("Tìm...");
        btTranslate.setToolTipText("Sử dụng chức năng dich đoạn văn bản");
        btStartPage.setToolTipText("Start Page");

        scpCenterCenter.getVerticalScrollBar().setUnitIncrement(16);

    }

    private void initEvents() {
        initPnTopButtonEvents();
        initBtSpeakerEvents();
        initBtStartPageEvents();
        initBtTranslateEvents();
        initBtSaveEvents();
        initBtFindEvents();
    }

    private void initBtTranslateEvents() {
        btTranslate.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                TranslatorPanel translatorPanel = new TranslatorPanel();
                scpCenterCenter.setViewportView(translatorPanel);
                scpCenterCenter.revalidate();
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

    public void loadVocabulary(String vocabulary, DictionaryEnum dicEnum) {
        if (this.dicEnum != dicEnum) {
            this.dicEnum = dicEnum;
            loadDictionariesData();
        }
        curWord = vocabulary;

        JEditorPane epWordView = new JEditorPane();
        epWordView.setName("Word_EditorPane");
        epWordView.setContentType("text/html");
        epWordView.setEditable(false);
        StringBuilder htmlText = new StringBuilder();

        if (dicEnum == DictionaryEnum.EN_NATIONS) {
            Map<String, Word> searchedWords = searchWordInAllDictionary(vocabulary);
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
        } else {
            // tra từ trong list từ đã tạo
            dictionaries.keySet().forEach(dictionaryName -> {
                for (Word tmpWord : dictionaries.get(dictionaryName)) {
                    if (tmpWord.getVocabulary().equalsIgnoreCase(vocabulary)) {
                        htmlText.append(tmpWord.toHTMLString());
                        break;
                    }
                }
            });
        }

        epSelected = epWordView;
        curWordToHTML = htmlText.toString();
        epWordView.setText(curWordToHTML);
        epWordView.setCaretPosition(0);

        scpCenterCenter.setViewportView(epWordView);
    }

    private Map<String, Word> searchWordInAllDictionary(String vocabulary) {
        Map<String, Word> result = new LinkedHashMap<>();

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
                        if (saveFile.createNewFile()) {
                            // add value into created file
                            FileWriter fileWriter = new FileWriter(saveFile);
                            fileWriter.write(HTMLCodeUtils.convertToHTMLCodes(curWordToHTML));
                            fileWriter.close();
                            JOptionPane.showMessageDialog(null, "Word was saved");
                        } else {
                            JOptionPane.showMessageDialog(null, "file name existed, please try another one");
                        }

                    } catch (IOException ex) {
                        ex.printStackTrace();
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
}
