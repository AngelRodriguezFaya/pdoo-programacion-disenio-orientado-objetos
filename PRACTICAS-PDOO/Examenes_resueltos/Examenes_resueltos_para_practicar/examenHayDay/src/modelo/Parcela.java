
package modelo;

import java.util.ArrayList;

/**
 *
 * @author angel_rodriguez
 */
public class Parcela {
    public static final String ICONO = "/img/parcela.png"; 
    public static final int PRECIO = 15;
    public static final int MAX_CULTIVOS = 1;
    public static final int CAPACIDAD = 2;
    public static final int EXPERIENCIA = 1;
    private ArrayList<Cultivo> cultivos;

    public Parcela() {
        this.cultivos = new ArrayList<Cultivo>();
    }

    public static int getMAX_CULTIVOS() {
        return MAX_CULTIVOS;
    }

    public static int getCAPACIDAD() {
        return CAPACIDAD;
    }

    public ArrayList<Cultivo> getCultivos() {
        return cultivos;
    }

    public void setCultivos(ArrayList<Cultivo> cultivos) {
        this.cultivos = cultivos;
    }
    
    public boolean puedeProducir(){
        return this.cultivos.size() < MAX_CULTIVOS;
    }
    
    public void producir(Cultivo cultivo){
        if(puedeProducir())
            this.cultivos.add(cultivo);
        else
            System.out.println("No se puede producir el cultivo");
    }
    
    public void recolectar(Cultivo cultivo){
        if(this.cultivos.contains(cultivo))
            this.cultivos.remove(cultivo);
    }
    
    public Cultivo getPrimerCultivo(){
        Cultivo cultivo = null;
        
        if(!this.cultivos.isEmpty()){
            cultivo = this.cultivos.get(0);
        }
        
        return cultivo;
    }
    
    public boolean estaCultivando(){
        return getPrimerCultivo() != null;
    }
    
    public boolean cultivandoTrigo(){
        return this.getPrimerCultivo().getTipo() == TipoCultivo.TRIGO;
    }
    
    public boolean cultivandoSoja(){
        return this.getPrimerCultivo().getTipo() == TipoCultivo.SOJA;
    } 
    
}
