// Clase abstracta Barco
package modelo.data;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import modelo.enums.TipoBarco;
import utiles.Utiles;

public class Barco implements Pesquero, Callable<InfoPesca> {
	protected String nombre;
	private Coordenada posicionActual;
	protected Puerto puertoBase;
	protected int capacidad;
	private int pescaActual = 0;
	protected Optional<Cardumen> objetivo;
	protected int velocidad;
	private ServicioSatelite servicioSatélite;
	private TipoBarco tipo;
	private Mar mar;

	public Barco(String nombre, Coordenada posicionActual, Puerto puertoBase, int capacidad, int velocidad,
			ServicioSatelite servicioSatélite, TipoBarco tipoBarco, Mar mar) {
		super();
		this.nombre = nombre;
		this.puertoBase = puertoBase;
		this.capacidad = capacidad;
		this.posicionActual = posicionActual;
		this.velocidad = velocidad;
		this.servicioSatélite = servicioSatélite;
		this.tipo = tipoBarco;
		this.mar = mar;
	}

	///////////////// metodos propios//////////////////////////////////
	@Override
	public InfoPesca call() {
		decidirCardumen(servicioSatélite.obtenerInformeDiario());
		this.pescaActual=0;
		if (objetivo.isPresent()) {
			while (!cardumenAlcanzado()) {
				moverse();
			}
			pescar();
			regresarAPuerto();
			return new InfoPesca(objetivo.get().getEspecie(), pescaActual, this, mar.getToday());
		}
		return new InfoPesca(null, 0, this, mar.getToday());
	}

	@Override
	public void decidirCardumen(List<Cardumen> cardumenes) {
		synchronized (cardumenes) {
			objetivo = cardumenes.stream().filter(this::isSuitableBoat)
					.filter(c -> c.getPeso() > capacidad * (1 - c.getFactorBiologico()))
					.min(Comparator.comparingDouble(c -> posicionActual.distanciaA(servicioSatélite.getPosicion(c))));
		}
	}

	@Override
	public void moverse() {
		Cardumen cardumen = objetivo.get();
		posicionActual.avanzarHacia(servicioSatélite.getPosicion(cardumen), velocidad);
		if (cardumenAlcanzado()) {
			objetivo.get().setEnPesca(true);
		}
	}

	@Override
	public void pescar() {
		this.pescaActual = 0;
		Cardumen cardumen = objetivo.get();
		while (pescaActual < capacidad && !cardumen.isLimiteAlcanzado()) {
			synchronized (cardumen) {
				cardumen.setEnPesca(true);
				pescaActual += cardumen.pescar();
			}
//			System.out.println("Barco "+nombre+" pescando "+pescaActual);
			Utiles.wasteTime(1000);
		}
		synchronized (cardumen) {
			cardumen.setEnPesca(false);
		}
	}

	@Override
	public void regresarAPuerto() {
		posicionActual = puertoBase.getSitio();
	}

	/////////////////////// metodos del objeto//////////////////////////////////////
	public TipoBarco getTipo() {
		return tipo;
	}

	private boolean isSuitableBoat(Cardumen cardumen) {
		return cardumen.isSuitable(getTipo());
	}

	public Optional<Cardumen> getObjetivo() {
		return objetivo;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getPescaActual() {
		return pescaActual;
	}

	private boolean cardumenAlcanzado() {
		return servicioSatélite.getPosicion(objetivo.get()).equals(this.posicionActual);
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
		return servicioSatélite;
	}

	public Mar getMar() {
		return mar;
	}
}