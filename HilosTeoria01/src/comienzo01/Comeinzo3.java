package comienzo01;

class Meollo2 extends Thread {
	Meollo2(String str) {
		super(str);
		System.out.println(str);
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("buena la hermos hecho");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class Comeinzo3 {
	public static void main(String[] args) {
		Meollo2 cosa = new Meollo2("pepe");
		// Luego la pruebo
		cosa.start();
		// observa el orden de la ejecucion
		System.out.println("Yo ya he llegado al final, soy el main");
	}
}
