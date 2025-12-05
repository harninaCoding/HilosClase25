package test.hilos;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import modelo.data.Cardumen;
import modelo.data.InfoPesca;
import modelo.data.Mundo;
import modelo.data.Puerto;
import modelo.enums.EspeciePez;

public class HilosMundoTest {

	Mundo mundo;

	@Test
	void test() throws InterruptedException {
		mundo = new Mundo();
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		newCachedThreadPool.execute(mundo);
		newCachedThreadPool.awaitTermination(1, TimeUnit.SECONDS);
		newCachedThreadPool.shutdown();
		long sum = 0;
		long sum2 = 0;
		sum = mundo.getPuertos().stream().mapToLong(puerto -> {
			return puerto.getInfoPescas().stream().mapToLong(InfoPesca::getCantidad).sum();
		}).sum();
		sum2 = mundo.getCardumenes().stream().mapToLong(cardumen -> {
			return (long) (cardumen.getPesoTotalDesdeInicio()-cardumen.getPeso());
		}).sum();
		assertEquals(sum, sum2);
		assertTrue(sum>0);
		sum = mundo.getPuertos().stream().mapToLong(puerto -> {
			return puerto.getInfoPescas().stream()
					.filter(info->{
						if(info.getEspeciePez()!=null)
							return info.getEspeciePez().equals(EspeciePez.ATUN);
						return false;
					})
					.mapToLong(InfoPesca::getCantidad).sum();
		}).sum();
		sum2 = mundo.getCardumenes().stream()
				.filter(card->card.getEspecie().equals(EspeciePez.ATUN))
				.mapToLong(cardumen -> {
			return (long) (cardumen.getPesoTotalDesdeInicio()-cardumen.getPeso());
		}).sum();
		System.out.println("pesca atun "+sum);
		assertEquals(sum, sum2);
	}
}
