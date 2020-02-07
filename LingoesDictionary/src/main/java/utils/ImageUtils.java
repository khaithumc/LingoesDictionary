/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author qphan
 */
public class ImageUtils {
    
    private static final String TITLE_DEFAULT_IMAGE = "C:\\Users\\qphan\\Pictures\\lesson18_images\\64px_flower.png";

    public ImageUtils() {
    }
    
    public static Image load(String path) {
        if (path == null || path.isEmpty()) {
            path = TITLE_DEFAULT_IMAGE;
        }
        ImageIcon icon = new ImageIcon(path);
        return icon.getImage();
    }
    
    public static ImageIcon loadImageIcon(String path) {
        if (path == null || path.isEmpty()) {
            path = TITLE_DEFAULT_IMAGE;
        }
        return new ImageIcon(path);
    }
    
    public static ImageIcon loadImageIcon(String path, int width, int height) {
        Image image = load(path).getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
    
}
