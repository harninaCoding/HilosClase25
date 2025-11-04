package sincronizacion03Solucion;

public class ProductoFinal {
	public static void main(String[] args) {
		ContenedorSincronizado c[] = new ContenedorSincronizado[4];
		ProductorSincronizado produce[] = new ProductorSincronizado[4];
		for (int i = 0; i < produce.length; i++) {
			c[i]=new ContenedorSincronizado();
			produce[i]=new ProductorSincronizado(c[i]);
			produce[i].start();
			
		}
		
		
	}
}
