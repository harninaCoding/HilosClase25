package cajero14Cola02;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Cola {
//	public ArrayDeque<Cliente> clientes;
	public ConcurrentLinkedQueue<Cliente> clientes = new ConcurrentLinkedQueue<>();

	public Cola() {
		super();
	}

	public boolean add(Cliente e) {
		return clientes.add(e);
	}

	public Cliente poll() {
		return clientes.poll();
	}

	public boolean isEmpty() {
		return clientes.isEmpty();
	}

}
