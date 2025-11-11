package cajero14Threads01;

import java.util.List;

public class MainPrueba {
	public static void main(String[] args) {
		List<Cliente> clientes = List.of(new Cliente("Cliente 1", new int[] { 2, 2, 1 }),
				new Cliente("Cliente 2", new int[] { 2, 2, 1 }), new Cliente("Cliente 3", new int[] { 2, 2, 1 }));
		Caja caja = new Caja("Alberto", 100l);
		clientes.forEach(caja::add);
		Thread cajaHilo = new Thread(caja);
		// comenzamos
		cajaHilo.start();
		caja.add(new Cliente("Juanito 1", new int[] { 2, 2, 1 }));
	}
}
