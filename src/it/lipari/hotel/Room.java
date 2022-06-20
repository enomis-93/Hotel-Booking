package it.lipari.hotel;

public class Room {

	int number;
	float mq = 10;
	boolean hasBalcony = false;
	boolean hasPrivateBathroom = true;
	int capacity = 2;
	boolean isAvailable= true;
	String roomType = getClass().getSimpleName();
	
	
	@Override
	public String toString() {
		return "Room {Room no.: " + number + " Available: " + isAvailable + " Type: " + roomType +"}";
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	//Se room viene inizializzata senza un numero, il numero della stanza sarà 101 di default
	Room() {
		this.number = 101;
	}
	
	// Costruttore con assegnazione di un numero
	public Room(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public float getMq() {
		return mq;
	}

	public void setMq(float mq) {
		this.mq = mq;
	}

	public boolean isHasBalcony() {
		return hasBalcony;
	}

	public void setHasBalcony(boolean hasBalcony) {
		this.hasBalcony = hasBalcony;
	}

	public boolean isHasPrivateBathroom() {
		return hasPrivateBathroom;
	}

	public void setHasPrivateBathroom(boolean hasPrivateBathroom) {
		this.hasPrivateBathroom = hasPrivateBathroom;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}
