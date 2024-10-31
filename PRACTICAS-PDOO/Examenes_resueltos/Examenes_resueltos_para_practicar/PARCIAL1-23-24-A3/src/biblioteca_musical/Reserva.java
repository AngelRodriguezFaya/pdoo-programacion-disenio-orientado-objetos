
package biblioteca_musical;

import java.util.Date;

/**
 * Clase Reserva
 * @author angel_rodriguez
 */
public class Reserva {
    private static int totalReservas = 0;
    private Date fecha;
    private int hora;
    private String nombreReservante;

    public Reserva(Date fecha, int hora, String nombreReservante) {
        this.fecha = fecha;
        this.hora = hora;
        this.nombreReservante = nombreReservante;
        totalReservas++;
    }
    
    public Reserva(){
        this(new Date(), 0, "");
    }
    
    public Reserva(Reserva r){
        this(r.fecha, r.hora, r.nombreReservante);
    }

    public static int getTotalReservas() {
        return totalReservas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Reserva{" + "fecha=" + fecha + ", hora=" + hora 
                + ", nombreReservante=" + nombreReservante + '}';
    }
    
    
}
