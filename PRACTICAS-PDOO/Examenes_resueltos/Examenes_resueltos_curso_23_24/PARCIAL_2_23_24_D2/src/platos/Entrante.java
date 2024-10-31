
package platos;

import java.util.List;

/**
 *
 * @author aulas
 */
public class Entrante extends Plato{
    private static final String entranteEspecial = "exotico";
    private String tipo;

    public Entrante(String tipo, String nombre, float precio, List<String> ingredientes) {
        super(nombre, precio, ingredientes);
        this.tipo = tipo;
    }
    
//    public Entrante(Entrante e){
//        this(e.tipo, e.getNombre(), e.getPrecio(),  e.getIngredientes());
//    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean esPlatoEspecial() {
        return this.tipo.equals(entranteEspecial);
    }
    
    @Override
    public String getTipoPlato() {
        return this.getClass().getSimpleName();
    }
//
//    @Override
//    public Plato copia() {
//        
//    }
    
    
}
