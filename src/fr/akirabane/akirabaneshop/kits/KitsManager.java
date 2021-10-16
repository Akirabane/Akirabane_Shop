package fr.akirabane.akirabaneshop.kits;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;

import fr.akirabane.akirabaneshop.DatabaseManager;

public enum KitsManager {

	RANGER(1, "Ranger"),
	GLADIATEUR(2, "Gladiateur");
	
	private int id;
	private String name;
	
	private KitsManager(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isExist(UUID uuid) {
		try {
			 PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("SELECT pseudo_player FROM kits WHERE uuid_player = ?");
			 preparedStatement.setString(1, uuid.toString());
			 ResultSet rs = preparedStatement.executeQuery();
			 
			 if(rs.next()) return true;
			 preparedStatement.close();
			 
			 return false;
			 
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void addKit(UUID uuid) {
		if(!isExist(uuid)) {
			try {
				PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("INSERT INTO kits (uuid_player, pseudo_player, kit_" + this.getName() + ") VALUES (?, ?, ?)");
				preparedStatement.setString(1, uuid.toString());
				preparedStatement.setString(2, Bukkit.getPlayer(uuid).getName());
				preparedStatement.setInt(3, 1);
				preparedStatement.execute();
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			} 
		} else {
			try {
				PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("UPDATE kits SET kit_" + this.getName() + " = ? WHERE uuid_player = ?");
				preparedStatement.setInt(1, 1);
				preparedStatement.setString(2, uuid.toString());
				preparedStatement.executeUpdate();
				preparedStatement.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public int getPlayerKit(UUID uuid) {
		try {
			
			PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("SELECT kit_" + this.getName() + " FROM kits WHERE uuid_player = ?");
			preparedStatement.setString(1, uuid.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			int kitpower = 0;
			
			if(rs.next()) {
				kitpower = rs.getInt("kit_" + this.getName());
			}
			preparedStatement.close();
			return kitpower;
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
	
}
