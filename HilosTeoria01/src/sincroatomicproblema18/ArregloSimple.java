package sincroatomicproblema18;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ArregloSimple 
// PRECAUCI�N: �NO ES SEGURO PARA LOS SUBPROCESOS!
{
	private final int arreglo[]; // el arreglo entero compartido
	//Es una forma muy sencilla de sincronizar valores
	private int indiceEscritura = 0; 
	// indice del siguiente elemento a escribir
	private final static Random generador = new Random();

	// construye un objeto ArregloSimple de un tamano dado
	public ArregloSimple(int tamanio) {
		arreglo = new int[tamanio];
	} // fin del constructor

	// agrega un valor al arreglo compartido
	public void agregar(int valor) {
		int posicion = ++indiceEscritura;

		try {
			Thread.sleep(generador.nextInt(500));
		} 
		catch (InterruptedException ex) {
			ex.printStackTrace();
		} 

		arreglo[posicion] = valor;
		System.out.printf("%s escribio %2d en el elemento %d.\n", Thread.currentThread().getName(), valor, posicion);

//		operacion no atomica
//		++indiceEscritura; // incrementa el indice del siguiente elemento a escribir
		System.out.printf("Siguiente indice de escritura: %d %s\n", indiceEscritura,Thread.currentThread().getName());
	} // fin del metodo agregar

	// se utiliza para imprimir el contenido del arreglo entero compartido
	public String toString() {
		String cadenaArreglo = "\nContenido de ArregloSimple:\n";

		for (int i = 0; i < arreglo.length; i++)
			cadenaArreglo += arreglo[i] + " ";

		return cadenaArreglo;
	}
} 