/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Carlos
 */
public class DBQuery {
    private Connection con;
    private PostgresqlConexion conexion;
    
    public DBQuery(){
        conexion = new PostgresqlConexion();
    }
    
    public boolean addRegistro(String usedNick, String usedPass){
        try{
            con = conexion.abrirConexion();
            String query = "INSERT INTO failedLoginReg (usednick,usedpass) Values (?,?)";
            PreparedStatement Pquery = con.prepareStatement(query);
            Pquery.setString(1,usedNick);
            Pquery.setString(2,usedPass);
            
            Pquery.executeUpdate();
            
            conexion.cerrarConexion(con);
        }catch(SQLException sqlE){
            System.out.println("Error: "+sqlE.getMessage());
            return false;
        }
        return true;
    
    }
    
    public boolean verificarUsuario(String User, String Pass){
        boolean flag = false;
        try{
            con = conexion.abrirConexion();
            String query = "SELECT * FROM researcher WHERE idresearcher = ? AND pass = ?";
            PreparedStatement PQuery = con.prepareStatement(query);
            PQuery.setString(1, User);
            PQuery.setString(2, Pass);
            ResultSet rs = PQuery.executeQuery();
            
            while(rs.next()){
                flag = true;
            }
            PQuery.close();
            conexion.cerrarConexion(con);
        }catch(SQLException sqlE){
            System.out.println("Error: " + sqlE.getMessage());
        }
        return flag;
    }
    
        public boolean verificarCategory(String User, String Pass){
        boolean flag = false;
        try{
            con = conexion.abrirConexion();
            String query = "SELECT * FROM researcher WHERE idresearcher = ? AND pass = ? AND idcat = 1 OR idcat = 2 OR idcat=3";
            PreparedStatement PQuery = con.prepareStatement(query);
            PQuery.setString(1, User);
            PQuery.setString(2, Pass);
            ResultSet rs = PQuery.executeQuery();
            
            while(rs.next()){
                flag = true;
            }
            PQuery.close();
            conexion.cerrarConexion(con);
        }catch(SQLException sqlE){
            System.out.println("Error: " + sqlE.getMessage());
        }
        return flag;
    }
}
