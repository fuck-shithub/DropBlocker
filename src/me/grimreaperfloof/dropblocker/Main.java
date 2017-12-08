package me.grimreaperfloof.dropblocker;

import java.io.File;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.grimreaperfloof.dropblocker.commands.Filter;

public class Main extends JavaPlugin implements Listener{
	
	private void createConfig() {
	    try {
	        if (!getDataFolder().exists()) {
	            getDataFolder().mkdirs();
	        }
	        File file = new File(getDataFolder(), "config.yml");
	        if (!file.exists()) {
	            getLogger().info("Config.yml not found, creating!");
	            saveDefaultConfig();
	        } else {
	            getLogger().info("Config.yml found, loading!");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
    public void onEnable() {
		createConfig();
        
		getServer().getPluginManager().registerEvents(new Events(this), this);
		getCommand("dbfilter").setExecutor(new Filter(this));
    }
}
