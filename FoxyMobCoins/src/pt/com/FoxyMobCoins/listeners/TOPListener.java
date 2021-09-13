package pt.com.FoxyMobCoins.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class TOPListener implements Listener {
	@EventHandler
	void InvClick(InventoryClickEvent e) {
		if(!(e.getInventory().getName().equals("§7MobCoins - TOP"))) return;
		e.setCancelled(true);
		if(e.getSlot() == 39)e.getWhoClicked().closeInventory();
	}
}
