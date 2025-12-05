package modelo.enums;

import java.awt.Dimension;

public enum Dimensiones {
	
	small(100,100), medium(500,500), huge(1000,1000);
	private int ancho,alto;

	private Dimensiones(int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;
	}

	public Dimension getDimension() {
		return new Dimension(ancho,alto);
	}
	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}
	 
}
