package ejerciciosincronizacion04.hilos;

import java.time.Duration;
import java.time.Instant;

public class Ser extends Thread{

	int vida;
	int tamano = 0;
	String nombre;

	public Ser(String nombre, int vida) {
		this.vida = vida;
		this.nombre = nombre;
	}

	@Override
	public void run() {
		super.run();
		vivir();
	}
	
	private void vivir() {
		System.out.println("Llego aqui " + nombre);
		int viajesALaFuenteSinExito=0;
		while (tamano < vida) {
			Instant now = Instant.now();
			int beber = Lago.getInstance().beber();
			if(beber==0) viajesALaFuenteSinExito++;
			tamano+=beber;
			System.out.println("creci "+beber);
			System.out.println(nombre);
			System.out.println("he tardado "+(Duration.between(Instant.now(), now)));
		}
		System.out.println("muriendo con tamano " + tamano);
		System.out.println("e hice "+viajesALaFuenteSinExito+" viajes sin exito");
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

	

}
