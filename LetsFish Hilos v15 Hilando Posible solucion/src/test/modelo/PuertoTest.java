package test.modelo;

import static org.junit.jupiter.api.Assertions.*;

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

class PuertoTest {

	Mar mar;
	Cardumen cardumen;
	ServicioSatelite servicioSatélite;
	Barco barco;
	Puerto puerto;

	@BeforeEach
	void before() {
		mar = new Mar(Dimensiones.huge.getDimension());
		cardumen = new Cardumen(EspeciePez.ATUN, new Coordenada(0, 0), 1000, 10,  mar.getDimension());
		mar.agregarCardumen(cardumen);
		servicioSatélite = new ServicioSatelite(mar);
		puerto=new Puerto(1l,"Siracusa", new Coordenada(100,100));
		barco = new Barco("Cosica", new Coordenada(50, 50), puerto, 500, 3, servicioSatélite,
				TipoBarco.PALANGRE,mar);
		barco.decidirCardumen(mar.obtenerCardumenes());
	}
	
	@Test
	void test() {
		barco.pescar();
		barco.regresarAPuerto();
		assertEquals(puerto.getInfoPescas().size(),1);
	}

}
