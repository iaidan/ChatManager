package net.aidantaylor.bukkit.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config extends YamlConfiguration {
	private File configFile;
	private String filename;
	private JavaPlugin plugin;
	
	public Config(String file, JavaPlugin plugin) {
		this.plugin = plugin;
		
		filename = file;
	    
	    saveDefaultConfig();
	    reloadConfig();
	}
	
	public void reloadConfig() {
	    if (configFile == null) {
	    	configFile = new File(plugin.getDataFolder(), filename);
	    }
	    
	    try {
			load(configFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	    
	    try {
		    Reader defConfigStream = new InputStreamReader(plugin.getResource(filename), "UTF8");
		    
		    if (defConfigStream != null) {
		        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
		        
		        setDefaults(defConfig);
		    }
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	}

	public void saveDefaultConfig() {
	    if (configFile == null) {
	    	configFile = new File(plugin.getDataFolder(), filename);
	    }
	    
	    if (!configFile.exists()) {            
	         plugin.saveResource(filename, false);
	     }
	}
}
