package test;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import modelo.Pit;
import modelo.Reference;
import modelo.User;

class PitTest {

	@Test
	void test() throws ExecutionException, InterruptedException {
		Pit pit = new Pit();
		User user = new User("1", pit);
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<Optional<Reference>> submit = executorService.submit(user);
		Reference randomReference = submit.get().get();
		assertNotNull(randomReference);
		System.out.println(randomReference);
		executorService.shutdown();
	}
}
