package xyz.vanillamc;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import xyz.vanillamc.commands.MainCommand;

public class TimeIsPower extends JavaPlugin {
	
	public void onEnable() {
		getLogger().info("§aEnabled !");
		Bukkit.getPluginManager().registerEvents(new EventListener(), this);
		getCommand("timeispower").setExecutor(new MainCommand(this));
		
		this.saveDefaultConfig();
		
		@SuppressWarnings("unused")
		BukkitTask task = new TimeTask(this).runTaskTimer(this, 10, 20);
	}
	
	@EventHandler
	public void handler(InventoryClickEvent e) {
		
	}
	
	public void onDisable() {
		getLogger().info("Disabled !");
	}

}
