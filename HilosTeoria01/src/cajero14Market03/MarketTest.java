package cajero14Market03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

class MarketTest {

	@Test
	void test() throws InterruptedException {
		Market superMercado = new Market();
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(superMercado);
		executorService.awaitTermination(2l, TimeUnit.SECONDS);
		executorService.shutdown();
		System.out.println("TEST: clientes creados: "+superMercado.cantidadClientes);
		System.out.println("TEST: clientes gestionados 1: "+superMercado.getCajero1().contadorClientes);
		System.out.println("TEST: clientes gestionados 2: "+superMercado.getCajero2().contadorClientes);
		
	}

}
