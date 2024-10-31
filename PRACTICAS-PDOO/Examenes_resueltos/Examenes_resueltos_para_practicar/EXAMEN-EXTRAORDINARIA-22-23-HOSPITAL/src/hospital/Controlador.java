
package hospital;

/**
 *
 * @author angel_rodriguez
 */
public class Controlador {
    private int unidadActual = 0; 
    private Vista vista;
    private Hospital hospital;

    public Controlador(Vista vista, Hospital hospital) {
        this.vista = vista;
        this.hospital = hospital;
        this.vista.setControlador(this);
        this.vista.actualizarVista(getUnidad(unidadActual));
    }

    public Vista getVista() {
        return vista;
    }

    public Hospital getHospital() {
        return hospital;
    }
    
    public Unidad getUnidad(int indice){
        return this.hospital.getUnidad(indice);
    }
    
    public void setVista(Vista vista) {
        this.vista = vista;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }    
    
    
    public void siguiente(){
        unidadActual = (unidadActual + 1) % hospital.getUnidades().size();
        vista.actualizarVista(getUnidad(unidadActual));
    }
    
    
}
