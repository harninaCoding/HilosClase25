package futuro20;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Recogedor02 implements Runnable {
	Future<?> nose;

	public Recogedor02(Future<?> nose) {
		super();
		this.nose = nose;
	}

	@Override
	public void run() {
		System.out.println("recogedor de fruto ha comenzado");
		do {
		} while (!nose.isDone());
		System.out.println("Pues ya me han contestado");
		try {
			System.out.println("venia de este hilo "+nose.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("el hilo recogedor termina");
	}
}
