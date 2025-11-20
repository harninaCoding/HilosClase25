package sincroatomicproblema18;

public class MyThread extends Thread{
	AtomicThings ss;
	
	public MyThread(AtomicThings ss,String name) {
		super(name);
		this.ss = ss;
	}

	@Override
	public void run() {
		int recibir=++ss.probar;
		System.out.println("valor de recibir "+recibir);
	}
}
