/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Database.DBQuery;
import Sockets.Cliente;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.*;

/**
 *
 * @author Carlos
 */
public class Login extends JFrame{
        public int count;
        private DBQuery sql;
        public JButton btnAbrirP;
        public JButton btnSolAcceso;
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
        btnSolAcceso = new JButton("Solicitar Acceso");
        btnSolAcceso.setEnabled(false);
        btnAbrirP = new JButton("Abrir Puerta");
        btnAbrirP.setEnabled(false);
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
                        /* Calendar fecha = Calendar.getInstance();
                        String anio = "" +fecha.get(Calendar.YEAR);
                        int mes = fecha.get(Calendar.MONTH)+1;
                        String dia = "" +fecha.get(Calendar.DAY_OF_MONTH);
                        String horas = "" +fecha.get(Calendar.HOUR_OF_DAY);
                        String minutos = "" +fecha.get(Calendar.MINUTE);
                        String fullFecha = anio + "-" + mes + "-" + dia + " " + horas + ":" + minutos;*/
                        Timestamp fullDate = getCurrentTime();
                        String usuario = txtCod.getText();
                        String pass = new String(txtPass.getPassword());
                        String Encpass = MD5(pass);
                        if(sql.verificarUsuario(usuario, Encpass)){
                            if(sql.verificarCategory(usuario, Encpass)){
                                txtCod.setEnabled(false);
                                txtPass.setEnabled(false);
                                btnValidar.setEnabled(false);
                                btnSolAcceso.setEnabled(true);
                            }else{
                                JOptionPane.showMessageDialog(null,"Usted no tiene la categoria suficiente para entrar","ATENCION",JOptionPane.DEFAULT_OPTION);
                            }
                        }else{
                            btnValidar.setEnabled(false);
                            sql.addRegistro(usuario, pass,fullDate);
                            JOptionPane.showMessageDialog(null,"Por seguridad se ha registrado su actividad","ATENCION",JOptionPane.DEFAULT_OPTION);

                        }
                        
                        
                    }
                }
        );
        
        btnCancel.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                
                }
        );
        
        btnSolAcceso.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        btnSolAcceso.setEnabled(false);
                        Thread esperar = new Thread(new Esperar());
                        esperar.start();
                    }
                
        });
        
        btnAbrirP.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showConfirmDialog(null,"Conexion establecida... Abriendo pueta de entrada","Conexion exitosa",JOptionPane.DEFAULT_OPTION);
                        System.exit(0);
                    }
                
                }
        );
        txtCod.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }
            @Override
            public void keyPressed(KeyEvent ke) {
                JTextField JAux= txtCod;
                String Aux= JAux.getText();
                int Limite= 5;
                
                if(Aux.length()> Limite-1){
                    Aux=Aux.substring(0, Limite-1);
                    JAux.setText(Aux);
                }
            }
            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
        txtPass.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }
            @Override
            public void keyPressed(KeyEvent ke) {
                JTextField JAux= txtCod;
                String Aux= JAux.getText();
                int Limite= 30;
                
                if(Aux.length()> Limite-1){
                    Aux=Aux.substring(0, Limite-1);
                    JAux.setText(Aux);
                }
            }
            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
    }
    
    private Timestamp getCurrentTime(){
	java.util.Date today = new java.util.Date();
	return new Timestamp(today.getTime());
    }
    
    public String MD5(String md5) {
        try {
             java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
             byte[] array = md.digest(md5.getBytes());
             StringBuffer sb = new StringBuffer();
             for (int i = 0; i < array.length; ++i) {
               sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } 
        catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
    
    public class Esperar implements Runnable{
        Solicitud s;
        @Override
        public void run() {
            try {
                s = new Solicitud();
                Thread.sleep(10);
            } catch (InterruptedException ex) {
            }
            String cadena;
            try {
                Cliente c = new Cliente();
                try {
                    cadena = c.leer("...");
                    if(cadena.equals("GO")){
                        s.dispose();
                        btnAbrirP.setEnabled(true);
                        
                    }else{
                        JOptionPane.showConfirmDialog(null,"No se ha autorizado su conexion... conexion fallo","Advertencia",JOptionPane.CLOSED_OPTION);
                        c.closeCon();
                        System.exit(0);
                    }
                } catch (InterruptedException ex) {
                        JOptionPane.showConfirmDialog(null,"No se ha autorizado su conexion... conexion fallo","Advertencia",JOptionPane.CLOSED_OPTION);
                }


            } catch (IOException ex) {
                JOptionPane.showConfirmDialog(null,"El servidor no esta disponible... conexion fallo","Advertencia",JOptionPane.CLOSED_OPTION);
                s.dispose();
                btnSolAcceso.setEnabled(true);
                
            } 
        }
    
    }
    
}
