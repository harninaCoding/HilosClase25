package sincronizacion02problema;

public class Consumidor extends Thread {
	private Contenedor contenedor;

	public Consumidor(Contenedor c) {
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
