package cajero14Market03;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

//solo hay una caja
public class Caja implements Runnable {

	private String nombre;

	private Cola cola;
	private Long timeStamp;
	//autopsia
	public int contadorClientes=0;

	public Caja(String nombre, Long timeStamp,Cola cola) {
		this(nombre);
		this.cola =cola;
		this.timeStamp = timeStamp;
	}

	public void add(Cliente e) {
		cola.push(Optional.ofNullable(e));
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
//		System.out.println("Demora base " + timeStamp);
		Instant now = Instant.now();
		Instant older = now;
//		System.out.println("El cajero " + this.nombre + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE "
//				+ cliente.getNombre() + " EN EL TIEMPO: " + older.getEpochSecond() + "seg");

		try {
			for (int i = 0; i < cliente.getCarroCompra().length; i++) {
				this.esperarXsegundos(cliente.getCarroCompra()[i]);
//			System.out.println("Procesado el producto " + (i + 1) + " ->Tiempo: "
//					+ Duration.between(now, now = Instant.now()).getSeconds() + "seg");
			}

//			System.out.println("El cajero " + this.nombre + " HA TERMINADO DE PROCESAR " + cliente.getNombre()
//					+ " EN EL TIEMPO: " + Duration.between(older, now) + "seg");
		} catch (Exception e) {
//			System.out.println("Caja:"+"cliente null");
			e.printStackTrace();
		}

	}

	private void esperarXsegundos(int segundos) {
		Utiles.wasteTime(segundos);
	}

		@Override
		public void run() {
			do {
				try {
					procesarCompra(cola.get(), 1);
					contadorClientes++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (true);
			
		}
	}
