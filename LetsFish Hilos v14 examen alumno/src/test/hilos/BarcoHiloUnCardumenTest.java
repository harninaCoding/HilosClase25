package test.hilos;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Dimension;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.data.mar.Cardumen;
import modelo.data.mar.Coordenada;
import modelo.data.mar.Mar;
import modelo.data.mar.ServicioSatelite;
import modelo.data.mundo.Barco;
import modelo.data.mundo.Puerto;
import modelo.enums.Dimensiones;
import modelo.enums.EspeciePez;
import modelo.enums.TipoBarco;

class BarcoHiloUnCardumenTest {
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
	void test1Barco() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(cardumen.getPeso()==cardumen.getPesoInicial());
		assertTrue(cardumen.getPesoTotalDesdeInicio()==cardumen.getPeso()+barco.getPescaActual());
		assertEquals(barco.getCapacidad(),barco.getPescaActual());
		assertEquals(barco.getPosicionActual(),puertoBase.getSitio());
	}
	@Test
	void test2BarcosMismoCardumen() throws InterruptedException {
		ExecutorService executorService=Executors.newCachedThreadPool();
		Barco goleta=new Barco("goleta",new Coordenada(10,50),
				puertoBase,
				capacidad,velocidad,servicioSatelite,TipoBarco.PALANGRE,mar);
		Thread.sleep(2000);
		assertTrue(cardumen.getPeso()==cardumen.getPesoInicial());
		assertEquals(barco.getPescaActual()+goleta.getPescaActual(),cardumen.getPesoInicial()-cardumen.getPesoInicial()*cardumen.getFactorBiologico(),1);
		assertEquals(barco.getPosicionActual(),puertoBase.getSitio());
		assertEquals(goleta.getPosicionActual(),puertoBase.getSitio());
	}

}
