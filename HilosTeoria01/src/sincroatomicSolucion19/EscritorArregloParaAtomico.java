package sincroatomicSolucion19;

public class EscritorArregloParaAtomico implements Runnable {
	private final ArregloSimpleAtomico arregloSimpleCompartido;
	private final int valorInicial;

	public EscritorArregloParaAtomico(int valor, ArregloSimpleAtomico arreglo) {
		valorInicial = valor;
		arregloSimpleCompartido = arreglo;
	} 

	public void run() {
		System.out.println("yo ya");
		for (int i = valorInicial; i < valorInicial + 3; i++) {
			arregloSimpleCompartido.agregar(i); // agrega un elemento al arreglo compartido
		} 
	} 
} 