package sincroatomicSolucion19;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ArregloSimpleAtomico // PRECAUCI�N: �NO ES SEGURO PARA LOS SUBPROCESOS!
{
	private final int arreglo[]; // el arreglo entero compartido
	//Es una forma muy sencilla de sincronizar valores
	private AtomicInteger indiceEscritura = new AtomicInteger(0); // �ndice del siguiente elemento a escribir
	private final static Random generador = new Random();

	// construye un objeto ArregloSimple de un tamano dado
	public ArregloSimpleAtomico(int tamanio) {
		arreglo = new int[tamanio];
	} // fin del constructor

	// agrega un valor al arreglo compartido
	public void agregar(int valor) {
		//Esta operacion, en el otro ejemplo se hace en dos lineas 19 y 32 
		int posicion = indiceEscritura.getAndIncrement(); // almacena el indice de escritura
		try {
			// pone el subproceso en inactividad de 0 a 499 milisegundos
			Thread.sleep(generador.nextInt(500));
		} // fin de try
		catch (InterruptedException ex) {
			ex.printStackTrace();
		} // fin de catch

		// coloca el valor en el elemento apropiado
		arreglo[posicion] = valor;
		System.out.printf("%s escribio %2d en el elemento %d.\n", Thread.currentThread().getName(), valor, posicion);

//		operacion no atomica
		System.out.println("Siguiente indice de escritura: "+indiceEscritura+" "+Thread.currentThread().getName()+"\n");
	} // fin del metodo agregar

	// se utiliza para imprimir el contenido del arreglo entero compartido
	public String toString() {
		String cadenaArreglo = "\nContenido de ArregloSimple:\n";

		for (int i = 0; i < arreglo.length; i++)
			cadenaArreglo += arreglo[i] + " ";

		return cadenaArreglo;
	}
} 