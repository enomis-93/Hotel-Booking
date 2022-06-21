package it.lipari.booking;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import it.lipari.hotel.Room;

public class BookingManager {
	Scanner scanner = new Scanner(System.in);

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
	public void removeBooking(String bookingID, String email) {
		ArrayList<Booking> userBooking = bookingsPerClient.get(email);
			
		if (bookingsPerClient.containsKey(email)) {
			//Ricavo l'array di prenotazioni corrispondenti all'utente

			userBooking.removeIf(b -> b.getId().equals(bookingID));
//			Iterator<Booking> it = userBooking.iterator();
//			while (it.hasNext()) {
//		        if (it.next().getId().equals(bookingID)) {
//		            it.remove();
//		            break;
//		        }
//		    }
//			for (Booking booking : userBooking) {
//				if (booking.getId() == bookingID) {
//					userBooking.remove(booking);
//				}
//			}
//			System.out.println(userBooking.toString());
//			System.out.println(bookingsPerClient.toString());
			System.out.println("Prenotazione cancellata con successo!");
			
			
		} else {
			System.out.println("Non ci risultano prenotazioni registrate a questa mail.");
		}

	}

	public void showBookingPerClient(String email) {
		//Verifica che ci sono prenotazioni effettuate con una determinata email
		if (bookingsPerClient.containsKey(email)) {
			//Ricavo l'array di prenotazioni corrispondenti all'utente
			ArrayList<Booking> userBooking = bookingsPerClient.get(email);
			for (Booking booking : userBooking) {
				System.out.println(
						"Prenotazione no. "+ booking.getId() + " Camera n°: " + booking.getRoom().getNumber() + " Prenotata per il: " + booking.getDate());
			}
		} else {
			System.out.println("Non ci risultano prenotazioni registrate a questa mail.");
		}
	}

	// Metodo modifica prenotaizone
	public void editBooking() {

	}

	// Metodo per mostrare le stanze disponibili
	public void roomsAvailable() {

	}
	
	public void backToMenu() {
		System.out.println("Desidera tornare al menù principale ?\n" + "[1] Si\n" + "[Press ANY button] No");
		int choice1 = scanner.nextInt();

		if (choice1 != 1) {
			System.out.println("Grazie, a presto!");
			return;
		}
	}
}
