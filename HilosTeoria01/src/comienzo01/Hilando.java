package comienzo01;

//Hilando hereda de thread, eso significa que puede usar sus metodos
//y propiedades como propias.
public class Hilando extends Thread {

	public Hilando(String str) {
		// el constructor de Hilando
		super(str);
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++)
			System.out.println(i + " " + getName());
		System.out.println("Termina thread " + getName());
	}

	public static void main(String[] args) {
		new Hilando("Pepe").start();
		new Hilando("Juan").start();
		new Hilando("Juan2").start();
		new Hilando("Juan3").start();
		new Hilando("Juan4").start();
		new Hilando("Juan5").start();
		new Hilando("Juan6").start();
		new Hilando("Juan7").start();
		new Hilando("Juan8").start();
		new Hilando("Juan9").start();
		new Hilando("Juan10").start();
		System.out.println("Termina thread main");
	}
}
