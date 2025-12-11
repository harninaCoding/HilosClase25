package modelo.interfaces;

import modelo.escenario.Barrio;
import modelo.escenario.Posicion;

public interface Deambulante extends Movible {
	/**
	 * Realiza un movimiento en el barrio.
	 * 
	 * @param posicion es la posicion en la que se encuentra el personaje
	 * @param barrio   es el barrio en el que se encuentra el personaje
	 * @return la nueva posicion del personaje
	 */
	public default Posicion deambula(Posicion posicion, Barrio barrio) {
		return posicion.deambula(barrio);
	};
}
