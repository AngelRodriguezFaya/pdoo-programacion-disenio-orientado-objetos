
package biblioteca_musical;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author angel_rodriguez
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        CentroEventos centro = new CentroEventos("Centro de Convenciones Internacional");
        
        centro.crearSala(101, 50);
        centro.crearSala(102, 200);
        
        List<SalaConferencia> salas = centro.getSalas();
        
        for(int i = 0; i < salas.size(); i++){
            SalaConferencia sala = salas.get(i);
            System.out.println("El tamaño de la sala" + sala.getNumeroSala() +
                    " es: " + sala.getCapacidad());
        }
        
        centro.solicitarReserva(101, new Date(), 0, "Juan");
    
        centro.solicitarReserva(101, new Date(), 0, "Juan");

        System.out.println("\nTotal de Reservas en el sistema: " 
                + Reserva.getTotalReservas() + "\n");
        
    }
    
}
