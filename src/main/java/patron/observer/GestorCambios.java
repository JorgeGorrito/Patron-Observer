package patron.observer;

/**
 *
 * @author JorgeGorrito
 */

import java.util.List;

public class GestorCambios {
    private final FuenteDatos fuenteDatos = FuenteDatos.obtenerFuenteDatos();
    private static GestorCambios gestorCambios = null;
    
    private GestorCambios(){}
    
    public static GestorCambios obtenerGestorCambios(){
        if (gestorCambios == null) gestorCambios = new GestorCambios();
        return gestorCambios;
    }
    
    public void registrarObservable(IObservador observador){
        this.fuenteDatos.agregarObservador(observador);
    }
    
    public void eliminarObservador(IObservador observador){
        this.fuenteDatos.eliminarObservador(observador);
    }
    
    public List<DatosSensorDTO> cargarLecturasSensores(){
        return fuenteDatos.obtenerHistoricoLecturasSensores();
    }
    
    public DatosSensorDTO cargarUltimaLectura(){
        return this.fuenteDatos.obtenerUltimaLectura();
    } 
    
    public List<RegistroDatosSensores> cargarRegistroDatosSensores(){
        return this.fuenteDatos.obtenerRegistroDatosSensores();
    }
    
    public List<DatosSensorDTO> cargarPromedioLecturasSensores(){
        return this.fuenteDatos.obtenerPromedioLecturas();
    }
}
