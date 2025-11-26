package test.NoThreads;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import modelo.Pit;
import modelo.PitCode;
import modelo.Reference;
import modelo.Seat;
import modelo.User;
import modelo.UserRequest;

class PitTestNOThread {

	@Test
	void testTAkeSeat() {
		Pit pit=new Pit();
		User user=new User("99", pit);
		Reference reference = new Reference('a', 1);
		boolean takeSeat = pit.takeSeat(reference,user);
		assertTrue(takeSeat);
		takeSeat = pit.takeSeat(reference,user);
		assertFalse(takeSeat);
		Seat seat=pit.getSeat(reference);
		assertEquals(seat.getTakerUser().get(),user);
		assertEquals(user.getRandomReference(),reference);
	}
	
	@Ignore
	void testResponseRequest() {
		Pit pit=new Pit();
		User user=new User("1", pit);
		User user2=new User("2", pit);
		Reference reference=new Reference('a', 1);
		assertEquals(PitCode.free,pit.responseRequest(new UserRequest(user, reference),user));
	}
}
