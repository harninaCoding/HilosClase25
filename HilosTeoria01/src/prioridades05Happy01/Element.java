package prioridades05Happy01;

import java.time.Duration;
import java.time.Instant;

public class Element extends Thread {

	public int value;
	Instant now;
	
	public Element(String string,Instant instant) {
		super(string);
		now=instant;
	}

	public void run() {
		int times=10;
		for (int i = 0; i < times; i++) {
			value++;
//			try {
//				sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.print("");
//			Utiles.wasteTime(30000);
		}
		System.out.println("terminando el proceso "+getName()+" con tiempo "+Duration.between(now, Instant.now()));
	};
}
