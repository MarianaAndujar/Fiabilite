package tests;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import application.Admin;
import application.AdminException;
import application.OralPresentation;
import application.Status;
import application.Student;
import application.TimeSlot;
import junit.framework.TestCase;

public class SampleTest extends TestCase {

	private String url = "jdbc:mysql://dbs-perso.luminy.univmed.fr/z1006502";
	private String piloteName = "com.mysql.jdbc.Driver";
	private String username = "z1006502";
	private String password = ".?aiNu";

	private IDatabaseTester databaseTester;
	private IDataSet databaseDataSet;
	private IDataSet expectedDataSet;

	// private DataBaseOperation dbo;

	private Admin admin;
	private Student student;
	private Student student2;
	private TimeSlot timeSlot;
	private TimeSlot timeSlot2;

	private JdbcDatabaseTester jdbc;

	public SampleTest(String name) {
		super(name);
	}

	@Before
	protected void setUp() throws Exception {
		student = new Student();
		student2 = new Student();
		timeSlot = new TimeSlot();
		timeSlot2 = new TimeSlot();
		admin = new Admin();

		admin.getConnection().setUsername(username);
		admin.getConnection().setPassword(password);
		admin.getConnection().setPiloteName(piloteName);
		admin.getConnection().setUrl(url);
		admin.getConnection().init();

		databaseTester = new JdbcDatabaseTester(piloteName, url, username, password);
		databaseDataSet = databaseTester.getConnection().createDataSet();
		expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/tests/expectedDataSet.xml"));

		// dbo.getSetUpOperation();
	}

	@After
	public void tearDown() throws Exception {

		String deleteEtudiant = "DELETE  FROM Etudiant ;";
		admin.executeUpdate(deleteEtudiant);

		String deleteSoutenance = "DELETE FROM Soutenance";
		admin.executeUpdate(deleteSoutenance);

		String deleteTimeslot = "DELETE FROM PSoutenance;";
		admin.executeUpdate(deleteTimeslot);

		// dbo.getTearDownOperation();

	}

	@Test
	public void testAddStudent() throws Exception {

		student.setStudent("Zouai", "Dalal", 1003502, Status.FSI);

		admin.deleteStudent(student);
		admin.addStudent(student);

		// Fetch database data after executing your code
		ITable actualTable = databaseDataSet.getTable("Etudiant");

		// Load expected data from an XML dataset
		expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/tests/expectedDataSet.xml"));
		ITable expectedTable = expectedDataSet.getTable("Etudiant");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
		admin.deleteStudent(student);
	}

	@Test
	public void testAddTimeSlot() throws Exception {

		timeSlot.setTimeSlotTotal(1, 1, 100, 10);

		admin.deleteTimeSlot(timeSlot);
		admin.addTimeSlot(timeSlot);

		// Fetch database data after executing your code
		ITable actualTable = databaseDataSet.getTable("PSoutenance");

		// Load expected data from an XML dataset
		expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/tests/expectedDataSet.xml"));
		ITable expectedTable = expectedDataSet.getTable("PSoutenance");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
		admin.deleteTimeSlot(timeSlot);
	}

	@Test
	public void testAddOralPresentation() throws Exception {
		OralPresentation oralPresentation = new OralPresentation();
		String title = "OralPresentation1";

		timeSlot.setTimeSlotTotal(1, 1, 100, 10);

		admin.deleteTimeSlot(timeSlot);
		admin.addTimeSlot(timeSlot);

		student.setStudent("Zouai", "Dalal", 1003502, Status.FSI);

		admin.deleteStudent(student);

		admin.addStudent(student);
		// admin.addTimeSlot(timeSlot);

		oralPresentation.setOralPresentation(student, timeSlot, title);

		admin.deleteOralPresentation(oralPresentation);
		admin.addOralPresentation(oralPresentation);

		// Fetch database data after executing your code
		ITable actualTable = databaseDataSet.getTable("Soutenance");

		// Load expected data from an XML dataset
		expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/tests/expectedDataSet.xml"));
		ITable expectedTable = expectedDataSet.getTable("Soutenance");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);

