package test.hilos;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Dimension;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

public class HiloDosBarcosUnCardumenTest {
	Barco barco;
	Cardumen cardumen;
	Puerto puertoBase;
	Coordenada coordenada;
	Mar mar;
	ServicioSatelite servicioSatelite;
	int capacidad = 100;
	int velocidad = 3;
	
	@BeforeEach
	void before() {
		coordenada = new Coordenada(50,50);
		mar=new Mar(Dimensiones.medium.getDimension());
		cardumen=new Cardumen(EspeciePez.ATUN,new Coordenada(0,0),200,1,Dimensiones.medium.getDimension());
		mar.agregarCardumen(cardumen);
		servicioSatelite=new ServicioSatelite(mar);
		puertoBase = new Puerto(1L,"Trapecio",coordenada);
		barco=new Barco("corsario",coordenada,
				puertoBase,
				capacidad,velocidad,servicioSatelite,TipoBarco.PALANGRE,mar);
	}

	@Test
	void test1Barco() throws InterruptedException, ExecutionException {
		ExecutorService executorService=Executors.newCachedThreadPool();
		executorService.execute(cardumen);
		InfoPesca infoPesca = executorService.submit(barco).get();
		assertTrue(cardumen.getPeso()!=cardumen.getPesoInicial());
		assertTrue(cardumen.getPesoTotalDesdeInicio()==cardumen.getPeso()+barco.getPescaActual());
		assertEquals(barco.getCapacidad(),barco.getPescaActual());
		assertEquals(barco.getPosicionActual(),puertoBase.getSitio());
	}
	@Test
	void test2BarcosMismoCardumen() throws InterruptedException, ExecutionException {
		ExecutorService executorService=Executors.newCachedThreadPool();
//		executorService.execute(cardumen);
		Barco goleta=new Barco("goleta",new Coordenada(10,50),
				puertoBase,
				capacidad,velocidad,servicioSatelite,TipoBarco.PALANGRE,mar);
		InfoPesca infoPesca = executorService.submit(barco).get();
		InfoPesca infoPesca2 = executorService.submit(goleta).get();
		assertTrue(cardumen.getPeso()!=cardumen.getPesoInicial());
		assertEquals(barco.getPescaActual()+goleta.getPescaActual(),cardumen.getPesoInicial()-cardumen.getPesoInicial()*cardumen.getFactorBiologico(),1);
		assertEquals(barco.getPosicionActual(),puertoBase.getSitio());
		assertEquals(goleta.getPosicionActual(),puertoBase.getSitio());
	}

}
