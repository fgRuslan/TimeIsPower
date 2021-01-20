package xyz.vanillamc;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.earth2me.essentials.utils.EnumUtil;

public class TimeTask extends BukkitRunnable {

    private final JavaPlugin plugin;
    
    private static final Statistic PLAY_ONE_TICK = EnumUtil.getStatistic("PLAY_ONE_MINUTE", "PLAY_ONE_TICK");

    public TimeTask(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        // What you want to schedule goes here
        //plugin.getServer().broadcastMessage("Welcome to Bukkit! Remember to read the documentation!");
    	plugin.getLogger().info("Checking times");
    	for(Player p : Bukkit.getOnlinePlayers()) {
    		long now = System.currentTimeMillis() / 1000L;
    		
    		long seconds = now - (p.getStatistic(PLAY_ONE_TICK) * 50);
    		long minutes = seconds / 60;
    		//return minutes;
    	}
    }

}