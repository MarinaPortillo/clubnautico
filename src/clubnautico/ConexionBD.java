
package clubnautico;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Marina
 */
public class ConexionBD implements IConexionBD{
     final String CADENA_CONEXION = "jdbc:mysql://localhost/club_nautico";
    final String USUARIO = "root";
    final String CONTRASENIA = "Evolet17";
    
   
     @Override
    public Connection crearConexion() throws SQLException {        
        // ESTABLECEMOS UNA CONEXION CON MYSQL.. SI NO SE PUEDE LANZA SQLEXCEPTION
        Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASENIA);        
        return conexion;
    }
}
