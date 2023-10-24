package patron.observer;

/**
 *
 * @author JorgeGorrito
 */
public interface IObservable {
    void agregarObservador(IObservador observador);
    void eliminarObservador(IObservador observador);
    void notificarObservadores();
}
