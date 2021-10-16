package fr.akirabane.akirabaneshop;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	public DatabaseManager database;
	
	@Override
	public void onEnable() {
		
		database = new DatabaseManager("jdbc:mysql://", "127.0.0.1", "shop_kits", "root", "");
		database.connexion();
		
		getCommand("boutique").setExecutor(new ShopCommand());
		
		Bukkit.getPluginManager().registerEvents(new KitShopMenu(), this);
	}
}
