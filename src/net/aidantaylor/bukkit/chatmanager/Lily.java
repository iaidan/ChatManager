package net.aidantaylor.bukkit.chatmanager;

import java.io.UnsupportedEncodingException;

import lilypad.client.connect.api.request.RequestException;
import lilypad.client.connect.api.request.impl.MessageRequest;

public class Lily {
	private String messageFormat, serverName, globalChannel = "GlobalChat";
    
    public void sendMessage(String format, String message) throws RequestException {
    	 try {
         	format = format.replace("%server", serverName);
             
         	MessageRequest request = new MessageRequest("", globalChannel, format + message);
             Main.getInstance().log("test1");
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
