
package modelo;

/**
 *
 * @author angel_rodriguez
 */
public abstract class Fenomeno {
   private String nombre;

    public Fenomeno(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
   
   public abstract int destruccion();
}
