package test.NoThreads;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Pit;
import modelo.PitCode;
import modelo.Reference;
import modelo.Row;
import modelo.Seat;
import modelo.User;
import modelo.UserRequest;

class PitTestNOThread {
	Pit pit;
	User user;
	Reference reference;
	int totalSeats;

	@BeforeEach
	void before() {
		pit = new Pit();
		user = new User("99", pit);
		reference = new Reference('a', 1);
		int amountRows = pit.amountRows;
		Row row = pit.getRow('a');
		int amountSeats = row.amountSeats;
		totalSeats = amountRows * amountSeats;
	}

	@Test
	void testTAkeSeat() {
		boolean takeSeat = pit.takeSeat(reference, user);
		assertTrue(takeSeat);
		takeSeat = pit.takeSeat(reference, user);
		assertFalse(takeSeat);
		Seat seat = pit.getSeat(reference);
		assertEquals(seat.getTakerUser().get(), user);
		assertEquals(user.getRandomReference(), reference);
	}

	@Test
	void testFreeSeats() {
		List<Reference> freeSeats = pit.getFreeSeats();
		assertEquals(totalSeats, freeSeats.size());
	}

	@Test
	void testTakinFreeSeats() {
		boolean takeSeat = pit.takeSeat(new Reference('a', 1), user);
		int leftSeats = totalSeats;
		List<Reference> freeSeats = pit.getFreeSeats();
		if (takeSeat) {
			leftSeats--;
			assertEquals(leftSeats, freeSeats.size());
		} else {
			assertEquals(totalSeats, freeSeats.size());
		}
		takeSeat = pit.takeSeat(new Reference('a', 1), user);
		freeSeats = pit.getFreeSeats();
		if (takeSeat) {
			leftSeats--;
			assertEquals(leftSeats, freeSeats.size());
		} else {
			assertEquals(totalSeats, freeSeats.size());
		}
	}

	@Ignore
	void testResponseRequest() {
		Pit pit = new Pit();
		User user = new User("1", pit);
		User user2 = new User("2", pit);
		Reference reference = new Reference('a', 1);
		assertEquals(PitCode.free, pit.responseRequest(new UserRequest(user, reference), user));
	}
}
