package sincronizacion03Solucion;

public class ConsumidorSincronizado extends Thread {
	private ContenedorSincronizado contenedor;

	public ConsumidorSincronizado(ContenedorSincronizado c) {
		contenedor = c;
	}

	public void run() {
		int value = 0;
		for (int i = 0; i < 10; i++) {
			value = contenedor.get();
			System.out.println("Consumidor. get: " + value);
		}
	}
}
