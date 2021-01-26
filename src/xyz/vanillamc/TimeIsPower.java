package xyz.vanillamc;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import xyz.vanillamc.commands.CreateNewLevel;
import xyz.vanillamc.commands.MainCommand;
import xyz.vanillamc.util.Utils;

public class TimeIsPower extends JavaPlugin {
	
	private static File messagesFile;
	private static File configFile;

	private static final String LANG_FILE_NAME = "messages.yml";
	private static final String CONFIG_FILE_NAME = "config.yml";

	public static YamlConfiguration messageConfig;
	public static YamlConfiguration pluginConfig;
	
	private static long CHECK_INTERVAL = 20L;
	
	@SuppressWarnings("unused")
	private BukkitTask checkTask;

	
	public void saveYamls() {
	    try {
	        messageConfig.save(messagesFile);
	        pluginConfig.save(configFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void loadYamls() {
	    try {
	        messageConfig.load(messagesFile);
	        pluginConfig.load(configFile);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	private void firstRun() {
		if(!messagesFile.exists()){
			messagesFile.getParentFile().mkdirs();
	        Utils.copy(getResource(LANG_FILE_NAME), messagesFile);
	    }
		
		if(!configFile.exists()){
			configFile.getParentFile().mkdirs();
			Utils.copy(getResource(CONFIG_FILE_NAME), configFile);
	    }
		
	}

	@SuppressWarnings("static-access")
	public void onEnable() {
		getLogger().info("§aEnabled !");
		Bukkit.getPluginManager().registerEvents(new EventListener(), this);
		
		getCommand("timeispower").setExecutor(new MainCommand(this));
		getCommand("timeispower").setTabCompleter(new CustomTabCompleter());

		getCommand("addlevel").setExecutor(new CreateNewLevel(this));
		
		messagesFile = new File(getDataFolder(), LANG_FILE_NAME);
		configFile = new File(getDataFolder(), CONFIG_FILE_NAME);
		
	    try {
	        firstRun();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	    messageConfig = new YamlConfiguration();
	    pluginConfig = new YamlConfiguration();
	    
		this.saveDefaultConfig();
		loadYamls();
		
		CHECK_INTERVAL = this.pluginConfig.getLong("CheckInterval");
		
		checkTask = new TimeTask(this).runTaskTimer(this, 0, CHECK_INTERVAL);
	}
	
	public void onDisable() {
		saveYamls();
		getLogger().info("Disabled !");
	}

}
