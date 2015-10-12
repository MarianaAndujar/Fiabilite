package tests;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import application.Admin;
import application.AdminException;
import application.InteractBD;
import application.Student;
import application.TimeSlot;
import application.OralPresentation;
import application.Status;

public class TestAdmin {
	private Admin admin;
	private Student student;
	private Student student1;
	private TimeSlot timeSlot;
	private TimeSlot timeSlot1;

	private OralPresentation oralPresentation;
	private OralPresentation oralPresentation1;

	private InteractBD interactBD;

	@Before
	public void setUp() throws ClassNotFoundException, SQLException{
		String piloteName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://dbs-perso.luminy.univmed.fr/z1006502";
		String username = "z1006502";
		String password = ".?aiNu";

		admin = new Admin();
		student = new Student();
		student1= new Student();
		timeSlot = new TimeSlot();
		timeSlot1 = new TimeSlot();
		oralPresentation = new OralPresentation();
		oralPresentation1 = new OralPresentation();

		admin.getConnection().setUsername(username);
		admin.getConnection().setPassword(password);
		admin.getConnection().setPiloteName(piloteName);
		admin.getConnection().setUrl(url);
		admin.getConnection().init();
		
		/* Initialize Student and TimeSlot tables*/
			
		student.setStudent("Gerard","Claire", 1003502, Status.ISL);
		
		student1.setStudent("Zouai","Dalal", 1006504, Status.ISL);
		
		timeSlot.setTimeSlotTotal(1,1, 20, 1);	
		
		timeSlot1.setTimeSlotTotal(2,2,30,2);		
		
	}
	
	
	@After
	public void close() throws SQLException{
		String deleteEtudiant = "DELETE FROM Etudiant WHERE nom='Gerard';";
		admin.executeUpdate(deleteEtudiant);
		
		String deleteSoutenance = "DELETE FROM Soutenance WHERE titre='OralPresentation';";
		admin.executeUpdate(deleteSoutenance);
		
		String deleteTimeslot = "DELETE FROM PSoutenance WHERE  idPlage='1';";
		admin.executeUpdate(deleteTimeslot);
	}
	
	
	/* Check if the methode addStudent add a student correctly */
	@Test
	public void testAddStudent() throws AdminException, SQLException {
		admin.addStudent(student);
		String query = "SELECT Nom, Prenom FROM Etudiant WHERE Nom='"+student.getLastName()+"';";
		ResultSet result = admin.executeQuery(query);
		Assert.assertNotNull(result);
	}
	
	
	/* Check if the methode deleteStudent delete a student correctly */
	@Test()
	public void testDeleteStudent() throws AdminException, SQLException {
		admin.addStudent(student1);
		admin.deleteStudent(student1);
		String query = "SELECT Nom, Prenom FROM Etudiant WHERE Nom='"+student1.getLastName()+"';";
		ResultSet result = admin.executeQuery(query);
		Assert.assertFalse(result.first());
	}
	
	
	
	
	/* Check if the method addOralPresentation add the oral presentation correctly*/
	@Test
	public void testAddOralPresentation() throws AdminException, SQLException {
		
		oralPresentation.setOralPresentation(student, timeSlot, "OralPresentation");
		
		admin.addOralPresentation(oralPresentation);
		
		String query = "SELECT titre FROM Soutenance WHERE titre='"+oralPresentation.getTitle()+"';";
		ResultSet result = admin.executeQuery(query);
		Assert.assertNotNull(result);
	}
	
	
	
	/* Check if the method deleteOralPresentation delete the oral presentation correctly*/
	@Test
	public void testDeleteOralPresentation() throws AdminException, SQLException {
		
		oralPresentation1.setOralPresentation(student, timeSlot, "OralPresentation1");

		admin.addOralPresentation(oralPresentation1);
		admin.deleteOralPresentation(oralPresentation1);
		
		String query = "SELECT titre FROM Soutenance WHERE titre='"+oralPresentation1.getTitle()+"';";
		ResultSet result = admin.executeQuery(query);
		Assert.assertFalse(result.first());
	}
	
	
	/* Check if the method updateOralPresentation update the oral presentation correctly*/
	@Test
	public void testUpdateOralPresentation() throws AdminException, SQLException {
	
		oralPresentation.setOralPresentation(student, timeSlot, "OralPresentation");
				
		admin.addOralPresentation(oralPresentation);
		admin.updateOralPresentation(oralPresentation, timeSlot, timeSlot1);
		String query = "SELECT titre FROM Soutenance WHERE idPlage='"+timeSlot1.getTimeSlotId()+"';";
		ResultSet result = admin.executeQuery(query);
		Assert.assertNotNull(result);
	}
	
	
	/* Check if the method addTimeSlot add the timeSlot correctly*/
	@Test
	public void testAddTimeSlot() throws AdminException, SQLException {

		admin.addTimeSlot(timeSlot);
		String query = "SELECT jour FROM PSoutenance WHERE idPlage="+timeSlot.getTimeSlotId()+";";
		ResultSet result = admin.executeQuery(query);
		Assert.assertNotNull(result);
	}
	
	/* Check if the method deleteTimeSlot delete the timeSlot correctly*/
	@Test
	public void testDeleteTimeSlot() throws AdminException, SQLException {

		admin.addTimeSlot(timeSlot1);
		admin.deleteTimeSlot(timeSlot1);
		String query = "SELECT jour FROM PSoutenance WHERE idPlage="+timeSlot1.getTimeSlotId()+";";
		ResultSet result = admin.executeQuery(query);
		Assert.assertFalse(result.first());
	}
}
