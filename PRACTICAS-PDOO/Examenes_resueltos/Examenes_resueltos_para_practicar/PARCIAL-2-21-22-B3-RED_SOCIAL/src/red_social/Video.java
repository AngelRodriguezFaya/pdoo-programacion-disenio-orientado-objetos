
package red_social;

/**
 *
 * @author angel_rodriguez
 */
public class Video extends Contenido{
    private int duracion;

    public Video(int duration, int peso, String archivo) {
        super(peso, archivo);
        this.duracion = duration;
    }
    
    public Video(Video otro){
        this(otro.duracion, otro.getPeso()/otro.duracion, otro.getArchivo());   
        for(Usuario usu: otro.getLikes()){
            super.addLike(usu); // No ponemos new Usuario porque es la misma referencia
            // al fin de al cabo, es el mismo usuario, por eso no creamos una nueva.
        }
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    
    @Override
    public void visualizar(){
        System.out.println("Vídeo: Peso: " + getPeso() +
                "\n       Archivo: " + getArchivo() +
                "\n       Duracion: " + duracion + "\n");
    }
    
    @Override
    public int getPeso(){
        return super.getPeso()*duracion;
    }
    
    //Antes de hacer este método hay que implementar Clone y que Contenido implemente Cloneable()
    @Override
    public Contenido copia(){
        return (new Video(this));
    }
}
