package modelo.personajes.states;

import modelo.personajes.EstadoRatero;
import modelo.personajes.Ratero;

/**
 * Estado cuando el Ratero está buscando un objetivo
 */
public class EstadoBuscando implements EstadoRateroState {

    @Override
    public void ejecutarAccion(Ratero ratero) {
        // Lógica de Buscando
        ratero.seleccionarRicoObjetivoCercanoSinVigilancia(ratero.getRicos(), ratero.getPolicias());
        if (ratero.getRicoObjetivoActual().isPresent()) {
            ratero.incrementaVelocidad();
            ratero.cambiarEstado(new EstadoPersiguiendoObjetivo());
        } else {
            ratero.deambular();
        }
    }

    @Override
    public EstadoRatero getTipo() {
        return EstadoRatero.BUSCANDO;
    }
}
