
package controlador;

import modelo.Fenomeno;
import modelo.Volcan;
import vista.Vista;
/**
 *
 * @author angel_rodriguez
 */
public class Controlador {
    private int fenomenoActual;
    Volcan volcan;
    Vista vista;

    public Controlador(Volcan volcan, Vista vista) {
        this.fenomenoActual = -1;
        this.volcan = volcan;
        this.vista = vista;
        this.vista.setControlador(this);
    }    

    public int getFenomenoActual() {
        return fenomenoActual;
    }

    public Vista getVista() {
        return vista;
    }

    public void setFenomenoActual(int fenomenoActual) {
        this.fenomenoActual = fenomenoActual;
    }

    public void setVista(Vista vista) {
        this.vista = vista;
    }
    
    public void siguiente(){
        this.fenomenoActual = (this.fenomenoActual + 1) % volcan.getFenomenos().size();
        vista.actualizarVista(volcan, volcan.getFenomeno(fenomenoActual));
    }
    
    
    
    
    public void siguienteFenomeno(){
        fenomenoActual++;
    }
    
    public void setVolcan(Volcan volcan) {
        this.volcan = volcan;
    }

    public Volcan getVolcan() {
        return volcan;
    } 
    
    
    
}
