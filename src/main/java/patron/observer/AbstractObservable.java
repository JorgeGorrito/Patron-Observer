package patron.observer;

/**
 *
 * @author JorgeGorrito
 */

import java.util.List;
import java.util.ArrayList;

public abstract class AbstractObservable implements IObservable{
    private final List<IObservador> observadores = new ArrayList<>();
    
    @Override
    public void agregarObservador(IObservador observador){
        this.observadores.add(observador);
    }
    
    @Override
    public void eliminarObservador(IObservador observador){
        this.observadores.remove(observador);
    }
    
    @Override 
    public void notificarObservadores(){
        for (IObservador observador: this.observadores){
            observador.notificar();
        }
    }
}
