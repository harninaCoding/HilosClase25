package modelo.personajes.states;

import static utiles.ShowMessages.showMessage;

import modelo.escenario.Posicion;
import modelo.personajes.EstadoRatero;
import modelo.personajes.Ratero;
import modelo.personajes.Rico;


/**
 * Estado cuando el Ratero está persiguiendo a un Rico objetivo
 */
public class EstadoPersiguiendoObjetivo implements EstadoRateroState {


    @Override
    public void ejecutarAccion(Ratero ratero) {
        // Lógica de PersiguiendoObjetivo
        if (ratero.getRicoObjetivoActual().isEmpty()) {
            ratero.cambiarEstado(new EstadoBuscando());
            return;
        }

        Rico rico = ratero.getRicoObjetivoActual().get();
        Posicion destino = rico.getPosicion();

        if (ratero.getPosicion().isAlcanzado(destino)) {
            ratero.decrementaVelocidad();
            if (!ratero.controlarPolicia(ratero.getPolicias())) {
                boolean perseguido = ratero.atracar();
                ratero.getRicosRobados().add(rico);
                showMessage("Ratero:atracar", ratero.getPosicion().toString(), false);
                if (perseguido) {
                    ratero.alarma();
                    ratero.cambiarEstado(new EstadoHuyendo());
                } else if (ratero.haTerminadoJornada()) {
                    ratero.cambiarEstado(new EstadoHuyendo());
                } else {
                    // Robo exitoso sin alarma y aun quiere mas
                    ratero.setRicoObjetivoActual(null);
                    ratero.cambiarEstado(new EstadoBuscando());
                }
            } else {
                // Policia cerca, abortar
                ratero.setRicoObjetivoActual(null);
                ratero.cambiarEstado(new EstadoBuscando());
            }
        } else {
            ratero.moverA(destino);
        }
    }

    @Override
    public EstadoRatero getTipo() {
        return EstadoRatero.PERSIGUIENDO_OBJETIVO;
    }
}
