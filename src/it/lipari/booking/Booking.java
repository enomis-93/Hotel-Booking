package it.lipari.booking;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

import it.lipari.hotel.Room;

public class Booking {
	String id;
	// Stanza prenotata
	Room room;
	// Data per il quale è stata prenotata
	Date date;

	// Cliente che vuole prenotare la stanza
	String clientEmail;
	int capacity = 1;

	boolean confirmed = false;
	boolean cancelled = false;
	float amountPaid = 0.0f;

	// Costruttore
	public Booking(Room r, Date date2, String email) {
		id = randomId();
		room = r;
		date = date2;
		clientEmail = email;
	}

	@Override
	public String toString() {
		return "\n{"+ "Booking ID: " + id + "\n Booking room=" + room + ",\n date=" + date + ",\n clientEmail=" + clientEmail + ",\n capacity="
				+ capacity + ",\n confirmed=" + confirmed + ",\n amountPaid=" + amountPaid + "\n}";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public float getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(float amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String randomId() {
		  // Random instance
        Random r = new Random();
        int n = r.nextInt();
        
        // n stores the random integer in defcimal form
        String Hexadecimal = Integer.toHexString(n);
        
        return Hexadecimal;
	}
}
