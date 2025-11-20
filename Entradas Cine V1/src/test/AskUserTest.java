package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import modelo.Pit;
import modelo.PitCode;
import modelo.User;

class AskUserTest {

	@Test
	void test() {
		Pit pit=new Pit();
		User user=new User("99", pit);
		assertEquals(PitCode.free, user.askForASeat());
		assertEquals(PitCode.taken, user.askForASeat());
	}

}
