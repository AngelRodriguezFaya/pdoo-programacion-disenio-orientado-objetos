
package modelo;

/**
 *
 * @author angel_rodriguez
 */
public abstract class Producto {
    private String icono;
    private int duracionProduccion;
    private int experienciaRecolecta;
    private int precioVenta;

    public Producto(String icono, int duracionProduccion, int experienciaRecolecta, int precioVenta) {
        this.icono = icono;
        this.duracionProduccion = duracionProduccion;
        this.experienciaRecolecta = experienciaRecolecta;
        this.precioVenta = precioVenta;
    }

    public Producto() {
        this("",1, 1, 1);
    }
    

    public String getIcono() {
        return icono;
    }

    public int getDuracionProduccion() {
        return duracionProduccion;
    }

    public int getExperienciaRecolecta() {
        return experienciaRecolecta;
    }

    public int getPrecioVenta() {
        return precioVenta;
    }
    

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public void setDuracionProduccion(int duracionProduccion) {
        this.duracionProduccion = duracionProduccion;
    }

    public void setExperienciaRecolecta(int experienciaRecolecta) {
        this.experienciaRecolecta = experienciaRecolecta;
    }

    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }
    
    public abstract void enProduccion();
    
}
