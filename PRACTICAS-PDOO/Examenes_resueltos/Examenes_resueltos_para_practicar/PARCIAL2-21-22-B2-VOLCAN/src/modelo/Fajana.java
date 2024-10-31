
package modelo;

/**
 *
 * @author angel_rodriguez
 */
public class Fajana extends Fenomeno{
    private int largo;
    private int ancho;

    public Fajana(int largo, int ancho, String nombre) {
        super(nombre);
        this.largo = largo;
        this.ancho = ancho;
    }
    
    public Fajana(Fajana f){
        this(f.largo, f.ancho, f.getNombre());
    }

    @Override
    public String getNombre() {
        return "Fajana: " + super.getNombre(); 
    }
    
    @Override
    public int destruccion(){
        return this.ancho * this.largo;
    }
    
    
    
}
