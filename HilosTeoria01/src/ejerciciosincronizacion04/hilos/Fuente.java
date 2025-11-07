package ejerciciosincronizacion04.hilos;

public class Fuente extends Thread {

	private int intervalo = 10;

	private static Fuente miFuente = new Fuente();

	private Fuente() {
	}

	public static Fuente getInstance() {
		return miFuente;
	}

	public void setIntervalo(int intervalo) {
		this.intervalo = intervalo;
	}

	public void incrementarLago(Lago lagoSagrado) {
		lagoSagrado.incrementarVida();
	}

	@Override
	public void run() {
		super.run();
		rellenar();
	}

	private void rellenar() {
		int recargas = 0;
		while (true) {
			try {
				Thread.sleep(intervalo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			incrementarLago(Lago.getInstance());
			
//			System.out.println("Fuente:incrementando lago "+recargas++);
		}
	}

}
