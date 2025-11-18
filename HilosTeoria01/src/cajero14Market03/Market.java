package cajero14Market03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Market implements Runnable {
	private Cola cola = new Cola();
	private Caja cajero1 = new Caja("Cajero 1",10l, cola);
	private Caja cajero2 = new Caja("Cajero 2",10l, cola);
	//autopsia
	public int cantidadClientes=0;

	public Caja getCajero1() {
		return cajero1;
	}

	public Caja getCajero2() {
		return cajero2;
	}
	//autopsia


	public Market() {
		super();
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(cajero1);
		executorService.execute(cajero2);
		executorService.shutdown();
	}


	@Override
	public void run() {
		do {
			if (!cola.totalClientesAlcanzado()) {
				cola.put(ClientesOM.getRandomClient());
				//autopsia
				cantidadClientes++;
				Utiles.wasteTime(5000);
			}
		} while (!cola.totalClientesAlcanzado());
	}
	
	
}
