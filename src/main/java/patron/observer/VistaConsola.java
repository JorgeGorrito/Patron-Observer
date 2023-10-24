package patron.observer;

/**
 *
 * @author JorgeGorrito
 */

import java.util.List;

public class VistaConsola implements IObservador {
    private GestorCambios gestorCambios;
    public VistaConsola(){
        this.gestorCambios = GestorCambios.obtenerGestorCambios();
        this.gestorCambios.registrarObservable(this);
    }
    
    @Override
    public void notificar(){
       System.out.println("Se actualizaron datos!!!");  
       System.out.println("Historico de lecturas de los sensores");
       this.mostrarHistoricoDeCambios();
       System.out.println("\nPromedio de las lecturas de los sensores");
       this.mostrarPromedioLecturasSensores();
    }
    
    private void mostrarPromedioLecturasSensores(){
         // Imprimiendo el header de la tabla
        System.out.printf("%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\n", "Fecha","Hora", "CO2", "NO2", "Opacimetro", "Anenometro", "Luxometro", "Ubicacion", "Sensor");
    
        for(DatosSensorDTO lectura: this.gestorCambios.cargarPromedioLecturasSensores()){
            System.out.printf("%-15s\t", lectura.obtenerFecha());
            System.out.printf("%-15s\t", lectura.obtenerHora());
            System.out.printf("%-15s\t", String.format("%.3f", lectura.obtenerCO2()));
            System.out.printf("%-15s\t", String.format("%.3f", lectura.obtenerNO2()));
            System.out.printf("%-15s\t", String.format("%.3f", lectura.obtenerOpacimetro()));
            System.out.printf("%-15s\t", String.format("%.3f", lectura.obtenerAnenometro()));
            System.out.printf("%-15s\t", String.format("%.3f", lectura.obtenerLuxometro()));
            System.out.printf("%-15s\t", lectura.obtenerUbicacion());
            System.out.printf("%-15s\t", lectura.obtenerNombre());
            System.out.println();
        }
        System.out.println();
    }
    
    private void mostrarHistoricoDeCambios(){
        // Imprimiendo el header de la tabla
        System.out.printf("%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\n", "Fecha","Hora", "CO2", "NO2", "Opacimetro", "Anenometro", "Luxometro", "Ubicacion", "Sensor");
        
        List<DatosSensorDTO> historico = this.gestorCambios.cargarLecturasSensores();
        for(DatosSensorDTO lectura: historico){
            System.out.printf("%-15s\t", lectura.obtenerFecha());
            System.out.printf("%-15s\t", lectura.obtenerHora());
            System.out.printf("%-15s\t", String.format("%.3f", lectura.obtenerCO2()));
            System.out.printf("%-15s\t", String.format("%.3f", lectura.obtenerNO2()));
            System.out.printf("%-15s\t", String.format("%.3f", lectura.obtenerOpacimetro()));
            System.out.printf("%-15s\t", String.format("%.3f", lectura.obtenerAnenometro()));
            System.out.printf("%-15s\t", String.format("%.3f", lectura.obtenerLuxometro()));
            System.out.printf("%-15s\t", lectura.obtenerUbicacion());
            System.out.printf("%-15s\t", lectura.obtenerNombre());
            System.out.println();
        }
        System.out.println("\nNumero de registro: " + historico.size() + "\n");
    }
}
