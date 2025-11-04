package comienzo01;

public class Comienzo5 {
	public static void main(String[] args) throws InterruptedException {
		Thread vamos = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					System.out.println("Pa habernos matao");
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		vamos.start();
		System.out.println("antes del sleep");
		Thread.sleep(1000);
		System.out.println("despues del sleep");
//		vamos.stop();
//		vamos.suspend();
//		Thread.sleep(1000);
//		vamos.resume();
//		vamos.destroy();
		
	}

}
