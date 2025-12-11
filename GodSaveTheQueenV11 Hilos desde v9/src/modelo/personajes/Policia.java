package modelo.personajes;

import static utiles.ShowMessages.showMessage;

import java.util.List;

import modelo.escenario.Barrio;
import modelo.escenario.Posicion;
import modelo.interfaces.Accionable;
import modelo.interfaces.Deambulante;

public class Policia implements Deambulante, Accionable, Runnable {
	private Posicion posicionActual = Barrio.Kensington.getPosicionAleatoriaEnBarrio();
	private int velocidad = 1;
	private int radioAccion = 10;
	private static int idActual = 1;
	private int id;
	private boolean enPersecucion = false;
	private Ratero rateroObjetivo = null;
	private List<Rico> ricos; // Para verificar fin de día
	private int capturas = 0; // Contador de capturas

	public Policia(List<Rico> ricos) {
		this.id = idActual++;
		this.ricos = ricos;
	}

	// Constructor sin argumentos para compatibilidad con tests
	public Policia() {
		this.id = idActual++;
	}

	@Override
	public void run() {
		showMessage("Policia:run", String.valueOf(id), true);
		while (!ricos.isEmpty()) {
			hacerUnMovimiento();
		}
		showMessage("Policia:run ending", String.valueOf(id), true);
	}

	@Override
	public void hacerUnMovimiento() {
		showMessage("Policia: hacer movimiento ", this.posicionActual.toString(), false);
		if (enPersecucion && rateroObjetivo != null) {
			perseguir();
		} else {
			patrullar();
		}
	}

	/**
	 * Cuando la policia se le dice que esta en persecucion
	 * cambia la forma en la que hacerUnMovimiento
	 * 
	 * @param ratero
	 */
	public void iniciarPersecucion(Ratero ratero) {
		this.enPersecucion = true;
		this.rateroObjetivo = ratero;
	}

	/**
	 * se mueve paseando (velocidad baja) por el barrio de los ricos
	 * 
	 * @param posicionActual en la que se encuentra el policia
	 * @param barrio         en el que se encuentra el policia
	 */
	private void patrullar() {
		this.velocidad = 1;
		setPosicion(deambula(posicionActual, Barrio.Kensington));
	}

	/**
	 * se mueve a toda velocidad por el barrio de los ricos
	 * cuando es avisado por el cuerpo de policia
	 * 
	 * @param posicionActual en la que se encuentra el policia
	 * @param barrio         en el que se encuentra el policia
	 */
	private void perseguir() {
		this.velocidad = 1;
		boolean alcanzado = false;
		posicionActual.moveTo(rateroObjetivo.getPosicion(), velocidad);
		showMessage("Policia:perseguir", posicionActual.toString(), false);
		alcanzado = posicionActual.isAlcanzado(rateroObjetivo.getPosicion());

		if (alcanzado) {
			showMessage("Policia:perseguir", "Alcanzado", false);
			encarcelarRatero(rateroObjetivo);
			finalizarPersecucion();
		} else if (!Barrio.Kensington.isInTo(rateroObjetivo.getPosicion())) {
			finalizarPersecucion();
		}
	}

	private void finalizarPersecucion() {
		this.enPersecucion = false;
		this.rateroObjetivo = null;
	}

	public void encarcelarRatero(Ratero ratero) {
		ratero.encarcelar();
		capturas++;
	}

	public Posicion getPosicion() {
		return posicionActual;
	}

	public void setPosicion(Posicion posicion) {
		this.posicionActual = posicion;
	}

	public int getRadioAccion() {
		return radioAccion;
	}

	public void setRadioAccion(int radioAccion) {
		this.radioAccion = radioAccion;
	}

	@Override
	public int getVelocidad() {
		return velocidad;
	}

	/**
	 * Verifica si quedan ricos activos
	 */
	private boolean hayRicosActivos() {
		if (ricos == null) {
			return false;
		}
		synchronized (ricos) {
			for (Rico rico : ricos) {
				if (rico.isActivo()) {
					return true;
				}
			}
		}
		return false;
	}

	public int getId() {
		return id;
	}

	public void setRicos(List<Rico> ricos) {
		this.ricos = ricos;
	}
}
