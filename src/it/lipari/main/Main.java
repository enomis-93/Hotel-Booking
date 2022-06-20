package it.lipari.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import javax.print.PrintServiceLookup;

import it.lipari.booking.Booking;
import it.lipari.booking.BookingManager;
import it.lipari.hotel.Hotel;
import it.lipari.hotel.Room;
import it.lipari.hotel.SuiteRoom;

public class Main {

	public static void main(String[] args) {

		Hotel hotel = new Hotel();
		hotel.setName("Lipari");

//		ArrayList<Room> rooms = new ArrayList<Room>();

//		List<Integer> standardRoomAvailable = new ArrayList<Integer>();
//		List<Integer> suiteRoomAvailable = new ArrayList<Integer>();

		ArrayList<Room> standardRooms = new ArrayList<Room>(); //
		ArrayList<Room> suiteRooms = new ArrayList<Room>(); //

		BookingManager bookingManager = new BookingManager();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 20; j++) {
				if (j != 13 && j != 17) {
					int number = (i + 1) * 100 + j;

					Room room = Math.random() < 0.1 ? new SuiteRoom(number) : new Room(number);
					// aggiungiamo la stanza...
					hotel.getRooms().add(room);
				}
			}
		}

//		println(hotel.getRooms().toString());
		// Divido le camera in due Array secondo la loro tipologia
		for (Room room : hotel.getRooms()) {
			String roomType = room.getClass().getSimpleName();

			if (roomType.equals("Room")) {
				standardRooms.add(room);
			}

			if (roomType.equals("SuiteRoom")) {
//				println("Room no.: " + room.getNumber() + " Room Type: " + roomType);
				suiteRooms.add(room);
			}
		}

