/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import util.Constant;


/**
 *
 * @author Heni Edrianti
 */
public class Toolbox {
    private static DBConsole console=null;
    
    public static DBConsole getDBConsole(){
        if(console==null){
            initDBConsole(Constant.getHost(),Constant.getDBName(),Constant.getUsername(),Constant.getPassword());
        }
        return console;
    }
    
    public static void initDBConsole(String addr, String databaseName, String id,String pass){
        console = new DBConsole(addr, databaseName, id,pass);
    }
    
    public static void info(String message){
        javax.swing.JOptionPane.showMessageDialog(null, message, "INFO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
    public static void alert(String message){
        javax.swing.JOptionPane.showMessageDialog(null, message, "ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}
