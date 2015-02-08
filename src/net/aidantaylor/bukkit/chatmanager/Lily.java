package net.aidantaylor.bukkit.chatmanager;

import java.io.UnsupportedEncodingException;

import org.bukkit.entity.Player;

import net.aidantaylor.bukkit.core.Formatter;
import net.milkbowl.vault.chat.Chat;
import lilypad.client.connect.api.request.RequestException;
import lilypad.client.connect.api.request.impl.MessageRequest;

public class Lily {
	private String messageFormat, serverName, globalChannel = "GlobalChat";
	private Chat chat = null;
    
	public Lily(Chat chat) {
		this.chat  = chat;
	}
    
	public Lily() {
		
	}
	
    public void sendMessage(String message, Player player) throws RequestException {
    	 try {
    		 /* set format */
            String prefix = "";
            String suffix = "";
            
            if (chat != null) {
            	prefix = chat.getPlayerPrefix(player);
            	suffix = chat.getPlayerPrefix(player);
            }

    		String format = messageFormat;
    		
         	format = format.replace("%server", serverName)
         				   .replace("%prefix", prefix)
            			   .replace("%world", player.getWorld().getName())
            			   .replace("%player", player.getDisplayName())
            			   .replace("%displayname", player.getDisplayName())
     			   		   .replace("%suffix", suffix);
            
            format = Formatter.translateCodes(Formatter.replaceTime(format));
    		   
            message = format.replace("%message", message);
            
         	MessageRequest request = new MessageRequest("", globalChannel, message);
         	
            Main.getInstance().getConnect().request(request);
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
    }

	public String getFormat() {
		return messageFormat;
	}

	public void setFormat(String messageFormat) {
		this.messageFormat = messageFormat;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getGlobalChannel() {
		return globalChannel;
	}

	public void setGlobalChannel(String globalChannel) {
		this.globalChannel = globalChannel;
	}
}
