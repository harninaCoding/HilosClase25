package modelo;

import java.util.ArrayList;
import java.util.List;

public class Row {
	public int amountSeats = 10;
	public List<Seat> seat;

	public Row() {
		super();
		createSeats();
	}

	private void createSeats() {
		seat = new ArrayList<Seat>();
		for (int i = 0; i < amountSeats; i++) {
			seat.add(new Seat());
		}
	}

	public Seat getSeat(int number) {
		return seat.get(number);
	}
}
