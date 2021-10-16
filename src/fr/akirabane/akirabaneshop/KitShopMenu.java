package fr.akirabane.akirabaneshop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import fr.akirabane.akirabaneshop.kits.KitsManager;

public class KitShopMenu implements Listener{

	private String invName = "Boutique > Kits";
	
	private Inventory inventory = Bukkit.createInventory(null, 9, invName);
	
	public KitShopMenu() {
		// TODO Auto-generated constructor stub
	}
	
	public void open(Player player) {
		player.openInventory(inventory);
		inventory.setItem(1, new ItemBuilder().type(Material.BLAZE_POWDER).name("Kit ranger").lore((KitsManager.RANGER.getPlayerKit(player.getUniqueId()) == 1 ? "Vous possez déjà ce kit." : "Acheter ce kit.")).build());
		inventory.setItem(3, new ItemBuilder().type(Material.IRON_SWORD).name("Kit Gladiateur").lore((KitsManager.GLADIATEUR.getPlayerKit(player.getUniqueId()) == 1 ? "Vous possez déjà ce kit." : "Acheter ce kit.")).build());
	}
	
	@EventHandler
	public void onClickInventory(InventoryClickEvent event) {

		Player player = (Player)event.getWhoClicked();
		
		if(event.getCurrentItem() == null && event.getAction() != null) return;
		
		if(player.getOpenInventory().getTitle().equalsIgnoreCase(invName)) {
			event.setCancelled(true);
		
			switch (event.getCurrentItem().getType()) {
			case BLAZE_POWDER:
				
				if(KitsManager.RANGER.getPlayerKit(player.getUniqueId()) < 1) {
					KitsManager.RANGER.addKit(player.getUniqueId());
					player.sendMessage("Vous venez d'acheter le kit ranger !");
				} else {
					player.sendMessage("Vous avez déjà ce kit");
				}
				
				break;
		
			case IRON_SWORD:
				
				if(KitsManager.GLADIATEUR.getPlayerKit(player.getUniqueId()) < 1) {
					KitsManager.GLADIATEUR.addKit(player.getUniqueId());
					player.sendMessage("Vous venez d'acheter le kit gladiateur !");
				} else {
					player.sendMessage("Vous avez déjà ce kit");
				}
				
				break;
				 
			default:
				break;
			}
		}
	}
	
}
