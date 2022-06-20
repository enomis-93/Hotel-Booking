package it.lipari.hotel;

public class SuiteRoom extends Room {
	

	boolean hasPool = false;
	boolean hasCameraService = true;

	//metodo che inizializza gli attributi che la classe deve avere 
	private void init() {
		this.mq = 25;
		this.hasBalcony = true;
		this.hasPrivateBathroom = true;
		this.capacity = 4;
		this.isAvailable = true;
	}
	
	public SuiteRoom() {
		super();
		init();
	}
	
	public SuiteRoom(int number) {
		super(number);
		init();
	}

	public boolean isHasPool() {
		return hasPool;
	}

	public void setHasPool(boolean hasPool) {
		this.hasPool = hasPool;
	}

	public boolean isHasCameraService() {
		return hasCameraService;
	}

	public void setHasCameraService(boolean hasCameraService) {
		this.hasCameraService = hasCameraService;
	}

	
	
}