//		hotel.setRooms(rooms);
//		println(suiteRooms.toString());
		// println(rooms.toString());

		;

		boolean stay = true;
		Scanner scan = new Scanner(System.in);
		System.out.println("Salve, benvenuto nell'Hotel " + hotel.getName());

		// INIZIO OPERAZIONI
		do {
			// Stampiamo il menu delle operazioni
			System.out.println("Con quali delle seguenti operazioni vuole proseguire?\n" + "[1] Prenota una camera\n"
					+ "[2] Libera una camera\n" + "[3] Esci dall'hotel");

			int choice = scan.nextInt();

			switch (choice) {
			case 1: 
			do {
				println("Abbiamo " + hotel.getRooms().size() + " camere disponibili:\n" + standardRooms.size()
						+ " Camere Standard,\n" + suiteRooms.size() + " Suite");
				println("Che tipologia di stanza intende prenotare ?\n" + "[1] Standard\n" + "[2] Suite");
				int roomType = scan.nextInt();
				println("Le stanze disponibili per la categoria da lei richiesta, sono le seguenti:");

				// Ciclo l'Array della tipologia di stanza scelta dall'utente
				if (roomType == 1) {
					for (Room standardRoom : standardRooms) {
						if (standardRoom.isAvailable()) {
							println("Room no.: " + standardRoom.getNumber() + " Room Type: "
									+ standardRoom.getRoomType());
						}
					}
				} else if (roomType == 2) {
					for (Room suiteRoom : suiteRooms) {
						if (suiteRoom.isAvailable()) {
							println("Room no.: " + suiteRoom.getNumber() + " Room Type: " + suiteRoom.getRoomType());
						}
					}
				}

				// ****** Scelta della camera da prenotare ******
				
				do {
					println("\n" + "Inserisca il numero della camera che desidera prenotare:\n"
							+ "(Scelga tra quelle elencate sopra)");
					int bookRoomNo = scan.nextInt();

					if (containsRoom(standardRooms, bookRoomNo)) {
						println("La camera da lei selezionata è la numero: " + bookRoomNo);
						// TODO Richiede e registrare dati di prenotazione

						println("Inserisca i suoi dati qui di seguito");
						println("Inserisca la sua mail: ");
						String email = scan.next();
						println("Per quale data vuole prenotare la stanza: ");

						println("Anno: ");
						int year = scan.nextInt();
						println("Mese: ");
						int month = scan.nextInt();
						println("Giorno: ");
						int day = scan.nextInt();

						LocalDate ld = LocalDate.of(year, month, day);
						Date date = java.sql.Date.valueOf(ld);

						for (Room room : standardRooms) {
							if (room.getNumber() == bookRoomNo) {

								Booking prenotazione = new Booking(room, date, email);
								prenotazione.setConfirmed(true);
								room.setAvailable(false);
								bookingManager.addBooking(prenotazione);
								println("Camera " + bookRoomNo + " prenotata con successo!");
								break;
							}
						}
						println("--- Prenotazione per stanza ---");
						println(bookingManager.getBookingsPerRoomNumber().toString());
						println("--- Prenotazione per utente ---");
						println(bookingManager.getBookingsPerClient().toString());

						println("Desidera tornare al menù principale ?\n" + "[1] Si\n" + "[Press ANY button] No");
						int choice1 = scan.nextInt();
						
						if (choice1 == 1) {
							break;
						} else {
							println("Grazie per la sua prenotazione, a presto!");
							return;
						}
						
					} else {
						println("La camera da lei selezionata non è nell'elenco.\n"
								+ "inserisca un numero di camera valido");
						continue;
					}
                
				} while (true);

				/////// ESEMPIO CON CICLO FOR
//
//				Room found = null;
//
//				for (Room standardRoom : standardRooms) {
//					if (standardRoom.getNumber() == bookRoomNo) {
//						found = standardRoom;
//						break;
//					}
//				}
//
//				if (found != null) {
//					println("La camera che vuole prenotare è la numero:" + bookRoomNo);
//
//					// Conferma di prenotazione della stanza
//					println("Vuole proseguire con la prenotazione ?\n" + "[1] Si\n" + "[2] No");
//
//					int confirmBooking = scan.nextInt();
//
//					// Se conferma, cicla l'array di camere e setta a true la disponibilità della
//					// camera che è stata prenotata
//
//					if (confirmBooking == 1) {
//						 
//							println("Chi vuole prenotare la camera ? (Inserisca la sua mail)");
//							String bookerEmail = scan.next();
//
//							Date date = new Date();
//							Booking book = new Booking(standardRoom, date, bookerEmail);
//
//							standardRoom.setAvailable(false); // int indefOfRoomToRemove =
//							standardRoomAvailable.indexOf(bookRoomNo); //
//							standardRoomAvailable.remove(indefOfRoomToRemove); //
//							println(standardRoomAvailable.toString());
//
//							println("Prenotazione della camera n: " + bookRoomNo + " confermata!");
//							println("I dati della sua prenotazione sono i seguenti: ");
//							println(book.toString());
//
//							// println(rooms.toString());
//							println("Vuole continuare con altre operazioni ?\n [1]Si\n [2]No");
//							int response = scan.nextInt();
//							if (response == 1) {
//								continue;
//
//							} else if (confirmBooking == 2) {
//								println("Grazie e arrivederci!");
//								break;
//							}
//						
//
//					}
//				} else {
//					println("Stanza non trovata nella lista");
//				}

				/////////

				break;
			} while(true);
			continue;
			case 2:
				// Recuperare le camere occupate e chiedi quale camera il cliente vuole liberare
				// Libera una stanza
				println("Hai scelto di lasciare una camera");
				break;

			case 3:
				// Esci dall'hotel
				println("Arrivederci e buona giornata !");
				break;

			default:
				println("L'operazione da lei richiesta non è tra quelle disponibili");
				continue;
			}
//			
			stay = scan.nextInt() != 0;

		} while(stay);

		scan.close();

	}

	private static void println(String string) {
		System.out.println(string);

	}

	// Metodo per verificare se l'array di camere ha un elemento che corrisponde al
	// numero di camera scelto dall'utente
	public static boolean containsRoom(final List<Room> list, final int number) {
		return list.stream().anyMatch(o -> o.getNumber() == number);
	}

}
