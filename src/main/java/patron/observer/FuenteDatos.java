package patron.observer;

/**
 *
 * @author JorgeGorrito
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class FuenteDatos extends AbstractObservable {
    private List<Sensor> sensores = new ArrayList<>();
    
    private List<DatosSensorDTO> lecturasSensores = new ArrayList<>();
    private List<RegistroDatosSensores> registroDatosSensores = new ArrayList<>();
    private List<DatosSensorDTO> promedioLecturas = new ArrayList<>();
    
    
    // Patron Singleton
    private static FuenteDatos fuenteDatos = null;
    
    public static FuenteDatos obtenerFuenteDatos(){
        if (fuenteDatos == null){
            fuenteDatos = new FuenteDatos();
        }
        return fuenteDatos;
    }
    
    // Funcionamiento principal
    public DatosSensorDTO obtenerUltimaLectura(){
        DatosSensorDTO ultimaLectura = null;
        int numeroRegistros = this.lecturasSensores.size();
        
        if (numeroRegistros > 0) 
            ultimaLectura = this.lecturasSensores.get(numeroRegistros-1);
        
        return new DatosSensorDTO(ultimaLectura); // Se envia una copia no la referencia
    }
    
    public List<RegistroDatosSensores> obtenerRegistroDatosSensores(){
        return Collections.unmodifiableList(this.registroDatosSensores);
    }
    
    public List<DatosSensorDTO> obtenerPromedioLecturas(){
        return Collections.unmodifiableList(this.promedioLecturas);
    }
    
    public List<DatosSensorDTO> obtenerHistoricoLecturasSensores(){
        return Collections.unmodifiableList(this.lecturasSensores);
    }
    
    private void calcularPromedio(DatosSensorDTO nuevaLectura) {
        int indexSensorBuscado = this.promedioLecturas.indexOf(nuevaLectura);
        DatosSensorDTO promedioSensor = this.promedioLecturas.get(indexSensorBuscado);
        RegistroDatosSensores registroSensor = this.registroDatosSensores.get(indexSensorBuscado);
        promedioSensor.modificarFecha(nuevaLectura.obtenerFecha());
        promedioSensor.modificarHora(nuevaLectura.obtenerHora());
        promedioSensor.modificarCO2((promedioSensor.obtenerCO2() * ((registroSensor.obtenerCantidad()-1.0)/registroSensor.obtenerCantidad())) + (nuevaLectura.obtenerCO2()/registroSensor.obtenerCantidad()));
        promedioSensor.modificarNO2(promedioSensor.obtenerNO2() * ((registroSensor.obtenerCantidad()-1.0)/registroSensor.obtenerCantidad()) + (nuevaLectura.obtenerNO2()/registroSensor.obtenerCantidad()));
        promedioSensor.modificarOpacimetro(promedioSensor.obtenerOpacimetro()* ((registroSensor.obtenerCantidad()-1.0)/registroSensor.obtenerCantidad()) + (nuevaLectura.obtenerOpacimetro()/registroSensor.obtenerCantidad()));
        promedioSensor.modificarAnenometro(promedioSensor.obtenerAnenometro()* ((registroSensor.obtenerCantidad()-1.0)/registroSensor.obtenerCantidad()) + (nuevaLectura.obtenerAnenometro()/registroSensor.obtenerCantidad()));
        promedioSensor.modificarLuxometro(promedioSensor.obtenerLuxometro()* ((registroSensor.obtenerCantidad()-1.0)/registroSensor.obtenerCantidad()) + (nuevaLectura.obtenerLuxometro()/registroSensor.obtenerCantidad()));               
    }
    
    public void realizarLecturasSensores(){
        Random aleatorio = new Random();
        
        String fecha, hora;
        int indexSensor;
        for (Sensor sensor : this.sensores){
            if (aleatorio.nextInt(0, 10)%2==0) continue; // 50% de probabilidad de que se genere reporte del sensor
            try{
                Thread.sleep(aleatorio.nextLong(500, 4500), 0);

                // Obtiene la fecha y hora actual
                LocalDateTime fechaHoraActual = LocalDateTime.now();

                // Formatea la fecha y la hora en cadenas separadas
                DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");

                fecha = fechaHoraActual.format(formatoFecha);
                hora = fechaHoraActual.format(formatoHora);
                
                DatosSensorDTO nuevaLectura = new DatosSensorDTO(
                        sensor.obtenerNombre(),
                        sensor.obtenerUbicacion(),
                        fecha,
                        hora,
                        sensor.obtenerCO2(),
                        sensor.obtenerNO2(),
                        sensor.obtenerOpacimetro(),
                        sensor.obtenerAnenometro(),
                        sensor.obtenerLuxometro()
                        );
                
                this.lecturasSensores.add(nuevaLectura);
                indexSensor = this.registroDatosSensores.indexOf(new RegistroDatosSensores(nuevaLectura.obtenerNombre()));
                this.registroDatosSensores.get(indexSensor).incrementar();
                this.calcularPromedio(nuevaLectura);
                
                
                this.notificarObservadores();
            } catch(Exception e){
                    e.printStackTrace();
            }
        }
    }
    
    public void agregarSensor(Sensor sensor){
        this.sensores.add(sensor);
        this.registroDatosSensores.add(new RegistroDatosSensores(sensor.obtenerNombre()));
        this.promedioLecturas.add(new DatosSensorDTO(
                sensor.obtenerNombre(),
                sensor.obtenerUbicacion(),
                "",
                "",
                0,
                0,
                0,
                0,
                0));
    }
    
    public void eliminarSensor(Sensor sensor){
        this.sensores.remove(sensor);
        this.promedioLecturas.remove(new DatosSensorDTO(sensor.obtenerNombre(), "", "", "", 0, 0, 0, 0, 0));
        this.registroDatosSensores.remove(new RegistroDatosSensores(sensor.obtenerNombre()));
    }
   
    public int obtenerCantidadSensores(){
        return this.sensores.size();
    }
}