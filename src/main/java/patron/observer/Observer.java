package patron.observer;

/**
 *
 * @author JorgeGorrito
 */

import java.util.Random;

public class Observer {
    public static void main(String []args){
        // Configurando los sensores 
        FuenteDatos fuenteDatos = FuenteDatos.obtenerFuenteDatos();
        fuenteDatos.agregarSensor(new Sensor("Sensor 1", "192-10-72", new double[][]{{0.0, 0.01},{0.0, 0.02},{0.0, 25.0},{0.0, 70.0},{0.0, 7.0}}));
        fuenteDatos.agregarSensor(new Sensor("Sensor 2", "180-20-31", new double[][]{{0.0, 0.03},{0.0, 0.03},{0.0, 20.0},{0.0, 50.0},{0.0, 9.0}}));
        fuenteDatos.agregarSensor(new Sensor("Sensor 3", "192-40-98", new double[][]{{0.0, 0.01},{0.0, 0.02},{0.0, 25.0},{0.0, 70.0},{0.0, 7.0}}));
        
        // Inicializando los elementos para visualizar la información
        VistaConsola vistaConsola = new VistaConsola();
        VistaGraficos vistaGraficos = new VistaGraficos();
        VistaTablaGUI vistaTabla = new VistaTablaGUI();
        
        while(true)
            fuenteDatos.realizarLecturasSensores();
    }
}
