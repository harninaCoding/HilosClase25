package comienzo01;

class Meollo3 implements Runnable{
     @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
			System.out.println("buena la hermos hecho");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
     }
}
public class Comienzo4 {
    public static void main(String[] args) {
        
    Meollo3 cosa=new Meollo3();
    //cosa.run();
    //Como veis lo anterior no tiene sentido pero hemos de
    //imaginar que no podemos usar extends pero necesitamos
    //que sea un hilo
    //Ahora es cuando construimos el hilo usando el objeto
    //runnable que ya tiene el metodo run definido
    Thread otro=new Thread (new Meollo3(),"noseyo");
    otro.start();
    System.out.println(otro.getName());
    System.out.println("soy el main yo ya he terminado");
 }
    
}
