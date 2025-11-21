package futuro20;

import java.util.Random;
import java.util.concurrent.Callable;

public class TareaImprimir01 implements Callable<String> {
	private final int tiempoInactividad; // tiempo de inactividad aleatorio para el subproceso
	private final String nombreTarea; // nombre de la tarea
	private final static Random generador = new Random();

	public TareaImprimir01(String nombre,int espera) {
		nombreTarea = nombre;
		tiempoInactividad = generador.nextInt(espera);
	}

	@Override
	public String call() throws Exception {
		try {
			System.out.printf("%s va a estar inactivo durante %d milisegundos.\n", nombreTarea, tiempoInactividad);
			Thread.sleep(tiempoInactividad);
		} catch (InterruptedException excepcion) {
			System.out.printf("%s %s\n", nombreTarea, "termino en forma prematura, debido a la interrupcion");
		}

		System.out.printf("%s termino su inactividad\n", nombreTarea);
		return nombreTarea;
	}
}