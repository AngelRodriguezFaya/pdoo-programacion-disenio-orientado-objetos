
package hospital;

/**
 *
 * @author angel_rodriguez
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //a
        System.out.println("Numero de hospitales que hay en el sistema: " + Hospital.getNumHospitales());
        
        //b
        Hospital hospital1 = new Hospital("Maternidad");
        
        //c
        System.out.println("Numero de hospitales que hay en el sistema: " + Hospital.getNumHospitales());
        
        //d
        hospital1.addUnidadUrgencias(true, false, 0);
        hospital1.addUnidadRadiologia("Baja", 1);
        
        //e
        System.out.println("número de facultativos de la unidad más grande del "
                +"hospital “Maternidad”: " + hospital1.unidadMasGrande().getMinFacultativos());
        
        //f
        ((Radiologia) hospital1.getUnidad(1)).setResolucion("Alta");
        
        //g
        Controlador controlador = new Controlador(new Vista(), hospital1);
    }
    
}
