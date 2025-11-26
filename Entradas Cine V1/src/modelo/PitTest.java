package modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PitTest {

	@Test
	void testResponseRequest() {
		Pit pit=new Pit();
		User user=new User("1", pit);
		User user2=new User("2", pit);
		Reference reference=new Reference('a', 1);
		assertEquals(PitCode.free,pit.responseRequest(new UserRequest(user, reference)));
	}

}
