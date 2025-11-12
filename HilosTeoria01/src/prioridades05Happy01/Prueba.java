package prioridades05Happy01;
import java.time.Instant;

public class Prueba {
	public static void main(String[] args) {
		Element uno = new Element("uno", Instant.now());
		uno.setPriority(Thread.NORM_PRIORITY);
		Element dos = new Element("dos", Instant.now());
		dos.setPriority(Thread.MAX_PRIORITY);
		uno.start();
		dos.start();
		int size = 20;
		Element random;
		for (int i = 0; i < size; i++) {
			random = new Element("tres", Instant.now());
			random.setPriority(Thread.MIN_PRIORITY);
			random.start();
		}
	}
}
