package executors06;

public class ProbandoRunnableDos implements Runnable{

	private String name;
	private Thread uno;
	
	
	public ProbandoRunnableDos(Thread uno) {
		super();
		this.uno = uno;
	}

	@Override
	public void run() {
		//lo que hace join es que da preferencia al uno
		//es decir el dos no comenzara hasta que no acabe el uno
		try {
			uno.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 10; i++) {
			System.out.println("haciendo el dos");
		}
		System.out.println(" terminando el hilo "+getName());
	};
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
