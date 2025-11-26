package modelo;

import java.util.HashMap;
import java.util.List;

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

	public Row getRow(char letter) {
		return rows.get(letter);
	}

	private Seat getSeat(char letra, int seat) {
		return getSeat(getRow(letra), seat);
	}

	private Seat getSeat(Row fila, int seat) {
		return fila.getSeat(seat);
	}

	
	public  boolean takeSeat(Reference reference,User user) {
		return takeSeat(reference.getRow(),reference.getColum(),user);
	}
	
	private  boolean takeSeat(char letter, int seat,User user) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Seat requestedSeat = getSeat(letter, seat);
		if (!requestedSeat.taken) {
			requestedSeat.taken=true;
			requestedSeat.setTakerUser(user);
			return true;
		}
		return false;
	}

	public PitCode responseRequest(UserRequest userRequest,User user) {
		if(takeSeat(userRequest.getReference(),user)) return PitCode.free;
		return PitCode.taken;
	}

	public Seat getSeat(Reference reference) {
		return getSeat(reference.getRow(),reference.getColum());
	}

	public List<Reference> getFreeSeats() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
