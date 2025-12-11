package modelo.escenario;

import java.util.Random;

public enum Barrio {
	// 0,0 en la esquina inferior derecha
	whiteChapel(new Posicion(50, 100), new Posicion(0, 0), 50),
	Kensington(new Posicion(100, 100), new Posicion(51, 0), 50);

	private Posicion posicionNorteOccidental;
	private Posicion posicionSurOriental;
	private int limiteEsteOeste;

	private Barrio(Posicion posicionNorteOccidental, Posicion posicionSurOriental, int limiteEsteOeste) {
		this.posicionNorteOccidental = posicionNorteOccidental;
		this.posicionSurOriental = posicionSurOriental;
		this.limiteEsteOeste = limiteEsteOeste;
	}

	public int getLimiteEsteOeste() {
		return limiteEsteOeste;
	}

	public Posicion getPosicionNorteOccidental() {
		return posicionNorteOccidental;
	}

	public Posicion getPosicionSurOriental() {
		return posicionSurOriental;
	}

	public Posicion getPosicionCentral() {
		return new Posicion(
				posicionNorteOccidental.x+((posicionNorteOccidental.x-posicionSurOriental.x)/2),
				posicionNorteOccidental.y/2);
	}
	public Posicion getPosicionAleatoriaEnBarrio() {
		Random random = new Random();
		return new Posicion(random.nextInt(posicionSurOriental.x, posicionNorteOccidental.x),
				random.nextInt(posicionSurOriental.y, posicionNorteOccidental.y));
	}

	public boolean isInTo(Posicion posicionActual) {
		int x = posicionActual.getX();
		int y = posicionActual.getY();

		int minX = Math.min(posicionNorteOccidental.getX(), posicionSurOriental.getX());
		int maxX = Math.max(posicionNorteOccidental.getX(), posicionSurOriental.getX());
		int minY = Math.min(posicionNorteOccidental.getY(), posicionSurOriental.getY());
		int maxY = Math.max(posicionNorteOccidental.getY(), posicionSurOriental.getY());

		return x >= minX && x <= maxX && y >= minY && y <= maxY;
	}

	public Posicion vueltaACasa() {
		return getPuntoCentral();
	}

	private Posicion getPuntoCentral() {
		return new Posicion((posicionNorteOccidental.getX() - posicionSurOriental.getX()) / 2,
				(posicionNorteOccidental.getY() - posicionSurOriental.getY()) / 2);
	}

}
