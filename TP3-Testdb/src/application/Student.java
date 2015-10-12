package application;

public class Student {
	
	private String lastName;
	private String firstName;
	private int numStudent;
	private Status status;
	
	public Student() {
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getNumStudent() {
		return this.numStudent;
	}
	public void setNumStudent(int numStudent) {
		this.numStudent = numStudent;
	}
	
	public String getStatus() {
		return status.name();
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public void setStudent(String lastName, String firstName, int numStudent, Status status){
		setLastName(lastName);
		setFirstName(firstName);
		setNumStudent(numStudent);
		setStatus(status);
	}
}
