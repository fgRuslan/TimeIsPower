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
	private static File playerDataFile;

	private static final String LANG_FILE_NAME = "messages.yml";
	private static final String CONFIG_FILE_NAME = "config.yml";
	private static final String PLAYERDATA_FILE_NAME = "playerdata.yml";

	public static YamlConfiguration messageConfig;
	public static YamlConfiguration pluginConfig;
	public static YamlConfiguration playerData;
	
	private static long CHECK_INTERVAL = 20L;
	
	@SuppressWarnings("unused")
	private BukkitTask checkTask;

	
	public void saveYamls() {
	    try {
	        messageConfig.save(messagesFile);
	        pluginConfig.save(configFile);
	        playerData.save(playerDataFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void loadYamls() {
	    try {
	        messageConfig.load(messagesFile);
	        pluginConfig.load(configFile);
	        playerData.load(playerDataFile);
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
		if(!playerDataFile.exists()){
			playerDataFile.getParentFile().mkdirs();
			Utils.copy(getResource(PLAYERDATA_FILE_NAME), playerDataFile);
	    }
		
	}

	@SuppressWarnings("static-access")
	public void onEnable() {
		getLogger().info("�aEnabled !");
		Bukkit.getPluginManager().registerEvents(new EventListener(), this);
		
		getCommand("timeispower").setExecutor(new MainCommand(this));
		getCommand("timeispower").setTabCompleter(new CustomTabCompleter());

		getCommand("addlevel").setExecutor(new CreateNewLevel(this));
		
		messagesFile = new File(getDataFolder(), LANG_FILE_NAME);
		configFile = new File(getDataFolder(), CONFIG_FILE_NAME);
		playerDataFile = new File(getDataFolder(), PLAYERDATA_FILE_NAME);
		
	    try {
	        firstRun();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	    messageConfig = new YamlConfiguration();
	    pluginConfig = new YamlConfiguration();
	    playerData = new YamlConfiguration();
	    
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
