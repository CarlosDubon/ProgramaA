/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Database.DBQuery;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.*;

/**
 *
 * @author Carlos
 */
public class Login extends JFrame{
        private DBQuery sql;
    public Login(int w, int h, String title){
        super(title);
        sql = new DBQuery();
        JTextField txtCod = new JTextField(17);
        JPasswordField txtPass = new JPasswordField(17);
        JButton btnValidar = new JButton("Validar");
        JPanel jpDatos = new JPanel();
        GridLayout gl = new GridLayout(5,0,1,10);
        
        
        jpDatos.setLayout(gl);
        jpDatos.add(new JLabel("Codigo"));
        jpDatos.add(txtCod);
        jpDatos.add(new JLabel("Password"));
        jpDatos.add(txtPass);
        jpDatos.add(btnValidar);
        //jpDatos.add(btnValidar);
        
        JPanel jpBotones = new JPanel();
        GridLayout gl2 = new GridLayout(4,0,3,10);
        JButton btnSolAcceso = new JButton("Solicitar Acceso");
        JButton btnAbrirP = new JButton("Abrir Puerta");
        JButton btnCancel = new JButton("Cancel");
        
        jpBotones.setLayout(gl2);
        jpBotones.add(new JLabel(""));
        jpBotones.add(btnSolAcceso);
        jpBotones.add(btnAbrirP);
        jpBotones.add(btnCancel);
        
        

        
        Container cp = getContentPane();
        FlowLayout layout = new FlowLayout();        
        cp.setLayout(layout);
        cp.add(jpDatos);
        cp.add(jpBotones);
        
        
        setSize(w,h);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        setLocationRelativeTo(null);
                
                
        setResizable(false);
        
        btnValidar.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String usuario = txtCod.getText();
                        String pass = txtPass.getText();
                        if(sql.addRegistro(usuario, pass)){
                            System.out.println("ya la hicimos");
                        }else{
                            System.out.println("No la hicimos :'v");
                        }
                        
                    }
                }
        );
    }
     
    
}
