package it.lipari.hotel;

import java.util.ArrayList;

public class Hotel {

	String name;
	String address;
	String phone;

	// Costo delle camere standard
	float[] roomPrice;
	
	// Costo delle suite (costo fisso)
	float suitePrice;

	int roomsPerFlat;
	
	//arraylist di piani, con all'interno array di camere
	ArrayList<Room> rooms = new ArrayList<Room>();
	ArrayList<Room> standardRooms = new ArrayList<Room>(); //
	ArrayList<Room> suiteRooms = new ArrayList<Room>(); //
	
	public void initHotel(String name) {
		float [] roomprice = {10, 20, 30};
		setName(name);
		setSuitePrice(100);
		setRoomPrice(roomprice);
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 20; j++) {
				if (j != 13 && j != 17) {
					int number = (i + 1) * 100 + j;

					Room room = Math.random() < 0.1 ? new SuiteRoom(number) : new Room(number);
					// aggiungiamo la stanza...
					rooms.add(room);
					
					// Divido le camera in due Array secondo la loro tipologia
					String roomType = room.getClass().getSimpleName();
					
					if (roomType.equals("Room")) {
						standardRooms.add(room);
					}

					if (roomType.equals("SuiteRoom")) {
						suiteRooms.add(room);
					}
				}
			}
		}
	}

	public ArrayList<Room> getStandardRooms() {
		return standardRooms;
	}

	public void setStandardRooms(ArrayList<Room> standardRooms) {
		this.standardRooms = standardRooms;
	}

	public ArrayList<Room> getSuiteRooms() {
		return suiteRooms;
	}

	public void setSuiteRooms(ArrayList<Room> suiteRooms) {
		this.suiteRooms = suiteRooms;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public float[] getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(float[] roomPrice) {
		this.roomPrice = roomPrice;
	}

	public float getSuitePrice() {
		return suitePrice;
	}

	public void setSuitePrice(float suitePrice) {
		this.suitePrice = suitePrice;
	}

	public int getRoomsPerFlat() {
		return roomsPerFlat;
	}

	public void setRoomsPerFlat(int roomsPerFlat) {
		this.roomsPerFlat = roomsPerFlat;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms2) {
		this.rooms = rooms2;
	}
	
	public void showAvailableStandardRooms() {
		for (Room standardRoom : standardRooms) {
			if (standardRoom.isAvailable()) {
				System.out.println("Room no.: " + standardRoom.getNumber() + " Room Type: "
						+ standardRoom.getRoomType());
			}
		}
	}
	
	public void showAvailableSuiteRooms() {
		for (Room suiteRoom : suiteRooms) {
			if (suiteRoom.isAvailable()) {
				System.out.println("Room no.: " + suiteRoom.getNumber() + " Room Type: "
						+ suiteRoom.getRoomType());
			}
		}
	}

}
