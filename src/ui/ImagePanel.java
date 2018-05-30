/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Icha
 */
public class ImagePanel extends javax.swing.JPanel{
    private Image image;
    private String imagePath;
    Graphics2D gd;
    public ImagePanel(){
        imagePath = "/ui/logo_45_CL.jpg";
        image=new ImageIcon(getClass().getResource(imagePath)).getImage();
    }
    public ImagePanel(String src){
        try{
            image = new ImageIcon(getClass().getResource(src)).getImage();
            imagePath = src;
        }catch(Exception ex){
            image = new ImageIcon(getClass().getResource("/ui/defaultPerson.png")).getImage();
            imagePath = "/ui/defaultPerson.png";
        }
    }
    
    public String getImagePath(){
        return imagePath;
    }
    
    public void setImage(String src){
        try{
            image = new ImageIcon(getClass().getResource(src)).getImage();
            imagePath = src;
        }catch(Exception ex){
            javax.swing.JOptionPane.showMessageDialog(null, "image cannot be selected","ERROR",javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        gd.drawImage(image, 0,0,getWidth(),getHeight(),this);
        gd.dispose();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        gd=(Graphics2D)g.create();
        gd.drawImage(image, 0,0,getWidth(),getHeight(),this);
        gd.dispose();
    }
}
