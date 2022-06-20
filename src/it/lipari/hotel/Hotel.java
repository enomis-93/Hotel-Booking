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

}
