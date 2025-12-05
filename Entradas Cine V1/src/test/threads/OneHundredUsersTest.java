package test.threads;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import modelo.Pit;
import modelo.Reference;
import modelo.User;

public class OneHundredUsersTest {

	@Test
	void test150() throws InterruptedException, ExecutionException {
		Pit pit = new Pit();
		int freeSeats = pit.getFreeSeats().size();
		ArrayList<User> users = new ArrayList();
		int amount = 200;
		for (int i = 0; i < amount; i++) {
			users.add(new User(String.valueOf(i), pit));
		}
		ExecutorService es = Executors.newCachedThreadPool();
		ArrayList<Optional<Reference>> responses = new ArrayList();
		for (User user : users) {
			responses.add(es.submit(user).get());
		}
		// no hay ninguno repetido
		Stream<Reference> map = users.stream().map(User::getTakenSeat)
				.filter(reference->!reference.equals(User.getReferenceControl()));
		Map<Reference, Long> collect = map
				.collect(Collectors.groupingBy(reference -> reference, Collectors.counting()));
		assertFalse(collect.values().stream().anyMatch(c -> c > 1));
		List<Reference> collectSeat = 
				responses.stream()
					.map(fut -> {
						return fut.get();
					})
					.filter(reference->!reference.equals(User.getReferenceControl()))
					.collect(Collectors.toList());
		assertEquals(freeSeats-collectSeat.size(), pit.getFreeSeats().size());
		for (Reference reference : collectSeat) {
			assertTrue(pit.isTaken(reference));
		}
		//Hay 50 clientes sin asiento, es decir , con el asiento de control ('x',100)
		long userWithNoSeat = users.stream().filter(use->User.getReferenceControl().equals(use.getTakenSeat())).count();
		assertEquals(userWithNoSeat+collectSeat.size(), users.size());
	}
	@Ignore
	void test101() throws InterruptedException, ExecutionException {
		Pit pit = new Pit();
		int freeSeats = pit.getFreeSeats().size();
		ArrayList<User> users = new ArrayList();
		int amount = 101;
		for (int i = 0; i < amount; i++) {
			users.add(new User(String.valueOf(i), pit));
		}
		ExecutorService es = Executors.newCachedThreadPool();
		ArrayList<Optional<Reference>> responses = new ArrayList();
		for (User user : users) {
			responses.add(es.submit(user).get());
		}
		assertEquals(freeSeats - 100, pit.getFreeSeats().size());
		// no hay ninguno repetido
		Stream<Reference> map = users.stream().map(User::getTakenSeat)
				.filter(reference->!reference.equals(User.getReferenceControl()));
		Map<Reference, Long> collect = map
				.collect(Collectors.groupingBy(reference -> reference, Collectors.counting()));
		assertFalse(collect.values().stream().anyMatch(c -> c > 1));
		List<Reference> collectSeat = 
				responses.stream()
				.map(fut -> {
					return fut.get();
				})
				.filter(reference->!reference.equals(User.getReferenceControl()))
				.collect(Collectors.toList());
		for (Reference reference : collectSeat) {
			assertTrue(pit.isTaken(reference));
		}
	}
	
	@Ignore
	void test100() throws InterruptedException, ExecutionException {
		Pit pit = new Pit();
		int freeSeats = pit.getFreeSeats().size();
		ArrayList<User> users = new ArrayList();
		// primero 10,cuando funciona con 100 prueba 101
		int amount = 100;
		for (int i = 0; i < amount; i++) {
			users.add(new User(String.valueOf(i), pit));
		}
		ExecutorService es = Executors.newCachedThreadPool();
		ArrayList<Optional<Reference>> responses = new ArrayList();
		for (User user : users) {
			responses.add(es.submit(user).get());
		}
		assertEquals(freeSeats - amount, pit.getFreeSeats().size());
		// no hay ninguno repetido
		Stream<Reference> map = users.stream().map(User::getTakenSeat);
		Map<Reference, Long> collect = map
				.collect(Collectors.groupingBy(reference -> reference, Collectors.counting()));
		assertFalse(collect.values().stream().anyMatch(c -> c > 1));
		List<Reference> collectSeat = responses.stream().map(fut -> {
			return fut.get();
		}).collect(Collectors.toList());
		for (Reference reference : collectSeat) {
			assertTrue(pit.isTaken(reference));
		}
	}
}
