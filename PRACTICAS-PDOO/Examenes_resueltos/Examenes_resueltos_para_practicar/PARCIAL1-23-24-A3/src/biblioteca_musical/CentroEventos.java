
package biblioteca_musical;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author angel_rodriguez
 */
public class CentroEventos {
    private String nombre;
    private List<SalaConferencia> salas;

    private CentroEventos(String nombre, List<SalaConferencia> otras_salas) {
        this.nombre = nombre;
        this.salas = new ArrayList<SalaConferencia>();
        for(SalaConferencia sala : otras_salas)
            this.salas.add(new SalaConferencia(sala));
    }
    
    public CentroEventos(String nombre){
        this(nombre, new ArrayList<SalaConferencia>());
    }
    
    public CentroEventos(){
        this("", new ArrayList<SalaConferencia>());
    }
    
    public CentroEventos(CentroEventos ce){
        this(ce.nombre, ce.salas);
    }
    
    public void crearSala(int numeroSala, int capacidad){
        if(buscarSalaPorNumero(numeroSala) == null) // si no existe una sala con ese número
        {
            SalaConferencia nueva_sala = new SalaConferencia(numeroSala, capacidad);
            salas.add(nueva_sala);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<SalaConferencia> getSalas() {
        return salas;
    }
    

    @Override
    public String toString() {
        return "CentroEventos{" + "nombre=" + nombre + ", salas=" + salas + '}';
    }
    
    public boolean solicitarReserva(int numeroSala, Date fecha, int hora, 
            String nombreReservante)
    {   
        SalaConferencia sala = buscarSalaPorNumero(numeroSala);
 
        if( (sala != null) && sala.estaDisponible(fecha, hora) )
            sala.crearReserva(fecha, hora, nombreReservante);
   
        return (sala != null) && sala.estaDisponible(fecha, hora);       
    }
    
    private SalaConferencia buscarSalaPorNumero(int numeroSala){
        for(SalaConferencia sala : salas){
            if( sala.getNumeroSala() == numeroSala )
                return sala;
        }
        // Si no se encuentra ninguna sala con el número especificado...
        return null;
    }
    
}
