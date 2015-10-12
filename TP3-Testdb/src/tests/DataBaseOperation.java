package tests;

import java.io.File;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import application.Admin;
import application.Student;
import application.TimeSlot;

public abstract class DataBaseOperation {
	
	private String url = "jdbc:mysql://dbs-perso.luminy.univmed.fr/z1006502";
	private String piloteName = "com.mysql.jdbc.Driver";
	private String username = "z1006502";
	private String password = ".?aiNu";
	
	private IDatabaseTester databaseTester;
	private IDataSet databaseDataSet;
	private IDataSet expectedDataSet;
	
	private Admin admin;
	private Student student;
	private Student student2;
	private TimeSlot timeSlot;
	private TimeSlot timeSlot2;

	private JdbcDatabaseTester jdbc;
	
	public DataBaseOperation(){
		
	}
	
	 public void getSetUpOperation() throws Exception{
		 
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
	    }
	 
	 public void getTearDownOperation() throws Exception{
			String deleteEtudiant = "DELETE  FROM Etudiant ;";
			admin.executeUpdate(deleteEtudiant);
			
			String deleteSoutenance = "DELETE FROM Soutenance";
			admin.executeUpdate(deleteSoutenance);
			
			String deleteTimeslot = "DELETE FROM PSoutenance;";
			admin.executeUpdate(deleteTimeslot);
			
		}

}
