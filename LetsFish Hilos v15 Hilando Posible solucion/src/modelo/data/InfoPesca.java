package modelo.data;

import modelo.enums.EspeciePez;

public class InfoPesca {
	private static Long last=0L;
	private Long id;
	private EspeciePez especiePez;
	private int cantidad;
	private Barco barco;
	private int dia;
	
	public InfoPesca(EspeciePez especiePez, int cantidad, Barco barco, int dia) {
		super();
		id=last++;
		this.especiePez = especiePez;
		this.cantidad = cantidad;
		this.barco = barco;
		this.dia = dia;
	}

	public static Long getLast() {
		return last;
	}

	public Long getId() {
		return id;
	}

	public EspeciePez getEspeciePez() {
		return especiePez;
	}

	public int getCantidad() {
		return cantidad;
	}

	public Barco getBarco() {
		return barco;
	}

	public int getDia() {
		return dia;
	}
	
}
