package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin {
	
	private InteractBD connection ;

	public Admin(){
		connection = new InteractBD();
	}

	public InteractBD getConnection() {
		return connection;
	}

	public void setConnection(InteractBD connection) {
		this.connection = connection;
	}

	public void addStudent (Student student) throws AdminException, SQLException{
		String nom = student.getLastName();
		String prenom = student.getFirstName();
		int numStudent = student.getNumStudent();
		String status = student.getStatus();

		try {
			connection.connect();
			String query = "INSERT INTO Etudiant (Nom, Prenom, statut, NumEtudiant) VALUES ('"+nom+"','"+prenom+"','"+status+"',"+numStudent+");";
			executeUpdate(query);

		} catch (SQLException e) {
			AdminException ex = new AdminException(e.getMessage());
			throw ex;
		}
	}

	public void deleteStudent (Student student) throws AdminException{
		int numStudent = student.getNumStudent();

		try {
			connection.connect();
			String query = "DELETE FROM Etudiant WHERE numEtudiant="+numStudent+";";
			executeUpdate(query);

		} catch (SQLException e) {
			AdminException ex = new AdminException(e.getMessage());
			throw ex;
		}
	}

	public void addOralPresentation (OralPresentation oralPresentation) throws AdminException, SQLException{
		String title = oralPresentation.getTitle();
		Student student = oralPresentation.getStudent();
		TimeSlot timeSlot = oralPresentation.getTimeSlot();


		try {
			connection.connect();
			String query = "INSERT INTO Soutenance (NumEtu, idPlage, titre) VALUES ("+student.getNumStudent()+","+timeSlot.getTimeSlotId()+","+"'"+title+"');";
			executeUpdate(query);

		} catch (SQLException e) {
			AdminException ex = new AdminException(e.getMessage());
			throw ex;
		}
	}

	public void deleteOralPresentation (OralPresentation oralPresentation) throws AdminException, SQLException{
		String title = oralPresentation.getTitle();

		try {
			connection.connect();
			String query = "DELETE FROM Soutenance WHERE titre='"+title+"';";
			executeUpdate(query);

		} catch (SQLException e) {
			AdminException ex = new AdminException(e.getMessage());
			throw ex;
		}
	}
	
	public void updateOralPresentation(OralPresentation sout, TimeSlot oldTimeSlot, TimeSlot newTimeSlot) throws AdminException, SQLException{
		try {
			connection.connect();
			String query = "UPDATE Soutenance SET idPlage='"+newTimeSlot.getTimeSlotId()+"' WHERE idPlage='"+oldTimeSlot.getTimeSlotId()+"' AND titre='"+sout.getTitle()+"';";
			executeUpdate(query);

		} catch (SQLException e) {
			AdminException ex = new AdminException(e.getMessage());
			throw ex;
		}
	}

	public void addTimeSlot (TimeSlot timeSlot) throws AdminException, SQLException{
		int day = timeSlot.getDay();
		int timeS = timeSlot.getTimeSlot();
		int roomNumber = timeSlot.getRoomNumber();
		int timeSlotId = timeSlot.getTimeSlotId();

		try {
			connection.connect();
			String query = "INSERT INTO PSoutenance (jour, plageHoraire, NumSalle, idPlage) VALUES ("+day+","+timeS+","+roomNumber+","+timeSlotId+");";
			executeUpdate(query);

		} catch (SQLException e) {
			AdminException ex = new AdminException(e.getMessage());
			throw ex;
		}
	}

	public void deleteTimeSlot (TimeSlot timeSlot) throws AdminException, SQLException{
		int timeSlotId = timeSlot.getTimeSlotId();

		try {
			connection.connect();
			String query = "DELETE FROM PSoutenance WHERE idPlage="+timeSlotId+";";
			executeUpdate(query);

		} catch (SQLException e) {
			AdminException ex = new AdminException(e.getMessage());
			throw ex;
		}
	}

	
	public int executeUpdate(String query) throws SQLException { 
		try {
			connection.connect();
			Statement st = connection.connection.createStatement();
			return st.executeUpdate(query);
		} catch (SQLException e) {
			throw e;
		} finally {
			if (connection.connection != null) connection.deconnect();
		}
	}

	public ResultSet executeQuery(String query) throws SQLException { 
		try {
			connection.connect();
			Statement st = connection.connection.createStatement();
			return st.executeQuery(query);
		} catch (SQLException e) {
			throw e;
		} finally {
//			if (connection.connection != null) connection.deconnect();
		}
	}

}
