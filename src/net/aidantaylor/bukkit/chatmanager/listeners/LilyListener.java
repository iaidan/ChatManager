package net.aidantaylor.bukkit.chatmanager.listeners;

import java.io.UnsupportedEncodingException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import lilypad.client.connect.api.event.EventListener;
import lilypad.client.connect.api.event.MessageEvent;

public class LilyListener {
	private String globalChannel;

	public LilyListener(String globalChannel) {
		this.globalChannel = globalChannel;
	}
	
    @EventListener
    public void onMessage(MessageEvent event) {
        if (!event.getChannel().equalsIgnoreCase(globalChannel)) {
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
}
