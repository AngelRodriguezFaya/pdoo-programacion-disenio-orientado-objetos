
package red_social;

/**
 *
 * @author angel_rodriguez
 */
public class Foto extends Contenido{
    private int alto; 
    private int ancho;

    public Foto(int alto, int ancho, int peso, String archivo) {
        super(peso, archivo);
        this.alto = alto;
        this.ancho = ancho;
    }
    
    public Foto(Foto otra){
        this(otra.alto, otra.ancho, otra.getPeso(), otra.getArchivo());
        for(Usuario usu: otra.getLikes()){
            super.addLike(usu); // No ponemos new Usuario porque es la misma referencia
            // al fin de al cabo, es el mismo usuario, por eso no creamos una nueva.
        }
    }

    public int getAlto() {
        return alto;
    }
 
    public int getAncho() {
        return ancho;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }    
    
    @Override
    public void visualizar(){
        System.out.println("Foto: Peso: " + getPeso() +
                "\n      Archivo: " + getArchivo() +
                "\n      Alto: " + alto +
                "\n      Ancho: " + ancho + "\n");
    }
    
    //Antes de hacer este método hay que implementar Clone y que Contenido implemente Cloneable()
    @Override
    public Contenido copia(){
        return (new Foto(this));
    }
    
    public void imprimir(){
        System.out.println("Imprimiendo la foto '" + getArchivo() + "'\n");
    }
    
    
    
}
