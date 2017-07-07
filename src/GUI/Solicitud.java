/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.*;

/**
 *
 * @author Carlos
 */
public class Solicitud extends JFrame {
    private Toolkit T1= Toolkit.getDefaultToolkit();
    public Solicitud(){
        JPanel msg = new JPanel();
        msg.add(new JLabel("Esperando Solicitud"));
        Container cp = getContentPane();
        FlowLayout fl = new FlowLayout();
        cp.setLayout(fl);
        cp.add(msg);
        
        setSize(250,100);
        this.setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(((int)T1.getScreenSize().getWidth()/2)+200,(int)(T1.getScreenSize().getHeight()/2)-200);
        setResizable(false);
       
        
    }
}
