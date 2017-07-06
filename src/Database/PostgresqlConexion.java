
package Database;

import java.sql.*;

/**
 * Esta clase se encarga de establecer la conexion con la base de datos. <br>
 * Deben tomarse en cuenta los siguientes minimos necesarios para no tener conflictos con postgreSQL:
 * <br>1- Usuario: "postgres"
 * <br>2- Password: "root"
 * <br>3- Puerto de postgres: 5432
 * @author Dougl
 */
public class PostgresqlConexion {   
    
    /**
     * Cierra la conexion iniciada en postgreSQL.
     * @param con Conexion a cerrar.
     */
    public void cerrarConexion(Connection con){
        try{
            con.close();
        }
        catch(SQLException e){
            System.out.println("ERROR: "+e.getMessage());
        }
    }
    
    /**
     * Retorna una conexion con la base de datos "TheLastOfPOO"
     * @return Devuelve la conexion creada
     */
    public Connection abrirConexion() {
        String driver = "org.postgresql.Driver";
        String connectString = "jdbc:postgresql://localhost:5432/parcialfinalPOO17";
        String user = "postgres";
        String password = "root";
        Connection con = null;
        try{
            con = DriverManager.getConnection(connectString, user, password);
        }   
        catch(Exception e){
            System.out.println("ERROR: "+e.getMessage());
        }
        finally{
            if(con != null)
                return con;
            else
                return null;
        }
    }
    

}
