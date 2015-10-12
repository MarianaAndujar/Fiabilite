package application;

public class TimeSlot {

	private int day;
	private int timeSlot;
	private int roomNumber;
	private int timeSlotId;

	public TimeSlot() {
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(int timeSlot) {
		this.timeSlot = timeSlot;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getTimeSlotId() {
		return timeSlotId;
	}

	public void setTimeSlotId(int timeSlotId) {
		this.timeSlotId = timeSlotId;
	}

	public void setTimeSlotTotal(int day,int timeSlot,int roomNumber,int timeSlotId ){
		
		setDay(day);
		setTimeSlot(timeSlot);
		setRoomNumber(roomNumber);
		setTimeSlotId(timeSlotId);
		
	}
}
