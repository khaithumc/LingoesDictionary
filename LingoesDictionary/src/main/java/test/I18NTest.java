/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
/**
 *
 * @author giasutinhoc.vn
 */
public class I18NTest {
 public static void main(String[] args) {
    int lang;
    Scanner s = new Scanner(System.in);
    ResourceBundle bundle;
    do {
     System.out.println("1. English");
     System.out.println("2. Vietnamese");
     System.out.println("3. Exit program");
     System.out.print("Please choose your language: ");
     lang = s.nextInt();
     switch (lang) {
      case 1:
        //internationalization.message is package name
        //MessageBundle is properties file name
        bundle = ResourceBundle.getBundle("test.MessageBundle");
        System.out.println("Message in " + Locale.US
                                 + ": " + bundle.getString("hello"));
        break;
      case 2:
        //changing the default locale to Vietnamese
        Locale.setDefault(new Locale("vi"));
        bundle = ResourceBundle.getBundle("test.MessageBundle");
        System.out.println("Message in " + Locale.getDefault()
                                 + ": " + bundle.getString("hello"));
        break;
     }
    } while (lang != 3);
  }
}
