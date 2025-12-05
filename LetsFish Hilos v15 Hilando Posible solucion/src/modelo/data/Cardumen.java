// Clase Cardumen
package modelo.data;

import java.awt.Dimension;
import java.util.Random;

import modelo.enums.EspeciePez;
import modelo.enums.TipoBarco;
import utiles.Utiles;

public class Cardumen implements Runnable {
	private final EspeciePez especie;
	private Coordenada posicion;
	private int peso;
	private double factorBiologico = .25;
	private int pesoInicial;
	private Long pesoTotalDesdeInicio;
	private final double velocidadRegeneracion;
	private final int velocidadMovimiento = 1;
	private boolean enPesca = false;
	private Dimension dimension;

	public Cardumen(EspeciePez especie, Coordenada posicion, int peso, double velocidadRegeneracion,
			Dimension dimension) {
		this.especie = especie;
		this.posicion = posicion;
		this.peso = peso;
		this.pesoInicial = peso;
		this.pesoTotalDesdeInicio = (long) peso;
		this.velocidadRegeneracion = velocidadRegeneracion;
		this.dimension = dimension;
	}

	public Cardumen(EspeciePez especie, int peso, double velocidadRegeneracion) {
		this.especie = especie;
		this.peso = peso;
		this.pesoInicial = peso;
		this.velocidadRegeneracion = velocidadRegeneracion;
		Random random = new Random();
		this.posicion = new Coordenada(random.nextInt(dimension.width), random.nextInt(dimension.height));
	}

	public int pescar() {
		int cantidad = 1;
		if (!isLimiteAlcanzado()) {
			peso -= cantidad;
			return cantidad;
		}
		return 0;

	}

	public synchronized void regenerar() {
		if (peso < pesoInicial) {
			peso += velocidadRegeneracion;
			pesoTotalDesdeInicio += (long) velocidadRegeneracion;
		}
	}

	public synchronized void moverEnMapa() {
		this.posicion.setRandomDelta(velocidadMovimiento, dimension.width, dimension.height);
	}

	@Override
	public void run() {
		while (true) {
			if (!isEnPesca()) {
				moverEnMapa();
				regenerar();
				Utiles.wasteTime(1000);
			}
		}
	}

	public EspeciePez getEspecie() {
		return especie;
	}

	public Coordenada getPosicion() {
		return posicion;
	}

	public boolean isSuitable(TipoBarco tipo) {
		return especie.isSuitable(tipo);
	}

	public double getPeso() {
		return peso;
	}

	public double getPesoInicial() {
		return pesoInicial;
	}

	public boolean isLimiteAlcanzado() {
		return peso <= pesoInicial * factorBiologico;
	}

	public double getFactorBiologico() {
		return factorBiologico;
	}

	public boolean isEnPesca() {
		return enPesca;
	}

	public void setEnPesca(boolean enPesca) {
		this.enPesca = enPesca;
	}

	public Long getPesoTotalDesdeInicio() {
		return pesoTotalDesdeInicio;
	}

	public double getVelocidadRegeneracion() {
		return velocidadRegeneracion;
	}

	public int getVelocidadMovimiento() {
		return velocidadMovimiento;
	}

	public Dimension getDimension() {
		return dimension;
	}
}
