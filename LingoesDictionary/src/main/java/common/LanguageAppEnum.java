/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author huyvi
 */
public enum LanguageAppEnum {
    VIETNAMESE (new Locale("vi")),
    ENGLISH (new Locale("en")),
    RUSSIAN (new Locale("ru")),
    FRENCH (new Locale("fr")),
    GERMAN (new Locale("de"));
    // 2 ngôn ngữ này khi load lên bị lỗi phông chữ
    //CHINESE (new Locale("cn")),
    //JAPANESE (new Locale("jp")),

    private String fileName = "documents.languages.language";
    private ResourceBundle bundle;
    private Locale locale;
    
    private LanguageAppEnum(Locale locale) {
        this.locale = locale;
        Locale.setDefault(locale);
        bundle = ResourceBundle.getBundle(fileName);
    }
    
    public String getValue(String key){
        return bundle.getString(key);
    }
    
    public Locale getLocale(){
        return this.locale;
    }
}
