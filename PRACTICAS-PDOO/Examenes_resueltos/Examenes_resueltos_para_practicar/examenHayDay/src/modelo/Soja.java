/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author angel_rodriguez
 */
public class Soja extends Cultivo{
    public static final String ICONO = "/img/soja.png";
    public static final int PRECIO_VENTA = 3;
    public static final int EXPERIENCIA = 2;
    public static final int DURACION = 5;
    public static final int RIEGO = 20;
    
    public Soja(){
        super(RIEGO, TipoCultivo.SOJA, ICONO, DURACION,
                EXPERIENCIA, PRECIO_VENTA);
    }
    
    @Override
    public void enProduccion(){
        System.out.println("Cultivando " + getTipo());
    }
    
    
    
    
    
}
