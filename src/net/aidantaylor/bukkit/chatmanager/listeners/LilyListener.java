package net.aidantaylor.bukkit.chatmanager.listeners;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import lilypad.client.connect.api.event.EventListener;
import lilypad.client.connect.api.event.MessageEvent;
import net.aidantaylor.bukkit.chatmanager.Main;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

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

        if (!Main.getInstance().getChat().isChatEnabled()) {
        	return;
        }
        
        try {
			String messageContents = event.getMessageAsString();
	        String[] messageParts = messageContents.split(" ");
	        
	        String playerName = messageParts[0];
            String chatMessage = URLDecoder.decode(messageParts[1], "UTF-8");
        	
            Bukkit.getConsoleSender().sendMessage(chatMessage);
			ConfigurationSection players = Main.getInstance().getPlayers();
			
			for(Player p : Bukkit.getOnlinePlayers()){
				try {
		        	ConfigurationSection cp = players.getConfigurationSection(p.getUniqueId().toString());
		        	
		            if (!playerName.equalsIgnoreCase(p.getName()) && chatMessage.toLowerCase().contains(p.getName().toLowerCase()) && cp.getBoolean("chatDing")) {
		                p.playSound(p.getLocation(), Sound.ORB_PICKUP, 100, 7);
		                chatMessage.replaceAll("(?i)" + p.getName(), ChatColor.ITALIC + p.getName() + ChatColor.RESET);
		            }
		            
		        	if (!cp.getBoolean("chatEnabled")) {
		        		continue;
		        	}
	        	} catch(Exception e) { }
				
				p.sendMessage(chatMessage);
	        }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
