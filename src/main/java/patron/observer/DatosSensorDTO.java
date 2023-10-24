package patron.observer;

/**
 *
 * @author JorgeGorrito
 */

public class DatosSensorDTO {
    private String nombre, ubicacion, fecha, hora;
    private double CO2, NO2, opacimetro, anenometro, luxometro;
    
    public DatosSensorDTO(String nombre, String ubicacion, String fecha, String hora, double CO2, double NO2, double opacimetro, double anenometro, double luxometro){
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.ubicacion = ubicacion;
        this.CO2 = CO2;
        this.NO2 = NO2;
        this.opacimetro = opacimetro;
        this.anenometro = anenometro;
        this.luxometro = luxometro;
    }
    
    public DatosSensorDTO(DatosSensorDTO datosSensorDTO){
        this.nombre = datosSensorDTO.nombre;
        this.fecha = datosSensorDTO.fecha;
        this.hora = datosSensorDTO.hora;
        this.ubicacion = datosSensorDTO.ubicacion;
        this.CO2 = datosSensorDTO.CO2;
        this.NO2 = datosSensorDTO.NO2;
        this.opacimetro = datosSensorDTO.opacimetro;
        this.anenometro = datosSensorDTO.anenometro;
        this.luxometro = datosSensorDTO.luxometro;
    }

    public void modificarNombre(String nombre) {
        this.nombre = nombre;
    }

    public void modificarUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void modificarFecha(String fecha) {
        this.fecha = fecha;
    }

    public void modificarHora(String hora) {
        this.hora = hora;
    }

    public void modificarCO2(double CO2) {
        this.CO2 = CO2;
    }

    public void modificarNO2(double NO2) {
        this.NO2 = NO2;
    }

    public void modificarOpacimetro(double opacimetro) {
        this.opacimetro = opacimetro;
    }

    public void modificarAnenometro(double anenometro) {
        this.anenometro = anenometro;
    }

    public void modificarLuxometro(double luxometro) {
        this.luxometro = luxometro;
    }
    
    public String obtenerFecha(){
        return this.fecha;
    }
    
    public String obtenerHora(){
        return this.hora;
    }

    public double obtenerCO2() {
        return CO2;
    }

    public double obtenerNO2() {
        return NO2;
    }

    public double obtenerOpacimetro() {
        return opacimetro;
    }

    public double obtenerAnenometro() {
        return anenometro;
    }

    public double obtenerLuxometro() {
        return luxometro;
    }
    
    public String obtenerNombre(){
        return this.nombre;
    }
    
    public String obtenerUbicacion(){
        return this.ubicacion;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (!(obj instanceof DatosSensorDTO)) return false;
        DatosSensorDTO other = (DatosSensorDTO) obj;
        return this.nombre.equals(other.nombre);
    }
}
