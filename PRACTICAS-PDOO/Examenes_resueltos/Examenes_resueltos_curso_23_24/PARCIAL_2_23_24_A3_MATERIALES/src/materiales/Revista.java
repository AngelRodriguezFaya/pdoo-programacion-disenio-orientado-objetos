
package materiales;

/**
 *
 * @author angel_rodriguez
 */
public class Revista extends Material implements Rankeable{
    private static final String PERIODICIDAD_TOP = "semanal";
    private String periodicidad;

    public Revista(String periodicidad, String titulo, int numPaginas) {
        super(titulo, numPaginas);
        this.periodicidad = periodicidad;
    }
    
    public Revista(Revista r){
        this(r.periodicidad, r.getTitulo(), r.getNumPaginas());
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    @Override
    public void prestar() {
        System.out.println("Se ha presatdo la resvista " + getTitulo() + " cuya "
                + "periodicidad es " + getPeriodicidad());
    }  

    @Override
    public boolean esTop() {
        return this.periodicidad.equals(PERIODICIDAD_TOP);
    }

    @Override
    public Material copia() {
        return (new Revista(this));
    }
    
}
