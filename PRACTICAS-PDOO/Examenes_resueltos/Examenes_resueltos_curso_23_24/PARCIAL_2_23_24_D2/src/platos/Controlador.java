
package platos;

import java.util.List;

/**
 *
 * @author frangarcia
 */
public class Controlador {
    private static int entranteActual = 0;
    private Vista vista;
    List<Plato> platos;
    
    public Controlador(Vista v, List<Plato> p){
        this.vista = v;
        this.platos = p;
        this.vista.setControlador(this);
        siguienteEntrante();        
    }   
    
//    public void siguienteEntrante(){
//        final int MAX_ITER = 1; //SI da una vuelta al array de platos y no encuentra ningún plato, no sigue
//        boolean puedeSalir = false;
//        while()
//        entranteActual = (entranteActual++) % platos.size();
//    }
//    
//    public Plato getSiguientePlato(){
//        Plato siguiente = null;
//        for(int i = entranteActual; i < platos.size(); i++){
//            if(platos.get(i) instanceof Entrante){
//                entranteActual = i;
//                siguiente = platos.get(i);
//                break;
//            }
//        }
//        return siguiente;
//   }
    
    public void siguienteEntrante(){
        boolean puedeSalir = false;
        
        while(!puedeSalir){
            entranteActual = (entranteActual++) % platos.size();
            if(platos.get(entranteActual) instanceof Entrante){
                puedeSalir = true;
            }
        }
        
        this.vista.actualizar(platos.get(entranteActual)); 
    } 
}

