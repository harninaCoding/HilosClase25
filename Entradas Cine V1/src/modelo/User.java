package modelo;

import java.util.List;
import java.util.Optional;
import java.util.Random;
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
		PitCode pitCode = askForASeat(randomReference);
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
	
	public String getId() {
		return id;
	}

	public PitCode askForASeat(Reference randomReference) {
		UserRequest userRequest = new UserRequest(this, randomReference);
		return pit.responseRequest(userRequest);
	}

	public Optional<Reference> pillaEntrada(Pit pit2) {
		List<Reference> freeSeats = pit.getFreeSeats();
		PitCode pitCode;
		Reference random;
		do {
			random = askRandomReference(freeSeats);
			pitCode = askForASeat(random);
		}while (pitCode!=PitCode.full&&pitCode==PitCode.taken);
		if(pitCode==PitCode.free) {
			randomReference=random;
		}
		return Optional.ofNullable(randomReference);
	}

	private Reference askRandomReference(List<Reference> freeSeats) {
		return freeSeats.get(new Random().nextInt(freeSeats.size()));
	}

	public Reference getTakenSeat() {
		return randomReference;
	}

}
