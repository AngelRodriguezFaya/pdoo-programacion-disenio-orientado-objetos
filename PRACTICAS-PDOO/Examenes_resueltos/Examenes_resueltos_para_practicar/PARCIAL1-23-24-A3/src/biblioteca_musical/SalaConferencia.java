
package biblioteca_musical;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Clase SalaConferencia
 * @author angel_rodriguez
 */
public class SalaConferencia {
    public static final int MAX_CAPACIDAD = 100;
    private int numeroSala;
    private int capacidad;
    
    private List<Reserva> reservas;
    private Date fecha;

    private SalaConferencia(int numeroSala, int capacidad, 
            List<Reserva> otras_reservas) 
    {
        
        if(capacidad <= MAX_CAPACIDAD){
            this.numeroSala = numeroSala;
            this.capacidad = capacidad;
            this.reservas = new ArrayList<Reserva>();
            for(Reserva reserva : otras_reservas){
                this.reservas.add(new Reserva(reserva));
            }
        }else{ // capacidad > MAX_CAPACIDAD
            System.out.println("\nLa capacidad proporcionada es superior al máximo, que es 100."+
                    "No se ha podido crear la sala.\n");
        }
    }
    
    public SalaConferencia(int capacidad){
        this(0, capacidad, new ArrayList<Reserva>());
    }
    
    // He creado otro constructor que tiene como parámetro numeroSala y la capacidad.
    // ya que no venía en lso diagramas, pero lo veía necesario 
    public SalaConferencia(int numeroSala, int capacidad){
        this(numeroSala, capacidad, new ArrayList<Reserva>());
    }
    
    public SalaConferencia(){
        this(0, 0, new ArrayList<Reserva>());    
    }
    
    public SalaConferencia(SalaConferencia sc){
        this(sc.numeroSala, sc.capacidad, sc.reservas);
    }
    
    public void crearReserva(Date fecha, int hora, String nombreReservante){
        if(estaDisponible(fecha, hora)){
            Reserva r = new Reserva(fecha, hora, nombreReservante);
            reservas.add(r);
            System.out.println("\nLa sala está disponible y se ha podido crear la reserva\n");
        }else
            System.out.println("\nLa sala no está disponible y no se ha podido crear la reserva\n");
    }
    
    public boolean estaDisponible(Date fecha, int hora){
        boolean estaDisponible = true;
        for(Reserva reserva : reservas){
            Date fechaReserva = reserva.getFecha();
            int horaReserva   = reserva.getHora();
            if (fechaReserva.equals(fecha) && horaReserva == hora) // Si son iguales...
                estaDisponible = false;  // ... no está disponible.
        }
        return estaDisponible;
    }

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        if(capacidad <= MAX_CAPACIDAD)
            this.capacidad = capacidad;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    @Override
    public String toString() {
        return "SalaConferencia{" + "numeroSala=" + numeroSala + ", capacidad=" 
                + capacidad + ", reservas=" + reservas + ", fecha=" + fecha + '}';
    }  
    
}
