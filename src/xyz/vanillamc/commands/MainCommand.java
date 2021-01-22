package xyz.vanillamc.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import xyz.vanillamc.TimeIsPower;

public class MainCommand implements CommandExecutor {

	@SuppressWarnings("unused")
	private TimeIsPower plugin;
	
	public MainCommand(TimeIsPower plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage(ChatColor.YELLOW + "/timeispower " + ChatColor.RESET + "- " + ChatColor.AQUA
				+ TimeIsPower.messageConfig.getString("commands.timeispower"));
		sender.sendMessage(ChatColor.YELLOW + "/rptime add <player> <time> " + ChatColor.RESET + "- " + ChatColor.AQUA
				+ TimeIsPower.messageConfig.getString("commands.add-time"));
		for(int i = 0; i < 999; i++) {
			if(TimeIsPower.pluginConfig.getString("levels." + i) != null)
				sender.sendMessage(TimeIsPower.pluginConfig.getString("levels." + i));
		}
		return true;
	}

}
