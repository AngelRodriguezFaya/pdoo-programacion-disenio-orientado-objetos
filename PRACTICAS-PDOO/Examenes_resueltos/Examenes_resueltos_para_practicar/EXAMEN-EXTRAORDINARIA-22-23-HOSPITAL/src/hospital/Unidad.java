
package hospital;

/**
 *
 * @author angel_rodriguez
 */
public abstract class Unidad {
    private static final int MIN_FACULTATIVOS = 2;
    private int planta;
    private int minFacultativos;

    public Unidad(int planta, int minFacultativos) {
        this.planta = planta;
        if(minFacultativos < MIN_FACULTATIVOS)
            this.minFacultativos = MIN_FACULTATIVOS;
        else  
            this.minFacultativos = minFacultativos;
    }

    public Unidad(int planta) {
        this(planta, MIN_FACULTATIVOS);
    }

    public int getPlanta() {
        return planta;
    }

    public int getMinFacultativos() {
        return minFacultativos;
    }

    public static int getMIN_FACULTATIVOS() {
        return MIN_FACULTATIVOS;
    }

    public void setPlanta(int planta) {
        this.planta = planta;
    }    
    
    public abstract int personal();   
    
    public abstract String getTipo();
    
}
