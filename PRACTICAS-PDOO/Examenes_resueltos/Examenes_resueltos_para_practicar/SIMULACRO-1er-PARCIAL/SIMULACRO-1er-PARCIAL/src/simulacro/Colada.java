
package simulacro;

/**
 * Esta clase representa la colada que sale del volcán.
 * @author angel_rodriguez
 */
public class Colada {
    private int longitud;
    private boolean llegaMar;
    private Volcan volcan;
    
    /**
     * Constructor parametrizado de la clase Colada.
     * @param longitud  longitud de la colada.
     * @param llegaMar  indica si la colada llega al mar.
     */
    public Colada(int longitud, boolean llegaMar) {
        this.longitud = longitud;
        this.llegaMar = llegaMar;
    }
    
    /**
     * Constructor por defecto de la clase Colada.
     */
    public Colada(){
        this(0, false);
    }
    
    /**
     * Constructor por copia de la clase Colada.
     * @param c objeto de tipo Colada
     */
    public Colada(Colada c){
        this(c.getLongitud(), c.getLlegaMar());
    }
    
    /**
     * Gets de la longitud de una colada.
     * @return devuelve 0 si longitud es menor que 0
     *                  y si no, devuelve la longitud.
     */
    public int getLongitud() {
        if(longitud < 0){
            System.err.println("\nLa longitud de la colada es menor que 0\n");
            return 0;
        }else
            return this.longitud;
    }
    
    /**
     * Gets de llegaMar
     * @return devuelve si una colada llega al mar.
     */
    public boolean getLlegaMar() {
        return this.llegaMar;
    }
    
    /**
     * Set de la longitud de una colada.
     * @param longitud longitud de la colada. Debe ser >= 0.
     */
    public void setLongitud(int longitud) {
        if(longitud < 0)
            System.err.println("\nLa longitud de la colada es menor que 0\n");
        else
            this.longitud = longitud;
    }
    
    /**
     * Set de llegaMar
     */
    public void setLlegaMar() {
        this.llegaMar = !(this.llegaMar);
    }
    
}
