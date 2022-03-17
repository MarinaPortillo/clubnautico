
package clubnautico;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author Marina
 */
public interface IConexionBD {
     public Connection crearConexion() throws SQLException;
    
}
