package modelo.personajes;

import static utiles.ShowMessages.showMessage;
import static utiles.WasteTime.wasteTime;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Callable;

import modelo.escenario.Barrio;
import modelo.escenario.Posicion;
import modelo.interfaces.Accionable;
import modelo.interfaces.Deambulante;

public class Rico implements Deambulante, Accionable, Callable<Integer> {
	private static int idActual = 1;
	private int id;
	private int bien = 100;
	private Posicion posicionActual;
	private int velocidad;
	private boolean activo = true;
	private int pasos = 0;
	private int pasosMaximos = 1000;
	private Optional<Ratero> rateroAtracador = Optional.empty();

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public Rico() {
		super();
		id = idActual++;
		this.posicionActual = Barrio.Kensington.getPosicionAleatoriaEnBarrio();
	}

	//////////////////////////////// metodos de implementacion especifica
	@Override
	public Integer call() throws Exception {
		showMessage("Rico:call", String.valueOf(id), true);
		int bienInicial = bien;
		
		while (activo) {
			hacerUnMovimiento();
			wasteTime(1000); // Pausa entre movimientos
		}
		
		// Si bien = 0, fue robado, retorna el dinero perdido
		// Si bien > 0, volvió a casa sin ser robado, retorna 0
		showMessage("Rico:call ending", String.valueOf(id), true);
		return (bien == 0) ? bienInicial : 0;
	}

	@Override
	public void hacerUnMovimiento() {
		this.posicionActual = deambula(this.posicionActual, Barrio.Kensington);
		showMessage("Rico:hacerUnMovimiento", String.valueOf(id), false);
		decideVolverACasa();
	}

	private void decideVolverACasa() {
		pasos++;
		if (activo && pasos >= pasosMaximos) {
			activo = false;
			showMessage("Rico:decideVolverACasa sin ser atracado ", String.valueOf(id), false);
		}
	}

	/**
	 * Es el metodo al que llama el ratero para que atraque al rico
	 * 
	 * @param ratero
	 * @return true si ha avisado a la policia, false en caso contrario
	 */
	public boolean atracando(Ratero ratero) {
		bien = 0;
		boolean atracado = false;
		if (notaRobo()) {
			rateroAtracador = Optional.of(ratero);
			atracado = true;
			pcs.firePropertyChange("atracado", null, ratero);
		}
		showMessage("Rico:atracado ", String.valueOf(id), true);
		activo = false;
		return atracado;
	}

	///////////////////////////////// Metodos de get y set

	public Optional<Ratero> getRateroAtracador() {
		return rateroAtracador;
	}

	private boolean notaRobo() {
		return new Random().nextBoolean();
	}

	public int getBien() {
		return bien;
	}

	public void setBien(int bien) {
		this.bien = bien;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getId() {
		return id;
	}

	public boolean isActivo() {
		return activo;
	}

	@Override
	public int getVelocidad() {
		return velocidad;
	}

	@Override
	public Posicion getPosicion() {
		return posicionActual;
	}

	@Override
	public void setPosicion(Posicion posicion) {
		this.posicionActual = posicion;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

}
