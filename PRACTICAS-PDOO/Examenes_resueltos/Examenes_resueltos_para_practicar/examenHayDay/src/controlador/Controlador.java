
package controlador;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import vista.IVista;
import modelo.Parcela;
import modelo.Cultivo;
import modelo.Soja;
import modelo.TipoCultivo;
import modelo.Trigo;

/**
 *
 * @author angel_rodriguez
 * 
 * Patron: Singleton
 */
public class Controlador {
    //Atributos
    //------Patron Singleton
    public static Controlador instancia = new Controlador();
    private Controlador(){};
    //-----
    
    private static final int[] PROGRESO = {27, 7, 14, 30, 50, 220, 370, 490, 790};
    
    //Atributos de juego
    private int dinero = 50;
    private int experiencia = 0;
    private int nivel = 1;
    
    //Atributos de relacion
    private IVista vista; 
    ArrayList<Parcela> parcelas = new ArrayList<Parcela>();
    ArrayList<Cultivo> cultivosDisponibles = new ArrayList<Cultivo>();

    
    public int getProgresoActual(){
        return PROGRESO[nivel-1];
    }
    
    public int getDinero() {
        return dinero;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public int getNivel() {
        return nivel;
    }

    public ArrayList<Parcela> getParcelas() {
        return parcelas;
    }

    public ArrayList<Cultivo> getCultivosDisponibles() {
        return cultivosDisponibles;
    }
    
    public boolean comprar(Parcela parcela){
        int dineroRestante = this.dinero - Parcela.PRECIO; 
        boolean puedeComprar = dineroRestante >= 0;
        if(puedeComprar){
            this.dinero = dineroRestante;
            this.experiencia += Parcela.EXPERIENCIA;
            subirNivel();
            
            // ----
            // EXAMEN
            
            // TODO Actualizar parcelas disponibles
            this.parcelas.add(parcela);
            
            // TODO Actualizar Vista del Juego
            this.vista.actualizarVista();
            
            // FIN DE EXAMEN
            // ----
             
        }
        return puedeComprar;
        
    }
    
    public boolean producir(Parcela parcela, TipoCultivo tipo){
        boolean exito = parcela.puedeProducir();
        
        if (exito){ //¿Puede producir?
            Cultivo cultivo = getCultivoDisponible(tipo);
            
            // ----
            // EXAMEN
            //TODO Obtener la duracion del cultivo 
            
//            int duracionCultivo = 1;
//            if(tipo == TipoCultivo.TRIGO)
//                duracionCultivo = Trigo.PRECIO_VENTA;
//            else
//                duracionCultivo = Soja.PRECIO_VENTA;  
            
            int duracionCultivo = (tipo == TipoCultivo.TRIGO) ? Trigo.DURACION: Soja.DURACION;
            // FIN DE EXAMEN
            // ----
            
            exito = cultivo != null;
            
            if(exito){
                // ----
                // EXAMEN
                
                // TODO Dar la orden de producir al productor
                parcela.producir(cultivo);
                                
                // TODO Actualizar cultivos disponibles
                this.cultivosDisponibles.remove(cultivo);

                // TODO Actualizar Vista del Juego
                vista.actualizarVista();
                
                // FIN DE EXAMEN
                // ----
                
                //------------------------------------
                //Esperar tiempo de cultivo
                //------------------------------------
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run () {
                        //recolectar
                        recolectar(parcela, cultivo);
                        
                        //cancelar el timer
                        timer.cancel();
                    }
                }, 
                        duracionCultivo * 1000
                );

            }
        }
        
        return exito;
    }
    
    public boolean vender(TipoCultivo tipo){
        Cultivo cultivo = getCultivoDisponible(tipo);
        boolean exito = cultivo != null;
        
        if(exito){
            //cobrar
            switch(tipo){
                case TRIGO:  
                    // ----
                    // EXAMEN
                    this.dinero += Trigo.PRECIO_VENTA;  //.PRECIO_VENTA; 
                    // FIN DE EXAMEN
                    // ----
                break;
                case SOJA:  
                    // ----
                    // EXAMEN
                    this.dinero += Soja.PRECIO_VENTA; //.PRECIO_VENTA; 
                    // FIN DE EXAMEN
                    // ----
                break;
            }
            
            // ----
            // EXAMEN

            //Actualizar cultivos disponibles tras la venta
            this.cultivosDisponibles.remove(cultivo);

            //Actualizar Vista del Juego
            vista.actualizarVista();

            // FIN DE EXAMEN
            // ----
        }
        
        return exito;
    }
    
    private void recolectar(Parcela parcela, Cultivo cultivo){
        parcela.recolectar(cultivo);

        // ----
        // EXAMEN
        
        this.experiencia += cultivo.getExperienciaRecolecta();

        // FIN DE EXAMEN
        // ----
        
        subirNivel();

        //Actualizar cultivos disponibles tras la recolecta
        for(int i=0; i<Parcela.CAPACIDAD; i++){
            Cultivo nuevo = null;
            switch(cultivo.getTipo()){
                case TRIGO: 
                    // ----
                    // EXAMEN
                    nuevo = new Trigo();
                   
                    // FIN DE EXAMEN
                    // ----
                    break;
                case SOJA: 
                    // ----
                    // EXAMEN
                    nuevo = new Soja();
                            
                    // FIN DE EXAMEN
                    // ----
                break;
            }

 		
            if(nuevo != null){
               // ----
               // EXAMEN
               this.cultivosDisponibles.add(nuevo);
                   
               // FIN DE EXAMEN
               // ----
                   
               // FIN DE EXAMEN
               // ----
            }
        }

        this.vista.actualizarVista();
    }
    
    
    /**
     * Este método sirve para btener un cultivo de la lista de disponibles, segun el tipo
     * @param tipo tipo de cultivo
     * @return objeto cultivo 
     */
    private Cultivo getCultivoDisponible(TipoCultivo tipo){
        Cultivo cultivo = null;
   
        //obtener un cultivo de la lista de disponibles, segun el tipo
        for(Cultivo c : this.cultivosDisponibles){
            if(c.getTipo() == tipo){
                cultivo = c;
                break;
            }
        }

        return cultivo;
    }
    
    
    public int getCultivosDisponiblesDeUnTipo(TipoCultivo tipo){
        int disponibles = 0;
        
        // Recorrer los cultivos disponibles y contabilizarlos
        for(Cultivo cultivo : this.cultivosDisponibles){
            if(cultivo.getTipo() == tipo){
                disponibles++;
            }
        }

        
        return disponibles;
    }
    
    public boolean subirNivel(){
        int progresoActual = getProgresoActual();
        boolean sube = this.experiencia >= progresoActual;
        if(sube){
           this.experiencia -= progresoActual;
           this.nivel ++;
        }
        return sube;
    }
    
    public void start(IVista vista, ArrayList<Parcela> parcelas,
            ArrayList<Cultivo> cultivos) {
        this.vista = vista;
        this.cultivosDisponibles = cultivos;
        this.parcelas = parcelas;
        
        this.vista.inicializarVista();
        this.vista.actualizarVista();
        this.vista.mostrarVista();
    }
    
    public void finish (int i) {
        System.exit(i);
    } 
        
    
}
