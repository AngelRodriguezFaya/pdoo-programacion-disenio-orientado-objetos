
package modelo;

/**
 *
 * @author angel_rodriguez
 */
public abstract class Camara {
    private String marca;
    private int resolucion;

    public Camara(String marca, int resolucion) {
        this.marca = marca;
        this.resolucion = resolucion;
    }  
    
    public Camara(){
        this("", 0);
    }

    public void setResolucion(int res) {
        this.resolucion = res;
    }
    
    public abstract void calibrar();    
    
}
