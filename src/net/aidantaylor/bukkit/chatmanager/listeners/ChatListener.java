package net.aidantaylor.bukkit.chatmanager.listeners;

import java.util.LinkedList;
import java.util.List;

import net.aidantaylor.bukkit.chatmanager.Lily;
import net.aidantaylor.bukkit.chatmanager.Main;
import net.aidantaylor.bukkit.core.Formatter;
import net.milkbowl.vault.chat.Chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
	private String messageFormat, overrideFormat;
	private Chat chat = null;
	private double chatRange = 100;
	private boolean ranged = false;
	private boolean chatEnabled = true;
	private boolean restricted = false;
	private Lily lily = null;
	
	public ChatListener(Chat chat) {
		this.chat = chat;
	}
	
	public ChatListener() {
		
	}
	
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
		ConfigurationSection players = Main.getInstance().getPlayers();
        
		ConfigurationSection cp = players.getConfigurationSection(player.getUniqueId().toString());
		
		if (cp != null && cp.getBoolean("chatEnabled") == false) {
			player.sendMessage(ChatColor.RED + "Your chat is currently disabled, do  " + ChatColor.DARK_RED + "/cm show" + ChatColor.RED + " to enable");
	        
	        event.setCancelled(true);
	        return;
		}
		
        if (!chatEnabled) {
        	player.sendMessage(ChatColor.RED + "Chat is currently disabled.");
        	
        	event.setCancelled(true);
        	return;
        }
        
        if (!(player.hasPermission("chatmanager.chat") || player.isOp())) {
        	player.sendMessage(ChatColor.RED + "You may not talk in chat!");
        	
        	event.setCancelled(true);
        	return;
        }
        
        if (!(player.hasPermission("chatmanager.chat.override") || player.isOp()) && restricted) {
        	player.sendMessage(ChatColor.RED + "You may not talk in chat, it is currently restricted to those with permission!");
        	
        	event.setCancelled(true);
        	return;
        }
        
        /* Format chat message*/
        String chatMessage = event.getMessage();
        
        if (player.hasPermission("chatmanager.chat.colour") || player.isOp()) {
        	chatMessage = Formatter.translateColourCodes(chatMessage);
        } else {
        	chatMessage = Formatter.translateColourCodes(chatMessage, true);
        }
        
        if (player.hasPermission("chatmanager.chat.format") || player.isOp()) {
        	chatMessage = Formatter.translateFormatCodes(chatMessage);
        } else {
        	chatMessage = Formatter.translateFormatCodes(chatMessage, true);
        }
        
        if (player.hasPermission("chatmanager.chat.magic") || player.isOp()) {
        	chatMessage = Formatter.translateMagicCode(chatMessage);
        } else {
        	chatMessage = Formatter.translateMagicCode(chatMessage, true);
        }
        
        event.setMessage(chatMessage);
        
        /* Send to other servers */
		if (lily != null) {
			try {
				lily.sendMessage(chatMessage, player);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			event.getRecipients().clear();
		} else {
	        /* Set format */
	        String format = messageFormat;
	        
	        String prefix = "";
	        String suffix = "";
	        
	        if (chat != null) {
	        	prefix = chat.getPlayerPrefix(player);
	        	suffix = chat.getPlayerPrefix(player);
	        }
	        
	        format = format.replace("%prefix", prefix)
	        			   .replace("%world", player.getWorld().getName())
	        			   .replace("%player", player.getDisplayName())
	 			   		   .replace("%suffix", suffix);
	        
	        format = Formatter.translateCodes(Formatter.replaceTime(format));
			   
	        format = format.replace("%displayname", "%1$s").replace("%message", "%2$s");
	        
	        event.setFormat(format);
			
			if (ranged == true) {
		        if (chatMessage.startsWith("!") && (player.hasPermission("chatmanager.ranged.override") || player.isOp())) {
		        	chatMessage = chatMessage.substring(1);
		        }
	
				event.getRecipients().clear();
				event.getRecipients().addAll(getNear(player, chatMessage, chatRange));
			}
			
	        for (Player p : event.getRecipients()) {
	        	try {
		        	cp = players.getConfigurationSection(p.getUniqueId().toString());
		        	
		            if (player.getName() != p.getName() && chatMessage.toLowerCase().contains(p.getName().toLowerCase()) && cp.getBoolean("chatDing")) {
		                p.playSound(p.getLocation(), Sound.ORB_PICKUP, 100, 7);
		                chatMessage.replaceAll("(?i)" + p.getName(), ChatColor.ITALIC + p.getName() + ChatColor.RESET);
		            }
		            
		        	if (!cp.getBoolean("chatEnabled")) {
		        		event.getRecipients().remove(p);
		        	}
	        	} catch(Exception e) {}
	        }
		}
    }

    protected List<Player> getNear(Player sender, String message, double range) {
        List<Player> nearBy = new LinkedList<Player>();
        double squaredDistance = Math.pow(range, 2);
        
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.getWorld().equals(sender.getWorld())) {
                continue;
            }

            if (sender.getLocation().distanceSquared(p.getLocation()) > squaredDistance && !(sender.hasPermission("chatmanager.ranged.override") || sender.isOp())) {
                continue;
            }
            
            nearBy.add(p);
        }
        
        return nearBy;
    }

	public String getFormat() {
		return messageFormat;
	}

	public void setFormat(String messageFormat) {
		this.messageFormat = messageFormat;
	}

	public double getChatRange() {
		return chatRange;
	}

	public void setChatRange(double range) {
		this.chatRange = range;
	}

	public String getOverrideFormat() {
		return overrideFormat;
	}

	public void setOverrideFormat(String overrideFormat) {
		this.overrideFormat = overrideFormat;
	}

	public boolean isRanged() {
		return ranged;
	}

	public void setRanged(boolean ranged) {
		this.ranged = ranged;
	}

	public Lily getLily() {
		return lily;
	}

	public void setLily(Lily lily) {
		this.lily = lily;
	}

	public boolean isChatEnabled() {
		return chatEnabled;
	}

	public void setChatEnabled(boolean chatEnabled) {
		this.chatEnabled = chatEnabled;
	}

	public boolean isRestricted() {
		return restricted;
	}

	public void setRestricted(boolean restricted) {
		this.restricted = restricted;
	}
}
