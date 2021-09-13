package pt.com.FoxyMobCoins.utils;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

public class zBUtils {
	public static boolean desatualizado;
	public static String versao;
	
	public static void sendSound(Player p, Sound s) {
		p.playSound(p.getLocation(), s, 0.75F, 1.0F);
	}
	
	public static void sendTitle(Player p, String line1, String line2) {
        PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("{\"text\":\"" + line1 + "\"}"), 20, 40, 20);
        PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ChatSerializer.a("{\"text\":\"" + line2 + "\"}"), 20, 40, 20);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(title);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(subtitle);
	}
	
	public static void sendActionBar(Player player, String message) {
	    CraftPlayer p = (CraftPlayer) player;
	    IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
	    PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte) 2);
	    p.getHandle().playerConnection.sendPacket(ppoc);
	}
	
	public static void ConsoleMsg(String msg) {
		Bukkit.getConsoleSender().sendMessage(msg.replace("&", "§"));
	}
	
	public static String Formatar(Double valor) {
		DecimalFormat d = new DecimalFormat("#,###");
		return d.format(valor).replace(",", ".");
	}
	
	public static String getPorcentagem(Double atual, Double maximo) {
		DecimalFormat d = new DecimalFormat("###.##");
		return d.format((atual/maximo)*100);
	}
	
	public static Integer getEmptySlots(Player p) {
        PlayerInventory inventory = p.getInventory();
        ItemStack[] cont = inventory.getContents();
        int i = 0;
        for (ItemStack item : cont)
          if (item != null && item.getType() != Material.AIR) {
            i+=64;
          }
        return 2304 - i;
    }
	public static String getPlayerName(String name) {
		String n;
		if (Bukkit.getPlayerExact(name) != null) n = Bukkit.getPlayerExact(name).getName();
		else n = name;
		return n;
	}
}
