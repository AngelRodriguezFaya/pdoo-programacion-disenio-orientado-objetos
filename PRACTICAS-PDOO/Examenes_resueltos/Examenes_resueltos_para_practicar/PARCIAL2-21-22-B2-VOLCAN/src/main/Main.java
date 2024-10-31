
package main;

import controlador.Controlador;
import modelo.Colada;
import modelo.Fajana;
import modelo.Volcan;
import vista.Vista;


/**
 *
 * @author angel_rodriguez
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        //a
        Volcan volcan1 = new Volcan("Cumbre Vieja");
        volcan1.addColada(10, true, "Colada norte");
        volcan1.addFajana(20, 30, "Fajana norte");
        
        //b
        System.out.println(volcan1.getFenomeno(0).destruccion());
        
        //c
        Volcan volcan2 = new Volcan(volcan1);
        
        //d
        Vista v = new Vista();
        Controlador c = new Controlador(volcan2, v);
    }
    
}
