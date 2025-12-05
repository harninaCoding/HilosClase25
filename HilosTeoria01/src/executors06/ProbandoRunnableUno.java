package executors06;

public class ProbandoRunnableUno implements Runnable{

	private String name;
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("haciendo el uno");
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
