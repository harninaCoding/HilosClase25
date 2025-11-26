package test.NoThreads;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import modelo.Pit;
import modelo.Reference;
import modelo.User;

class UserTestNoThread {

	@Test
	void testAskForASeat() {
		Pit pit=new Pit();
		User user=new User("99", pit);
		Reference reference=new Reference('a', 1);
		pit.takeSeat(reference, user);
		//siempre pide la misma a1
		user.askForASeat();
		assertEquals(reference,user.getRandomReference());
		assertEquals(pit.getSeat(reference).getTakerUser().get(),user);
	}

}
