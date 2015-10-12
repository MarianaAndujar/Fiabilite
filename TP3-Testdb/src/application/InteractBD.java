package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InteractBD {
	
	public String piloteName;
	public String url;
	public String username;
	public String password;
	public Connection connection;
	
	public void init() throws ClassNotFoundException { 
		Class.forName(this.piloteName);
	}

	public void close() { 
	}

	public Connection connect() throws SQLException { 
		connection = DriverManager.getConnection(url, username, password);
		return connection;
	}

	public void deconnect() throws SQLException { 
		if (connection != null) connection.close();
	}
	
	public boolean isConnected (){
		if (this.connection != null)
			return true;
		
		return false;
	}
	
	public String getPiloteName() {
		return piloteName;
	}

	public void setPiloteName(String piloteName) {
		this.piloteName = piloteName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
