
package platos;

import java.util.ArrayList;

/**
 *
 * @author aulas
 */
public class Usuario {
    private String nombre;
    private ArrayList<Plato> platos;

    public Usuario(String nombre, ArrayList<Plato> platos) {
        this.nombre = nombre;
        this.platos = platos;
    }
    
    public Usuario(Usuario usuario){
        this.nombre = usuario.nombre;
        this.platos = new ArrayList<Plato>();
        
        for(Plato p : usuario.platos){
            
        }
    }    
}
