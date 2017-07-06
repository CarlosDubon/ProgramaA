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
}
