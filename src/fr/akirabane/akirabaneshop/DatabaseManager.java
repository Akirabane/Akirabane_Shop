package fr.akirabane.akirabaneshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	
	private String urlBase;
	private String host;
	private String database;
	private String userName;
	private String password;
	private static Connection connection;
	
	public DatabaseManager(String urlBase, String host, String database, String userName, String password) {
		// TODO Auto-generated constructor stub
		this.urlBase = urlBase;
		this.host = host;
		this.database = database;
		this.userName = userName;
		this.password = password;
	}
	
	public void connexion() {
		if(!isOnline()) {
			try {
				connection = DriverManager.getConnection(this.urlBase + this.host + "/" + this.database, this.userName, this.password);
				System.out.println("§a[DatabaseManager] Connection réussie.");
				return;
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	

	public static Connection getConnexion() {
		return connection;
	}
	
	
	public void deconnexion() {
		if(isOnline()) {
			try {
				connection.close();
				System.out.println("§a[DatabaseManager] Déconnection réussie.");
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public boolean isOnline() {
		try {
			if((connection == null) || (connection.isClosed()))
			{
				return false;
			}
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
}
