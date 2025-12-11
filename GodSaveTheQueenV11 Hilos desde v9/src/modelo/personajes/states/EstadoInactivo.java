package modelo.personajes.states;

import modelo.personajes.EstadoRatero;
import modelo.personajes.Ratero;

/**
 * Estado cuando el Ratero está inactivo (en cárcel o con botín suficiente)
 */
public class EstadoInactivo implements EstadoRateroState {

    @Override
    public void ejecutarAccion(Ratero ratero) {
        // Lógica de Inactivo
        ratero.mensajear();
    }

    @Override
    public EstadoRatero getTipo() {
        return EstadoRatero.INACTIVO;
    }
}
