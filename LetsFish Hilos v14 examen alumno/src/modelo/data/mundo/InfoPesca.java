package modelo.data.mundo;

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

	public static void setLast(Long last) {
		InfoPesca.last = last;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EspeciePez getEspeciePez() {
		return especiePez;
	}

	public void setEspeciePez(EspeciePez especiePez) {
		this.especiePez = especiePez;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Barco getBarco() {
		return barco;
	}

	public void setBarco(Barco barco) {
		this.barco = barco;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}
	
}
