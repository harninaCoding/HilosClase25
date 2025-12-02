package test.NoThreads;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import modelo.Pit;
import modelo.Reference;
import modelo.User;

public class UserRAndomSeatTest {
	@Test
	void test() {
		Pit pit=new Pit();
		User user=new User("99", pit);
		Optional<Reference> pillaEntrada = user.pillaEntrada(pit);
		assertEquals(pillaEntrada,Optional.of(user.getTakenSeat()));
		pillaEntrada = user.pillaEntrada(pit);
		assertEquals(pillaEntrada,Optional.of(user.getTakenSeat()));
		pillaEntrada = user.pillaEntrada(pit);
		assertEquals(pillaEntrada,Optional.of(user.getTakenSeat()));
		pillaEntrada = user.pillaEntrada(pit);
		assertEquals(pillaEntrada,Optional.of(user.getTakenSeat()));
		pillaEntrada = user.pillaEntrada(pit);
		assertEquals(pit.getFreeSeats().size(),95);
	}

}
