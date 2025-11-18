package cajero14Market03;

import java.util.ArrayDeque;
import java.util.Optional;

public class Cola {
	public ArrayDeque<Cliente> clientes = new ArrayDeque<>();
	private int totalClientesAgregados = 0;
	public int totalClientesProcesados = 0;

	public void push(Optional<Cliente> randomClient) {
		randomClient.ifPresent((cliente) -> clientes.push(cliente));
	}

	public boolean isBigger(int i) {
		return clientes.size() > i;
	}

	public synchronized Cliente get() throws InterruptedException {
		while (!(totalClientesProcesados<totalClientesAgregados)) {
			wait();
		}
//		System.out.println("is Empty "+clientes.isEmpty());
//		System.out.println("is total "+totalClientesAlcanzado());
		if (!clientes.isEmpty()) {
			Cliente pop = clientes.pop();
			totalClientesProcesados++;
			return pop;
		}
		return null;
	}

	public boolean totalClientesAlcanzado() {
		return totalClientesAgregados > 20;
	}

	public synchronized void put(Optional<Cliente> optional) {
		totalClientesAgregados++;
//		System.out.println("Cola:"+totalClientesAgregados);
		push(optional);
//		optional.ifPresent((cliente)->System.out.println("hay cliente nuevo"));
		notify();
	}

	public int size() {
		return clientes.size();
	}
}
