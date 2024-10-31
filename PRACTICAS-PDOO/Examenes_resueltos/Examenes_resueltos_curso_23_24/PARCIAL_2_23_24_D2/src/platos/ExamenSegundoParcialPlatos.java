
package platos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author frangarcia
 */
public class ExamenSegundoParcialPlatos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Plato> platos = new ArrayList<Plato>(Arrays.asList(
                 
                // a)
                //crea aqui todos los platos
                
                new Entrante("exotico", "entrante1", 5, null),
                new PlatoPrincipal("mejicana", Integer.MAX_VALUE, "plato1", 10, null),
                new Postre(0, "postre1", 4, null),
                
                new Entrante("suave", "entrante2", 6, null),
                new PlatoPrincipal("indio", Integer.MIN_VALUE, "plato2", 12, null),
                new Postre(111, "postre2", 8, null),
                
                new Entrante("italiano", "entrante3", (float) 4.5, null)
     
        ));
        
//        Vista v = new Vista();
//        Controlador c = new Controlador(v, platos);
        
        
        // b)
       
        
        // c)
        System.out.println("Numero de platos total que hay en el sistema: " + Plato.getTotalPlatos());

        // d) e) f)
        int numEntrantes = 0;
        for(Plato p: platos){
            if(p instanceof Entrante)
                numEntrantes++;                
            else if(p instanceof PlatoPrincipal){
                System.out.println("PlatoPrincipal: " + p.getNombre() + " Calificacion: " +
                        ((PlatoPrincipal) p).obtenerCalificacion());
            }else if(p.esPlatoEspecial())
                System.out.println("Plato: " + p.getNombre() + " es ESPECIAL.");            
        }
        
        System.out.println("Numero de entrantes que hay en el sistema: " + numEntrantes);
    
    }
    
}
