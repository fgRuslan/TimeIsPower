package xyz.vanillamc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import xyz.vanillamc.util.Utils;

public class TimeTask extends BukkitRunnable {

    private final TimeIsPower plugin;
   
    private static final Statistic PLAY_ONE_TICK = Statistic.PLAY_ONE_MINUTE;

    public TimeTask(TimeIsPower plugin) {
        this.plugin = plugin;
    }
    
    private TimeImplementation getPlayerTime(Player p) {
		long seconds = (p.getStatistic(PLAY_ONE_TICK) * 50);
		long minutes = seconds / 60;
		long hours = minutes / 60;
		return new TimeImplementation(hours, minutes, seconds);
    }

    @Override
    public void run() {
    	@SuppressWarnings("static-access")
		int oneLevelStage = plugin.pluginConfig.getInt("LevelInterval");

    	for(Player p : Bukkit.getOnlinePlayers()) {
    		int minutes = (int) getPlayerTime(p).GetMinutes();
    		int levelCount = (int) Math.floor(minutes / oneLevelStage);
    		String command = Utils.GetLevelCommand(levelCount);
    		plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), command);
    		plugin.getLogger().info(ChatColor.GREEN + "Player " + p.getDisplayName() + " reached TimeIsPower level " + levelCount + "!");
    	}
    }

}