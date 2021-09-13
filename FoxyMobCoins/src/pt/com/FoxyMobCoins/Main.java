package pt.com.FoxyMobCoins;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import pt.com.FoxyMobCoins.comands.MobCoinsCMD;
import pt.com.FoxyMobCoins.listeners.JoinEvent;
import pt.com.FoxyMobCoins.listeners.KillMob;
import pt.com.FoxyMobCoins.listeners.ShopListener;
import pt.com.FoxyMobCoins.listeners.TOPListener;
import pt.com.FoxyMobCoins.methods.SQL;
import pt.com.FoxyMobCoins.methods.TOP;
import pt.com.FoxyMobCoins.utils.zBUtils;

public class Main extends JavaPlugin {
	public Map<String, Double> coins = new HashMap<String, Double>();
	private static Main instance;
	public static SQL sql;
	@Override
	public void onEnable() {
		instance = this;
		sql = new SQL();
		saveDefaultConfig();
		loadListeners();
		loadCmds();
		sql.Iniciar();
		setUpHologram();
		TOP.StartAtualizador();
	}
	
	@Override
	public void onDisable() {
		sql.Encerrar();
	}
	
	private void loadListeners() {
		Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
		Bukkit.getPluginManager().registerEvents(new KillMob(), this);
		Bukkit.getPluginManager().registerEvents(new ShopListener(), this);
		Bukkit.getPluginManager().registerEvents(new TOPListener(), this);
	}
	
	private void loadCmds() {
		getCommand("mobcoins").setExecutor(new MobCoinsCMD());		
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public void setUpHologram() {
		if (!Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays")) {
        	zBUtils.ConsoleMsg("§6[FoxyMobCoins] §cHolographicDisplays não encontrado, desabilitando plugin.");
			getPluginLoader().disablePlugin(this);
			return;
		}
	}
}
