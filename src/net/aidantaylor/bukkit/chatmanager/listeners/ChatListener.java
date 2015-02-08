package net.aidantaylor.bukkit.chatmanager.listeners;

import java.util.LinkedList;
import java.util.List;

import net.aidantaylor.bukkit.core.Formatter;
import net.milkbowl.vault.chat.Chat;

import org.bukkit.Bukkit;
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
	private LilyListener lily = null;
	
	public ChatListener(Chat chat) {
		this.chat = chat;
	}
	
	public ChatListener() {
		
	}
	
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        
        /* Format chat message*/
        String chatMessage = event.getMessage();
        
        if (player.hasPermission("chatmanager.chat.colour")) {
        	chatMessage = Formatter.translateColourCodes(chatMessage);
        }
        
        if (player.hasPermission("chatmanager.chat.format")) {
        	chatMessage = Formatter.translateFormatCodes(chatMessage);
        }
        
        if (player.hasPermission("chatmanager.chat.magic")) {
        	chatMessage = Formatter.translateMagicCode(chatMessage);
        }
        
        event.setMessage(chatMessage);
        
        /* Set format */
        String format = messageFormat;
        
        String prefix = "";
        String suffix = "";
        
        if (chat != null) {
        	prefix = chat.getPlayerPrefix(player);
        	suffix = chat.getPlayerPrefix(player);
        }
        
        format = format.replace("%prefix", prefix)
 			   		   .replace("%suffix", suffix)
        			   .replace("%world", player.getWorld().getName())
        			   .replace("%player", player.getDisplayName())
        			   .replace("%displayname", "%1$s")
        			   .replace("%message", "%2$s");
        
        format = Formatter.translateCodes(Formatter.replaceTime(format));
        
        event.setFormat(format);
        
        /* Send to other servers */
		if (lily != null) {
			chatMessage = chatMessage.replace("%2$s", "");
			
			try {
				lily.sendMessage(format, chatMessage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			event.getRecipients().clear();
		} else if (ranged == true) {
	        if (chatMessage.startsWith("!") && (player.hasPermission("chatmanager.ranged.override") || player.isOp())) {
	        	chatMessage = chatMessage.substring(1);
	        }

			event.getRecipients().clear();
			event.getRecipients().addAll(getNear(player, chatMessage, chatRange));
		}
    }

    protected List<Player> getNear(Player sender, String message, double range) {
        List<Player> nearBy = new LinkedList<Player>();
        double squaredDistance = Math.pow(range, 2);
        
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.getWorld().equals(sender.getWorld())) {
                continue;
            }

            if (sender.getLocation().distanceSquared(p.getLocation()) > squaredDistance && !sender.hasPermission("chatmanager.ranged.override")) {
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

	public LilyListener getLily() {
		return lily;
	}

	public void setLily(LilyListener lily) {
		this.lily = lily;
	}
}
