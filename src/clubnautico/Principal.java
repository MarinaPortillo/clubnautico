
package clubnautico;

import Gui.SociosForm;

/**
 *
 * @author Marina
 */
public class Principal {
    
    

    public static void main(String[] args) {
        
IConexionBD conexionBD = new ConexionBD();
ISociosDAO sociosDAO = new SociosDAO(conexionBD);

         /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SociosForm(sociosDAO).setVisible(true);
            }
        });

     
    }
    
}
