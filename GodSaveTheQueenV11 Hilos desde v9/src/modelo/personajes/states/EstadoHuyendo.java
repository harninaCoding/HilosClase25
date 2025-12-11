package modelo.personajes.states;

import modelo.escenario.Posicion;
import modelo.personajes.EstadoRatero;
import modelo.personajes.Ratero;

/**
 * Estado cuando el Ratero está huyendo a su refugio
 */
public class EstadoHuyendo implements EstadoRateroState {

    @Override
    public void ejecutarAccion(Ratero ratero) {
        // Lógica de Huyendo
        Posicion refugio = ratero.getPosicionRefugio();
        if (refugio.equals(ratero.getPosicion())) {
            ratero.decrementaVelocidad();
            ratero.cambiarEstado(new EstadoInactivo());
            ratero.mensajear();
        } else {
            ratero.moverA(refugio);
        }
    }

    @Override
    public EstadoRatero getTipo() {
        return EstadoRatero.HUYENDO;
    }
}
