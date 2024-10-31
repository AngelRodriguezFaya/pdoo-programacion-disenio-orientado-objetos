
package red_social;

/**
 *
 * @author angel_rodriguez
 */
public class Controlador {
    private int actual;  //Va a ser modificado en la interfaz gráfica.
    private Contenido contenidos[];
    private Vista vista;

    public Controlador(Contenido[] contenidos, Vista vista) {
        this.actual = 0;
        this.contenidos = contenidos;
        this.vista = vista;
    }
    
    public void cambiar(){
        this.actual = (this.actual+1)%2;
        this.vista =     }
    
}
