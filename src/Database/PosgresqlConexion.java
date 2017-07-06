/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import java.sql.*;
/**
 *
 * @author Carlos
 */
public class PosgresqlConexion {
    public void closeConexion(Connection con){
        try{
            con.close();
        }catch(SQLException sqlE){
            System.out.println("Error: " + sqlE.getMessage());
        }
    }
    
    public Connection openConexion(){
        String conexion = "jdbc:postgresql://192.168.1.2:5432/parcialfinalPOO17";
        String user = "posgres";
        String password = "root";
        Connection con = null;
        
        try{
            con = DriverManager.getConnection(conexion, user , password);
        }catch(SQLException sqlE){
            System.out.println("ERROR: "+ sqlE);
        }
        finally{
            if(con != null)
                return con;
            else
                return null;
        }
    }
}
