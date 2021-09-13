package pt.com.FoxyMobCoins.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import pt.com.FoxyMobCoins.Main;
import pt.com.FoxyMobCoins.methods.Jogador;

public class JoinEvent implements Listener {
	@EventHandler
	void onJoin(PlayerJoinEvent e) {
		Jogador j = new Jogador(e.getPlayer());
		if (!j.Exists()) {
			Main.getInstance().coins.put(j.getName().toLowerCase(), 0.0);
		}
	}
}
