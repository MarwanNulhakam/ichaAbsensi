/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Heni Edrianti
 */
public class Jam extends Thread {
    private javax.swing.JLabel tanggal,jam;
    private boolean running;
    private String day[] = {"","Minggu","Senin","Selasa","Rabu","Kamis","Jum'at","Sabtu"};
    
    public Jam(javax.swing.JLabel tanggal, javax.swing.JLabel jam){
        this.tanggal = tanggal;
        this.jam=jam;
    }
    
    public void startTimer(){
        running = true;
    }
    
    @Override
    public void run(){
        startTimer();
        String[]extTime = (new SimpleDateFormat("dd-MM-yyyy#HH:mm:ss").format(new Date())).split("#");
        int j = Integer.parseInt(extTime[1].split(":")[0]);
        int m = Integer.parseInt(extTime[1].split(":")[1]);
        int d = Integer.parseInt(extTime[1].split(":")[2]);

        tanggal.setText(day[java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_WEEK)]+", "+extTime[0]);
        
        setTime(j,m,d);
        
        while(running){
            try{
                Thread.sleep(1000);
                d++;
                if(d>59){
                    d=0;
                    m++;
                    if(m>59){
                        j++;
                        m=0;
                        if(j>23){
                            j=0;
                        }
                    }
                }
                setTime(j,m,d);
            }catch(InterruptedException ie){
                return;
            }
        }
    }
    
    private void setTime(int j,int m, int d){
        jam.setText(f0(j)+":"+f0(m)+":"+f0(d));
    }
    
    private String f0(int data){
        return (data<10? "0":"")+Integer.toString(data);
    }
}
