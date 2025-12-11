package modelo.personajes;

import static utiles.ShowMessages.showMessage;
import static utiles.WasteTime.wasteTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import modelo.escenario.Barrio;
import modelo.escenario.Posicion;
import modelo.estadisticas.InfoRatero;
import modelo.estadisticas.InfoRateroAdapter;
import modelo.interfaces.Accionable;
import modelo.interfaces.Deambulante;
import modelo.personajes.states.EstadoBuscando;
import modelo.personajes.states.EstadoInactivo;
import modelo.personajes.states.EstadoRateroState;

public class Ratero implements Accionable, Deambulante, Callable<InfoRatero> {
	private ArrayList<Rico> ricosRobados = new ArrayList<>();

	private static int idActual = 1;
	private int id;
	private Posicion posicionActual;
	private Posicion posicionRefugio;
	private int velocidad = 1;
	private int botin = 0;
	private int botinConfiscado = 0; // Dinero confiscado cuando el ratero es encarcelado
	private int botinSuficiente = 100;
	private int radioBusqueda;
	private Optional<Rico> ricoObjetivoActual = Optional.empty();
	private List<Rico> ricos;
	private List<Policia> policias;
	private boolean enCarcel = false;

	private EstadoRateroState estadoActual;

	public Ratero(Posicion posicion, int radioBusqueda, int botinSuficiente, List<Rico> ricos, List<Policia> policias) {
		super();
		this.radioBusqueda = radioBusqueda;
		this.botinSuficiente = botinSuficiente;
		this.ricos = ricos;
		this.policias = policias;
		comenzarDia();
		id = idActual++;
	}

	////////////////////////////////////////// Metodos de implementacion especifica

	@Override
	public InfoRatero call() throws Exception {
		showMessage("Ratero:call", String.valueOf(id), true);
		while (estadoActual != null && estadoActual.getTipo() != EstadoRatero.INACTIVO && hayRicosActivos()) {
			hacerUnMovimiento();
			wasteTime(10); // Pausa entre movimientos a mayor pausa mas detenciones
		}

		// Retorna el botín acumulado
		return new InfoRateroAdapter() {
		}.map(botin, ricosRobados);
	}

