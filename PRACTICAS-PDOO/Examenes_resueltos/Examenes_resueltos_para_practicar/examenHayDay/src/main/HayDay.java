
package main;

import controlador.Controlador;
import java.util.ArrayList;
import modelo.Cultivo;
import modelo.Parcela;
import modelo.Soja;
import modelo.Trigo;
import vista.VistaPrincipal;

/**
 *
 * @author angel_rodriguez
 */
public class HayDay {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Parcela parcela1 = new Parcela();
        Parcela parcela2 = new Parcela();
        ArrayList<Parcela> parcelas = new ArrayList<Parcela>();
        parcelas.add(parcela1);
        parcelas.add(parcela2);
        
        Trigo cultivo1 = new Trigo();
        Trigo cultivo2 = new Trigo();
        Soja cultivo3 = new Soja();
        ArrayList<Cultivo> cultivosDisponibles = new ArrayList<Cultivo>();
        cultivosDisponibles.add(cultivo1);
        cultivosDisponibles.add(cultivo2);
        cultivosDisponibles.add(cultivo3);
        
        
        
        
        Controlador.instancia.start(new VistaPrincipal(), parcelas, cultivosDisponibles);

    }
    
}
