package test.modelo;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.data.Barco;
import modelo.data.Cardumen;
import modelo.data.Coordenada;
import modelo.data.Mar;
import modelo.data.Puerto;
import modelo.data.ServicioSatelite;
import modelo.enums.Dimensiones;
import modelo.enums.EspeciePez;
import modelo.enums.TipoBarco;

class BarcoTest {
	Mar mar;
	Cardumen cardumen;
	ServicioSatelite servicioSatélite;
	Barco barco;
	Puerto puerto;

	@BeforeEach
	void before() {
		mar = new Mar(Dimensiones.huge.getDimension());
		cardumen = new Cardumen(EspeciePez.ATUN, new Coordenada(0, 0), 1000, 10, mar.getDimension());
		mar.agregarCardumen(cardumen);
		servicioSatélite = new ServicioSatelite(mar);
		puerto=new Puerto(1l,"Siracusa", new Coordenada(100,100));
		barco = new Barco("Cosica", new Coordenada(50, 50), puerto, 500, 3, servicioSatélite,
				TipoBarco.PALANGRE,mar);
		barco.decidirCardumen(mar.obtenerCardumenes());
	}

	@Test
	void testMoverse() {
		do {
			barco.moverse();
		} while (!barco.getPosicionActual().equals(cardumen.getPosicion()));
		assertEquals(barco.getPosicionActual(), cardumen.getPosicion());
	}

	@Test
	void testMoverseConCardumen() {
		do {
			cardumen.moverEnMapa();
			barco.moverse();
		} while (!barco.getPosicionActual().equals(cardumen.getPosicion()));
		assertEquals(barco.getPosicionActual(), cardumen.getPosicion());
	}

	@Test
	void testPescarCapacidadSuficiente() {
		do {
			barco.moverse();
		} while (!barco.getPosicionActual().equals(cardumen.getPosicion()));
		barco.pescar();
		assertEquals(barco.getPescaActual(), barco.getCapacidad());
	}

	@Test
	void testPescarCapacidadExcedida() {
		int capacidad = 1500;
		barco = new Barco("Cosica", new Coordenada(50, 50), puerto, capacidad, 3, servicioSatélite,
				TipoBarco.PALANGRE,mar);
		barco.decidirCardumen(mar.obtenerCardumenes());
		try {
			// porque le barco tiene mucha capacidad para el cardumen
			barco.pescar();
			fail();
		} catch (Exception e) {
		}
		boolean empty = barco.getObjetivo().isPresent();
		assertTrue(!empty);
	}
	
	@Test
	void testPescarCapacidadaCorrectaCardumenYaPescado() {
		barco.pescar();
		//no deberia dejar pescar porque no respetas el indiceBiologico
		int capacidad = 500;
		barco = new Barco("Cosica", new Coordenada(50, 50), 
				puerto, capacidad, 3, servicioSatélite,
				TipoBarco.PALANGRE,mar);
		barco.decidirCardumen(mar.obtenerCardumenes());
		barco.pescar();
		assertNotEquals(barco.getCapacidad(),barco.getPescaActual());
		assertEquals(barco.getPescaActual(),barco.getCapacidad()-cardumen.getPesoInicial()*cardumen.getFactorBiologico());
	}
}
