package sincroLevelDos16;

public class HiloTransferencia implements Runnable {
	private Cuenta cuentaOrigen;
	private Cuenta cuentaDestino;
	private Integer delay;

	public HiloTransferencia(Cuenta cuentaOrigen, Cuenta cuentaDestino,int delay) {
		this.cuentaOrigen = cuentaOrigen;
		this.cuentaDestino = cuentaDestino;
		this.delay=delay;
	}

	public void run() {
		System.out.println("Los retiros e imposiciones son de 50");
		String hilo = Thread.currentThread().getName();
		System.out.println("comienza el proceso de transferencia de "+cuentaOrigen.getNombre());
		synchronized (cuentaDestino) {
			System.out.println(hilo + " bloquea cuenta destino de " + cuentaOrigen.getNombre());
			int cantidad=hacerRetiro(cuentaOrigen, 50);
			try {
				Thread.currentThread().sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (cuentaOrigen.getSaldo() < 0) {
				System.out.println("saldo insuficiente");
			}
			System.out.println(hilo + " inicia el deposito " + "en cuenta " + cuentaDestino.getNombre()+" de cantidad "+cantidad);
			hacerDeposito(cuentaDestino, cantidad);
			System.out.println(hilo + " termina la transferencia ");
			System.out.println(hilo + " termina el bloqueo de " + cuentaOrigen.getNombre());
		}
	}

	private int hacerRetiro(Cuenta cuenta, int cantidad) {
		String hilo = Thread.currentThread().getName();
		if (cuenta.getSaldo() >= cantidad) {
			System.out.println(hilo + " antes a realizar un retiro: saldo " + cuenta.getSaldo());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Error de thread");
			}
			cuenta.retiro(cantidad);
			System.out.println(hilo + " despues de realizar un retiro: saldo " + cuenta.getSaldo());
		} else {
			System.out.println(
					"No hay suficiente saldo para que " + hilo + " realice un retiro: saldo (" + cuenta.getSaldo() + ")");
			cantidad=cuenta.getSaldo();
		}
		return cantidad;
	}

	private void hacerDeposito(Cuenta cuenta, int cantidad) {
		String hilo = Thread.currentThread().getName();
		synchronized (cuenta) {
			System.out.println(hilo + " antes de realizar un deposito: saldo " + cuenta.getSaldo());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Error de thread");
			}
			cuenta.deposito(cantidad);
			System.out.println(hilo + " despues de realizar un deposito: saldo " + cuenta.getSaldo());
		}
	}
}
