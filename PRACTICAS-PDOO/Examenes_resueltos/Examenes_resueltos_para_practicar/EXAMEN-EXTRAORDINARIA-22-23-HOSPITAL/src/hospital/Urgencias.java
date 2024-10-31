
package hospital;

/**
 *
 * @author angel_rodriguez
 */
public class Urgencias extends Unidad{
    private boolean pediatria;
    private boolean helipuerto;

    public Urgencias(boolean pediatria, boolean helipuerto, int planta, int numFacultativos) {
        super(planta, numFacultativos);
        this.pediatria = pediatria;
        this.helipuerto = helipuerto;
    }

    public Urgencias(boolean pediatria, boolean helipuerto, int planta) {
        super(planta);
        this.pediatria = pediatria;
        this.helipuerto = helipuerto;    }

    public boolean isPediatria() {
        return pediatria;
    }

    public boolean isHelipuerto() {
        return helipuerto;
    }

    public void setPediatria(boolean pediatria) {
        this.pediatria = pediatria;
    }

    public void setHelipuerto(boolean helipuerto) {
        this.helipuerto = helipuerto;
    }

    @Override
    public int personal() {
        return this.getMinFacultativos()*3;
    }   
    
    @Override
    public String getTipo() {
        return this.getClass().getSimpleName();
    }
    
}
