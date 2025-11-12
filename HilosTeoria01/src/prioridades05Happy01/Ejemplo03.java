package prioridades05Happy01;

import java.util.ArrayList;
class Hilo extends Thread{
	
	public Hilo(int priority) {
		super();
		this.setPriority(priority);
	}

	@Override
	public void run() {
		super.run();
		//probar con valores entre 10 y 10000
		int times=10000;
		String cString="";
		for (int i = 0; i < times; i++) {
			cString+="c";
		}
		System.out.println("Hilo de prioridad "+getPriority()+" ha terminado");
	}
}
public class Ejemplo03 {
public static void main(String[] args) {
	int size=5;
	ArrayList<Hilo> hiloMax=new ArrayList<>();
	ArrayList<Hilo> hiloNorm=new ArrayList<>();
	ArrayList<Hilo> hiloMin=new ArrayList<>();
	
	for (int i = 0; i < size; i++) {
		hiloMax.add(new Hilo(Thread.MAX_PRIORITY));
		hiloNorm.add(new Hilo(Thread.NORM_PRIORITY));
		hiloMin.add(new Hilo(Thread.MIN_PRIORITY));
	}
	for (int i = 0; i < size; i++) {
		hiloMin.get(i).start();
		hiloNorm.get(i).start();
		hiloMax.get(i).start();
	}
}
}
