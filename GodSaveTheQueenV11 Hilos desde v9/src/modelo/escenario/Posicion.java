package modelo.escenario;

import java.util.Objects;
import java.util.Random;

public class Posicion {
	int x, y;

	public Posicion(Posicion posicionRefugio) {
		this(posicionRefugio.getX(), posicionRefugio.getY());
	}

	public Posicion(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * Genera una nueva posicion aleatoria dentro del barrio. El
	 * delta es la distancia maxima que puede recorrer en cada eje.
	 * y viene definida entre (-1,2) tanto para X como para Y.
	 * 
	 * @param barrio es el barrio en el que se encuentra la posicion
	 * @return la nueva posicion
	 */
	public Posicion deambula(Barrio barrio) {
		Random random = new Random();
		int increXmin = -1, increXmax = 2;
		int increYmin = -1, increYmax = 2;
		if (this.x <= barrio.getPosicionSurOriental().getX())
			increXmin = 0;
		if (this.x >= barrio.getPosicionNorteOccidental().getX())
			increXmax = 0;
		if (this.y <= barrio.getPosicionSurOriental().getY())
			increYmin = 0;
		if (this.y >= barrio.getPosicionCentral().getY())
			increYmax = 0;
		Posicion posicion = new Posicion(this.x + random.nextInt(increXmin, increXmax),
				this.y + random.nextInt(increYmin, increYmax));
		if (posicion.x == this.x && posicion.y == this.y) {
			posicion.x = this.x + 1;
		}
		return posicion;
	}

	/**
	 * Nos dice si la posicion, pasada como parametro, esta dentro del radio de
	 * accion
	 * es decir si dos posiciones se solapan cuando se les aplica el radio de accion
	 * 
	 * @param posicion    la posicion a comprobar
	 * @param radioAccion el radio de accion
	 * @return true si se solapan, false en caso contrario
	 */
	public boolean solapaMiArea(Posicion posicion, int radioAccion) {
		return solapaArea(this, posicion, radioAccion);
	}

	/**
	 * cambia la posicion actual segun la velocidad, hacia el destino
	 * 
	 * @param destino   posicion que queremos alcanzar
	 * @param velocidad velocidad con la que se mueve
	 */
	public void moveTo(Posicion destino, int velocidad) {
		if (destino == null || velocidad <= 0)
			return;

		int dx = destino.getX() - this.x;
		int dy = destino.getY() - this.y;

		// ya est� en el destino
		if (dx == 0 && dy == 0)
			return;

		// si puedo llegar este turno, me planto en el destino
		int distanciaManhattan = Math.abs(dx) + Math.abs(dy);
		if (distanciaManhattan <= velocidad) {
			this.x = destino.getX();
			this.y = destino.getY();
			return;
		}

		// reparto la velocidad entre X e Y seg�n lo lejos que est� en cada eje
		int total = Math.abs(dx) + Math.abs(dy);

		int pasoX = (int) Math.round((double) velocidad * Math.abs(dx) / total);
		int pasoY = velocidad - pasoX;

		// aplicar signo
		pasoX *= Integer.signum(dx);
		pasoY *= Integer.signum(dy);

		// no pasarme del destino en cada eje
		if (Math.abs(pasoX) > Math.abs(dx))
			pasoX = dx;
		if (Math.abs(pasoY) > Math.abs(dy))
			pasoY = dy;

		// si por redondeo no se mueve, forzar al menos 1 casilla
		if (pasoX == 0 && pasoY == 0) {
			if (Math.abs(dx) >= Math.abs(dy)) {
				pasoX = Integer.signum(dx);
			} else {
				pasoY = Integer.signum(dy);
			}
		}

		this.x += pasoX;
		this.y += pasoY;
	}

	public int getDistanciaCuadrado(Posicion otra) {
		int dx = this.x - otra.x;
		int dy = this.y - otra.y;
		return dx * dx + dy * dy;
	}

	private boolean isSolapada(Posicion otra, int radio) {
		int dx = this.x - otra.x;
		int dy = this.y - otra.y;
		int distancia2 = dx * dx + dy * dy;
		return distancia2 <= radio * radio;
	}

	private boolean solapaArea(Posicion posicionUno, Posicion posicionDos, int radioAccion) {
		return posicionUno.isSolapada(posicionDos, radioAccion);
	}

	/**
	 * Nos dice si la posicion esta a una distancia de 1 o menos de otra
	 * 
	 * @param p la posicicion a la que se compara
	 * @return true si esta a una distancia de 1 o menos, false en caso contrario
	 */
	public boolean isAlcanzado(Posicion p) {
		return solapaArea(this, p, 1);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicion other = (Posicion) obj;
		return x == other.x && y == other.y;
	}

	@Override
	public String toString() {
		return "x:" + x + "--" + "y:" + y;
	}

}
