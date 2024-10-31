
package hospital;

import java.util.ArrayList;

/**
 *
 * @author angel_rodriguez
 */
public class Hospital {
    private static int numHospitales = 0;
    private static final int MAX_UNIDADES = 3;
    private String nombre;
    private ArrayList<Unidad> unidades;

    public Hospital(String nombre) {
        this.nombre = nombre;
        this.unidades = new ArrayList<Unidad>();
        this.numHospitales++;
    }

    public static int getNumHospitales() {
        return numHospitales;
    }

    public static int getMAX_UNIDADES() {
        return MAX_UNIDADES;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Unidad> getUnidades() {
        return unidades;
    }
    
    public Unidad getUnidad(int indice){
        return unidades.get(indice);
    }
    
    public void addUnidadUrgencias(boolean pediatria, boolean helipuerto, int planta){
        if(unidades.size() < MAX_UNIDADES)
            this.unidades.add(new Urgencias(pediatria, helipuerto, planta));
        else
            System.out.println("ERROR. No se ha podido añadir la unidad debido "
                    + "a que el Hospital ya tiene el máximo de unidades");
    }
    
    public void addUnidadRadiologia(String resolucion, int planta){
        if(unidades.size() < MAX_UNIDADES)
            this.unidades.add(new Radiologia(resolucion, planta));
        else
            System.out.println("ERROR. No se ha podido añadir la unidad debido "
                    + "a que el Hospital ya tiene el máximo de unidades");
    }
    
    public Unidad unidadMasGrande(){
        Unidad unidadMasGrande = null;
        int mayorHospital = Integer.MIN_VALUE;
        for(Unidad unidad: unidades){
            if(unidad.personal() >= mayorHospital){
                unidadMasGrande = unidad;
                mayorHospital = unidad.personal();
            }
        }
        
        return unidadMasGrande;
    }
    
    
    
}
