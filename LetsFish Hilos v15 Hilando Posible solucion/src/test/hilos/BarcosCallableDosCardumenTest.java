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

class BarcosCallableDosCardumenTest {
	Barco barco;
	Cardumen cardumenPalangre;
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
		cardumenPalangre=new Cardumen(EspeciePez.ATUN,new Coordenada(0,0),200,1,Dimensiones.medium.getDimension());
		mar.agregarCardumen(cardumenPalangre);
		servicioSatelite=new ServicioSatelite(mar);
		puertoBase = new Puerto(1L,"Trapecio",coordenada);
		barco=new Barco("corsario",coordenada,
				puertoBase,
				capacidad,velocidad,servicioSatelite,TipoBarco.PALANGRE,mar);
	}

	@Test
	void test2BarcosMismoCardumen() throws InterruptedException, ExecutionException {
		ExecutorService executorService=Executors.newCachedThreadPool();
//		executorService.execute(cardumenPalangre);
		Barco goleta=new Barco("goleta",new Coordenada(10,50),
				puertoBase,
				capacidad,velocidad,servicioSatelite,TipoBarco.PALANGRE,mar);
		puertoBase.add(executorService.submit(barco).get());
		puertoBase.add(executorService.submit(goleta).get());
		assertTrue(puertoBase.getInfoPescas().size()==2);
		Double result=cardumenPalangre.getPesoInicial()*cardumenPalangre.getFactorBiologico();
		assertEquals(barco.getPescaActual()+goleta.getPescaActual(),cardumenPalangre.getPesoInicial()-result);
		assertEquals(cardumenPalangre.getPeso(),result);
	}

}
