package cajero14Market03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainPrueba {
	public static void main(String[] args) {
		Market superMercado = new Market();
		// primero probamos sin arrancar el hilo del super
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(superMercado);
		// si no lo pones no cierra el programa
		executorService.shutdown();
	}
}
