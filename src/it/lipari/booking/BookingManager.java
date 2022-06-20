package it.lipari.booking;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import it.lipari.hotel.Room;

public class BookingManager {

	// Prenotazioni per cliente
	HashMap<String, ArrayList<Booking>> bookingsPerClient = new HashMap<String, ArrayList<Booking>>();

	// Prenotazioni per numero di camera
	HashMap<Integer, HashMap<Date, Booking>> bookingsPerRoomNumber = new HashMap<Integer, HashMap<Date, Booking>>();

	// Metodo per aggiungere un nuova prenotazione
	public void addBooking(Booking b) {
		// Lista delle prenotazioni in base alla mail del cliente
		ArrayList<Booking> list = bookingsPerClient.get(b.getClientEmail());

		// Verifica che l'utente abbia già delle prenotazioni
		// Altrimenti list è inizializzato ad un array di prenotazioni vuoto
		if (list == null) {
			list = new ArrayList<Booking>();
		}
		list.add(b);

		// Registrazione delle prenotazioni di un singolo utente
		bookingsPerClient.put(b.getClientEmail(), list);

		Room r = b.getRoom();
		Integer rn = r.getNumber();

		// Array di prenotazioni per data
		HashMap<Date, Booking> bookingsPerDate = bookingsPerRoomNumber.get(rn);

		if (bookingsPerDate == null) {
			bookingsPerDate = new HashMap<Date, Booking>();
		}
		// Aggiunta prenotazione per data
		bookingsPerDate.put(b.getDate(), b);
		bookingsPerRoomNumber.put(rn, bookingsPerDate);
	}

	public HashMap<String, ArrayList<Booking>> getBookingsPerClient() {
		return bookingsPerClient;
	}

	public void setBookingsPerClient(HashMap<String, ArrayList<Booking>> bookingsPerClient) {
		this.bookingsPerClient = bookingsPerClient;
	}

	public HashMap<Integer, HashMap<Date, Booking>> getBookingsPerRoomNumber() {
		return bookingsPerRoomNumber;
	}

	public void setBookingsPerRoomNumber(HashMap<Integer, HashMap<Date, Booking>> bookingsPerRoomNumber) {
		this.bookingsPerRoomNumber = bookingsPerRoomNumber;
	}

	// Metodo per rimozione prenotazione
	public void removeBooking() {

	}

	// Metodo modifica prenotaizone
	public void editBooking() {

	}
	
	//Metodo per mostrare le stanze disponibili
	public void roomsAvailable() {
		
	}
}
