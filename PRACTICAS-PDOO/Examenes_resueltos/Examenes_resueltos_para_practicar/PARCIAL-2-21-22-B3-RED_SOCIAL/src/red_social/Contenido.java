 
package red_social;

import java.util.ArrayList;

/**
 *
 * @author angel_rodriguez
 */
public abstract class Contenido implements Cloneable{
    
    //---------------------------------
    // Atributes
    //---------------------------------
    
    private int peso;
    private String archivo;
    private ArrayList<Usuario> likes; 
    
    //---------------------------------
    // Constructors
    //---------------------------------

//    Este constructor no hace falta porque ya tenemos un metodo que añade los likes addLike(...)
//    Por lo que ahora solo hay que crear un array vacio y ya se irán añadiendo likes (Usuarios)

//    public Contenido(int peso, String archivo, ArrayList<Usuario> like) {
//        this.peso = peso;
//        this.archivo = archivo;
//        this.like = like;
//    }
    
    
    public Contenido(int peso, String archivo) {
        this.peso        = peso;
        this.archivo    = archivo;   
        this.likes      = new ArrayList<Usuario>();
    }
    
    public Contenido(Contenido c){
        this(c.peso, c.archivo);
    }
    
    //---------------------------------
    // Getters & Setters
    //---------------------------------

    public int getPeso() {
        return peso;
    }

    public String getArchivo() {
        return archivo;
    }

    public ArrayList<Usuario> getLikes() {
        return likes;
    }
    
    public void setPeso(int longitud) {
        this.peso = longitud;
    }

    public void setArchivo() {
        this.archivo = "";
    }
    
    //---------------------------------
    // Public Methods
    //---------------------------------
    
    public int numeroLikes(){
        return likes.size();
    }
    
    public void addLike(Usuario usuario){
        likes.add(usuario);
    }
    
    //---------------------------------
    // Abstract Methods
    //---------------------------------
    
    public abstract void visualizar();
    
    // Este método no viene en el diagrama pero es muy útil y sirve para copiar 
    // objetos de tipo Contenido. Se implementa en las clases hijas, por eso es abstract.
    public abstract Contenido copia();

    @Override
    protected Contenido clone() throws CloneNotSupportedException {
        return copia(); 
    }
    
    

}
