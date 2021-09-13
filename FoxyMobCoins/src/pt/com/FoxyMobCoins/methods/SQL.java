package pt.com.FoxyMobCoins.methods;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.bukkit.scheduler.BukkitRunnable;

import pt.com.FoxyMobCoins.Main;
import pt.com.FoxyMobCoins.utils.zBUtils;

public class SQL {
	public Map<String, Double> coins = Main.getInstance().coins;
	public static Connection con = null;
	private static String url;
	private static String user;
	private static String password;
	public void Iniciar() {
		String adress = Main.getInstance().getConfig().getString("MySQL.address");
		String database = Main.getInstance().getConfig().getString("MySQL.database");
		url = "jdbc:mysql://" + adress + "/" + database;
		user = Main.getInstance().getConfig().getString("MySQL.username");
		password = Main.getInstance().getConfig().getString("MySQL.password");
		if (openMySQL()) {
			zBUtils.ConsoleMsg("§6[FoxyMobCoins] §aLigação com MySQL criada com sucesso.");
			loadCoins();
		} else {
			if (openSQLite()) loadCoins();
			else return;
		}
		AutoSaveTimer();
	}
	
	public void Encerrar() {
		close();
		saveCoins();
	}
	
	private Boolean openMySQL() {
		if (Main.getInstance().getConfig().getBoolean("MySQL.ativo")) {
			try {
				con = DriverManager.getConnection(url, user, password);
				createtable();
				return true;
			} catch (SQLException e) {
			}
		}
		return false;
	}
	
	private Boolean openSQLite() {
		File file = new File(Main.getInstance().getDataFolder(), "database.db");
		String url = "jdbc:sqlite:" + file;
		try {
			con = DriverManager.getConnection(url);
			createtable();
			return true;
		} catch (SQLException e) {
			Main.getInstance().getPluginLoader().disablePlugin(Main.getInstance());
			zBUtils.ConsoleMsg("§6FoxyMobCoins §7- §cNão foi possível conectar com o banco de dados.");
		}
		return false;
	}
	
	private void close() {
		if (con != null) {
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
			}
		}
	}
	
	private void createtable() {
		PreparedStatement stm = null;
		try {
			stm = con.prepareStatement("CREATE TABLE IF NOT EXISTS `mobcoins` (`jogador` TEXT, `coins` DOUBLE)");
			stm.execute();
			stm.close();
		} catch (SQLException e) {
			Main.getInstance().getPluginLoader().disablePlugin(Main.getInstance());
			zBUtils.ConsoleMsg("§6FoxyMobCoins §7- §cOcorreu um erro ao criar tabela no banco de dados.");
		}
	}
	
	private void loadCoins() {
		PreparedStatement stm = null;
		try {
			stm = con.prepareStatement("SELECT * FROM `mobcoins`");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				String nick = rs.getString("jogador");
				double coins = rs.getDouble("coins");
				this.coins.put(nick, coins);
			}
		} catch (SQLException e) {
		}
		close();
	}
	
	private void saveCoins() {
		if (!openMySQL()) {
			openSQLite();
		}
		for(String nick : this.coins.keySet()) {
			if (!contains(nick)) {
				criarPlayer(nick);
			} else {
				PreparedStatement stm = null;
				try {
					stm = SQL.con.prepareStatement("UPDATE `mobcoins` SET `coins` = ? WHERE `jogador` = ?");
					stm.setDouble(1, coins.get(nick));
					stm.setString(2, nick.toLowerCase());
					stm.executeUpdate();
				} catch (SQLException e) {
				}
			}
		}
		close();
	}
	
	private Boolean contains(String PlayerName) {
		PreparedStatement stm = null;
		Boolean b = false;
		try {
			stm = con.prepareStatement("SELECT * FROM `mobcoins` WHERE `jogador` = ?");
			stm.setString(1, PlayerName.toLowerCase());
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				b = true;
			}
		} catch (SQLException e) {
		}
		return b;
	}
	
	private void criarPlayer(String PlayerName) {
		PreparedStatement stm = null;
		try {
			stm = con.prepareStatement("INSERT INTO `mobcoins` (`jogador`, `coins`) VALUES (?,?)");
			stm.setString(1, PlayerName.toLowerCase());
			if (!coins.containsKey(PlayerName)) {
				stm.setDouble(2, 0.0);
			} else {
				stm.setDouble(2, coins.get(PlayerName.toLowerCase()));
			}
			stm.executeUpdate();
		} catch (SQLException e) {
		}
	}
	
	private void AutoSaveTimer() {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				AutoSave();
			}
		}.runTaskTimer(Main.getInstance(), 1200*30L, 1200*30L);
	}
	
	private void AutoSave() {
		saveCoins();
		zBUtils.ConsoleMsg("§6[FoxyMobCoins] §aAuto-Save completo.");
	}
}
