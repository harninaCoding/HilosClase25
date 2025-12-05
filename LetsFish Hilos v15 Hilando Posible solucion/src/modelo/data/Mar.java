package modelo.data;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class Mar {
	private List<Cardumen> cardumenes=new ArrayList();
	private int ancho;
	private int alto;
	public static int dia=1;
	private Dimension dimension; 

	private Mar(int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;
	}

	public Mar(Dimension dimension) {
		this(dimension.width, dimension.height);
		this.dimension=dimension;
	}

	public void agregarCardumen(Cardumen cardumen) {
		cardumenes.add(cardumen);
	}

	public void setNextDay() {
		dia++;
	}

	public List<Cardumen> obtenerCardumenes() {
		return cardumenes;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

	public int getToday() {
		return dia;
	}

	public void setCardumenes(List<Cardumen> cardumenes) {
		this.cardumenes = cardumenes;
	}

	public Dimension getDimension() {
		return dimension;
	}
}
