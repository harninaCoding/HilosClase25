package justblockin17;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class BlockingTest {

	@Test
	void test() throws InterruptedException {
		CommonQeue commonQeue=new CommonQeue();
		WriterPushy writerPushy=new WriterPushy(commonQeue.numbers, 0);
		int initialNumber = 2000;
		WriterPushy writerPushyB=new WriterPushy(commonQeue.numbers, initialNumber);
//		ExecutorService newCachedThreadPool =Executors.newCachedThreadPool();
//		ExecutorService newCachedThreadPool =Executors.newFixedThreadPool(2);
		//Pool de Threads preparada para gran cantidad de tareas pequenas
		//la gestion incluye division de tareas en tareas más pequenas
		//llamadas works y el robo de tareas (un hilo puede robar tareas a otro hilo)
		ExecutorService newCachedThreadPool =ForkJoinPool.commonPool();
		Instant now = Instant.now();
		newCachedThreadPool.execute(writerPushy);
		newCachedThreadPool.execute(writerPushyB);
		newCachedThreadPool.awaitTermination(2, TimeUnit.SECONDS);
		newCachedThreadPool.shutdown();
		System.out.println(commonQeue.numbers.size()/2);
		long amount=howManyNumbersBiggerThanXbeforeZ(commonQeue.numbers,initialNumber,commonQeue.numbers.size()/2);
		System.out.println(amount);
		System.out.println(Duration.between(Instant.now(), now));
	}

	@Test
	void testTwo() {
		assertEquals(3, howManyNumbersBiggerThanXbeforeZ(List.of(100,100,100,23,23,100,100,100,100,100).stream()
				.collect(Collectors.toCollection(ArrayDeque::new)), 100, 5));
		
	}
	private long howManyNumbersBiggerThanXbeforeZ(Queue<Integer> numbers, int initialNumber, int i) {
		return numbers.stream().limit(i).filter((value)->{return value>=initialNumber;}).count();
	}

	

}
