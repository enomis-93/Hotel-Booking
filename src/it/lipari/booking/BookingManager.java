package it.lipari.booking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
			// Ricavo l'array di prenotazioni corrispondenti all'utente

			userBooking.removeIf(b -> b.getId().equals(bookingID));
//			Iterator<Booking> it = userBooking.iterator();
//			while (it.hasNext()) {
//		        if (it.next().getId().equals(bookingID)) {
//		            it.remove();
//		            break;
//		        }
//		    }
			
//			System.out.println(userBooking.toString());
//			System.out.println(bookingsPerClient.toString());
			System.out.println("Prenotazione cancellata con successo!");

		} else {
			System.out.println("Non ci risultano prenotazioni registrate a questa mail.");
		}

	}

	public void showBookingPerClient(String email) {
		// Verifica che ci sono prenotazioni effettuate con una determinata email
		if (bookingsPerClient.containsKey(email)) {
			// Ricavo l'array di prenotazioni corrispondenti all'utente
			ArrayList<Booking> userBooking = bookingsPerClient.get(email);
			for (Booking booking : userBooking) {
				System.out.println("Prenotazione no. " + booking.getId() + " Camera n°: "
						+ booking.getRoom().getNumber() + " Prenotata per il: " + booking.getDate());
			}
		} else {
			System.out.println("Non ci risultano prenotazioni registrate a questa mail.");
		}
	}

	// Metodo modifica prenotaizone
	public void editBooking(String bookingID, String email) {
		ArrayList<Booking> userBooking = bookingsPerClient.get(email);

		if (bookingsPerClient.containsKey(email)) {
			// Ricavo l'array di prenotazioni corrispondenti all'utente
	
			// TODO Chiedere all'utente cosa vuole modificare e applicare la modifica alla
			// prenotazione corrispondente
			System.out.println("Ha deciso di proseguire con la modifica della prenotazione n°: " + bookingID);
			System.out.println("Cosa desidera modificare della sua prenotazione ?\n" + "[1] Data\n"
					+ "[2] Numero di camera\n" + "[3] Tipo di camera");
			int datumToModify = scanner.nextInt();

			switch (datumToModify) {
			case 1:
				for (Booking booking : userBooking) {
					if (booking.getId().equals(bookingID)) {
						Date oldDate = booking.getDate();
						System.out.println("L'attuale data della sua prenotazione è: " + oldDate);
						System.out.println("Inserisca la nuova data: ");

						System.out.println("Giorno (DD): ");
						int day = scanner.nextInt();
						System.out.println("Mese (MM): ");
						int month = scanner.nextInt();
						System.out.println("Anno (YYYY): ");
						int year = scanner.nextInt();

						LocalDate ld = LocalDate.of(year, month, day);
						Date date = java.sql.Date.valueOf(ld);
						booking.setDate(date);
						
						System.out.println("Data modificata con successo! Data precedente: " + oldDate
								+ " La nuova data è: " + date);
						break;
					}
				}
				System.out.println(bookingsPerClient.toString());

				break;
			case 2:

				break;
			case 3:

				break;

			default:
				System.out.println("L'opzione da lei richiesta non è nella lista");
				break;
			}

		} else {
			System.out.println("Non ci risultano prenotazioni registrate a questa mail.");
		}
		
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
	
	public static boolean containsBooking(final ArrayList<Booking> list, final String bookingNumber) {
		return list.stream().anyMatch(o -> o.getId().equals(bookingNumber));
	}

}
