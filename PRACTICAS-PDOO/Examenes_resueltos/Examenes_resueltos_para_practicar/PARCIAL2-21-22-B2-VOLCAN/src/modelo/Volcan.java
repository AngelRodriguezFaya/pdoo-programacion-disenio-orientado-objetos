
package modelo;

import java.util.ArrayList;

/**
 *
 * @author angel_rodriguez
 */
public class Volcan {
    private static final int MAX_FENOMENOS = 10;
    private String nombre;
    
    private ArrayList<Fenomeno> fenomenos;

    private Volcan(String nombre, ArrayList<Fenomeno> fenomenos) {
        this.nombre = nombre;
        this.fenomenos = fenomenos;
    }

    public Volcan(String nombre) {
        this(nombre, new ArrayList<Fenomeno>());
    }
    
    public Volcan(Volcan v){
        this.nombre = v.nombre;
        this.fenomenos = new ArrayList<Fenomeno>();
        
        for(Fenomeno fenomeno: v.fenomenos){
            if(fenomeno instanceof Colada){
                Colada colada = (Colada) fenomeno;
                this.fenomenos.add(new Colada(colada));
            }else if(fenomeno instanceof Fajana){
                Fajana fajana = (Fajana) fenomeno;
                this.fenomenos.add(new Fajana(fajana));
            }
        }
        
    }

    public ArrayList<Fenomeno> getFenomenos() {
        return fenomenos;
    }
    
    public void addColada(int longitud, boolean llegaMar, String nombre){
        if(this.fenomenos.size() < 10){
            Colada nuevaColada = new Colada(longitud, llegaMar, nombre);
            this.fenomenos.add(nuevaColada);
        }else
            System.out.println("El volcan " + nombre + " ya tiene el maximo de "
            + "fenomenos (10).");
    }
    
    public void addFajana(int largo, int ancho, String nombre){
        if(this.fenomenos.size() < 10){
            Fajana nuevaFajana = new Fajana(largo, ancho, nombre);
            this.fenomenos.add(nuevaFajana);
        }else
            System.out.println("El volcan " + nombre + " ya tiene el maximo de "
            + "fenomenos (10).");
    }
    
    public Fenomeno getFenomeno(int fenomeno){
        return this.fenomenos.get(fenomeno);
    }

    public String getNombre() {
        return nombre;
    }
    
    
    public int destruccion(){
        int suma = 0;
        for(Fenomeno f: this.fenomenos){
            suma += f.destruccion();            
        }
        return suma;
    }    
    
}
