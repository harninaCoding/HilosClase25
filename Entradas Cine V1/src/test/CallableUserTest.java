package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;

import modelo.Pit;
import modelo.Reference;
import modelo.User;

class CallableUserTest {

	@Test
	void test() {
		Pit pit = new Pit();
		User user = new User("99", pit);
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<Optional<Reference>> submit = executorService.submit(user);
		try {
			Reference reference = submit.get().get();
			assertEquals(new Reference('a', 1), reference);
			submit = executorService.submit(user);
			assertEquals(Optional.empty(), submit.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
