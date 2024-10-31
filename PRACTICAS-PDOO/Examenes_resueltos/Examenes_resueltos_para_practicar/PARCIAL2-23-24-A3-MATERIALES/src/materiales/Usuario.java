
package materiales;

import java.util.ArrayList;

/**
 *
 * @author angel_rodriguez
 */
public class Usuario {
    private String nombre;
    private ArrayList<Material> materiales;

    public Usuario(String nombre, ArrayList<Material> materiales) {
        this.nombre = nombre;
        this.materiales = materiales;
    }
    
    public Usuario(){
        this("", new ArrayList<Material>());
    }
    
    public Usuario(Usuario u){
        this.nombre = u.nombre;
        this.materiales = new ArrayList<Material>();
        for(Material material: materiales){
            this.materiales.add(material.copia());
        }
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Material> getMateriales() {
        return materiales;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMateriales(ArrayList<Material> materiales) {
        this.materiales = materiales;
    }
    
    
    
}
