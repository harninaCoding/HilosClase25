package test.NoThreads;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import modelo.Pit;
import modelo.Reference;
import modelo.User;

class UserTestNoThread {

	// Lo qu eprobamos aqui es que si el user pide un asiento en una referncia libre
	// se le asigna el asiento y en el asiento esta asociado el user
	@Test
	void testAskForASeat() throws Exception {
		Pit pit = new Pit();
		User user = new User("99", pit);
		Reference reference = new Reference('a', 1);
		pit.takeSeat(reference, user);
		// siempre pide la misma a1
		user.askForASeat(reference);
		assertEquals(reference, user.getRandomReference());
		Optional<User> takerUser = pit.getSeat(reference).getTakerUser();
		takerUser.ifPresent(a -> {
			assertEquals(a, user);
		});
		takerUser.orElseThrow(()->{return new Exception();});
	}

}
