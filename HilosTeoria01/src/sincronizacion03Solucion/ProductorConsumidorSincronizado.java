package sincronizacion03Solucion;

public class ProductorConsumidorSincronizado {
	public static void main(String[] args) {
		ContenedorSincronizado c = new ContenedorSincronizado();
		ProductorSincronizado produce = new ProductorSincronizado(c);
		ConsumidorSincronizado consume = new ConsumidorSincronizado(c);
		produce.start();
		consume.start();
	}
}
