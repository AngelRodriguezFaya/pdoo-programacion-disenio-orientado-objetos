
package platos;

import java.util.List;

/**
 *
 * @author aulas
 */
public class PlatoPrincipal extends Plato implements Clasificable{
    private static final String TIPO_COCINA_ESPECIAL = "mejicana";
    private static final int MAX_NIVEL_PICANTE = Integer.MAX_VALUE;
    private static final int MIN_NIVEL_PICANTE = Integer.MIN_VALUE;    
    private String tipoCocina;
    private int nivelPicante;

    public PlatoPrincipal(String tipoCocina, int nivelPicante, String nombre, float precio, List<String> ingredientes) {
        super(nombre, precio, ingredientes);
        this.tipoCocina = tipoCocina;
        this.nivelPicante = nivelPicante;
    }

    public PlatoPrincipal(PlatoPrincipal p){
        this(p.tipoCocina, p.nivelPicante, p.getNombre(), p.getPrecio(), p.getIngredientes());
    }
  
    public String getTipoCocina() {
        return tipoCocina;
    }

    public void setTipoCocina(String tipoCocina) {
        this.tipoCocina = tipoCocina;
    }

    public int getNivelPicante() {
        return nivelPicante;
    }

    public void setNivelPicante(int nivelPicante) {
        this.nivelPicante = nivelPicante;
    }

    @Override
    public boolean esPlatoEspecial() {
        return true;
    }

    @Override
    public int obtenerCalificacion() {
        int calificacion = 0;
        
        if(tipoCocina.equals(TIPO_COCINA_ESPECIAL) && nivelPicante == MAX_NIVEL_PICANTE)
            calificacion = 1;
        else if( nivelPicante == MIN_NIVEL_PICANTE && !(tipoCocina.equals(TIPO_COCINA_ESPECIAL)) )
            calificacion = 2;
        
        return calificacion;
    }

    @Override
    public String getTipoPlato() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Plato copia() {
        return new PlatoPrincipal(this);
    }
    
    
  
}
