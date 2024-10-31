
package modelo;

/**
 *
 * @author angel_rodriguez
 */
public class Trigo extends Cultivo{
    public static final String ICONO = "/img/trigo.png";
    public static final int PRECIO_VENTA = 2;
    public static final int EXPERIENCIA = 1;
    public static final int DURACION = 2;
    public static final int RIEGO = 10;

    public Trigo() {
        super(RIEGO, TipoCultivo.TRIGO, ICONO, DURACION,
                EXPERIENCIA, PRECIO_VENTA);
    }

    @Override
    public void setRiego(int riego) {
        if(riego <= RIEGO)
            super.setRiego(riego);
        else
            System.out.println("El parámetro riego es mayor que 10");
    }
    
    @Override
    public void enProduccion(){
        System.out.println("Cultivando " + getTipo());
    }
    
        
}
