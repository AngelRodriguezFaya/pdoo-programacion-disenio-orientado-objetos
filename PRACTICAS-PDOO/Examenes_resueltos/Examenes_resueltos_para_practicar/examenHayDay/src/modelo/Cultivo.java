
package modelo;

/**
 *
 * @author angel_rodriguez
 */
public abstract class Cultivo extends Producto{
    private int riego;
    private TipoCultivo tipo;

    public Cultivo(int riego, TipoCultivo tipo, String icono, 
            int duracionProduccion, int experienciaRecolecta, int precioVenta) {
        super(icono, duracionProduccion, experienciaRecolecta, precioVenta);
        this.riego = riego;
        this.tipo = tipo;
    }
    

    public int getRiego() {
        return riego;
    }

    public TipoCultivo getTipo() {
        return tipo;
    }

    public void setRiego(int riego) {
        this.riego = riego;
    }

    public void setTipo(TipoCultivo tipo) {
        this.tipo = tipo;
    }
    
    
}
