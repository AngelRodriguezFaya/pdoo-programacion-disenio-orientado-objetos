
package materiales;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author angel_rodriguez
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //a
        ArrayList<Material> materiales = new ArrayList<Material>();
        materiales.addAll(Arrays.asList(
                       new Libro("01234", "El Principito", 309),
                       new Libro("56789", "Aprende Java", 290),
                       new Revista("diaria", "Cocina", 23),
                       new Revista("semanal", "F1", 39)));
        
        //b
        Vista vista = new Vista();
        Controlador controlador = new Controlador(vista, materiales);
        
        //c
        System.out.println("Total de materiales que se han creado en el sistema: "
                + Material.getTotal());
        
        int totalLibros = 0;
        
        for(Material material: materiales){
            
            if(material instanceof Libro)
                totalLibros++;
            
            else if(material instanceof Revista){
                if(((Revista) material).esTop())   
                    System.out.println("Es top la revista: " + material.getTitulo());   //e
            }
            
            else if(material != null && material.esEdicionEspecial())
                System.out.println(material.getTitulo() + " es edición especial.-----------------------"); //f  
        
        }
        
        System.out.println("El total de libros que hay en el array materiales es: " //d
                + totalLibros + "\n\n");
        
        //EJERCICIO EXTRA
        Usuario usuario1 = new Usuario("mi-nombre", materiales);
        Usuario usuario2 = new Usuario(usuario1);
        
        for(int i = 0; i < materiales.size(); i++){
            System.out.println("Titulo: " + materiales.get(i).getTitulo() + "\tnumPaginas: " +
                    materiales.get(i).getNumPaginas());
        }
        
        
    
    }

}