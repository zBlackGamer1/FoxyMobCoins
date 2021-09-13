package pt.com.FoxyMobCoins.comands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pt.com.FoxyMobCoins.methods.Jogador;
import pt.com.FoxyMobCoins.methods.MobCoinsShop;
import pt.com.FoxyMobCoins.methods.TOP;
import pt.com.FoxyMobCoins.utils.zBUtils;

public class MobCoinsCMD implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String arg2, String[] a) {
		if (s instanceof Player) {
			Player p = (Player) s;
			if (a.length == 0) {
				MobCoinsShop.Open(p);
				return true;
			}
			switch (a[0]) {
			case "ver":
			case "see":
				if (a.length == 2) {
					Jogador j = new Jogador(a[1]);
					if (j.Exists()) {
						p.sendMessage("§b§lMOBCOINS! §fSaldo de §b" + j.getName() + "§f: §e" + zBUtils.Formatar(j.getCoins()));
					} else {
						p.sendMessage("§c§lERRO! §cJogador não encontrado.");
					}
				} else p.sendMessage("§c§lERRO! §cUse, §c§n/mobcoins ver (Jogador)§c.");
				break;

			case "enviar":
			case "send":
				if (a.length == 3) {
					if (a[1].equalsIgnoreCase(p.getName())) {
						p.sendMessage("§c§lERRO! §cEsse jogador é você!");
						return true;
					}
					Double valor = 0.0;
					try {
						valor = Double.parseDouble(a[2]);
					} catch (Exception e) {
						p.sendMessage("§c§lERRO! §cValor inválido.");
						return true;
					}
					Jogador sender = new Jogador(p.getName());
					if (sender.getCoins() < valor) {
						p.sendMessage("§c§lERRO! §cVocê não tem coins suficientes.");
						return true;
					}
					Jogador target = new Jogador(a[1]);
					if (!target.Exists()) {
						p.sendMessage("§c§lERRO! §cJogador não encontrado.");
						return true;
					}
					sender.removeCoins(valor);
					target.addCoins(valor);
					Player targetp = Bukkit.getPlayer(a[1]);
					if (targetp != null) {
						targetp.sendMessage("§b§lMOBCOINS! §a" + p.getName() + "§f enviou §a" + zBUtils.Formatar(valor) + "§f de mobcoins a você.");
					}
					p.sendMessage("§b§lMOBCOINS! §fVocê enviou §a" + zBUtils.Formatar(valor) + "§f de mobcoins a §a" + zBUtils.getPlayerName(target.getName()) + "§f.");
					
				} else p.sendMessage("§c§lERRO! §cUse, §c§n/mobcoins enviar (Jogador) (Valor)§c.");
				break;

			case "top":
				if (a.length == 1) TOP.Open(p);
				else p.sendMessage("§c§lERRO! §cUse, §c§n/mobcoins top§c.");
				break;

			case "setar":
			case "set":
				if (!p.hasPermission("mobcoins.admin")) {
					p.sendMessage("§cSem permissão.");
					break;
				}
				if (a.length == 3) {
					Jogador j = new Jogador(a[1]);
					if (j.Exists()) {
						Double valor = j.getCoins();
						try {
							valor = Double.parseDouble(a[2]);
						} catch (Exception e) {
							p.sendMessage("§c§lERRO! §cQuantia inválida.");
							return true;
						}
						j.setCoins(valor);
						p.sendMessage("§b§lMOBCOINS! §fVocê setou o saldo de §n§a" + zBUtils.getPlayerName(a[1]) + "§f para §n§a" + zBUtils.Formatar(valor) + "§f.");
					} else {
						p.sendMessage("§c§lERRO! §cJogador não encontrado.");
					}
				} else {
					p.sendMessage("§c§lERRO! §cUse, §c§n/mobcoins set (Jogador) (Valor)§c.");
				}
				break;
				
			case "adicionar":
			case "add":
				if (!p.hasPermission("mobcoins.admin")) {
					p.sendMessage("§cSem permissão.");
					break;
				}
				if (a.length == 3) {
					Jogador j = new Jogador(a[1]);
					if (j.Exists()) {
						Double valor = j.getCoins();
						try {
							valor = Double.parseDouble(a[2]);
						} catch (Exception e) {
							p.sendMessage("§c§lERRO! §cQuantia inválida.");
							return true;
						}
						j.addCoins(valor);
						p.sendMessage("§b§lMOBCOINS! §fVocê adicionou §n§a" + zBUtils.Formatar(valor) + "§f de mobcoins a §n§a" + zBUtils.getPlayerName(a[1]) + "§f.");
					} else {
						p.sendMessage("§c§lERRO! §cJogador não encontrado.");
					}
				} else {
					p.sendMessage("§c§lERRO! §cUse, §c§n/mobcoins add (Jogador) (Valor)§c.");
				}
				break;
			case "remover":
			case "remove":
				if (!p.hasPermission("mobcoins.admin")) {
					p.sendMessage("§cSem permissão.");
					break;
				}
				if (a.length == 3) {
					Jogador j = new Jogador(a[1]);
					if (j.Exists()) {
						Double valor = j.getCoins();
						try {
							valor = Double.parseDouble(a[2]);
						} catch (Exception e) {
							p.sendMessage("§c§lERRO! §cQuantia inválida.");
							return true;
						}
						if (j.getCoins() < valor) {
							p.sendMessage("§c§lERRO! §cEsse jogador não tem §c§n" + zBUtils.Formatar(valor) + "§c de mobcoins.");
							return true;
						}
						j.removeCoins(valor);
						p.sendMessage("§b§lMOBCOINS! §fVocê removeu §n§a" + zBUtils.Formatar(valor) + "§f de mobcoins a §n§a" + zBUtils.getPlayerName(a[1]) + "§f.");
					} else {
						p.sendMessage("§c§lERRO! §cJogador não encontrado.");
					}
				} else {
					p.sendMessage("§c§lERRO! §cUse, §c§n/mobcoins remove (Jogador) (Valor)§c.");
				}
				break;
			case "resetar":
			case "reset":
				if (!p.hasPermission("mobcoins.admin")) {
					p.sendMessage("§cSem permissão.");
					break;
				}
				if (a.length == 2) {
					Jogador j = new Jogador(a[1]);
					if (j.Exists()) {
						j.setCoins(0.0);
						p.sendMessage("§b§lMOBCOINS! §fVocê resetou o saldo de §a" + j.getName() + "§f.");
					} else {
						p.sendMessage("§c§lERRO! §cJogador não encontrado.");
					}
				}
				break;
			case "ajuda":
			case "help":
				if (!p.hasPermission("mobcoins.admin")) {
					p.sendMessage(new String[] {
							"§b§l        MOBCOINS §f➡§7 Todos os Comandos",
							"§b ▶§7 /mobcoins",
							"§b ▶§7 /mobcoins §ftop",
							"§b ▶§7 /mobcoins §fver (Jogador)",
							"§b ▶§7 /mobcoins §fenviar (Jogador) (Valor)",
							"§b ▶§7 /mobcoins §fajuda",
							""
					});
				} else {
					p.sendMessage(new String[] {
							"§b§l       MOBCOINS §f➡§7 Todos os Comandos",
							"§b ▶§7 /mobcoins",
							"§b ▶§7 /mobcoins §ftop",
							"§b ▶§7 /mobcoins §fver (Jogador)",
							"§b ▶§7 /mobcoins §freset (Jogador)",
							"§b ▶§7 /mobcoins §fenviar (Jogador) (Valor)",
							"§b ▶§7 /mobcoins §fset (Jogador) (Valor)",
							"§b ▶§7 /mobcoins §fadd (Jogador) (Valor)",
							"§b ▶§7 /mobcoins §fremove (Jogador) (Valor)",
							"§b ▶§7 /mobcoins §fajuda",
							""
					});
				}
				break;
			default:
				p.sendMessage("§c§lERRO! §cArgumentos inválidos, use: §c§n/mobcoins ajuda§c.");
				break;
			}

		} else {
			// CODIGO PARA CONSOLE
		}
		return true;
	}

}
