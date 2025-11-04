package sincronizacion03Solucion;

public class ProductorSincronizado extends Thread {
	private ContenedorSincronizado contenedor;
	public ProductorSincronizado(ContenedorSincronizado c) {
		contenedor = c;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			contenedor.put(i);
			System.out.println("Productor. put: " + i);
			try {
				sleep((int) (Math.random() * 100));
			} catch (InterruptedException e) {
			}
		}
	}
}
