package executors06;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PruebaNoThreadAndJoin {
	public static void main(String[] args) {
	ProbandoRunnableUno uno = new ProbandoRunnableUno();
	//No puede hacer el join con executors service 
	ProbandoRunnableDos dos=new ProbandoRunnableDos(uno);
	ExecutorService executorService=Executors.newCachedThreadPool();
	executorService.execute(uno);
}}
