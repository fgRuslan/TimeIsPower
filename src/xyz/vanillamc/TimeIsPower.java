package xyz.vanillamc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import xyz.vanillamc.commands.CreateNewLevel;
import xyz.vanillamc.commands.MainCommand;

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
	
	private void copy(InputStream in, File file) {
	    try {
	        OutputStream out = new FileOutputStream(file);
	        byte[] buf = new byte[1024];
	        int len;
	        while((len=in.read(buf))>0){
	            out.write(buf,0,len);
	        }
	        out.close();
	        in.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
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
	
	private void firstRun() {
		if(!messagesFile.exists()){
			messagesFile.getParentFile().mkdirs();
	        copy(getResource(LANG_FILE_NAME), messagesFile);
	    }
		
		if(!configFile.exists()){
			configFile.getParentFile().mkdirs();
	        copy(getResource(CONFIG_FILE_NAME), configFile);
	    }
		
	}

	@EventHandler
	public void handler(InventoryClickEvent e) {
		
	}
	
	public void onDisable() {
		saveYamls();
		getLogger().info("Disabled !");
	}

}