	/**
	 * Verifica si quedan ricos activos en la lista
	 */
	private boolean hayRicosActivos() {
		synchronized (ricos) {
			for (Rico rico : ricos) {
				if (rico.isActivo()) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void hacerUnMovimiento() {
		/**
		 * Esto es lo que realiza el ratero en un movimiento
		 * Delega la acción al estado actual (Patrón State)
		 */
		if (estadoActual != null) {
			estadoActual.ejecutarAccion(this);
		}
	}

	public ArrayList<Rico> getRicosRobados() {
		return ricosRobados;
	}

	public void comenzarDia() {
		seleccionarRefugio();
		posicionActual = new Posicion(posicionRefugio);
		// seleccionarDestino();
		this.estadoActual = new EstadoBuscando();
		this.botin = 0;
	}

	public List<Rico> establecerRicosActivosObjetivo(List<Rico> ricos) {
		// la lista de los ricos cercanos
		List<Rico> ricosActivos = new ArrayList<>();
		for (Rico rico : ricos) {
			if (rico.isActivo() && this.posicionActual.solapaMiArea(rico.getPosicion(), radioBusqueda)) {
				ricosActivos.add(rico);
			}
		}
		return ricosActivos;
	}

	/**
	 * selecciona el rico objetivo cercano, que depende del radio de accion del
	 * ratero, sin vigilancia
	 * que esta vigilado depende de la posicion y del radio de accion del policia.
	 * El resultado final es la seleccion del rico objetivo cercano sin vigilancia
	 * en la propiedad de la clase ricoObjetivoActual. Puede que no lo encuentre.
	 * 
	 * @param ricos    ricos que quiero comprobar
	 * @param policias policias que quiero comprobar
	 */
	public void seleccionarRicoObjetivoCercanoSinVigilancia(List<Rico> ricos, List<Policia> policias) {
		List<Rico> ricosCercanos = establecerRicosActivosObjetivo(ricos);
		List<Rico> ricosCercanosSinVigilancia = isNotListProtegidoPorPolicia(ricosCercanos, policias);

		Rico ricoMasCercano = null;
		int distanciaMinima = Integer.MAX_VALUE;
		for (Rico rico : ricosCercanosSinVigilancia) {
			int distancia = posicionActual.getDistanciaCuadrado(rico.getPosicion());
			if (distancia < distanciaMinima) {
				distanciaMinima = distancia;
				ricoMasCercano = rico;
			}
		}
		ricoObjetivoActual = Optional.ofNullable(ricoMasCercano);
	}

	/**
	 * obtiene la lista de ricos que no estan protegidos por policias
	 * 
	 * @param ricosCercanos
	 * @param policias
	 * @return
	 */
	private List<Rico> isNotListProtegidoPorPolicia(List<Rico> ricosCercanos, List<Policia> policias) {
		List<Rico> ricosSinProteccion = new ArrayList<>();
		for (Rico rico : ricosCercanos) {
			boolean protegido = false;
			for (Policia policia : policias) {
				if (rico.getPosicion().solapaMiArea(policia.getPosicion(), policia.getRadioAccion())) {
					protegido = true;
					break;
				}
			}
			if (!protegido) {
				ricosSinProteccion.add(rico);
			}
		}
		return ricosSinProteccion;
	}

	/**
	 * Nos dice si esta mi posicion dentro del radio de accion de policias
	 * 
	 * @param policias
	 * @return
	 */
	public boolean controlarPolicia(List<Policia> policias) {
		for (Policia policia : policias) {
			if (this.posicionActual.solapaMiArea(policia.getPosicion(), policia.getRadioAccion())) {
				return true;
			}
		}
		return false;
	}

	public synchronized void encarcelar() {
		if (!enCarcel) {
			botinConfiscado = botin; // Guardar el botín antes de confiscarlo
			botin = 0;
			enCarcel = true;
			estadoActual = new EstadoInactivo();
		}
	}

	/**
	 * Intenta atracar al rico objetivo actual
	 * Usa synchronized para evitar condiciones de carrera
	 * 
	 * @return true si el rico notó el robo y activó la alarma
	 */
	public synchronized boolean atracar() {
		if (enCarcel || ricoObjetivoActual.isEmpty()) {
			return false;
		}

		Rico rico = ricoObjetivoActual.get();
		synchronized (rico) {
			if (!rico.isActivo()) {
				return false;
			}
			int loot = rico.getBien();
			boolean alarmaActivada = rico.atracando(this);
			// El rico siempre pierde el dinero (se pone a 0 en atracando), así que el
			// ratero siempre se lo lleva
			botin += loot;
			showMessage("Ratero:" + this.id, "atraca a rico " + rico.getId() + " botin " + getBotin(), true);
			return alarmaActivada;
		}
	}

	////////////////////////////////////////// Metodos de get&set
	/**
	 * Cambia el estado del ratero (Patrón State)
	 * 
	 * @param nuevoEstado El nuevo estado
	 */
	public void cambiarEstado(EstadoRateroState nuevoEstado) {
		this.estadoActual = nuevoEstado;
	}

	private void seleccionarRefugio() {
		posicionRefugio = Barrio.whiteChapel.getPosicionCentral();
	}

	// Cambiado a public para que los estados puedan acceder
	public void incrementaVelocidad() {
		velocidad *= 2;
	}

	public void decrementaVelocidad() {
		velocidad /= 2;
	}

	@Override
	public Posicion getPosicion() {
		return posicionActual;
	}

	@Override
	public int getVelocidad() {
		return velocidad;
	}

	public void setPosicion(Posicion posicionActual) {
		this.posicionActual = posicionActual;
	}

	public int getBotinSuficiente() {
		return botinSuficiente;
	}

	public void setBotinSuficiente(int botinSuficiente) {
		this.botinSuficiente = botinSuficiente;
	}

	public int getRadioAccion() {
		return radioBusqueda;
	}

	public void setRadioAccion(int radioAccion) {
		this.radioBusqueda = radioAccion;
	}

	public Optional<Rico> getRicoObjetivoActual() {
		return ricoObjetivoActual;
	}

	public void setRicoObjetivoActual(Rico rico) {
		if (rico == null) {
			this.ricoObjetivoActual = Optional.empty();
		} else {
			this.ricoObjetivoActual = Optional.of(rico);
		}
	}

	public int getRadioBusqueda() {
		return radioBusqueda;
	}

	public int getBotin() {
		return botin;
	}

	public boolean isBotinSuficiente() {
		return this.botin >= this.botinSuficiente;
	}

	public boolean isEnCarcel() {
		return enCarcel;
	}

	public int getBotinConfiscado() {
		return botinConfiscado;
	}

	// Cambiado a public para que los estados puedan acceder
	public void mensajear() {
		if (estadoActual != null && estadoActual.getTipo() == EstadoRatero.INACTIVO) {
			if (this.botin >= this.botinSuficiente) {
				showMessage("Ratero:" + this.id, "Ha terminado su jornada", true);
			} else {
				showMessage("Ratero:" + this.id, "En la carcel", true);
			}
			showMessage("Ratero" + this.id, "con " + this.botin + " de botin", true);
		}
	}

	public EstadoRatero getEstado() {
		return estadoActual != null ? estadoActual.getTipo() : null;
	}

	// Getters para que los estados puedan acceder
	public List<Rico> getRicos() {
		return ricos;
	}

	public List<Policia> getPolicias() {
		return policias;
	}

	public void deambular() {
		this.posicionActual = deambula(this.posicionActual, Barrio.Kensington);
	}

	public void moverA(Posicion destino) {
		this.posicionActual.moveTo(destino, velocidad);
	}

	public boolean haTerminadoJornada() {
		return this.botin >= this.botinSuficiente;
	}

	public Posicion getPosicionRefugio() {
		return posicionRefugio;
	}

	/**
	 * Incrementa la velocidad cuando hay alarma (el ratero huye más rápido)
	 */

	public void alarma() {
		incrementaVelocidad();
	}

}
