/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Carlos
 */
public class Cliente {
    OutputStream s1out;
    InputStream s1In;
    Socket s1;
    DataOutputStream salida;
    DataInputStream entrada;
    public Cliente() throws IOException {
            s1 = new Socket("192.168.1.2",1254);
            //canalaes de lectura y escritura
            s1out = s1.getOutputStream();
            s1In = s1.getInputStream();
            salida = new DataOutputStream (s1out);
            entrada = new DataInputStream(s1In);
    }
    public String leer(String msg) throws InterruptedException, IOException{
        System.out.println(msg);
        while(entrada.available() == 0){
            Thread.sleep(10);
        }
        String cadenaRecibida = new String (entrada.readUTF());
        return cadenaRecibida;
    }
    
    public void escribir(String cadena) throws IOException{
        salida.writeUTF(cadena);
    }
    public void closeCon() throws IOException{
        entrada.close();
        salida.close();
        s1In.close();
        s1.close();
    } 
}
