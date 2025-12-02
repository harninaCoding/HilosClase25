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
		user.pillaEntrada(pit);
		Optional<User> takerUser = pit.getSeat(user.getRandomReference()).getTakerUser();
		takerUser.ifPresent(a -> {
			assertEquals(a, user);
		});
		takerUser.orElseThrow(()->{return new Exception();});
	}

}
