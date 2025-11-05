package ejerciciosincronizacion04.planteamiento;

import java.time.Duration;
import java.time.Instant;

public class Ser {

	int vida;
	int tamano = 0;
	String nombre;

	public Ser(String nombre, int vida) {
		this.vida = vida;
		this.nombre = nombre;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getTamano() {
		return tamano;
	}

	public void setTamano(int tamano) {
		this.tamano = tamano;
	}

	@Override
	public String toString() {
		return "tiene de vida " + vida + " y de tamano " + tamano;
	}

	public void vivir() {
		System.out.println("Llego aqui " + nombre);
		int contadorMaximo=10;
		while (tamano < vida&&contadorMaximo-->0) {
			Instant now = Instant.now();
			int beber = Lago.getInstance().beber();
			tamano+=beber;
			System.out.println("creci "+beber);
			System.out.println(nombre);
			System.out.println("he tardado "+(Duration.between(Instant.now(), now)));
		}
		System.out.println("muriendo con tamano " + tamano);
	}

}
