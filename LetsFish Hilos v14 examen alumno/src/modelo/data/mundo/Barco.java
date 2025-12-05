// Clase abstracta Barco
package modelo.data.mundo;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import modelo.data.mar.Cardumen;
import modelo.data.mar.Coordenada;
import modelo.data.mar.Mar;
import modelo.data.mar.ServicioSatelite;
import modelo.enums.TipoBarco;
import utiles.Utiles;

public class Barco implements Pesquero {
	protected String nombre;
	private Coordenada posicionActual;
	protected Puerto puertoBase;
	protected int capacidad;
	private int pescaActual = 0;
	/**
	 * Es probable que no haya ningun cardumen adecuado
	 */
	protected Cardumen objetivo = null;
	protected int velocidad;
	private ServicioSatelite servicioSatelite;
	private TipoBarco tipo;
	private Mar mar;

	public Barco(String nombre, Coordenada posicionActual, Puerto puertoBase, int capacidad, int velocidad,
			ServicioSatelite servicioSatelite, TipoBarco tipoBarco, Mar mar) {
		super();
		this.nombre = nombre;
		this.puertoBase = puertoBase;
		this.capacidad = capacidad;
		this.posicionActual = posicionActual;
		this.velocidad = velocidad;
		this.servicioSatelite = servicioSatelite;
		this.tipo = tipoBarco;
		this.mar = mar;
	}

	///////////////// metodos propios//////////////////////////////////

	@Override
	public void decidirCardumen(List<Cardumen> cardumenes) {
		Optional<Cardumen> provisional = cardumenes.stream().filter(this::isSuitableBoat)
				.filter(c -> c.getPeso() > capacidad * (1 - c.getFactorBiologico()))
				.min(Comparator.comparingDouble(c -> posicionActual.distanciaA(servicioSatelite.getPosicion(c))));
		if (provisional.isPresent())
			objetivo = provisional.get();
	}

	@Override
	public void moverse() {
		if (objetivo != null) {
			Cardumen cardumen = objetivo;
			posicionActual.avanzarHacia(servicioSatelite.getPosicion(cardumen), velocidad);
			if (cardumenAlcanzado()) {
				objetivo.setEnPesca(true);
			}
		}
	}

	@Override
	public void pescar() {
		if (objetivo != null) {
			pescaActual = 0;
			while (pescaActual < capacidad && !objetivo.isLimiteAlcanzado()) {
				objetivo.setEnPesca(true);
				pescaActual += objetivo.pescar();
			}
			Utiles.wasteTime(10);
		}
		objetivo.setEnPesca(false);
	}

	@Override
	public void regresarAPuerto() {
		posicionActual = puertoBase.getSitio();
	}

	private boolean isSuitableBoat(Cardumen cardumen) {
		return cardumen.isSuitable(getTipo());
	}

	/////////////////////// metodos del objeto//////////////////////////////////////
	public TipoBarco getTipo() {
		return tipo;
	}

	public Cardumen getObjetivo() {
		return objetivo;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getPescaActual() {
		return pescaActual;
	}

	private boolean cardumenAlcanzado() {
		return servicioSatelite.getPosicion(objetivo).equals(this.posicionActual);
	}

	public boolean isInPort() {
		return puertoBase.getSitio().equals(posicionActual);
	}

	public String getNombre() {
		return nombre;
	}

	public Coordenada getPosicionActual() {
		return posicionActual;
	}

	public Puerto getPuertoBase() {
		return puertoBase;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public ServicioSatelite getServicioSatélite() {
		return servicioSatelite;
	}

	public Mar getMar() {
		return mar;
	}
}