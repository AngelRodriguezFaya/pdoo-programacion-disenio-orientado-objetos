
package simulacro;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa un Volcán.
 * @author angel_rodriguez
 */
public class Volcan {
    private static final int MAX_COLADAS = 10;
    private String nombre;
    
    private List<Colada> coladas;
    
    /**
     * Constructor paramétrico PRIVADO de la clase Volcán.
     * Cómo es una composición, el constructor con parámetros que inizializa
     * las coladas debe ser privado, porque si no alguien podría crear las 
     * coladas desde el main, y ya no es una composición.
     * @param nombre    nombre del volcán
     */
    private Volcan(String nombre, List<Colada> c) {
        this.nombre = nombre;
        this.coladas = new ArrayList<Colada>(c);
    }
    
    /**
     * Constructor con un sólo parámetro de la clase Volcán.
     * @param nombre    nombre del volcán
     */
    public Volcan(String nombre){
        this(nombre, new ArrayList<Colada>());
    }
    
    /**
     * Constructor por defecto de la clase Volcán.
     */
    public Volcan(){
        this("", new ArrayList<Colada>());
    }
    
    /**
     * Constructor por copia de la clase Volcán.
     * @param v objeto de tipo Volcán.
     */
    public Volcan(Volcan v){
        this(v.nombre, v.coladas);
    }
    
    /**
     * Gets de coladas
     * @return coladas
     */
    public List<Colada> getColadas(){
        return this.coladas;
    }
    
    /**
     * Añade una colada sin parámetros a nuestro contenedor de coladas.
     */
    public void addColada(){
        boolean añadeColada = coladas.size() < MAX_COLADAS;
        if(añadeColada){
            Colada c = new Colada (0, false);
            coladas.add(c);
        }else{
            System.err.println("No se puede añadir la colada."
                    + "Se sobrepasa el máximo de coladas"); 
        }
    }
    
    /**
     * Añade una colada sin parámetros a nuestro contenedor de coladas.
     * @param longitud  longitud de la colada.
     * @param llegaMar  indica si la colada llega al mar.
     */
    public void addColada(int longitud, boolean llegaMar){
        boolean añadeColada = coladas.size() < MAX_COLADAS;
        if(añadeColada){
            Colada c = new Colada (longitud, llegaMar);
            coladas.add(c);
        }else{
            System.err.println("No se puede añadir la colada."
                    + "Se sobrepasa el máximo de coladas"); 
        }
    }
    
    /**
     * Gets de colada
     * @param colada    nºindice de la colada.
     * @return colada con nºindice colada.,
     */
    public Colada getColada(int colada){
        return coladas.get(colada);
    }
    
    /**
     * Este método devuelve la longitud total destruida por el volcán, 
     * es decir, la suma de la longitud de todas sus coladas.
     * @return suma de coaldas.
     */
    public int destruccion(){
        int suma = 0;
        for(Colada c : coladas)
            suma += c.getLongitud();
        return suma;
    }
    
    /**
     * Este método devuelve una cadena que nos indica cuantas coladas han
     * llegado al mar de todas las que tenemos.
     * @return número de coladas que han llegado al mar.
     */
    public String informacion(){
        //1.1 : coladas = getColadas()
        int llega = 0;
        for(Colada colada : coladas){   //la propia colada es ya 1.2 colada = siguiente()
            if(colada.getLlegaMar())
                llega++;
        }
        return "Han llegado " + llega + " coladas al mar.";
    }
    
}
