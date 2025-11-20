package justblockin17;

import java.util.Queue;
import java.util.Random;

public class WriterPushy implements Runnable{

	Queue<Integer> queue;
	int initialNumber;
	
	public WriterPushy(Queue<Integer> queue, int initialNumber) {
		super();
		this.queue = queue;
		this.initialNumber = initialNumber;
	}

	@Override
	public void run() {
		int capacity=0;
		do {
			queue.add(new Random().nextInt(initialNumber+1000));
		}while(++capacity<400);
	}
	

}
