package tests;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import application.Admin;
import application.AdminException;
import application.InteractBD;
import application.Status;
import application.Student;

public class InteractBDtestConnection {
	
	private InteractBD interactBD;
	private Admin admin;
	private Student student;


	@Before
	public void setUp() throws ClassNotFoundException{	
		String piloteName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://dbs-perso.luminy.univmed.fr/z1006502";
		String username = "z1006502";
		String password = ".?aiNu";
		
		interactBD = new InteractBD();
		interactBD.setUsername(username);
		interactBD.setPassword(password);
		interactBD.setPiloteName(piloteName);
		interactBD.setUrl(url);
		interactBD.init();
		
		admin = new Admin();
		student = new Student();
		
		student.setLastName("Gerard");
		student.setFirstName("Claire");
		student.setNumStudent(1006504);
		student.setStatus(Status.ISL);
	}

	@Test
	public void testConnect() throws SQLException {
		Connection connection = interactBD.connect();
		Assert.assertNotNull(connection);	
	}

	@Test
	public void testDeconnect() throws SQLException {
		interactBD.deconnect();
		Assert.assertNull(interactBD.connection);
	}

	@Test
	public void testIsConnected() throws SQLException {
		boolean result;
		interactBD.connect();
		result = interactBD.isConnected();
		Assert.assertTrue(result);
	}

	@Test
	public void testGetPiloteName() {
		Assert.assertNotNull(interactBD.getPiloteName());
	}

	@Test
	public void testGetUrl() {
		Assert.assertNotNull(interactBD.getUrl());
	}

	@Test
	public void testGetUsername() {
		Assert.assertNotNull(interactBD.getUsername());
	}

	@Test
	public void testGetPassword() {
		Assert.assertNotNull(interactBD.getPassword());
	}
}
