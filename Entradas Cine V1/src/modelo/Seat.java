package modelo;

import java.util.Optional;

public class Seat {
	public boolean taken = false;
	private Optional<User> takerUser = Optional.empty();

	public Seat() {
		super();
	}

	public Optional<User> getTakerUser() {
		return takerUser;
	}

	public void setTakerUser(User takerUser) {
		this.takerUser = Optional.of(takerUser);
	}

	public boolean isFree() {
		return !taken;
	}
}
