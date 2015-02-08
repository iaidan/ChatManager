package net.aidantaylor.bukkit.core;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.ChatPaginator;
import org.bukkit.util.ChatPaginator.ChatPage;

public class Content {
	private PluginDescriptionFile info;

	public Content(JavaPlugin plugin) {
		setInfo(plugin.getDescription());
	}

	public Content(PluginDescriptionFile description) {
		setInfo(description);
	}

	public void versionInfo(CommandSender sender, JavaPlugin plugin) {
		versionInfo(sender, plugin.getDescription());
	}

	public void versionInfo(CommandSender sender, PluginDescriptionFile info) {
		sender.sendMessage(header("Plugin Version & Infomation"));
		sender.sendMessage(ChatColor.WHITE + "Name: " + ChatColor.GREEN + info.getName());
		sender.sendMessage(ChatColor.WHITE + "Version: " + ChatColor.GREEN + info.getVersion());
		sender.sendMessage(ChatColor.WHITE + "Website: " + ChatColor.GREEN + info.getWebsite());
		sender.sendMessage(ChatColor.WHITE + "Author: " + ChatColor.GREEN + info.getAuthors());
		sender.sendMessage(ChatColor.WHITE + "Description: " + ChatColor.GREEN + info.getDescription());
	}

	public void showPaginated(Player player, String command, String content, String title) {
		showPaginated(player, 1, command, content, title);
	}

	public void showPaginated(Player player, int pagenum, String command, String content, String title) {
		ChatPage page = ChatPaginator.paginate(content, pagenum);
		String[] lines = page.getLines();

		player.sendMessage(header(info.getName() + " " + title + " " + ChatColor.DARK_GREEN + "--" + ChatColor.GOLD + " page " + page.getPageNumber() + " of " + page.getTotalPages()));

		for (String s : lines) {
			player.sendMessage(s);
		}

		if (page.getPageNumber() != page.getTotalPages()) {
			player.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.GOLD + "/" + command + " " + page.getPageNumber() + ChatColor.DARK_GREEN + " to read the next page.");
		}
	}

	public void showPaginated(CommandSender sender, String command, String content, String title) {
		showPaginated(sender, 1, command, content, title);
	}

	public void showPaginated(CommandSender sender, int pagenum, String command, String content, String title) {
		ChatPage page = ChatPaginator.paginate(content, pagenum);
		String[] lines = page.getLines();

		sender.sendMessage(header(info.getName() + " " + title + " " + ChatColor.DARK_GREEN + "--" + ChatColor.GOLD + " page " + page.getPageNumber() + " of " + page.getTotalPages()));

		for (String s : lines) {
			sender.sendMessage(s);
		}

		if (page.getPageNumber() != page.getTotalPages()) {
			sender.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.GOLD + "/" + command + " " + page.getPageNumber() + ChatColor.DARK_GREEN + " to read the next page.");
		}
	}

	public String header(String string) {
		return ChatColor.DARK_GREEN + "---- " + ChatColor.GOLD + string + ChatColor.DARK_GREEN + " ----";
	}

	public PluginDescriptionFile getInfo() {
		return info;
	}

	public void setInfo(PluginDescriptionFile info) {
		this.info = info;
	}

	public String colourReplace(String colour) {
		String r = colour;

		r.replace("&0", ChatColor.BLACK.toString());
		r.replace("&1", ChatColor.BLACK.toString());

		return r;
	}
}
