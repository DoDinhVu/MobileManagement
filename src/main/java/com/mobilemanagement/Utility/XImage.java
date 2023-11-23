/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mobilemanagement.Utility;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author DINHVU
 */
public class XImage {
    public static Image getApImage(){
        URL url = XImage.class.getResource("/com/mobilemanagement/icon/logo-100.png");
        return new ImageIcon(url).getImage();
    }
    
public static boolean saveImage(File src){
    File dst = new File("src\\main\\resources\\com\\mobilemanagement\\Image",src.getName());
    if (!dst.getParentFile().exists()) {
        dst.getParentFile().mkdirs();// tao thu muc
    }
    try {
        Path from = Paths.get(src.getAbsolutePath());
        Path to = Paths.get(dst.getAbsolutePath());
        Files.copy(from,to,StandardCopyOption.REPLACE_EXISTING);
        return true;
    } catch (Exception e) {
        return false;
    }
}

public static ImageIcon read(String fileName){
    File path = new File("src\\main\\resources\\com\\mobilemanagement\\Image",fileName);
    return new ImageIcon(path.getAbsolutePath());
}
}
