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
		coordenada = new Coordenada(50,50);
		mar=new Mar(Dimensiones.medium.getDimension());
		cardumenPalangre=new Cardumen(EspeciePez.ATUN,new Coordenada(0,0),200,1,Dimensiones.medium.getDimension());
		cardumenArrastre=new Cardumen(EspeciePez.SARDINA,new Coordenada(100,100),200,1,Dimensiones.medium.getDimension());
		mar.agregarCardumen(cardumenPalangre);
		mar.agregarCardumen(cardumenArrastre);
		servicioSatelite=new ServicioSatelite(mar);
		puertoBase = new Puerto(1L,"Trapecio",coordenada);
		barco=new Barco("corsario",coordenada,
				puertoBase,
				capacidad,velocidad,servicioSatelite,TipoBarco.PALANGRE,mar);
	}

	@Test
	void testUnBarcoPesca() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals((long)cardumenArrastre.getPesoInicial(),cardumenArrastre.getPesoTotalDesdeInicio());
	}
	@Test
	void test2BarcosMismoCardumen() throws InterruptedException {
		Barco goleta=new Barco("goleta",new Coordenada(10,50),
				puertoBase,
				capacidad,velocidad,servicioSatelite,TipoBarco.CERCO,mar);
		Thread.sleep(2000);
		assertEquals(barco.getPescaActual(),barco.getCapacidad());
		assertEquals(goleta.getPescaActual(),goleta.getCapacidad());
		assertEquals(cardumenArrastre.getPesoTotalDesdeInicio(),(long)cardumenArrastre.getPeso()+goleta.getPescaActual());
		assertEquals(cardumenPalangre.getPesoTotalDesdeInicio(),(long)cardumenPalangre.getPeso()+barco.getPescaActual());
	}

}
