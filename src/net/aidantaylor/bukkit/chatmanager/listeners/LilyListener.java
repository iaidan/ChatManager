package net.aidantaylor.bukkit.chatmanager.listeners;

import java.io.UnsupportedEncodingException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import lilypad.client.connect.api.Connect;
import lilypad.client.connect.api.event.EventListener;
import lilypad.client.connect.api.event.MessageEvent;
import lilypad.client.connect.api.request.RequestException;
import lilypad.client.connect.api.request.impl.MessageRequest;

public class LilyListener {
	private Connect connect;
	private String messageFormat, serverName;
	
	public LilyListener(Connect connect) {
		this.setConnect(connect);
	}

    @EventListener
    public void onMessage(MessageEvent event) {
        if (!event.getChannel().equalsIgnoreCase("global")) {
            return;
        }
        
        try {
            String message = event.getMessageAsString();
            
            Bukkit.getConsoleSender().sendMessage(message);
            
            for(Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage(message);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    public void sendMessage(String format, String message) throws RequestException {
        try {
        	format = format.replace("%server", serverName);
            
        	MessageRequest request = new MessageRequest("", "global", format + message);
            
            connect.request(request);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

	public Connect getConnect() {
		return connect;
	}

	public void setConnect(Connect connect) {
		this.connect = connect;
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
}