		// admin.deleteTimeSlot(timeSlot);
		admin.deleteStudent(student);
		admin.deleteOralPresentation(oralPresentation);
		admin.deleteTimeSlot(timeSlot);
	}

	@Test
	public void testDeleteStudent() throws Exception {

		// student one
		student.setStudent("Zouai", "Dalal", 1003502, Status.FSI);
		admin.deleteStudent(student);
		admin.addStudent(student);

		// student two
		student2.setStudent("Gerard", "Claire", 1003503, Status.ISL);
		admin.deleteStudent(student2);
		admin.addStudent(student2);

		admin.deleteStudent(student2);

		// Fetch database data after executing your code
		ITable actualTable = databaseDataSet.getTable("Etudiant");

		// Load expected data from an XML dataset
		expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/tests/expectedDataSet.xml"));
		ITable expectedTable = expectedDataSet.getTable("Etudiant");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
		admin.deleteStudent(student2);
	}

	@Test
	public void testDeleteTimeSlot() throws Exception {
		// timeslot one
		timeSlot.setTimeSlotTotal(1, 1, 100, 10);

		admin.deleteTimeSlot(timeSlot);
		admin.addTimeSlot(timeSlot);

		timeSlot2.setTimeSlotTotal(2, 2, 102, 20);

		admin.deleteTimeSlot(timeSlot2);
		admin.addTimeSlot(timeSlot2);
		admin.deleteTimeSlot(timeSlot2);

		// Fetch database data after executing your code
		ITable actualTable = databaseDataSet.getTable("PSoutenance");

		// Load expected data from an XML dataset
		expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/tests/expectedDataSet.xml"));
		ITable expectedTable = expectedDataSet.getTable("PSoutenance");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);
		admin.deleteTimeSlot(timeSlot);

	}

	@Test
	public void testDeleteOralPresentation() throws Exception {
		// oral presentation 1
		OralPresentation oralPresentation = new OralPresentation();
		String title = "OralPresentation1";

		timeSlot.setTimeSlotTotal(1, 1, 100, 10);
		admin.deleteTimeSlot(timeSlot);
		admin.addTimeSlot(timeSlot);

		student.setStudent("Zouai", "Dalal", 1003502, Status.FSI);
		admin.deleteStudent(student);
		admin.addStudent(student);

		oralPresentation.setOralPresentation(student, timeSlot, title);
		admin.deleteOralPresentation(oralPresentation);
		admin.addOralPresentation(oralPresentation);

		// oral presentation 2
		OralPresentation oralPresentation2 = new OralPresentation();
		String title2 = "OralPresentation2";

		timeSlot2.setTimeSlotTotal(2, 2, 102, 20);
		admin.deleteTimeSlot(timeSlot2);
		admin.addTimeSlot(timeSlot2);

		student2.setStudent("Gerard", "Claire", 1003503, Status.ISL);
		admin.deleteStudent(student2);
		admin.addStudent(student2);

		oralPresentation2.setOralPresentation(student2, timeSlot2, title2);
		admin.deleteOralPresentation(oralPresentation2);
		admin.addOralPresentation(oralPresentation2);
		admin.deleteOralPresentation(oralPresentation2);

		// Fetch database data after executing your code
		ITable actualTable = databaseDataSet.getTable("Soutenance");

		// Load expected data from an XML dataset
		expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/tests/expectedDataSet.xml"));
		ITable expectedTable = expectedDataSet.getTable("Soutenance");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);

		admin.deleteTimeSlot(timeSlot);
		admin.deleteStudent(student);
		admin.deleteOralPresentation(oralPresentation);
		// admin.deleteTimeSlot(timeSlot);

		admin.deleteTimeSlot(timeSlot2);
		admin.deleteStudent(student2);
		admin.deleteOralPresentation(oralPresentation2);
	}

	@Test
	public void testUpdateOralPresentation() throws Exception {

		OralPresentation oralPresentation = new OralPresentation();
		String title = "OralPresentation1";

		timeSlot.setTimeSlotTotal(1, 1, 100, 10);
		admin.deleteTimeSlot(timeSlot);
		admin.addTimeSlot(timeSlot);

		timeSlot2.setTimeSlotTotal(2, 2, 102, 10);
		admin.deleteTimeSlot(timeSlot2);
		admin.addTimeSlot(timeSlot2);

		student.setStudent("Zouai", "Dalal", 1003502, Status.FSI);
		admin.deleteStudent(student);
		admin.addStudent(student);

		oralPresentation.setOralPresentation(student, timeSlot, title);
		admin.deleteOralPresentation(oralPresentation);
		admin.addOralPresentation(oralPresentation);

		admin.updateOralPresentation(oralPresentation, timeSlot, timeSlot2);

		// Fetch database data after executing your code
		ITable actualTable = databaseDataSet.getTable("Soutenance");

		// Load expected data from an XML dataset
		expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/tests/expectedDataSet.xml"));
		ITable expectedTable = expectedDataSet.getTable("Soutenance");

		// Assert actual database table match expected table
		Assertion.assertEquals(expectedTable, actualTable);

		// admin.deleteTimeSlot(timeSlot);
		admin.deleteStudent(student);
		admin.deleteOralPresentation(oralPresentation);
		admin.deleteTimeSlot(timeSlot);

	}

}
