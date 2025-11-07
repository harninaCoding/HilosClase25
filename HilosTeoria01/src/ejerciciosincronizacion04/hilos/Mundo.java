package ejerciciosincronizacion04.hilos;

public class Mundo {
	public static void main(String[] args) {
		Fuente fuente = Fuente.getInstance();
		fuente.start();
		Ser humano = new Ser("Felix", 4);
		humano.start();
		Ser humanoe = new Ser("Manolito", 5);
		humanoe.start();

	}
}
