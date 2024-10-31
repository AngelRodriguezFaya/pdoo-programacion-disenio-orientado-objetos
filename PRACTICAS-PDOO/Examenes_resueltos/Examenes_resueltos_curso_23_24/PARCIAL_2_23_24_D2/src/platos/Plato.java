
package platos;

import java.util.List;

/**
 *
 * @author frangarcia
 */
public abstract class Plato implements Cloneable{
    private static int totalPlatos = 0;
    private String nombre;
    private float precio;
    List<String> ingredientes;

    public Plato(String nombre, float precio, List<String> ingredientes) {
        this.nombre = nombre;
        this.precio = precio;
        this.ingredientes = ingredientes;
        totalPlatos++;
    }

    public static int getTotalPlatos() {
        return totalPlatos;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }
    
    public abstract boolean esPlatoEspecial();  
    
    public abstract String getTipoPlato();
    
//    public abstract Plato copia();
//
//    @Override
//    protected Plato clone() throws CloneNotSupportedException {
//        return copia();
//    }
    
    

}
