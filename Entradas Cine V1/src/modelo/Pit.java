package modelo;

import java.util.HashMap;

public class Pit {
	public int amountRows = 10;
	private HashMap<Character, Row> rows;
	private char letter = 'a';

	public Pit() {
		super();
		rows = new HashMap<Character, Row>();
		for (int i = 0; i < amountRows; i++) {
			rows.put(letter, new Row());
			letter++;
		}
	}

	private Row getRow(char letter) {
		return rows.get(letter);
	}

	private Seat getSeat(char letra, int seat) {
		return getSeat(getRow(letra), seat);
	}

	private Seat getSeat(Row fila, int seat) {
		return fila.getSeat(seat);
	}

	private void takenSeat(Seat seat) {
		seat.taken = true;
	}
	public  boolean takeSeat(Reference reference) {
		return takeSeat(reference.getRow(),reference.getColum());
	}
	
	private  boolean takeSeat(char letter, int seat) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Seat requestedSeat = getSeat(letter, seat);
		if (!requestedSeat.taken) {
			takenSeat(requestedSeat);
			return true;
		}
		return false;
	}

	public PitCode responseRequest(UserRequest userRequest) {
		if(takeSeat(userRequest.getReference())) return PitCode.free;
		return PitCode.taken;
	}
}
