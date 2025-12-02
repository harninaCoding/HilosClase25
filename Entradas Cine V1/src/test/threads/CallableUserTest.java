package test.threads;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;

import modelo.Pit;
import modelo.Reference;
import modelo.Seat;
import modelo.User;

class CallableUserTest {

	@Test
	void test() {
		
		Pit pit = new Pit();
		User user = new User("99", pit);
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<Optional<Reference>> submit = executorService.submit(user);
		try {
			Optional<Reference> reference = submit.get();
			assertTrue(reference.isPresent());
			submit = executorService.submit(user);
			assertEquals(Optional.empty(), submit.get());
			Seat seat = pit.getSeat(reference.get());
			seat.getTakerUser().ifPresent(seated->assertEquals(user,seated));
			//Pues ahora hay que probar con 2 y luego con hasta 100 y luego con 101
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}
