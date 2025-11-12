package cajero14Market03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Market implements Runnable {
	Cola cola = new Cola();
	Caja cajero1 = new Caja("Cajero 1",10l, cola);
	Caja cajero2 = new Caja("Cajero 2",10l, cola);
	

	public Market() {
		super();
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(cajero1);
		executorService.execute(cajero2);
		executorService.shutdown();
	}


	@Override
	public void run() {

	}

}
