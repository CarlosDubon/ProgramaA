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
        btnSolAcceso.setEnabled(false);
        JButton btnAbrirP = new JButton("Abrir Puerta");
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
                        String pass = txtPass.getText();
                        boolean flag;
                        if(sql.verificarUsuario(usuario, pass)){
                            if(sql.verificarCategory(usuario, pass)){
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
                        String cadena;
                        btnSolAcceso.setEnabled(false);
                        try {
                            Cliente c = new Cliente();
                            try {
                                cadena = c.leer("Verificando");
                                if(cadena.equals("GO")){
                                    btnAbrirP.setEnabled(true);
                                    JOptionPane.showMessageDialog(null,"ha sido autorizado. Abriendo puerta...","EXITO",JOptionPane.DEFAULT_OPTION);
                                    System.exit(0);
                                }else{
                                    c.closeCon();
                                    System.exit(0);
                                }
                            } catch (InterruptedException ex) {
                            }
                            
                            
                        } catch (IOException ex) {
                        }
                        
                        

                        
                    }
                
        });
        
    }
    
    private Timestamp getCurrentTime(){
	java.util.Date today = new java.util.Date();
	return new Timestamp(today.getTime());
    }
    
}
