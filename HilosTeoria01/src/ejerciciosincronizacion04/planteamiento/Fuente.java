package ejerciciosincronizacion04.planteamiento;

public class Fuente {

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

	public void rellenar() {
		int contadorMaximo=3;
		int recargas=0;
		while (true&&contadorMaximo-->0) {
			try {
				Thread.sleep(intervalo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			incrementarLago(Lago.getInstance());
			recargas++;
		}
		System.out.println("recargas "+recargas);
	}

}
