
package modelo;

/**
 *
 * @author angel_rodriguez
 */
public class Colada extends Fenomeno{
    private int longitud;
    private boolean llegaMar;

    public Colada(int longitud, boolean llegaMar, String nombre) {
        super(nombre);
        this.longitud = longitud;
        this.llegaMar = llegaMar;
    }

    public Colada() {
        this(0, false, "");
    }
    
    public Colada(Colada c){
        this(c.longitud, c.llegaMar, c.getNombre());
    }

    public int getLongitud() {
        return longitud;
    }

    public boolean isLlegaMar() {
        return llegaMar;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public void setLlegaMar() {
        this.llegaMar = !this.llegaMar;
    }
    
    
    @Override
    public String getNombre() {
        return "Colada: " + super.getNombre(); 
    }
    
    @Override
    public int destruccion(){
        return this.longitud;
    }
    
    
    
}
