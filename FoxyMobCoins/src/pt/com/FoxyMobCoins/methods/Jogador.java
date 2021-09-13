package pt.com.FoxyMobCoins.methods;

import org.bukkit.entity.Player;

import pt.com.FoxyMobCoins.Main;
import pt.com.FoxyMobCoins.utils.zBUtils;

public class Jogador {
	SQL sql = new SQL();
	private String PlayerName;
	private Double MobCoins;
	
	public Boolean Exists() {
		if (Main.getInstance().coins.containsKey(this.PlayerName)) return true;
		else return false;
	}
	
	public String getName() {
		return zBUtils.getPlayerName(this.PlayerName);
	}
	
	public Double getCoins() {
		return this.MobCoins;
	}
	
	public void setCoins(Double valor) {
		sql.coins.put(PlayerName, valor);
	}
	
	public void addCoins(Double valor) {
		setCoins(getCoins() + valor);
	}
	
	public void removeCoins(Double valor) {
		setCoins(getCoins() - valor);
	}
	
	public Jogador(Player Player) {
		this.PlayerName = Player.getName().toLowerCase();
		if (Exists()) this.MobCoins = sql.coins.get(Player.getName().toLowerCase());
		else this.MobCoins = 0.0;
	}
	
	public Jogador(String PlayerName) {
		this.PlayerName = PlayerName.toLowerCase();
		if (Exists()) this.MobCoins = sql.coins.get(PlayerName.toLowerCase());
		else this.MobCoins = 0.0;
	}
}
