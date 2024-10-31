
package platos;

import java.util.List;

/**
 *
 * @author aulas
 */
public class Postre extends Plato{
    private static final int NIVEL_MINIMO_AZUCAR = 0;
    public static final int NIVEL_PELIGROSO_AZUCAR = 100;
    private int nivelAzucar;

    public Postre(int nivelAzucar, String nombre, float precio, List<String> ingredientes) {
        super(nombre, precio, ingredientes);
        this.nivelAzucar = nivelAzucar;
    }

    public int getNivelAzucar() {
        return nivelAzucar;
    }

    public void setNivelAzucar(int nivelAzucar) {
        this.nivelAzucar = nivelAzucar;
    }
    
    public boolean noAptoParaDiabeticos(){
        return nivelAzucar > NIVEL_PELIGROSO_AZUCAR;
    }

    @Override
    public boolean esPlatoEspecial() {
        return nivelAzucar == NIVEL_MINIMO_AZUCAR;
    }
    
    @Override
    public String getTipoPlato() {
        return this.getClass().getSimpleName();
    }

//    @Override
//    public Plato copia() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    
    
}
