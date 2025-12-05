package modelo.data;

import java.util.*;

// Coordenada
public class Coordenada {
	private int x, y;

	public Coordenada(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Coordenada(Coordenada posicion) {
		this.x = posicion.x;
		this.y = posicion.y;
	}

	public double distanciaA(Coordenada otra) {
		return Math.sqrt(Math.pow(otra.x - this.x, 2) + Math.pow(otra.y - this.y, 2));
	}

	public void avanzarHacia(Coordenada destino, int velocidad) {
		double distancia = distanciaA(destino);
		if (distancia > velocidad) {
			double factor = velocidad / distancia;
			double d = (destino.x - x) * factor;
			double e = (destino.y - y) * factor;
			this.setDelta(d, e);;
		}else {
			this.x=destino.x;
			this.y=destino.y;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Coordenada))
			return false;
		Coordenada otra = (Coordenada) obj;
		return Double.compare(x, otra.x) == 0 && Double.compare(y, otra.y) == 0;
	}

	private void setDelta(double d, double e) {
		this.x += d;
		this.y += e;
	}

	public void setRandomDelta(int velocidadMovimiento, int ancho, int alto) {
		int horizontal = 0, vertical = 0;
		Random random = new Random();
		do {
			boolean horizontalFlag = random.nextBoolean();
			boolean verticalFlag = true;
			if (horizontalFlag) {
				horizontal = velocidadMovimiento * (random.nextBoolean() ? 1 : -1);
				verticalFlag = random.nextBoolean();
			}
			if (verticalFlag)
				vertical = velocidadMovimiento * (random.nextBoolean() ? 1 : -1);
			
		} while (!isInToLimits(ancho, alto,this.x+horizontal,this.y+vertical));
		setDelta(horizontal, vertical);
	}

	private boolean isInToLimits(double ancho, double alto, double x2, double y2) {
		return x2 >= 0 && x2 < ancho && y2 >= 0 && y2 < alto;
	}
	@Override
	public String toString() {
		return "posicion X:"+x+" posicionY:"+y;
	}
}
