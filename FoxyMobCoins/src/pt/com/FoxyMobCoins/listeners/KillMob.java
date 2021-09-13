package pt.com.FoxyMobCoins.listeners;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import pt.com.FoxyMobCoins.Main;
import pt.com.FoxyMobCoins.methods.Jogador;
import pt.com.FoxyMobCoins.utils.zBUtils;

public class KillMob implements Listener {
	@EventHandler
	void onKill(EntityDeathEvent e) {
		if (!(e.getEntity().getKiller() instanceof Player)) return;
		if (new Random().nextInt(100) <= 40) {
			Player p = e.getEntity().getKiller();
			Jogador j = new Jogador(p);
			Double valor = (p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS) + 1) * 10.0;
			j.addCoins(valor);
			Location loc = e.getEntity().getLocation().add(0.0, +1.4, 0.0);
			setHologram(p, loc, valor);
		}
	}
	
	void setHologram(Player p, Location loc, Double valor) {
		Hologram holograma = HologramsAPI.createHologram(Main.getInstance(), loc);
		holograma.insertTextLine(0, "§6+" + zBUtils.Formatar(valor));
		holograma.insertTextLine(1, "§6 ❂ §7Mob Coins");
		new BukkitRunnable() {
			
			@Override
			public void run() {
				holograma.delete();
				cancel();
			}
		}.runTaskTimer(Main.getInstance(), 20L*3L, 20L);
		
	}
}



