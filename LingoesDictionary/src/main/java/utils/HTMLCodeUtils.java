/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author huyvi
 */
public class HTMLCodeUtils {
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
}
