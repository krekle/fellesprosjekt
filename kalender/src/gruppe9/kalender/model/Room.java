package gruppe9.kalender.model;

public class Room {
	private int id;
	private String building;
	private int floor;
	private String description;
	private int size;
	
	
	

	public Room(int id, String building, int floor, String description, int size) {
		super();
		this.id = id;
		this.building = building;
		this.floor = floor;
		this.description = description;
		this.size = size;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	
}
