package it.lipari.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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
		// Inizializzazione hotel
		hotel.initHotel("Lipari");

		// Inizializzazione Booking Manager
		BookingManager bookingManager = new BookingManager();

		String userEmail;
		boolean stay = true;
		Scanner scan = new Scanner(System.in);
		System.out.println("Salve, benvenuto nell'Hotel " + hotel.getName());

		// INIZIO OPERAZIONI
		do {
			// Stampiamo il menu delle operazioni
			System.out.println("\nCon quali delle seguenti operazioni vuole proseguire?\n" + "[1] Prenota una camera\n"
					+ "[2] Libera una camera\n" + "[3] Mostra prenotazioni utente\n" + "[4] Cancella una prenotazione\n"
					+ "[5] Modifica prenotazione\n" +"[0] Esci dall'hotel\n");

			int choice = scan.nextInt();

			HashMap<String, ArrayList<Booking>> prenotazioni;
			switch (choice) {
			case 1:
				do {
					println("Abbiamo " + hotel.getRooms().size() + " camere disponibili:\n"
							+ hotel.getStandardRooms().size() + " Camere Standard,\n" + hotel.getSuiteRooms().size()
							+ " Suite");
					println("Che tipologia di stanza intende prenotare ?\n" + "[1] Standard\n" + "[2] Suite");
					int roomType = scan.nextInt();
					println("Le stanze disponibili per la categoria da lei richiesta, sono le seguenti:");

					// Ciclo l'Array della tipologia di stanza scelta dall'utente
					if (roomType == 1) {
						hotel.showAvailableStandardRooms();
					} else if (roomType == 2) {
						hotel.showAvailableSuiteRooms();
					}

					// ****** Scelta della camera da prenotare ******

					do {
						println("\n" + "Inserisca il numero della camera che desidera prenotare:\n"
								+ "(Scelga tra quelle elencate sopra)");
						int bookRoomNo = scan.nextInt();

						if (containsRoom(hotel.getStandardRooms(), bookRoomNo)) {
							println("La camera da lei selezionata è la numero: " + bookRoomNo);
							// TODO Richiede e registrare dati di prenotazione

							println("Inserisca i suoi dati qui di seguito");
							println("Inserisca la sua mail: ");
							String email = scan.next();
							println("Per quale data vuole prenotare la stanza: ");

							println("Giorno (DD): ");
							int day = scan.nextInt();
							println("Mese (MM): ");
							int month = scan.nextInt();
							println("Anno (YYYY): ");
							int year = scan.nextInt();

							LocalDate ld = LocalDate.of(year, month, day);
							Date date = java.sql.Date.valueOf(ld);

							for (Room room : hotel.getStandardRooms()) {
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

							bookingManager.backToMenu();
							break;

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
				} while (true);
				continue;
			case 2:
				//TODO Case Da completare
				// Recuperare le camere occupate e chiedi quale camera il cliente vuole liberare
				// Libera una stanza

				println("Hai scelto di lasciare una camera");
				println("inserisca la sua email: ");

				String email = scan.next();
				// TODO Mostra Stanze prenotate collegate alla mail inserita
				ArrayList<Booking> usersBookings = bookingManager.getBookingsPerClient().get(email);

				if (bookingManager.getBookingsPerClient().containsKey(email)) {
					println("Quali delle seguenti camere da lei ordinate intende lasciare ? ");

					for (Booking booking : usersBookings) {
						println("Camera: " + booking.getRoom().getNumber() + " Prenotata per il: " + booking.getDate());
					}

					int roomToleave = scan.nextInt();
					println("\nInserisca il giorno per il quale aveva prenotato la camera.\n"
							+ "nel seguente formato YYYY-MM-dd");
					String dateOfRommBooking = scan.next();

					println("Conferma di lasciare la camera numero: " + roomToleave + "?\n" + "[1] Si\n" + "[2] No");
					int response = scan.nextInt();
					if (response == 1) {
						for (Booking prenotazione : usersBookings) {
							if (prenotazione.getRoom().getNumber() == roomToleave
									&& prenotazione.getClientEmail().equalsIgnoreCase(email)
									&& prenotazione.getDate().toString().equalsIgnoreCase(dateOfRommBooking)) {
								usersBookings.remove(prenotazione);
							}
						}
//							for (Booking booking : userBookings) {
//								if(booking.getRoom().getNumber() == roomToleave) {
//									booking.getRoom().setAvailable(true);
////									userBookings.remove(booking);
//									bookingManager.removeBooking(booking);
//								};
//							}
						println(bookingManager.getBookingsPerClient().toString());
					} else {
						println("Ha scelto di non lasciare la camera, per cui verrà reindirizzato al menù principale");
					}
				} else {
					println("Non ci risultano camere registrate alla sua email\n"
							+ "si assicuri di aver prenotato la stanza, o di aver eseguito la procedura inserendo una mail valida.");
				}

				continue;

			case 3:
				// TODO Mostra prenotazioni fatte da uno specifico utente
				println("Salve, la preghiamo di inserire la sua mail per verificare quali camere ha ordinato presso il nostro hotel.");
				println("Inserisca la sua e-mail");
				userEmail = scan.next();

				println("Ecco la lista delle sue prenotazioni");
				bookingManager.showBookingPerClient(userEmail);

				continue;

			case 4:
				println("Salve ha deciso di cancellare una prenotazione, la preghiamo di inserire la sua mail per verificare quali prenotazione ha effettauto");
				println("Inserisca la sua e-mail");
				userEmail = scan.next();

				println("Ecco la lista delle sue prenotazioni: ");
				bookingManager.showBookingPerClient(userEmail);

				println("Quali delle sue prenotazioni vuole cancellare ?");
				println("Inserisca l'id della prenotazione corrispondente");
				println("ID: ");
				String bookingToDelete = scan.next();
				bookingManager.removeBooking(bookingToDelete, userEmail);
				bookingManager.backToMenu();

				continue;
			case 5: 
				println("Salve ha deciso di modificare una prenotazione, la preghiamo di inserire la sua mail per verificare quali prenotazione ha effettauto");
				println("Inserisca la sua e-mail");
				userEmail = scan.next();
				System.out.println("Quali delle sue prenotazioni desidera modificare ?");
				
				bookingManager.showBookingPerClient(userEmail);
				
				System.out.println("Inserisca l'id della prenotazione corrispondente");
				System.out.println("ID: ");
				String bookingToEdit = scan.next();
				bookingManager.editBooking(bookingToEdit, userEmail);

				bookingManager.backToMenu();

				continue;
			case 0:
				// Esci dall'hotel
				println("Arrivederci e buona giornata !");
				break;

			default:
				println("L'operazione da lei richiesta non è tra quelle disponibili");
				continue;
			}  
//			
			stay = scan.nextInt() != 0;

		} while (stay);

		scan.close();

	}

	private static void println(int number) {
		System.out.println(number);

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
