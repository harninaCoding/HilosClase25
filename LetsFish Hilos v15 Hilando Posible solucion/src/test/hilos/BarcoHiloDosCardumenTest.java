package test.hilos;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Dimension;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.data.Barco;
import modelo.data.Cardumen;
import modelo.data.Coordenada;
import modelo.data.InfoPesca;
import modelo.data.Mar;
import modelo.data.Puerto;
import modelo.data.ServicioSatelite;
import modelo.enums.Dimensiones;
import modelo.enums.EspeciePez;
import modelo.enums.TipoBarco;

class BarcoHiloDosCardumenTest {
	Barco barco;
	Cardumen cardumenPalangre;
	Cardumen cardumenArrastre;
	Puerto puertoBase;
	Coordenada coordenada;
	Mar mar;
	ServicioSatelite servicioSatelite;
	int capacidad = 100;
	int velocidad = 3;

	@BeforeEach
	void before() {
		coordenada = new Coordenada(50, 50);
		mar = new Mar(Dimensiones.medium.getDimension());
		cardumenPalangre = new Cardumen(EspeciePez.ATUN, new Coordenada(0, 0), 200, 1,
				Dimensiones.medium.getDimension());
		cardumenArrastre = new Cardumen(EspeciePez.SARDINA, new Coordenada(100, 100), 200, 1,
				Dimensiones.medium.getDimension());
		mar.agregarCardumen(cardumenPalangre);
		mar.agregarCardumen(cardumenArrastre);
		servicioSatelite = new ServicioSatelite(mar);
		puertoBase = new Puerto(1L, "Trapecio", coordenada);
		barco = new Barco("corsario", coordenada, puertoBase, capacidad, velocidad, servicioSatelite,
				TipoBarco.PALANGRE, mar);
	}

	@Test
	void test1Barco1CardumenPalangre() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(cardumenPalangre);
		Future<InfoPesca> submit = executorService.submit(barco);
		InfoPesca infoPesca = submit.get();
		assertEquals(barco.getObjetivo().get(),cardumenPalangre);
		assertEquals((long) cardumenArrastre.getPesoInicial(), cardumenArrastre.getPesoTotalDesdeInicio());
		assertEquals(barco.getCapacidad(), barco.getPescaActual());
	}

	@Test
	void test2Barcos2Cardumen() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(cardumenPalangre);
		Barco goleta = new Barco("goleta", new Coordenada(10, 50), puertoBase, capacidad, velocidad, servicioSatelite,
				TipoBarco.CERCO, mar);
		Future<InfoPesca> submit = executorService.submit(barco);
		Future<InfoPesca> submit2 = executorService.submit(goleta);
		InfoPesca infoPesca = submit.get();
		assertEquals(barco.getObjetivo().get(),cardumenPalangre);
		assertEquals(goleta.getObjetivo().get(),cardumenArrastre);
		assertEquals(barco.getPescaActual(), barco.getCapacidad());
		assertEquals(goleta.getPescaActual(), goleta.getCapacidad());
		assertEquals(cardumenArrastre.getPesoTotalDesdeInicio(),
				(long) cardumenArrastre.getPeso() + goleta.getPescaActual());
		assertEquals(cardumenPalangre.getPesoTotalDesdeInicio(),
				(long) cardumenPalangre.getPeso() + barco.getPescaActual());
	}

}
