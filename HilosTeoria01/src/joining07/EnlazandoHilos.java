package joining07;


public class EnlazandoHilos {
	public static void main(String[] args) {
		Thread uno = new Thread("uno") {
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("haciendo el uno");
				}
				System.out.println(" terminando el hilo "+getName());
			};
		};
		Thread dos = new Thread("dos") {
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
			
		};
		System.out.println("comenzando el dos");
		dos.start();
		System.out.println("comenzando el uno");
		uno.start();
		
			
	}
}
