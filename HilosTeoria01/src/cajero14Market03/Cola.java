package cajero14Market03;

import java.util.ArrayDeque;
import java.util.Optional;

public class Cola {
	public ArrayDeque<Cliente> clientes = new ArrayDeque<>();
	private int totalClientes = 0;
	
	public Cola() {
		super();
	}

	public void push(Optional<Cliente> randomClient) {
		randomClient.ifPresent((cliente) -> clientes.add(cliente));
	}

	public boolean isBigger(int i) {
		return clientes.size() > i;
	}
	
	public Cliente poll() {
		return clientes.poll();
	}

	public boolean isEmpty() {
		return clientes.isEmpty();
	}

}
