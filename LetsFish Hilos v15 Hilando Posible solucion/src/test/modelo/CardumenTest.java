package test.modelo;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.data.Cardumen;
import modelo.data.Coordenada;
import modelo.data.Mar;
import modelo.enums.Dimensiones;
import modelo.enums.EspeciePez;

class CardumenTest {
	// Datos de prueba
	EspeciePez especie;
	Coordenada posicion;
	int pesoInicial; // Peso inicial del cardumen
	double velocidadRegeneracion;
	// Crear el cardumen
	Cardumen cardumen;
	Mar mar;

	@BeforeEach
	void before() {
		// Datos de prueba
		especie = EspeciePez.ATUN;
		posicion = new Coordenada(0, 0);
		pesoInicial = 1000; // Peso inicial del cardumen
		velocidadRegeneracion = 10;
		mar=new Mar(Dimensiones.huge.getDimension());
		// Crear el cardumen
		cardumen = new Cardumen(especie, posicion, pesoInicial, velocidadRegeneracion, mar.getDimension());
	}

	@Test
	void testMovimiento() {
		int cantidad = 100;
		for (int i = 0; i < cantidad; i++) {
			Coordenada antigua = new Coordenada(cardumen.getPosicion());
			cardumen.moverEnMapa();
			assertNotEquals(antigua, cardumen.getPosicion());
		}
	}

	@Test
	void testIsLimiteAlcanzado() {
		// Comprobar que no ha alcanzado el límite biológico al inicio
		assertFalse(cardumen.isLimiteAlcanzado(),
				"El cardumen no debería haber alcanzado el límite biológico al inicio.");
		int pesca = 0;
		// Simular pesca para reducir el peso a justo el límite biológico
		int capacidad = 1000;
		synchronized (cardumen) {
			for (int i = 0; i < capacidad; i++) {
				pesca += cardumen.pescar();
			}
		}
		assertNotEquals(pesca, capacidad);
		// Comprobar que ahora ha alcanzado el límite biológico
		assertEquals(cardumen.getPeso() + pesca, cardumen.getPesoInicial());
		assertTrue(cardumen.isLimiteAlcanzado(), "El cardumen debería haber alcanzado el límite biológico.");
	}

	@Test
	void testIsLimiteAlcanzadoBelowLimit() {

		int pesca = 0;
		int capacidad = 10;
		// Reducir el peso, pero mantenerlo por encima del límite biológico
		synchronized (cardumen) {
			for (int i = 0; i < capacidad; i++) {
				pesca += cardumen.pescar();
			}
		}
		// Comprobar que no ha alcanzado el límite biológico
		assertFalse(cardumen.isLimiteAlcanzado(), "El cardumen no debería haber alcanzado el límite biológico.");
	}
	
	@Test
	void testRegeneracionCardumen() {
		int pesca=600;
		for (int i = 0; i < pesca; i++) {
			cardumen.pescar();
		}
		double peso = cardumen.getPeso();
		cardumen.regenerar();
		assertNotEquals(peso,cardumen.getPeso());
		assertEquals(peso+velocidadRegeneracion,cardumen.getPeso());
	}
}
