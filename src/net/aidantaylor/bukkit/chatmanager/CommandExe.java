package net.aidantaylor.bukkit.chatmanager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandExe implements CommandExecutor {
	private Main main;
	
	public CommandExe() {
		main = Main.getInstance();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("chatmanager")) {
			if (args.length < 1) {
				sender.sendMessage(ChatColor.RED + "You do not have permission to access this command.");
			} else if (args[0].toLowerCase().equals("reload")) {
				if (sender instanceof Player && !sender.hasPermission("chatmanager.reload") && !sender.isOp()) {
					sender.sendMessage(ChatColor.RED + "You do not have permission to access this command.");
				} else {
					sender.sendMessage(ChatColor.DARK_GREEN + "[ChatManager] Reloading...");
					
					main.saveDefaultConfig();
					main.reload();
					
					sender.sendMessage(ChatColor.DARK_GREEN + "[Chatmanager] Done");
				}
			} else if (args[0].toLowerCase().equals("ding")) {
				if (sender instanceof Player && !sender.hasPermission("chatmanager.chat.ding") && !sender.isOp()) {
					sender.sendMessage(ChatColor.RED + "You do not have permission to access this command.");
				} else if (!(sender instanceof Player)) {
					sender.sendMessage(ChatColor.RED + "Command must be done as a player.");
				} else {
					Player player = (Player) sender;
					String uuid = player.getUniqueId().toString();
					ConfigurationSection players = Main.getInstance().getPlayers();
					
					ConfigurationSection cp = players.getConfigurationSection(uuid);
					
					if (cp == null) {
						cp = players.createSection(uuid);
					}
					
					if (cp.getBoolean("chatDing")) {
						sender.sendMessage(ChatColor.RED + "[ChatManager] Your chat will now not make sound when you're mentioned! Do the command again to enable");
						cp.set("chatDing", false);
					} else {
						sender.sendMessage(ChatColor.DARK_GREEN + "[ChatManager] Your chat will now make a sound when you're mentioned! Do the command again to disable");
						cp.set("chatDing", true);
					}
					
					Main.getInstance().save();
				}
			} else if (args[0].toLowerCase().equals("hide") || args[0].toLowerCase().equals("show")) {
				if (sender instanceof Player && !sender.hasPermission("chatmanager.chat.hideshow") && !sender.isOp()) {
					sender.sendMessage(ChatColor.RED + "You do not have permission to access this command.");
				} else if (!(sender instanceof Player)) {
					sender.sendMessage(ChatColor.RED + "Command must be done as a player.");
				} else {
					Player player = (Player) sender;
					String uuid = player.getUniqueId().toString();
					ConfigurationSection players = Main.getInstance().getPlayers();
					
					ConfigurationSection cp = players.getConfigurationSection(uuid);
					
					if (cp == null) {
						cp = players.createSection(uuid);
					}
					
					if (args[0].toLowerCase().equals("show")) {
						sender.sendMessage(ChatColor.DARK_GREEN + "[ChatManager] Chat is now visible! do " + ChatColor.GREEN + "/cm hide" + ChatColor.DARK_GREEN + " to disable");
						cp.set("chatEnabled", true);
					} else {
						sender.sendMessage(ChatColor.RED + "[ChatManager] Chat is now hidden! do " + ChatColor.GREEN + "/cm show" + ChatColor.DARK_GREEN + " to enable");
						cp.set("chatEnabled", false);
					}
					
					Main.getInstance().save();
				}
			} else if (args[0].toLowerCase().equals("mode")) {
				if (sender instanceof Player && !sender.hasPermission("chatmanager.mode") && !sender.isOp()) {
					sender.sendMessage(ChatColor.RED + "You do not have permission to access this command.");
				} else if (args.length < 2) {
					sender.sendMessage(ChatColor.RED + "Correct usage: /cm mode [disabled|enabled|restricted]");
				} else if (args[1].toLowerCase().equals("disabled") || args[1].toLowerCase().equals("enabled")) {
					if (sender instanceof Player && !sender.hasPermission("chatmanager.mode.on") && !sender.isOp()) {
						sender.sendMessage(ChatColor.RED + "You do not have permission to change to this mode.");
					} else {
						Main main = Main.getInstance();
						FileConfiguration config = main.getConfig();
						
						if (args[1].toLowerCase().equals("enabled")) {
							Main.getInstance().getServer().broadcastMessage(ChatColor.DARK_GREEN + "[ChatManager] Chat is now enabled!");
							//sender.sendMessage(ChatColor.DARK_GREEN + "[ChatManager] Chat is now enabled!");
							config.set("chatEnabled", true);
							main.getChat().setChatEnabled(true);
						} else {
							Main.getInstance().getServer().broadcastMessage(ChatColor.RED + "[ChatManager] Chat is now disabled!");
							//sender.sendMessage(ChatColor.RED + "[ChatManager] Chat is now disabled!");
							config.set("chatEnabled", false);
							main.getChat().setChatEnabled(false);
						}
						
						config.set("restricted", false);
						main.getChat().setRestricted(false);
						
						main.save();
					}
				} else if (args[1].toLowerCase().equals("restricted")) {
					if (sender instanceof Player && !sender.hasPermission("chatmanager.mode.restricted") && !sender.isOp()) {
						sender.sendMessage(ChatColor.RED + "You do not have permission to change to this mode.");
					} else {
						Main main = Main.getInstance();
						FileConfiguration config = main.getConfig();

						Main.getInstance().getServer().broadcastMessage(ChatColor.DARK_GREEN + "[ChatManager] Chat is now restricted to those with the permission!");
						//sender.sendMessage(ChatColor.DARK_GREEN + "[ChatManager] Chat is now restricted to those with the permission node!");
						
						config.set("restricted", true);
						main.getChat().setRestricted(true);
						
						config.set("chatEnabled", true);
						main.getChat().setChatEnabled(true);
						
						main.save();
					}
				} else {
					sender.sendMessage(ChatColor.RED + "Invalid mode");
				}
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "That command in chatmanager was not found.");
			}
		}

		return false;
	}
}