package modelo;

import java.util.Optional;
import java.util.concurrent.Callable;

public class User implements Callable<Optional<Reference>> {
	private String id;
	private Pit pit;
	private Reference randomReference=null;
	
	public User(String id, Pit pit) {
		super();
		this.id = id;
		this.pit = pit;
	}

	@Override
	public Optional<Reference> call() {
//		boolean takeSeat = false;
//		do {
//			// aqui obtengo el asiento que quiero
//			randomReference = Reference.getRandomReference();
//			// Pedir la reserva de asiento
//			takeSeat = pit.takeSeat(randomReference);
//		} while (!takeSeat);
		PitCode pitCode = askForASeat();
		if(pitCode==PitCode.free)
		return Optional.of(randomReference);
		return Optional.ofNullable(null);
	}

	public Reference getRandomReference() {
		return randomReference;
	}

	public void setRandomReference(Reference randomReference) {
		this.randomReference = randomReference;
	}
	
	public PitCode askForASeat() {
		randomReference= new Reference('a', 1);
		UserRequest userRequest = new UserRequest(this, randomReference);
		return pit.responseRequest(userRequest,this);
	}

}
