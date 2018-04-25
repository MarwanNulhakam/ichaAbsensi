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
 * @author Heni Edrianti
 */
public class ImagePanel extends javax.swing.JPanel{
    private Image image;
    public ImagePanel(){
        image=new ImageIcon(getClass().getResource("/ui/logo_45_CL.jpg")).getImage();
    }
    public ImagePanel(String src){
        try{
            image = new ImageIcon(getClass().getResource(src)).getImage();
        }catch(Exception ex){
            image = new ImageIcon(getClass().getResource("/ui/defaultPerson.png")).getImage();
        }
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gd=(Graphics2D)g.create();
        gd.drawImage(image, 0,0,getWidth(),getHeight(),this);
        
        gd.dispose();
    }
}
