package pt.com.FoxyMobCoins.listeners;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import pt.com.FoxyMobCoins.methods.Jogador;
import pt.com.FoxyMobCoins.methods.MobCoinsShop;
import pt.com.FoxyMobCoins.methods.TOP;
import pt.com.FoxyMobCoins.utils.zBUtils;

public class ShopListener implements Listener {
	@EventHandler
	void onInventoryClick(InventoryClickEvent e) {
		if (!e.getInventory().getName().equals("§7MobCoins - Loja")) return;
		e.setCancelled(true);
		if (e.getCurrentItem() == null) return;
		if (!(e.getClickedInventory().getType() == InventoryType.CHEST)) return;
		
		Player p = (Player) e.getWhoClicked();
		Jogador j = new Jogador(p);
		
		switch (e.getCurrentItem().getType()) {
		case GOLD_INGOT:
			TOP.Open(p);
			break;
		case MONSTER_EGG:
			if (j.getCoins() >= MobCoinsShop.creeperValor) {
				p.getInventory().addItem(new ItemStack(Material.MONSTER_EGG, 1, (short) 50));
				j.removeCoins(MobCoinsShop.creeperValor);
				p.sendMessage("§b§lMOBCOINS! §fVocê comprou §ex1 Creeper§f por §e§n" + zBUtils.Formatar(MobCoinsShop.creeperValor) + "§f MobCoins.");
				MobCoinsShop.Open(p);
			}
			break;
		case DIAMOND_SWORD:
			if (j.getCoins() >= MobCoinsShop.swordValor) {
				p.getInventory().addItem(sword());
				j.removeCoins(MobCoinsShop.swordValor);
				p.sendMessage("§b§lMOBCOINS! §fVocê comprou §ex1 Espada§f por §e§n" + zBUtils.Formatar(MobCoinsShop.swordValor) + "§f MobCoins.");
				MobCoinsShop.Open(p);
			}
			break;
		case DIAMOND_PICKAXE:
			if (j.getCoins() >= MobCoinsShop.pickValor) {
				p.getInventory().addItem(pick());
				j.removeCoins(MobCoinsShop.pickValor);
				p.sendMessage("§b§lMOBCOINS! §fVocê comprou §ex1 Picareta§f por §e§n" + zBUtils.Formatar(MobCoinsShop.pickValor) + "§f MobCoins.");
				MobCoinsShop.Open(p);
			}
			break;
		case DIAMOND_HELMET:
			if (j.getCoins() >= MobCoinsShop.capaValor) {
				p.getInventory().addItem(armor(Material.DIAMOND_HELMET));
				j.removeCoins(MobCoinsShop.capaValor);
				p.sendMessage("§b§lMOBCOINS! §fVocê comprou §ex1 Capacete§f por §e§n" + zBUtils.Formatar(MobCoinsShop.capaValor) + "§f MobCoins.");
				MobCoinsShop.Open(p);
			}
			break;
		case DIAMOND_CHESTPLATE:
			if (j.getCoins() >= MobCoinsShop.peitoValor) {
				p.getInventory().addItem(armor(Material.DIAMOND_CHESTPLATE));
				j.removeCoins(MobCoinsShop.peitoValor);
				p.sendMessage("§b§lMOBCOINS! §fVocê comprou §ex1 Peitoral§f por §e§n" + zBUtils.Formatar(MobCoinsShop.peitoValor) + "§f MobCoins.");
				MobCoinsShop.Open(p);
			}
			break;
		case DIAMOND_LEGGINGS:
			if (j.getCoins() >= MobCoinsShop.calcaValor) {
				p.getInventory().addItem(armor(Material.DIAMOND_LEGGINGS));
				j.removeCoins(MobCoinsShop.calcaValor);
				p.sendMessage("§b§lMOBCOINS! §fVocê comprou §ex1 Calças§f por §e§n" + zBUtils.Formatar(MobCoinsShop.calcaValor) + "§f MobCoins.");
				MobCoinsShop.Open(p);
			}
			break;
		case DIAMOND_BOOTS:
			if (j.getCoins() >= MobCoinsShop.botaValor) {
				p.getInventory().addItem(armor(Material.DIAMOND_BOOTS));
				j.removeCoins(MobCoinsShop.botaValor);
				p.sendMessage("§b§lMOBCOINS! §fVocê comprou §ex1 Calças§f por §e§n" + zBUtils.Formatar(MobCoinsShop.botaValor) + "§f MobCoins.");
				MobCoinsShop.Open(p);
			}
			break;

		default:
			break;
		}
	}
	
	
	ItemStack sword() {
		ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta m = i.getItemMeta();
		m.addEnchant(Enchantment.DAMAGE_ALL, 10, true);
		m.addEnchant(Enchantment.LOOT_BONUS_MOBS, 5, true);
		m.setDisplayName("§eEspada Farm");
		m.setLore(Arrays.asList("", "§eAdquirido com MobCoins   "));
		i.setItemMeta(m);
		return i;
	}
	ItemStack pick() {
		ItemStack i = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemMeta m = i.getItemMeta();
		m.addEnchant(Enchantment.DIG_SPEED, 10, true);
		m.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 10, true);
		m.setDisplayName("§ePicareta OP");
		m.setLore(Arrays.asList("", "§eAdquirido com MobCoins   "));
		i.setItemMeta(m);
		return i;
	}
	
	ItemStack armor(Material material) {
		ItemStack i = new ItemStack(material);
		ItemMeta m = i.getItemMeta();
		m.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 9, true);
		m.addEnchant(Enchantment.DURABILITY, 9, true);
		m.setLore(Arrays.asList("", "§eAdquirido com MobCoins   "));
		i.setItemMeta(m);
		return i;
	}
}
