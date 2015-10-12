package application;


public class OralPresentation {
	
	private Student student;
	private TimeSlot timeSlot;
	private String title;
	
	
	public OralPresentation() {
	}
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public TimeSlot getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setOralPresentation(Student student,TimeSlot timeSlot,String title){
		
		setStudent(student);
		setTimeSlot(timeSlot);
		setTitle(title);
	}
	

}
