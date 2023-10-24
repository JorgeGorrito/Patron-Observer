package patron.observer;

/**
 *
 * @author JorgeGorrito
 */

import java.util.Random;

/**
 * Constructor para la clase Sensor.
 *
 * @param nombre El nombre del sensor.
 * @param ubicacion La ubicación del sensor.
 * @param configuracionSensores Una matriz bidimensional que contiene la configuración de sensores.
 *                             La matriz debe tener un tamaño de 5 filas y 2 columnas, donde cada fila
 *                             corresponde a un atributo del sensor (CO2, NO2, opacímetro, anemómetro, luxómetro).
 *                             Las columnas representan la configuración específica para cada atributo.
 */
    
public class Sensor {
    private Random generadorAleatorio = new Random(); 
    
    private String nombre, ubicacion;
    private double []CO2 = new double[2];
    private double []NO2 = new double[2];
    private double []opacimetro = new double[2];
    private double []anenometro = new double[2];
    private double []luxometro = new double[2];
    
    public Sensor(String nombre, String ubicacion, double [][]configuracionSensores){
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.CO2 = configuracionSensores[0];
        this.NO2 = configuracionSensores[1];
        this.opacimetro = configuracionSensores[2];
        this.anenometro = configuracionSensores[3];
        this.luxometro = configuracionSensores[4];
    }
    
    public double obtenerLuxometro(){
       return this.generadorAleatorio.nextDouble(this.luxometro[0], this.luxometro[1]);
    }

    public double obtenerAnenometro(){
       return this.generadorAleatorio.nextDouble(this.anenometro[0], this.anenometro[1]);
    }
    
    public double obtenerOpacimetro(){
       return this.generadorAleatorio.nextDouble(this.opacimetro[0], this.opacimetro[1]);
    }
    
    public double obtenerNO2(){
       return this.generadorAleatorio.nextDouble(this.NO2[0], this.NO2[1]);
    }
    
    public double obtenerCO2(){
       return this.generadorAleatorio.nextDouble(this.CO2[0], this.CO2[1]);
    }
    
    public String obtenerNombre(){
        return this.nombre;
    }
    
    public String obtenerUbicacion(){
        return this.ubicacion;
    }
}
