
package hospital;

/**
 *
 * @author angel_rodriguez
 */
public class Radiologia extends Unidad{
    private String resolucion;

    public Radiologia(String resolucion, int planta, int minFacultativos) {
        super(planta, minFacultativos);
        this.resolucion = resolucion;
    }

    public Radiologia(String resolucion, int planta) {
        super(planta);
        this.resolucion = resolucion;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }
    
    @Override
    public int personal() {
        return this.getMinFacultativos()*2;
    }

    @Override
    public String getTipo() {
        return this.getClass().getSimpleName();
    }
    
    
}
