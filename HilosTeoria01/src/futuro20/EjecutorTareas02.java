package futuro20;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EjecutorTareas02 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// crea y nombra cada objeto Runnable
		TareaImprimir01 tarea1 = new TareaImprimir01("tarea1",3000);
		TareaImprimir01 tarea2 = new TareaImprimir01("tarea2",1000);
		TareaImprimir01 tarea3 = new TareaImprimir01("tarea3",1000);
		System.out.println("Iniciando Executor");
		// crea objeto ExecutorService para administrar los subprocesos
		ExecutorService ejecutorSubprocesos = Executors.newCachedThreadPool();
		Future<String> submitResponse = ejecutorSubprocesos.submit(tarea1);
		ejecutorSubprocesos.submit(tarea2);
		ejecutorSubprocesos.submit(tarea3);
		//Aqui el main no espera el resultado
		//		System.out.println(submitResponse);
		//Aqui el main SI espera el resultado
		//		System.out.println(submitResponse.get());
		ejecutorSubprocesos.execute(new Recogedor02(submitResponse));
		ejecutorSubprocesos.shutdown();
		
		System.out.println("Tareas iniciadas, main termina.\n");
	}
}
