package sincroLevelDos16;

public class Prueba {

	public static void main(String[] args) {
		Cuenta origen=new Cuenta(1, "origen"),
		destino=new Cuenta(0,"destino");
		origen.deposito(100);
		new Thread(new HiloTransferencia(origen,destino,50)).start();
		new Thread(new HiloTransferencia(origen,destino,50)).start();
//		new Thread(new HiloTransferencia(origen,destino,50)).start();
//		new Thread(new HiloTransferencia(origen,destino,50)).start();
		System.out.println();
	}

}
