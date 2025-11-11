package cajero14Cola02;

import java.util.List;

public class MainPrueba {
	public static void main(String[] args) {
		List<Cliente> clientes = List.of(
				new Cliente("Cliente 1", new int[] { 2, 2, 1 }),
				new Cliente("Cliente 2", new int[] { 2, 2 }),
				new Cliente("Cliente 3", new int[] { 2, 2, 1 }));
		Cola cola=new Cola();
		clientes.forEach(cola::add);
		Caja caja = new Caja("Alberto", 100l,cola);
		Caja caja1 = new Caja("Sonsoles", 100l,cola);
		Caja caja2 = new Caja("Remigio", 100l,cola);
		Caja caja3 = new Caja("Eduviges", 100l,cola);
		Thread cajaHilo = new Thread(caja);
		Thread cajaHilo1 = new Thread(caja1);
		Thread cajaHilo2 = new Thread(caja2);
		Thread cajaHilo3 = new Thread(caja3);
		// comenzamos
		cajaHilo.start();
		cajaHilo1.start();
		cajaHilo2.start();
		cajaHilo3.start();
		cola.add(new Cliente("Juanito 1", new int[] { 2, 2, 1 }));
	}
}
