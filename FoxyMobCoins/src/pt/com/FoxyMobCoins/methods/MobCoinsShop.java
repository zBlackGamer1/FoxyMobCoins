package pt.com.FoxyMobCoins.methods;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import pt.com.FoxyMobCoins.utils.zBUtils;

public class MobCoinsShop {
	public static Double capaValor = 2000.0;
	public static Double peitoValor = 2000.0;
	public static Double calcaValor = 2000.0;
	public static Double botaValor = 2000.0;
	public static Double swordValor = 8000.0;
	public static Double creeperValor = 5000.0;
	public static Double pickValor = 6500.0;

	public static void Open(Player p) {
		Double saldo = new Jogador(p).getCoins();
		ArrayList<String> lore = new ArrayList<>();
		Inventory inv = Bukkit.createInventory(null, 9 * 6, "§7MobCoins - Loja");
		
		ItemStack top = new ItemStack(Material.GOLD_INGOT);
		ItemMeta topmeta = top.getItemMeta();
		lore.add("");
		lore.add("§eClique para acessar.   ");
		topmeta.setLore(lore);
		topmeta.setDisplayName("§6§lTOP");
		top.setItemMeta(topmeta);
		lore.clear();
		
		ItemStack sobre = new ItemStack(Material.DOUBLE_PLANT);
		ItemMeta smeta = sobre.getItemMeta();
		lore.add("§7Ao matar mobs você terá uma   ");
		lore.add("§7chance de receber §7§n10§7 mobcoins   ");
		lore.add("§7que será multiplicado conforme   ");
		lore.add("§7o seu nivel de pilhagem.   ");
		lore.add("");
		lore.add("§b ▶ §fVocê tem §b§n" + zBUtils.Formatar(saldo) + "§f mobcoins.");
		smeta.setLore(lore);
		smeta.setDisplayName("§6§lMOB COINS");
		sobre.setItemMeta(smeta);
		lore.clear();

		ItemStack c = new ItemStack(Material.MONSTER_EGG, 1, (short) 50);
		ItemMeta cm = c.getItemMeta();
		lore.add("");
		lore.add("§7Custo:§6 " + zBUtils.Formatar(creeperValor) + " MobCoins    ");
		lore.add("");
		if (saldo >= creeperValor)
			lore.add("§aClique para comprar.   ");
		else
			lore.add("§cVocê não tem saldo suficiente.   ");
		cm.setLore(lore);
		cm.setDisplayName("§eCreeper");
		c.setItemMeta(cm);
		lore.clear();

		ItemStack capa = new ItemStack(Material.DIAMOND_HELMET);
		ItemMeta capameta = capa.getItemMeta();
		lore.add("§7Proteção 9");
		lore.add("§7Inquebrável 9");
		lore.add("");
		lore.add("§7Custo:§6 " + zBUtils.Formatar(capaValor) + " MobCoins    ");
		lore.add("");
		if (saldo >= capaValor) lore.add("§aClique para comprar.   ");
		else lore.add("§cVocê não tem saldo suficiente.   ");
		capameta.setLore(lore);
		capameta.setDisplayName("§eP9");
		capameta.addEnchant(Enchantment.DURABILITY, 1, true);
		capameta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		capameta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		capa.setItemMeta(capameta);
		lore.clear();
		
		ItemStack peito = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta peitometa = capa.getItemMeta();
		lore.add("§7Proteção 9");
		lore.add("§7Inquebrável 9");
		lore.add("");
		lore.add("§7Custo:§6 " + zBUtils.Formatar(peitoValor) + " MobCoins    ");
		lore.add("");
		if (saldo >= peitoValor) lore.add("§aClique para comprar.   ");
		else lore.add("§cVocê não tem saldo suficiente.   ");
		peitometa.setLore(lore);
		peitometa.setDisplayName("§eP9");
		peitometa.addEnchant(Enchantment.DURABILITY, 1, true);
		peitometa.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		peitometa.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		peito.setItemMeta(peitometa);
		lore.clear();
		
		ItemStack calca = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemMeta calcameta = calca.getItemMeta();
		lore.add("§7Proteção 9");
		lore.add("§7Inquebrável 9");
		lore.add("");
		lore.add("§7Custo:§6 " + zBUtils.Formatar(calcaValor) + " MobCoins    ");
		lore.add("");
		if (saldo >= calcaValor) lore.add("§aClique para comprar.   ");
		else lore.add("§cVocê não tem saldo suficiente.   ");
		calcameta.setLore(lore);
		calcameta.setDisplayName("§eP9");
		calcameta.addEnchant(Enchantment.DURABILITY, 1, true);
		calcameta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		calcameta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		calca.setItemMeta(calcameta);
		lore.clear();
		
		ItemStack bota = new ItemStack(Material.DIAMOND_BOOTS);
		ItemMeta botameta = bota.getItemMeta();
		lore.add("§7Proteção 9");
		lore.add("§7Inquebrável 9");
		lore.add("");
		lore.add("§7Custo:§6 " + zBUtils.Formatar(botaValor) + " MobCoins    ");
		lore.add("");
		if (saldo >= botaValor) lore.add("§aClique para comprar.   ");
		else lore.add("§cVocê não tem saldo suficiente.   ");
		botameta.setLore(lore);
		botameta.setDisplayName("§eP9");
		botameta.addEnchant(Enchantment.DURABILITY, 1, true);
		botameta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		botameta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		bota.setItemMeta(botameta);
		lore.clear();
		
		ItemStack espada = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta espadameta = espada.getItemMeta();
		lore.add("§7Afiação 10");
		lore.add("§7Pilhagem 5");
		lore.add("");
		lore.add("§7Custo:§6 " + zBUtils.Formatar(swordValor) + " MobCoins    ");
		lore.add("");
		if (saldo >= swordValor) lore.add("§aClique para comprar.   ");
		else lore.add("§cVocê não tem saldo suficiente.   ");
		espadameta.setLore(lore);
		espadameta.setDisplayName("§eEspada");
		espadameta.addEnchant(Enchantment.DURABILITY, 1, true);
		espadameta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		espadameta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		espada.setItemMeta(espadameta);
		lore.clear();
		
		ItemStack pick = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemMeta pickmeta = pick.getItemMeta();
		lore.add("§7Eficiência 10");
		lore.add("§7Fortuna 10");
		lore.add("");
		lore.add("§7Custo:§6 " + zBUtils.Formatar(pickValor) + " MobCoins    ");
		lore.add("");
		if (saldo >= pickValor) lore.add("§aClique para comprar.   ");
		else lore.add("§cVocê não tem saldo suficiente.   ");
		pickmeta.setLore(lore);
		pickmeta.setDisplayName("§ePicareta");
		pickmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		pickmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		pickmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		pick.setItemMeta(pickmeta);
		lore.clear();
		
		inv.setItem(4, top);
		inv.setItem(23, pick);
		inv.setItem(21, espada);
		inv.setItem(10, capa);
		inv.setItem(19, peito);
		inv.setItem(28, calca);
		inv.setItem(37, bota);
		inv.setItem(22, c);
		inv.setItem(49, sobre);
		p.openInventory(inv);
	}
}
