package modelo.escenario;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import modelo.personajes.Policia;
import modelo.personajes.Ratero;
import modelo.personajes.Rico;

public class CuerpoPolicia implements PropertyChangeListener {
	private List<Policia> policias;

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// Cuando un Rico es atracado, recibimos la notificación
		if ("atracado".equals(evt.getPropertyName())) {
			Ratero ratero = (Ratero) evt.getNewValue();
			avisar(ratero);
		}
	}

	/**
	 * Avisa a un policia para que inicie la persecucion al ratero
	 * 
	 * @param ratero
	 */
	public void avisar(Ratero ratero) {
		Policia policia = buscarPoliciaMasCercano(ratero);
		if (policia != null) {
			policia.iniciarPersecucion(ratero);
		}
	}

	/**
	 * Busca el policia mas cercano al ratero
	 * 
	 * @param ratero
	 * @return el policia mas cercano al ratero
	 */
	private Policia buscarPoliciaMasCercano(Ratero ratero) {
		Policia policiaMasCercano = null;
		int distanciaMinima = Integer.MAX_VALUE;
		for (Policia policia : policias) {
			int distancia = policia.getPosicion().getDistanciaCuadrado(ratero.getPosicion());
			if (distancia < distanciaMinima) {
				distanciaMinima = distancia;
				policiaMasCercano = policia;
			}
		}
		return policiaMasCercano;
	}

	public List<Policia> getPolicias() {
		return policias;
	}

	public void setCuerpoPolicia(List<Rico> ricos, List<Policia> policias) {
		this.policias = policias;
		// Registrar CuerpoPolicia como observer de todos los Ricos
		for (Rico rico : ricos) {
			rico.addPropertyChangeListener(this);
		}
	}

}
