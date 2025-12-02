package test.threads;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

import modelo.Pit;
import modelo.PitCode;
import modelo.Reference;
import modelo.User;

class AskUserTest {

	@Test
	void test() {
		Pit pit=new Pit();
		User user=new User("99", pit);
		Reference reference=new Reference('a', 1);
		assertEquals(PitCode.free, user.askForASeat(reference));
		assertEquals(PitCode.taken, user.askForASeat(reference));
	}

}
