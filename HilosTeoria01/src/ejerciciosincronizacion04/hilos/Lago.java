package ejerciciosincronizacion04.hilos;

public class Lago {

	private static Lago instancia = new Lago();
	private int vida = 0;

	private Lago() {
	}

	public int getVida() {
		return vida;
	}

	public static Lago getInstance() {
		return instancia;
	}

	public synchronized void incrementarVida() {
		vida++;
		notify();
	}

	public synchronized int beber() {
		while(isVacio()) {
			try {
				System.out.println("Lago: hay un colega parado");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		vida--;
		return 1;
	}

	public boolean isVacio() {
		return vida == 0;
	}
}
