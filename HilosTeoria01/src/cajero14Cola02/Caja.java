package cajero14Cola02;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;

//solo hay una caja
public class Caja implements Runnable {

	private String nombre;

	private Cola clientes;
	private Long timeStamp;

	public Caja(String nombre, Long timeStamp,Cola cola) {
		this(nombre);
		this.clientes =cola;
		this.timeStamp = timeStamp;
	}

	public boolean add(Cliente e) {
		return clientes.add(e);
	}

	public Caja() {
	}

	public Caja(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void procesarCompra(Cliente cliente, long timeStamp) {
		System.out.println("Demora base " + timeStamp);
		Instant now = Instant.now();
		Instant older = now;
		System.out.println("El cajero " + this.nombre + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE "
				+ cliente.getNombre() + " EN EL TIEMPO: " + older.getEpochSecond() + "seg");

		for (int i = 0; i < cliente.getCarroCompra().length; i++) {
			this.esperarXsegundos(cliente.getCarroCompra()[i]);
			System.out.println("Procesado el producto " + (i + 1) + " ->Tiempo: "
					+ Duration.between(now, now = Instant.now()).getSeconds() + "seg");
		}

		System.out.println("El cajero " + this.nombre + " HA TERMINADO DE PROCESAR " + cliente.getNombre()
				+ " EN EL TIEMPO: " + Duration.between(older, now) + "seg");

	}

	private void esperarXsegundos(int segundos) {
		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	@Override
	public void run() {
		while (!clientes.isEmpty()) {
			procesarCompra(clientes.poll(), timeStamp);
		}
	}
}