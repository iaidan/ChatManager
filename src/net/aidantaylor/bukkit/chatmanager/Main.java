package net.aidantaylor.bukkit.chatmanager;

import lilypad.client.connect.api.Connect;
import net.aidantaylor.bukkit.chatmanager.listeners.ChatListener;
import net.aidantaylor.bukkit.chatmanager.listeners.LilyListener;
import net.aidantaylor.bukkit.core.Config;
import net.milkbowl.vault.chat.Chat;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
	private boolean debug;
	private ChatListener chat = null;
	private Lily lily = null;
	private Config lilyConfig;
	private Connect connect;
	private LilyListener lilyListener;
	private Config playerConfig;
	private ConfigurationSection players;
	private FileConfiguration configFile;
	private static Main instance;

	@Override
	public void onEnable() {
		setInstance(this);
		
		getCommand("chatmanager").setExecutor(new CommandExe());

		if (getServer().getPluginManager().getPlugin("LilyPad-Connect") == null) {
			log(getName() + " LilyPad-Connect was not found.", true);
		} else {
	        connect = Bukkit.getServer().getServicesManager().getRegistration(Connect.class).getProvider();
	        
	        lily = new Lily();
	        lilyListener = new LilyListener(lily.getGlobalChannel());
	        
	        connect.registerEvents(lilyListener);
		}

		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			log(getName() + " Vault was not found.", true);
			
			chat = new ChatListener();
		} else {
	        RegisteredServiceProvider<Chat> rsp2 = getServer().getServicesManager().getRegistration(Chat.class);
			chat = new ChatListener(rsp2.getProvider());
		}
		
		getServer().getPluginManager().registerEvents(chat, this);
		
		load();

		log(getName() + " has been enabled!", true);
	}

	@Override
	public void onDisable() {
		unload();
		
		log(getName() + " has been disabled!", true);
	}

	public void load() {
		saveDefaultConfig();
		getConfig().options().copyDefaults(true);
		configFile = getConfig();
		
		if (configFile.getBoolean("enabled")) {
            log("ChatManager is not enabled in your config.yml");
            
            getPluginLoader().disablePlugin(this);
            return;
		}
		
		debug = configFile.getBoolean("debug");
		
		chat.setFormat(configFile.getString("messageFormat"));
		
		if (configFile.getBoolean("restricted")) {
			configFile.set("chatEnabled", true);
			saveDefaultConfig();
		}
		
		if (!configFile.getBoolean("chatEnabled")) {
			configFile.set("restricted", false);
			saveDefaultConfig();
		}
		
		chat.setRestricted(configFile.getBoolean("restricted"));
		chat.setChatEnabled(configFile.getBoolean("chatEnable"));
		
		/* Range mode config */
		ConfigurationSection ranged = configFile.getConfigurationSection("rangedMode");
		
		chat.setRanged(ranged.getBoolean("enabled"));
		
		if (chat.isRanged()) {
			chat.setChatRange(ranged.getDouble("chatRange"));
			chat.setOverrideFormat(ranged.getString("overrideFormat"));
		}
		
		/* LilyPad config */
		if (lily != null) {
			lilyConfig = new Config("lilypad.yml", this);
			lilyConfig.saveDefaultConfig();
			lilyConfig.options().copyDefaults(true);
			
			if (lilyConfig.getBoolean("enabled")) {
				lily.setFormat(lilyConfig.getString("messageFormat"));
				lily.setServerName(lilyConfig.getString("serverName"));
				
				chat.setLily(lily);
			} else {
				chat.setLily(null);
			}
		}
		
		/* Player config */
		playerConfig = new Config("players.yml", this);
		playerConfig.saveDefaultConfig();
		playerConfig.options().copyDefaults(true);
		
		players = playerConfig.getConfigurationSection("players");
		
		if (players == null) {
			players = playerConfig.createSection("players");
		}
	}
	
	public void unload() {
		if (lily != null) {
			connect.unregisterEvents(lilyListener);
		}
	}
	
	public void reload() {
		reloadConfig();
		
		if (lilyConfig != null) {
			lilyConfig.reloadConfig();
		}
		
		unload();
		load();
	}
	
	public void log(String string) {
		log(string, false);
	}
	
	public void log(String string, boolean bypassdebug) {
		if (bypassdebug == true || debug == true) {
			getLogger().info(string);
		}
	}

	public static Main getInstance() {
		return instance;
	}

	public void setInstance(Main instance) {
		Main.instance = instance;
	}

	public Connect getConnect() {
		return connect;
	}

	public void setConnect(Connect connect) {
		this.connect = connect;
	}

	public ConfigurationSection getPlayers() {
		return players;
	}

	public void save() {
		configFile.set("chatEnabled", chat.isChatEnabled());
		configFile.set("restricted", chat.isRestricted());
		
		saveConfig();
		playerConfig.save();
		lilyConfig.save();
	}

	public ChatListener getChat() {
		return chat;
	}
}
