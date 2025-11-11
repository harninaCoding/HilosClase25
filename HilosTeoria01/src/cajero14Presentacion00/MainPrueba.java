package cajero14Presentacion00;

public class MainPrueba {
public static void main(String[] args) {
	Cliente cliente=new Cliente("juan",  new int[]{1,2,2});
	Caja caja=new Caja("Alberto");
	caja.procesarCompra(cliente, 100);
}
}
