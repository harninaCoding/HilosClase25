package sincroatomicproblema18;

public class EscritorArreglo implements Runnable {
	private final ArregloSimple arregloSimpleCompartido;
	private final int valorInicial;

	public EscritorArreglo(int valor, ArregloSimple arreglo) {
		valorInicial = valor;
		arregloSimpleCompartido = arreglo;
	} 

	public void run() {
		System.out.println("aqui empezando");
		for (int i = valorInicial; i < valorInicial + 3; i++) {
			arregloSimpleCompartido.agregar(i); // agrega un elemento al arreglo compartido
		} 
	} 
} 