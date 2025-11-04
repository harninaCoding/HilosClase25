package comienzo01;

public class Comienzo6 {
	public static void main(String[] args) {
		Thread vamos = new Thread() {
			@Override
			public void run() {
				super.run();
				while (!isInterrupted()) {
					System.out.println("Pa habernos matao");
					try {
						this.sleep(200);
					} catch (InterruptedException e) {
						this.interrupt();
					}
				}
				System.out.println("fin del hilo");
			}
		};
		vamos.start();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vamos.interrupt();
	}
}
