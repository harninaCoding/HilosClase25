package test.hilos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.data.Cardumen;
import modelo.data.Coordenada;
import modelo.enums.Dimensiones;
import modelo.enums.EspeciePez;

class CardumenHillosTest {
	Cardumen cardumen;
	Coordenada inicial;
	Long pesoTotal;
	
	@BeforeEach
	void before() {
		cardumen=new Cardumen(EspeciePez.BACALAO,new Coordenada(0,0),1000,10,Dimensiones.medium.getDimension());
		inicial=new Coordenada(cardumen.getPosicion());
		pesoTotal=cardumen.getPesoTotalDesdeInicio();
	}

	@Test
	void testNadiePesca() {
		Thread cardThread=new Thread(cardumen);
		cardThread.start();
		assertNotEquals(cardumen.getPosicion(),inicial);
		assertEquals(pesoTotal,(long)cardumen.getPesoInicial());
	}

}
