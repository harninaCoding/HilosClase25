package futuro20;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EjecutorTareas011{
	public static void main(String[] args) {
		// crea y nombra cada objeto Runnable
		//Comparar con Ejecutor00 de teoriahilosv11
		TareaImprimir01 tarea1 = new TareaImprimir01("tarea1",5000);
		TareaImprimir01 tarea2 = new TareaImprimir01("tarea2",1000);
		TareaImprimir01 tarea3 = new TareaImprimir01("tarea3",1000);
		System.out.println("Iniciando Executor");
		// crea objeto ExecutorService para administrar los subprocesos
		ExecutorService ejecutorSubprocesos = Executors.newCachedThreadPool();
		ejecutorSubprocesos.execute(new Runnable() {
			public void run() {
				System.out.println();
			}
		});
		Future<String> submitResponse = ejecutorSubprocesos.submit(tarea1);
		ejecutorSubprocesos.submit(tarea2);
		ejecutorSubprocesos.submit(tarea3);
		ejecutorSubprocesos.shutdown();
		try {
			//esperando la respuesta del Callable
			System.out.println("submit response "+submitResponse.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Tareas iniciadas, main termina.\n");
	}
}
