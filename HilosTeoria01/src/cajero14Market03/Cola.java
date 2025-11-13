package cajero14Market03;

import java.util.ArrayDeque;
import java.util.Optional;

public class Cola {
	public ArrayDeque<Cliente> clientes = new ArrayDeque<>();
	private int totalClientes = 0;

	public void push(Optional<Cliente> randomClient) {
		randomClient.ifPresent((cliente) -> clientes.push(cliente));
	}

	public boolean isBigger(int i) {
		return clientes.size() > i;
	}

	public synchronized Cliente get() throws InterruptedException {
		while (clientes.isEmpty() && !totalClientesAlcanzado()) {
			wait();
		}
//		System.out.println("is Empty "+clientes.isEmpty());
//		System.out.println("is total "+totalClientesAlcanzado());
		if(!clientes.isEmpty())
		return clientes.pop();
		return null;
	}

	public boolean totalClientesAlcanzado() {
		return totalClientes > 20;
	}

	public synchronized void put(Optional<Cliente> optional) {
		totalClientes++;
		push(optional);
//		optional.ifPresent((cliente)->System.out.println("hay cliente nuevo"));
		notifyAll();
	}

	public int size() {
		return clientes.size();
	}
}
