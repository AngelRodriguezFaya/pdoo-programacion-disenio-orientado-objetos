
package materiales;

import java.util.ArrayList;

/**
 *
 * @author angel_rodriguez
 */
public class Controlador {
    private Vista vista;
    private ArrayList<Material> materiales;
    private int materialActual = 0;

    public Controlador(Vista vista, ArrayList<Material> materiales) {
        this.vista = vista;
        this.materiales = materiales;
        this.vista.setControlador(this);
        this.vista.actualizar(materiales.get(materialActual));
    }
    
    public void siguiente(){
        materialActual = (materialActual + 1) % materiales.size();
        vista.actualizar(materiales.get(materialActual));
    }
    
}
