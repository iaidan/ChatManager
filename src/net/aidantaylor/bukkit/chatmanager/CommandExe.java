package net.aidantaylor.bukkit.chatmanager;

import net.aidantaylor.bukkit.chatmanager.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandExe implements CommandExecutor {
	private JavaPlugin javaplugin;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("nameplates")) {
			if (args.length < 1) {
				sender.sendMessage(ChatColor.RED + "You do not have permission to access this command.");
			} else if (args[0].toLowerCase().equals("reload")) {
				if (sender instanceof Player && !sender.hasPermission("chatmanager.reload") && !sender.isOp()) {
					sender.sendMessage(ChatColor.RED + "You do not have permission to access this command.");
				} else {
					sender.sendMessage(ChatColor.DARK_GREEN + "[ChatManager] Reloading...");
					
					javaplugin.saveDefaultConfig();
					((Main) javaplugin).reload();
					
					sender.sendMessage(ChatColor.DARK_GREEN + "[Chatmanager] Done");
				}
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "That command in chatmanager was not found.");
			}
		}

		return false;
	}
}