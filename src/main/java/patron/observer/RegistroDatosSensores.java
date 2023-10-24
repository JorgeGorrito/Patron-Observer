package patron.observer;

/**
 *
 * @author JorgeGorrito
 */
public class RegistroDatosSensores {
    private int cantidad;
    private String nombreSensor;
    
    public RegistroDatosSensores(String nombreSensor){
        this.cantidad = 0;
        this.nombreSensor = nombreSensor;
    }
    
    public void incrementar(){
        this.cantidad+=1;
    }
    
    public String obtenerNombre(){
        return this.nombreSensor;
    }
    
    public int obtenerCantidad(){
        return this.cantidad;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (!(obj instanceof RegistroDatosSensores)) return false;
        return this.nombreSensor.equals(((RegistroDatosSensores)obj).nombreSensor);
    }
}
